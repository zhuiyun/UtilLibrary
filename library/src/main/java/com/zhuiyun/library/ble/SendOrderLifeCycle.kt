//
//import com.zhuiyun.library.ble.BleLogListener
//import com.zhuiyun.library.ble.launchBle
//import com.zhuiyun.library.utils.LogUtil
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.cancel
//import kotlinx.coroutines.delay
//
//
///**
// * 发送指令带回复判断
// * timeDelay 发送指令延时时间
// * timeOut 超时没有回复时间
// * repeatCount 重试次数
// * juge 回复判断
// */
//
//suspend fun CoroutineScope.sendOrderWithReply(
//    bleService: BleService,
//    order: ByteArray,
//    timeDelay: Long = 50L,
//    timeOut: Long = 2000L,
//    repeatCount: Int = 3,
//    judge: (bytes: ByteArray) -> Boolean,
//): CoroutineScope {
//    var repeatCountTemp = repeatCount
//    var bleDataListener: BleLogListener? = null
//    val job = this.launchBle({
//        while (repeatCountTemp >= 0) {
//            //是否符合外部传入规则
//            var isActived = false
//            bleDataListener?.let { bleService.removeBleOrderListener(it) }
//            bleDataListener = object : BleLogListener {
//                override fun updataData(data: ByteArray) {
//                    isActived = judge(data)
//                }
//            }
//            bleService.addBleOrderListener(bleDataListener!!)
//            val time = System.currentTimeMillis()
//            delay(timeDelay)
//            bleService.writeData(order)
//            while (!isActived && System.currentTimeMillis() - time < timeOut) {
//                delay(10L)
//            }
//            if (!isActived) {
//                repeatCountTemp--
//            } else {
//                cancel()
//            }
//        }
//    }, onFinally = {
//        bleDataListener?.let { bleService.removeBleOrderListener(it) }
//    }, onError = {
//
//    })
//    job.join()
//    LogUtil.e("BleLifeScope", "$this")
//    return this
//}
//
//
///**
// * 延迟发送指令
// * timeDelay 发送指令延时时间
// * timeOut 超时没有回复时间
// * repeatCount 重试次数
// * juge 回复判断
// */
//suspend fun CoroutineScope.sendOrderDelay(
//    bleService: BleService,
//    order: ByteArray,
//    timeDelay: Long = 50L,
//): CoroutineScope {
//    val job = this.launchBle({
//        delay(timeDelay)
//        bleService.addWriteData(order)
//    }, onFinally = {
//    }, onError = {
//    })
//    job.join()
//    return this
//}
//
///**
// * 发送指令带成功后下一步操作
// * 发送指令带回复判断
// * timeDelay 发送指令延时时间
// * timeOut 超时没有回复时间
// * repeatCount 重试次数
// * juge 回复判断
// * onNext 发送成功或者发送失败下一步
// */
//suspend fun CoroutineScope.sendOrderWithNextAction(
//    bleService: BleService,
//    order: ByteArray,
//    timeDelay: Long = 50L,
//    timeOut: Long = 2000L,
//    repeatCount: Int = 3,
//    judge: (bytes: ByteArray) -> Boolean,
//    onNext: (bytes: ByteArray) -> Unit
//): CoroutineScope {
//    var repeatCountTemp = repeatCount
//    var bleDataListener: BleLogListener? = null
//    val job = this.launchBle({
//        while (repeatCountTemp > 0) {
//            //是否符合外部传入的判断条件
//            var isActived = false
//            bleDataListener?.let { bleService.removeBleOrderListener(it) }
//            bleDataListener = object : BleLogListener {
//                override fun updataData(data: ByteArray) {
//                    isActived = judge(data)
//                    if (isActived) {
//                        onNext(data)
//                        cancel()
//                    }
//                }
//            }
//            val time = System.currentTimeMillis()
//            bleService.addBleOrderListener(bleDataListener!!)
//            delay(timeDelay)
//            bleService.writeData(order)
//            while (!isActived && System.currentTimeMillis() - time < timeOut) {
//                delay(10L)
//            }
//            if (repeatCountTemp > 0) {
//                repeatCountTemp--
//            } else {
//                cancel()
//            }
//        }
//    }, onFinally = {
//        bleDataListener?.let { bleService.removeBleOrderListener(it) }
//    }, onError = {
//        bleDataListener?.let { bleService.removeBleOrderListener(it) }
//    })
//    job.join()
//    return this
//}
//
//
///**
// * 发送指令带成功后下一步操作
// * 发送指令带回复判断
// * timeDelay 发送指令延时时间
// * timeOut 超时没有回复时间
// * repeatCount 重试次数
// * juge 回复判断
// * onNext 发送成功下一步
// * onFail 发送失败下一步
// */
//suspend fun CoroutineScope.sendOrderWithNextAndFailAction(
//    bleService: BleService,
//    order: ByteArray,
//    timeDelay: Long = 50L,
//    timeOut: Long = 2000L,
//    repeatCount: Int = 3,
//    judge: (bytes: ByteArray) -> Boolean,
//    onNext: (bytes: ByteArray) -> Unit,
//    onFail: () -> Unit
//): CoroutineScope {
//    var repeatCountTemp = repeatCount
//    var bleDataListener: BleLogListener? = null
//    val job = this.launchBle({
//        while (repeatCountTemp > 0) {
//            //是否符合外部传入的判断条件
//            var isActived = false
//            bleDataListener?.let { bleService.removeBleOrderListener(it) }
//            bleDataListener = object : BleLogListener {
//                override fun updataData(data: ByteArray) {
//                    isActived = judge(data)
//                    if (isActived) {
//                        onNext(data)
//                        cancel()
//                    }
//                }
//            }
//            val time = System.currentTimeMillis()
//            bleService.addBleOrderListener(bleDataListener!!)
//            delay(timeDelay)
//            bleService.writeData(order)
//            while (!isActived && System.currentTimeMillis() - time < timeOut) {
//                delay(10L)
//            }
//            if (repeatCountTemp > 0) {
//                repeatCountTemp--
//            } else {
//                onFail()
//                cancel()
//            }
//        }
//    }, onFinally = {
//        bleDataListener?.let { bleService.removeBleOrderListener(it) }
//    }, onError = {
//        bleDataListener?.let { bleService.removeBleOrderListener(it) }
//    })
//    job.join()
//    return this
//}
//
//
///**
// * 连续发送指令带成功后下一步操作
// * 发送指令带回复判断
// * timeDelay 发送指令延时时间
// * timeOut 超时没有回复时间
// * repeatCount 重试次数
// * juge 回复判断
// * onNext 发送成功下一步
// *
// */
//suspend fun CoroutineScope.sendContinuousOrderWithNextAction(
//    bleService: BleService,
//    order: ByteArray,
//    timeDelay: Long = 50L,
//    timeOut: Long = 2000L,
//    repeatCount: Int = 3,
//    judge: (bytes: ByteArray) -> Boolean,
//    onNext: (bytes: ByteArray) -> Unit,
//    onFail: () -> Unit
//): CoroutineScope {
//    var repeatCountTemp = repeatCount
//    var bleDataListener: BleLogListener? = null
//    val job = this.launchBle({
//        while (repeatCountTemp > 0) {
//            //是否符合外部传入的判断条件
//            var isActived = false
//            bleDataListener?.let { bleService.removeBleOrderListener(it) }
//            bleDataListener = object : BleLogListener {
//                override fun updataData(data: ByteArray) {
//                    isActived = judge(data)
//                    if (isActived) {
//                        onNext(data)
//                        cancel()
//                    }
//                }
//            }
//            val time = System.currentTimeMillis()
//            bleService.addBleOrderListener(bleDataListener!!)
//            delay(timeDelay)
//            bleService.writeData(order)
//            while (!isActived && System.currentTimeMillis() - time < timeOut) {
//                delay(10L)
//            }
//            if (repeatCountTemp > 0) {
//                repeatCountTemp--
//            } else {
//                onFail()
//                cancel()
//            }
//        }
//    }, onFinally = {
//        bleDataListener?.let { bleService.removeBleOrderListener(it) }
//    }, onError = {
//        bleDataListener?.let { bleService.removeBleOrderListener(it) }
//    })
//    job.join()
//    return this
//}
//
//
///**
// * 连续发送指令带成功后下一步操作
// * 发送指令带回复判断
// * timeDelay 发送指令延时时间
// * timeOut 超时没有回复时间
// * repeatCount 重试次数
// * juge 回复判断
// * onNext 发送成功下一步
// *
// */
//suspend fun CoroutineScope.sendContinuousOrderWithNextAction(
//    bleService: BleService,
//    order: ByteArray,
//    timeDelay: Long = 50L,
//    timeOut: Long = 2000L,
//    repeatCount: Int = 3,
//    judge: (bytes: ByteArray) -> Boolean,
//    onNext: (bytes: ByteArray) -> Unit
//): CoroutineScope {
//    var repeatCountTemp = repeatCount
//    var bleDataListener: BleLogListener? = null
//    val job = this.launchBle({
//        while (repeatCountTemp > 0) {
//            //是否符合外部传入的判断条件
//            var isActived = false
//            bleDataListener?.let { bleService.removeBleOrderListener(it) }
//            bleDataListener = object : BleLogListener {
//                override fun updataData(data: ByteArray) {
//                    isActived = judge(data)
//                    if (isActived) {
//                        onNext(data)
//                        cancel()
//                    }
//                }
//            }
//            val time = System.currentTimeMillis()
//            bleService.addBleOrderListener(bleDataListener!!)
//            delay(timeDelay)
//            bleService.writeData(order)
//            while (!isActived && System.currentTimeMillis() - time < timeOut) {
//                delay(10L)
//            }
//            if (repeatCountTemp > 0) {
//                repeatCountTemp--
//            } else {
//                cancel()
//            }
//        }
//    }, onFinally = {
//        bleDataListener?.let { bleService.removeBleOrderListener(it) }
//    }, onError = {
//        bleDataListener?.let { bleService.removeBleOrderListener(it) }
//    })
//
//    job.join()
//    LogUtil.e("BleLifeScope", "$this")
//    return this
//}