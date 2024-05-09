package com.inkbird.inkbirdapp.device.ibsp02rw.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.github.mikephil.chart.inkbird.utils.ChartUtils;
import com.inkbird.base.utils.DateUtils;
import com.inkbird.inkbirdapp.device.ibsp02rw.bean.HistoryProbeBean;
import com.inkbird.inkbirdapp.device.ibsth.bean.HistoryBean;
import com.inkbird.inkbirdapp.device.ibsth.bean.HistoryValueBean;
import com.inkbird.inkbirdapp.device.ibsth.utils.VPDUtils;
import com.inkbird.inkbirdapp.device.ibsth3.utils.TempUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CrvUti {
    public static boolean writeCsvFile(@NonNull List<HistoryProbeBean> historyBeans, @NonNull String sharePath, @NonNull String unit) {
        String u = "";
        if ("c".equals(unit)) {
            u = "C";
        } else if ("f".equals(unit)) {
            u = "F";
        }
        String title = "Date" + "\t";
        for (HistoryProbeBean.ProbeBean probeBean : historyBeans.get(0).getList()) {
            if (probeBean.isTemp()) {
                if (probeBean.isOut()) {
                    title = title + "Out Temperature" + " Unit" + "\t";
                } else {
                    title = title + "In Temperature" + " Unit" + "\t";
                }
            } else {
                if (probeBean.isOut()) {
                    title = title + "Out Humidity" + " Unit" + "\t";
                } else {
                    title = title + "In Humidity" + " Unit" + "\t";
                }
            }
        }
        title = title + "\n";
        StringBuilder stringBuilder = new StringBuilder(title);
        for (int j = 0; j < historyBeans.size(); j++) {
            HistoryProbeBean historyBean = historyBeans.get(j);
            stringBuilder.append(DateUtils.timeToString8(historyBean.getTime())).append("\t");
            for (HistoryProbeBean.ProbeBean probeBean : historyBean.getList()) {
                try {
//                    String v = String.valueOf(probeBean.getDp_value());
                    int value = probeBean.getDp_value();//Integer.parseInt(v.substring(v.length() - 3, v.length()));
                    if (probeBean.isTemp()) {
                        String str = String.valueOf(unit.equals("c") ? TempUtils.formatFloat(value / 10.0f) : TempUtils.CToF(value / 10.0f));
                        if (probeBean.isOut()) {
                            stringBuilder.append(str).append(u).append("\t");
                        } else {
                            stringBuilder.append(str).append(u).append("\t");
                        }
                    } else {
                        if (probeBean.isOut()) {
                            stringBuilder.append(value / 10.0).append("%").append("\t");
                        } else {
                            stringBuilder.append(value / 10.0).append("%").append("\t");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            stringBuilder.append("\n");
        }
        return saveFile(sharePath, stringBuilder);
    }

    public static boolean writeCsvFile(@NonNull List<HistoryProbeBean> historyBeans, @NonNull String sharePath, @NonNull int unit) {
        String u = "";
        if (unit == 0) {
            u = "C";
        } else if (unit == 1) {
            u = "F";
        }
        String title = "Date" + "\t";
        for (HistoryProbeBean.ProbeBean probeBean : historyBeans.get(0).getList()) {
            if (probeBean.isTemp()) {
                title = title + "Temperature" + "\t";
            } else {
                title = title + "Humidity" + "\t";
            }
        }
        title = title + "\n";
        StringBuilder stringBuilder = new StringBuilder(title);
        for (int j = 0; j < historyBeans.size(); j++) {
            HistoryProbeBean historyBean = historyBeans.get(j);
            stringBuilder.append(DateUtils.timeToString8(historyBean.getTime())).append("\t");
            if (historyBean.getList().size() == 1) {
                if (historyBean.getList().get(0).isTemp()) {
                    String str = String.valueOf(unit == 0 ? TempUtils.formatFloat(historyBean.getList().get(0).getDp_value() / 10.0f) : TempUtils.CToF(historyBean.getList().get(0).getDp_value() / 10.0f));
                    stringBuilder.append(str).append(u).append("\t").append("").append("\t");
                } else {
                    stringBuilder.append("").append("\t").append(historyBean.getList().get(0).getDp_value() / 10.0).append("%").append("\t");
                }
            } else if (historyBean.getList().size() == 2) {
                if (historyBean.getList().get(0).isTemp()) {
                    String str = String.valueOf(unit == 0 ? TempUtils.formatFloat(historyBean.getList().get(0).getDp_value() / 10.0f) : TempUtils.CToF(historyBean.getList().get(0).getDp_value() / 10.0f));
                    stringBuilder.append(str).append(u).append("\t").append(historyBean.getList().get(1).getDp_value() / 10.0).append("%").append("\t");
                } else {
                    String str = String.valueOf(unit == 0 ? TempUtils.formatFloat(historyBean.getList().get(1).getDp_value() / 10.0f) : TempUtils.CToF(historyBean.getList().get(1).getDp_value() / 10.0f));
                    stringBuilder.append(str).append(u).append("\t").append(historyBean.getList().get(0).getDp_value() / 10.0).append("%").append("\t");
                }
            }

            stringBuilder.append("\n");
        }
        return saveFile(sharePath, stringBuilder);
    }

    public static boolean writeCsvFile(@NonNull HistoryBean historyBean, @NonNull String sharePath, boolean isShowVPD, boolean isShowDewPoint, @NonNull int unit, int tempCa, int humCa, long fromDate, long toDate, String macAddress) throws InterruptedException, ExecutionException {
        String title = "MAC address: " + macAddress + "\nDate" + "\t";

        if (historyBean.getTempBeans() != null && historyBean.getTempBeans().size() > 0) {
            title = title + "Temperature" + "\t";
        }

        if (historyBean.getHumBeans() != null && historyBean.getHumBeans().size() > 0) {
            title = title + "Humidity" + "\t";
        }

        if (isShowVPD) {
            title = title + "vpd" + "\t";
        }
        if (isShowDewPoint) {
            title = title + "dew point" + "\t";
        }
        title = title + "\n";

        int blockSize = 10000; //数据块容积
        int dataSize = historyBean.getTempBeans().size(); //数据总量
        int blockNum = (dataSize + blockSize - 1) / blockSize; //数据块多少个
        int totalSize = historyBean.getTempBeans().size(); //数据源长度

        //一个含有n个线程的线程池
        ExecutorService es = Executors.newFixedThreadPool(blockNum);
        //一个提交任务和获取任务结果的服务
        CompletionService<Map<Integer, String>> cs = new ExecutorCompletionService<>(es);

        //将源数据分组
        //按顺序讲分组送入线程池处理
        List<List<HistoryValueBean>> historyBeanLists = new ArrayList<>();
        for (int i = 0, j = 0; i < totalSize; i += blockSize) {
            List<HistoryValueBean> listBean = historyBean.getTempBeans().subList(i, Math.min(i + blockSize, totalSize));

            historyBeanLists.add(historyBean.getTempBeans().subList(i, Math.min(i + blockSize, totalSize)));

            cs.submit(new thsCsvTask(listBean, j, i, historyBean, fromDate, toDate, tempCa, humCa, unit, isShowVPD, isShowDewPoint));
            j++;
        }

        //根据顺序输出处理结果
        StringBuffer stringBuffer = new StringBuffer(title);
        Map<Integer, String> result = new HashMap<>();
        for (int i = 0; i < historyBeanLists.size(); i++) {
            Future<Map<Integer, String>> future = cs.take();
            result.putAll(future.get());
        }

        for (int i = 0; i < historyBeanLists.size(); i++) {
            for (Map.Entry<Integer, String> entry : result.entrySet()) {
                if (i == entry.getKey()) {
                    stringBuffer.append(entry.getValue());
                }
            }
        }

        //线程池关闭
        es.shutdown();
        return saveFile2(sharePath, stringBuffer);
    }

    static class thsCsvTask implements Callable<Map<Integer, String>> {
        private List<HistoryValueBean> list;
        private HistoryBean historyBean;
        private int taskId;
        private int firstPosition;
        private long fromDate;
        private long toDate;
        private int tempCa;
        private int humCa;
        private int unit;
        private boolean isShowVPD;
        private boolean isShowDewPoint;

        public thsCsvTask(List<HistoryValueBean> list, int taskId, int firstPosition, HistoryBean historyBean, long fromDate, long toDate, int tempCa, int humCa, int unit, boolean isShowVPD, boolean isShowDewPoint) {
            this.list = list;
            this.taskId = taskId;
            this.firstPosition = firstPosition;
            this.historyBean = historyBean;
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.tempCa = tempCa;
            this.humCa = humCa;
            this.unit = unit;
            this.isShowVPD = isShowVPD;
            this.isShowDewPoint = isShowDewPoint;
        }

        @Override
        public Map<Integer, String> call() throws Exception {
            Map<Integer, String> map = new HashMap<>();
            String u = "";
            if (unit == 0) {
                u = "℃";
            } else if (unit == 1) {
                u = "℉";
            }

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                HistoryValueBean historyTempValueBean = null;
                if (historyBean.getTempBeans() != null && historyBean.getTempBeans().size() > 0) {
                    historyTempValueBean = historyBean.getTempBeans().get(i + firstPosition);
                }

                HistoryValueBean historyHumValueBean = null;
                if (historyBean.getHumBeans() != null && historyBean.getHumBeans().size() > 0) {
                    historyHumValueBean = historyBean.getHumBeans().get(i + firstPosition);
                }

                if (historyTempValueBean != null) {
                    if (historyTempValueBean.getDateline() < fromDate || historyTempValueBean.getDateline() > toDate) {
                        continue;
                    }
                }

                if (historyTempValueBean != null) {
                    stringBuilder.append(DateUtils.timeToString8(historyTempValueBean.getDateline())).append("\t");
                    float value = unit == 1 ? (historyTempValueBean.getValue() + tempCa) / 100f * 1.8f + 32f : (historyTempValueBean.getValue() + tempCa) / 100f;
                    stringBuilder.append(String.format("%.2f", value)).append(u).append("\t");
                }

                if (historyHumValueBean != null) {
                    float value = (historyHumValueBean.getValue() + humCa) / 100f;
                    stringBuilder.append(String.format("%.2f", value)).append("%").append("\t");
                }

                if (isShowVPD) {
                    stringBuilder.append(VPDUtils.VPDCal((historyTempValueBean.getValue() + tempCa) / 100f, (historyHumValueBean.getValue() + humCa) / 100f)).append("kPa").append("\t");
                }

                if (isShowDewPoint) {
                    stringBuilder.append(VPDUtils.calDewPointTemp((historyTempValueBean.getValue() + tempCa) / 100f, (historyHumValueBean.getValue() + humCa) / 100f)).append(u).append("\t");
                }
                stringBuilder.append("\n");
            }
            map.put(taskId, stringBuilder.toString());
            return map;
        }
    }


    public static boolean writeCsvFile(@NonNull Map<Long, Integer> tempMap, @NonNull Map<Long, Integer> humMap, @NonNull List<Long> keyList, boolean temp, boolean hum, boolean dewPoint, boolean vpd, @NonNull String sharePath, @NonNull int unit, String macAddress) {
        String u = "";
        if (unit == 0) {
            u = "℃";
        } else if (unit == 1) {
            u = "℉";
        }
        String mac = "";
        for (int i = 0; i < macAddress.length(); i++) {
            mac = mac + macAddress.charAt(i);
            if (!macAddress.contains(":")) {
                if (i % 2 > 0) {
                    mac = mac + ":";
                }
            }
        }
        String title = "MAC:" + mac.substring(0, macAddress.contains(":") ? mac.length() : mac.length() - 1) + "\n";
        title = title + "Date" + "\t";
        if (temp) title = title + "Temperature" + "( " + u + " )" + "\t";
        if (hum) title = title + "Humidity" + "( % )" + "\t";
        if (dewPoint) title = title + "DewPoint" + "( " + u + " )" + "\t";
        if (vpd) title = title + "VPD" + "( kPa )" + "\t";
        title = title + "\n";
        StringBuilder stringBuilder = new StringBuilder(title);
        for (int j = 0; j < keyList.size(); j++) {
            long aLong = keyList.get(j);
            stringBuilder.append(DateUtils.timeToString8(aLong)).append("\t");
            float tempValue = 0, humValue = 0;
            if (tempMap.containsKey(aLong)) {
                tempValue = tempMap.get(aLong) / 10.0f;
                String str = String.valueOf(unit == 0 ? TempUtils.formatFloat(tempValue) : TempUtils.CToF(tempValue));
                if (temp) {
                    stringBuilder.append(str).append("\t");
                }
            } else {
                if (temp) {
                    stringBuilder.append("--").append("\t");
                }
            }

            if (humMap.containsKey(aLong)) {
                humValue = humMap.get(aLong) / 10.0f;
                if (hum) {
                    stringBuilder.append(TempUtils.formatFloat(humValue)).append("\t");
                }
            } else {
                if (hum) {
                    stringBuilder.append("--").append("\t");
                }
            }
            if (tempMap.containsKey(aLong) && humMap.containsKey(aLong)) {
                float dewPointValue = VPDUtils.calDewPointTemp1(tempValue, humValue);
                String str = String.valueOf(unit == 0 ? TempUtils.formatFloat(dewPointValue) : TempUtils.CToF(dewPointValue));
                if (dewPoint) {
                    stringBuilder.append(str).append("\t");
                }
            } else {
                if (dewPoint) {
                    stringBuilder.append("--").append("\t");
                }
            }

            if (tempMap.containsKey(aLong) && humMap.containsKey(aLong)) {
                float vpdValue = VPDUtils.VPDCal1(tempValue, humValue);
                String str = TempUtils.formatFloat(vpdValue);
                if (vpd) {
                    stringBuilder.append(str).append("\t");
                }
            } else {
                if (vpd) {
                    stringBuilder.append("--").append("\t");
                }
            }
            stringBuilder.append("\n");
        }
        return saveFile(sharePath, stringBuilder);
    }

    public static boolean writeCsvFile(@NonNull Map<Long, Integer> tempMap, @NonNull Map<Long, Integer> humMap, @NonNull List<Long> keyList, boolean temp, boolean hum, boolean dewPoint, boolean vpd, @NonNull String sharePath, @NonNull int unit, String macAddress, boolean isHour24) {
        String u = "";
        if (unit == 0) {
            u = "℃";
        } else if (unit == 1) {
            u = "℉";
        }
        String mac = "";
        for (int i = 0; i < macAddress.length(); i++) {
            mac = mac + macAddress.charAt(i);
            if (!macAddress.contains(":")) {
                if (i % 2 > 0) {
                    mac = mac + ":";
                }
            }
        }
        String title = "MAC:" + mac.substring(0, macAddress.contains(":") ? mac.length() : mac.length() - 1) + "\n";
        title = title + "Date" + "\t";
        if (temp) title = title + "Temperature" + "( " + u + " )" + "\t";
        if (hum) title = title + "Humidity" + "( % )" + "\t";
        if (dewPoint) title = title + "DewPoint" + "( " + u + " )" + "\t";
        if (vpd) title = title + "VPD" + "( kPa )" + "\t";
        title = title + "\n";
        StringBuilder stringBuilder = new StringBuilder(title);
        for (int j = 0; j < keyList.size(); j++) {
            long aLong = keyList.get(j);
            stringBuilder.append(isHour24 ? DateUtils.timeToString8(aLong) : ChartUtils.timeToString9(aLong)).append("\t");
            float tempValue = 0, humValue = 0;
            if (tempMap.containsKey(aLong)) {
                tempValue = tempMap.get(aLong) / 10.0f;
                String str = String.valueOf(unit == 0 ? TempUtils.formatFloat(tempValue) : TempUtils.CToF(tempValue));
                if (temp) {
                    stringBuilder.append(str).append("\t");
                }
            } else {
                if (temp) {
                    stringBuilder.append("--").append("\t");
                }
            }

            if (humMap.containsKey(aLong)) {
                humValue = humMap.get(aLong) / 10.0f;
                if (hum) {
                    stringBuilder.append(TempUtils.formatFloat(humValue)).append("\t");
                }
            } else {
                if (hum) {
                    stringBuilder.append("--").append("\t");
                }
            }
            if (tempMap.containsKey(aLong) && humMap.containsKey(aLong)) {
                float dewPointValue = VPDUtils.calDewPointTemp1(tempValue, humValue);
                String str = String.valueOf(unit == 0 ? TempUtils.formatFloat(dewPointValue) : TempUtils.CToF(dewPointValue));
                if (dewPoint) {
                    stringBuilder.append(str).append("\t");
                }
            } else {
                if (dewPoint) {
                    stringBuilder.append("--").append("\t");
                }
            }

            if (tempMap.containsKey(aLong) && humMap.containsKey(aLong)) {
                float vpdValue = VPDUtils.VPDCal1(tempValue, humValue);
                String str = TempUtils.formatFloat(vpdValue);
                if (vpd) {
                    stringBuilder.append(str).append("\t");
                }
            } else {
                if (vpd) {
                    stringBuilder.append("--").append("\t");
                }
            }
            stringBuilder.append("\n");
        }
        return saveFile(sharePath, stringBuilder);
    }


    public static boolean writeCsvFileIth21(@NonNull Map<Long, Integer> tempMap, @NonNull Map<Long, Integer> humMap, @NonNull List<Long> keyList, boolean temp, boolean hum, boolean dewPoint, boolean vpd, @NonNull String sharePath, @NonNull int unit, String macAddress, boolean isHour24) {
        String u = "";
        if (unit == 0) {
            u = "℃";
        } else if (unit == 1) {
            u = "℉";
        }
        String mac = "";
        for (int i = 0; i < macAddress.length(); i++) {
            mac = mac + macAddress.charAt(i);
            if (!macAddress.contains(":")) {
                if (i % 2 > 0) {
                    mac = mac + ":";
                }
            }
        }
        String title = "MAC:" + mac.substring(0, macAddress.contains(":") ? mac.length() : mac.length() - 1) + "\n";
        title = title + "Date" + "\t";
        if (temp) title = title + "Temperature" + "( " + u + " )" + "\t";
        if (hum) title = title + "Humidity" + "( % )" + "\t";
        if (dewPoint) title = title + "DewPoint" + "( " + u + " )" + "\t";
        if (vpd) title = title + "VPD" + "( kPa )" + "\t";
        title = title + "\n";
        StringBuilder stringBuilder = new StringBuilder(title);
        for (int j = 0; j < keyList.size(); j++) {
            long aLong = keyList.get(j);
            stringBuilder.append(isHour24 ? DateUtils.timeToString24(aLong) : ChartUtils.timeToString8_12(aLong)).append("\t");
            double tempValue = 0;
            float humValue = 0;
            if (tempMap.containsKey(aLong)) {
                tempValue = tempMap.get(aLong) / 10.0;
                String str = String.format(Locale.ENGLISH, "%.1f", unit == 0 ? tempValue : (float) (Math.floor(TempUtils.onlyCToF(tempValue) * 10) / 10));
//                String str = String.valueOf(unit == 0 ? TempUtils.formatFloat1(tempValue) : TempUtils.CToF2(tempValue));
                if (temp) {
                    stringBuilder.append(str).append("\t");
                }
            } else {
                if (temp) {
                    stringBuilder.append("--").append("\t");
                }
            }

            if (humMap.containsKey(aLong)) {
                humValue = humMap.get(aLong) / 10.0f;
                if (hum) {
                    stringBuilder.append(TempUtils.FToIntDown(humValue)).append("\t");
                }
            } else {
                if (hum) {
                    stringBuilder.append("--").append("\t");
                }
            }


            if (tempMap.containsKey(aLong) && humMap.containsKey(aLong)) {
                float dewPointValue = VPDUtils.calDewPointTemp1((float) tempValue, humValue);
                String str = String.format(Locale.ENGLISH, "%.1f", unit == 0 ? dewPointValue : TempUtils.CToF(dewPointValue));
                if (dewPoint) {
                    stringBuilder.append(str).append("\t");
                }
            } else {
                if (dewPoint) {
                    stringBuilder.append("--").append("\t");
                }
            }

            if (tempMap.containsKey(aLong) && humMap.containsKey(aLong)) {

                float vpdValue = VPDUtils.VPDCal1((float) tempValue, humValue);
                String str = String.format(Locale.ENGLISH, "%.1f", vpdValue);
                if (vpd) {
                    stringBuilder.append(str).append("\t");
                }
            } else {
                if (vpd) {
                    stringBuilder.append("--").append("\t");
                }
            }
            stringBuilder.append("\n");
        }
        return saveFile(sharePath, stringBuilder);
    }

    public static boolean saveFile(String sharePath, StringBuilder stringBuilder) {
        try {
            byte[] bytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_16);
            byte[] bs = new byte[bytes.length];
            if (bytes[0] == -1) {
                bs = bytes;
            } else {
                for (int i = 1; i < bytes.length; i += 2) {
                    bs[i - 1] = bytes[i];
                    bs[i] = bytes[i - 1];
                }
            }
            File file = new File(sharePath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bs);
            fos.flush();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveFile2(String sharePath, StringBuffer stringBuffer) {

        try {
            byte[] bytes = stringBuffer.toString().getBytes(StandardCharsets.UTF_16);
            byte[] bs = new byte[bytes.length];
            if (bytes[0] == -1) {
                bs = bytes;
            } else {
                for (int i = 1; i < bytes.length; i += 2) {
                    bs[i - 1] = bytes[i];
                    bs[i] = bytes[i - 1];
                }
            }
            File file = new File(sharePath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bs);
            fos.flush();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}