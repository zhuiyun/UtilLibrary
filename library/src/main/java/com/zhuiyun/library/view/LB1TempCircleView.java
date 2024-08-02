package com.inkbird.inkbirdapp.device.lb1sc.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.inkbird.inkbirdapp.R;


/**
 * create by app-inkbird on 2023/10/30 Time：14:24
 * description:
 */
public class LB1TempCircleView extends View {
    private float mTriangleX1, mTriangleX2, mTriangleX3, mTriangleY1, mTriangleY2, mTriangleY3;

    //进度条
    private float progress = 50;
    private float lineWidth; // 绘制圆环的线宽
    private float pointWidth; // 绘制圆点线宽
    private float pointHigh;//绘制圆点填充大小
    private float scaleWidth;//绘制刻度大小
    private int rectangleWidth; // 绘制矩形的线宽

    private int bgColor=Color.WHITE;

    private static final float START_ANGLE = 135f;
    private static final float MAX_ANGLE = 270f;
    //图片
    private Bitmap mBitmap;
    //图片宽高
    private float mOriginalWidth, mOriginalHeight;
    //图片中心点
    private float mRotateX, mRotateY;

    //内环颜色
    private int colorCircle = Color.parseColor("#E6E6E6");
    private int colorBottomCircle = Color.parseColor("#F5F5F5");

    //画笔
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private Paint mCenterUnitPaint;
    private Paint mOutCirclePaint;
    private Paint mBottomOutCirclePaint;
    private Paint mActionCirclePaint;
    private Paint mArrowPaint;
    private Paint mStartCirclePaint;
    private Paint mMaxMinTextPaint;
    private Paint mMaxMinUnitPaint;

    private Paint mStartPointPaint;

    private Paint centerTipsPaint;

    private Paint scaleBluePaint;
    private Paint scaleYellowPaint;
    private Paint scaleRedPaint;

    private Paint circleProgressPaint;
    private Paint bgCircleProgressPaint;

    private Paint mBitmapPaint;

    private Rect textBond = new Rect();

    //屏幕居中坐标宽高
    private int centerWith;
    private int centerHeight;
    // View宽
    private int mWidth;
    // View高
    private int mHeight;

    //该控件在XML上的边距
    private int mPaddingRight;
    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingBottom;

    //圆环宽高
    private int circleRoundWith;
    private int circleRoundHigh;

    //中间文本内容
    private String centerText = "";
    private String unitText = "℉";
    //最大最小值文本
    private String maxText = "";
    private String minText = "";

    private String centerTipText="";


    //要测量的文字数组
//    String[] mtext;
    //内圆半径
    private int radisCircle = centerWith / 2;
    //内圆圆环宽度
    private int radisCircleWith = centerWith / 2;
    private int[] colors = {
            Color.parseColor("#D43333"),
            Color.parseColor("#1E8EC8"),
            Color.parseColor("#1E8EC8"),
            Color.parseColor("#F4B433"),
            Color.parseColor("#F4B433"),
            Color.parseColor("#D43333")
    };


    private static final int CIRCLE_RADIUS_OUT = 75;  // 外圆半径
    private static final float ROTATION_ANGLE = 8.5f;       // 旋转角度
    private static final int LONG_POINT = 40;          // 长刻度线
    private static final int SHORT_POINT = 20;         // 短刻度线
    private static final int TITLE_TEXT_SIZE = 60;     // 标题文字大小
    private static final int POINT_TEXT_SIZE = 30;     // 刻度文字大小
    private static final int TITLE_STROKE_WIDTH = 1;   // 标题画笔宽度
    private static final int POINT_STROKE_WIDTH = 5;   // 刻度画笔宽度
    private static final int CIRCLE_RADIUS_IN = 200;   // 内圆半径
    private static final int STAT_ANGLE = 226;          // 开始角度
    private static final int SWEEP_ANGLE = -210;       // 扫过角度
    private static final float CIRCLE_PROGRESS_WITH = 19f;

    public LB1TempCircleView(Context context) {
        super(context);
        initView();
    }

    public LB1TempCircleView(Context context, @androidx.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTempCircle);
        lineWidth = array.getDimension(R.styleable.MyTempCircle_lineWidth, 8f);
        pointWidth = array.getDimension(R.styleable.MyTempCircle_pointWidth, 4f);
        pointHigh = array.getDimension(R.styleable.MyTempCircle_pointHigh, 4f);
        scaleWidth = array.getDimension(R.styleable.MyTempCircle_scaleWidth, 4f);
        initView();
    }

    public LB1TempCircleView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);


        //定义圆画笔
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.WHITE);

        //定义文字画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(dip2px(getContext(), 46));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        centerTipsPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        centerTipsPaint.setColor(Color.BLACK);
        centerTipsPaint.setTextSize(dip2px(getContext(),10));
        centerTipsPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        mCenterUnitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterUnitPaint.setColor(Color.BLACK);
        mCenterUnitPaint.setTextSize(dip2px(getContext(), 22));
        mCenterUnitPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mCenterUnitPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mMaxMinTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMaxMinTextPaint.setColor(Color.GRAY);
        mMaxMinTextPaint.setTextSize(dip2px(getContext(), 14));
        mMaxMinTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mMaxMinUnitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMaxMinUnitPaint.setColor(Color.GRAY);
        mMaxMinUnitPaint.setTextSize(dip2px(getContext(), 10));
        mMaxMinUnitPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        //定义外圆环画笔
        mOutCirclePaint = new Paint();
        mOutCirclePaint.setAntiAlias(true);
        mOutCirclePaint.setStyle(Paint.Style.STROKE);
        mOutCirclePaint.setColor(colorCircle);
        mOutCirclePaint.setStrokeWidth(dip2px(getContext(), 60));
        mOutCirclePaint.setStrokeCap(Paint.Cap.BUTT);

        //定义外圆环顶部画笔
        mBottomOutCirclePaint = new Paint();
        mBottomOutCirclePaint.setAntiAlias(true);
        mBottomOutCirclePaint.setStyle(Paint.Style.STROKE);
        mBottomOutCirclePaint.setColor(colorBottomCircle);
        mBottomOutCirclePaint.setStrokeWidth(dip2px(getContext(), 60));
        mBottomOutCirclePaint.setStrokeCap(Paint.Cap.BUTT);

        mStartPointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mStartPointPaint.setStrokeWidth(dip2px(getContext(),1));
        mStartPointPaint.setStyle(Paint.Style.STROKE);
        mStartPointPaint.setColor(Color.parseColor("#f4f4f4"));


        //定义刻度画笔
        // 画圆
        mActionCirclePaint = new Paint();
        mActionCirclePaint.setAntiAlias(true);
        mActionCirclePaint.setStrokeWidth(pointHigh);
        mActionCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mActionCirclePaint.setColor(Color.parseColor("#F5F5F5"));
        mActionCirclePaint.setStyle(Paint.Style.STROKE);

        mArrowPaint = new Paint();
        mArrowPaint.setAntiAlias(true);
        mArrowPaint.setStrokeWidth(pointHigh);
        mArrowPaint.setStrokeCap(Paint.Cap.ROUND);
        mArrowPaint.setStyle(Paint.Style.FILL);

        mStartCirclePaint = new Paint();
        mStartCirclePaint.setAntiAlias(true);
        mStartCirclePaint.setStrokeWidth(pointHigh);
        mStartCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mStartCirclePaint.setColor(Color.parseColor("#F5F5F5"));
        mStartCirclePaint.setStyle(Paint.Style.STROKE);


        // 刻度画笔蓝色
        scaleBluePaint = new Paint();
        scaleBluePaint.setAntiAlias(true);
        scaleBluePaint.setStrokeWidth(scaleWidth);
//        scaleBluePaint.setStrokeCap(Paint.Cap.ROUND);
        scaleBluePaint.setColor(Color.parseColor("#1E8EC8"));
        scaleBluePaint.setStyle(Paint.Style.STROKE);


        // 刻度画笔黄色
        scaleYellowPaint = new Paint();
        scaleYellowPaint.setAntiAlias(true);
        scaleYellowPaint.setStrokeWidth(scaleWidth);
        scaleYellowPaint.setStrokeCap(Paint.Cap.ROUND);
        scaleYellowPaint.setColor(Color.parseColor("#F4B433"));
        scaleYellowPaint.setStyle(Paint.Style.STROKE);

        // 刻度画笔红色
        scaleRedPaint = new Paint();
        scaleRedPaint.setAntiAlias(true);
        scaleRedPaint.setStrokeWidth(scaleWidth);
        scaleRedPaint.setStrokeCap(Paint.Cap.ROUND);
        scaleRedPaint.setColor(Color.parseColor("#D43333"));
        scaleRedPaint.setStyle(Paint.Style.STROKE);

        //起点圆画笔
        circleProgressPaint = new Paint();
        circleProgressPaint.setColor(Color.GRAY);
        circleProgressPaint.setStyle(Paint.Style.STROKE);
        circleProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        circleProgressPaint.setStrokeWidth(lineWidth);

        //进度圆弧画笔
        bgCircleProgressPaint = new Paint();
        bgCircleProgressPaint.setColor(Color.parseColor("#F6F6F6"));
        bgCircleProgressPaint.setStyle(Paint.Style.STROKE);
        bgCircleProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        bgCircleProgressPaint.setStrokeWidth(lineWidth);

        //图片画笔
        mBitmapPaint = new Paint();



    }

    private void getPaddingAttr() {
        //获取控件上下左右的内间距
        mPaddingRight = getPaddingRight();
        mPaddingLeft = getPaddingLeft();
        mPaddingTop = getPaddingTop();
        mPaddingBottom = getPaddingBottom();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int myWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int myWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int myHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int myHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取宽
        if (myWidthSpecMode == MeasureSpec.EXACTLY) {
            // match_parent/精确值
            mWidth = myWidthSpecSize;
        } else {
            // wrap_content
            mWidth = dip2px(getContext(), 40);
        }

        // 获取高
        if (myHeightSpecMode == MeasureSpec.EXACTLY) {
            // match_parent/精确值
            mHeight = myHeightSpecSize;
        } else {
            // wrap_content
            mHeight = dip2px(getContext(), 40);
        }

        // 设置该view的宽高
        setMeasuredDimension(mWidth, mHeight);

        //获取画布中心点宽高
        centerWith = mWidth / 2;
        centerHeight = mHeight / 2;

        getPaddingAttr();
    }

    @SuppressLint("NewApi")
    public void setBitmap(int id) {
        mBitmap = BitmapFactory.decodeResource(getResources(), id);
        mOriginalWidth = mBitmap.getWidth();
        mOriginalHeight = mBitmap.getHeight();
        mRotateX = mOriginalWidth / 2;
        mRotateY = mOriginalHeight / 2;
        float scale_w = ((float) 390) / mOriginalWidth;
        float scale_h = ((float) 390) / mOriginalHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap mBitmaps = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        mOriginalWidth = mBitmaps.getWidth();
        mOriginalHeight = mBitmaps.getHeight();
        mRotateX = mOriginalWidth / 2;
        mRotateY = mOriginalHeight / 2;
        mBitmap = mBitmaps;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            canvas.drawColor(bgColor);
            //画内外圆
            drawCircle(canvas);
            //画中心背景图
            drawImg(canvas);
            //画字
            drawCenterText(canvas);
            drawMaxMinText(canvas);
            //画刻度
            drawScale(canvas);

            ///画进度圆环
            drawArc(canvas);
            //画进度圆点
            drawPoint(canvas);


//            invalidate();
        }
    }


    private void drawCircle(Canvas canvas) {
        radisCircle = (mWidth - centerWith) / 2 - (mPaddingLeft + mPaddingRight);
        mOutCirclePaint.setStrokeWidth(radisCircle);
        mBottomOutCirclePaint.setStrokeWidth(radisCircle);
        //画内圆
        RectF ovel = new RectF(centerWith - radisCircle, centerHeight - radisCircle, centerWith + radisCircle, centerHeight + radisCircle);
        mOutCirclePaint.setColor(colorCircle);
        canvas.drawArc(ovel, 135, 270, false, mOutCirclePaint);
        //画底部内圆
        RectF ovelB = new RectF(centerWith - radisCircle, centerHeight - radisCircle, centerWith + radisCircle, centerHeight + radisCircle);
        mBottomOutCirclePaint.setColor(colorBottomCircle);
        canvas.drawArc(ovelB, 45, 90, false, mBottomOutCirclePaint);
        //画圆心
        canvas.drawCircle(centerWith, centerHeight, radisCircle, mCirclePaint);

    }

    private void drawScale(Canvas canvas) {
        canvas.save();

        float startX = centerWith + radisCircle * 2;
        float startY = centerHeight;
        float stopX = centerWith + (radisCircle * 2 - radisCircle / 5f);
        float stopY = centerHeight;

        canvas.rotate(-STAT_ANGLE, centerWith, centerHeight);

        for (int i = 0; i <= 32; i++) {
//            if (i < 9){
//
//                canvas.drawLine(startX, startY, stopX , stopY, scaleBluePaint);
//            }
//            if (i>8 && i<24){
//                canvas.drawLine(startX, startY, stopX , stopY, scaleYellowPaint);
//            }
//            if (i>23 && i <= 32){
//                canvas.drawLine(startX, startY, stopX , stopY, scaleRedPaint);
//            }
            scaleBluePaint.setColor(setColor(i, 32));
            canvas.drawLine(startX, startY, stopX, stopY, scaleBluePaint);
            canvas.rotate(ROTATION_ANGLE, centerWith, centerHeight);
        }

        canvas.restore();
    }

    private void drawCenterText(Canvas canvas) {
        //计算中间文本的宽高
        mTextPaint.getTextBounds(centerText, 0, centerText.length(), textBond);
        float centerTextWidh = centerWith - textBond.width() / 2f;
        float centerTextHeight = centerHeight + textBond.height() / 2f;
        float endWidth=centerWith+textBond.width()/2f;
        float startHeight=centerTextHeight-textBond.height()/2f;
        canvas.drawText(centerText, centerTextWidh, centerTextHeight, mTextPaint);
        mCenterUnitPaint.getTextBounds(unitText, 0, unitText.length(), textBond);
        centerTextWidh = endWidth;
        canvas.drawText(unitText, centerTextWidh+dip2px(getContext(),2f), startHeight, mCenterUnitPaint);
        centerTipsPaint.getTextBounds(centerTipText,0,centerTipText.length(),textBond);
        canvas.drawText(centerTipText,centerWith-textBond.width()/2f, centerTextHeight +dip2px(getContext(),20)+textBond.height(),centerTipsPaint);
    }

    private void drawMaxMinText(Canvas canvas) {
        //计算最大最小范围文本的宽高
        mMaxMinTextPaint.getTextBounds(maxText, 0, maxText.length(), textBond);
        float centerTextWith = mWidth * 3 / 4f - textBond.width() / 2f;
        float centerTextHeight =mHeight-radisCircle*2/5f- textBond.height()/2f;
        float endWidth=mWidth*3/4f+textBond.width() / 2f;

        float startHeight= centerTextHeight - textBond.height()/2f;
        canvas.drawText(maxText, centerTextWith, centerTextHeight, mMaxMinTextPaint);
        mMaxMinUnitPaint.getTextBounds(unitText,0,unitText.length(),textBond);
        canvas.drawText(unitText,endWidth+dip2px(getContext(),2),startHeight,mMaxMinUnitPaint);

        mMaxMinTextPaint.getTextBounds(maxText, 0, maxText.length(), textBond);
        centerTextWith = mWidth / 4f - textBond.width() / 2f;
        centerTextHeight = mHeight - radisCircle * 2 / 5f - textBond.height()/2f;
        endWidth=mWidth / 4f+textBond.width() / 2f;
        canvas.drawText(minText, centerTextWith, centerTextHeight, mMaxMinTextPaint);
        canvas.drawText(unitText,endWidth+dip2px(getContext(),2),startHeight,mMaxMinUnitPaint);
    }

    @SuppressLint("NewApi")
    private void drawImg(Canvas canvas) {
        if (mBitmap == null) return;
        canvas.drawBitmap(mBitmap, centerWith - mRotateX, centerHeight - mRotateY, mBitmapPaint);
    }

    @SuppressLint("NewApi")
    private void drawPoint(Canvas canvas) {
        Path path = new Path();
        float sweepAngle = MAX_ANGLE * progress / 100;
        path.addArc(centerWith - radisCircle - radisCircle / 2f, centerHeight - radisCircle - radisCircle / 2f, centerWith + radisCircle + radisCircle / 2f, centerHeight + radisCircle + radisCircle / 2f, START_ANGLE, sweepAngle);
        PathMeasure measure = new PathMeasure(path, false);
        float[] pos = new float[2];
        float[] pos1 = new float[2];
        measure.getPosTan(measure.getLength() - 1, pos, null);
        measure.getPosTan(0, pos1, null);

        if (progress <= 25 && progress > 0) {
            mActionCirclePaint.setColor(Color.parseColor("#1E8EC8"));
            mArrowPaint.setColor(Color.parseColor("#1E8EC8"));
        }
        if (progress <= 75 && progress > 25) {
            mActionCirclePaint.setColor(Color.parseColor("#F4B433"));
            mArrowPaint.setColor(Color.parseColor("#F4B433"));
        }

        if (progress <= 100 && progress > 75) {
            mActionCirclePaint.setColor(Color.parseColor("#D43333"));
            mArrowPaint.setColor(Color.parseColor("#D43333"));
        }

        Path arrow = new Path();
        mStartPointPaint.setStrokeWidth(dip2px(getContext(),1));
        mStartPointPaint.setStyle(Paint.Style.STROKE);
        mStartPointPaint.setColor(Color.parseColor("#f4f4f4"));
        canvas.drawCircle(pos1[0],pos1[1],dip2px(getContext(),12.5f),mStartPointPaint);
        mStartPointPaint.setColor(Color.WHITE);
        mStartPointPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(pos1[0],pos1[1],dip2px(getContext(),12),mStartPointPaint);
        mStartPointPaint.setColor(Color.parseColor("#1E8EC8"));
        canvas.drawCircle(pos1[0],pos1[1],dip2px(getContext(),6),mStartPointPaint);



        canvas.rotate(MAX_ANGLE * progress , pos[0], pos[1]);
        int mRaduis=dip2px(getContext(),15);
        mTriangleX1 = pos[0];
        mTriangleY1 = (float) (pos[1] + 2 * mRaduis * Math.sin(Math.PI /4));
        mTriangleX2 = (float) (pos[0] - mRaduis * Math.cos(Math.PI /4));
        mTriangleX3 = (float) (pos[0] + mRaduis * Math.cos(Math.PI / 4));
        mTriangleY2 = mTriangleY3 = (float) (pos[1]+ mRaduis * Math.sin(Math.PI / 4));


        canvas.drawCircle(pos[0], pos[1], mRaduis, mArrowPaint);
        //绘制顶部三角形
        arrow.moveTo(mTriangleX1, mTriangleY1);
        arrow.lineTo(mTriangleX2, mTriangleY2);
        arrow.lineTo(mTriangleX3, mTriangleY3);
        //lineto起点
        arrow.close();
        canvas.drawPath(arrow, mArrowPaint);



        canvas.drawPath(arrow, mArrowPaint);

        mActionCirclePaint.setStyle(Paint.Style.STROKE);//仅描边
        canvas.drawCircle(pos[0], pos[1], lineWidth - pointWidth, mActionCirclePaint);

        mActionCirclePaint.setColor(Color.WHITE);
        mActionCirclePaint.setStyle(Paint.Style.FILL);//仅填充内部
        canvas.drawCircle(pos[0], pos[1], lineWidth - pointWidth - pointWidth / 3, mActionCirclePaint);

        mStartCirclePaint.setColor(Color.WHITE);
        mStartCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);//仅描边
        //canvas.drawCircle(centerWith - radisCircle - (lineWidth+ pointWidth), centerHeight + radisCircle +(lineWidth + pointWidth), lineWidth ,mStartCirclePaint);

        mStartCirclePaint.setColor(Color.parseColor("#55ADD9"));
        mStartCirclePaint.setStyle(Paint.Style.FILL);//仅填充内部
        //canvas.drawCircle(centerWith - radisCircle -(lineWidth + pointWidth) , centerHeight + radisCircle +(lineWidth + pointWidth ) , lineWidth - lineWidth/2, mStartCirclePaint);

    }

    @SuppressLint("NewApi")
    private void drawArc(Canvas canvas) {
        float sweepAngle = MAX_ANGLE * progress / 100;
        Shader shader = new SweepGradient(centerWith, centerHeight, colors, new float[]{0f, 0.4F, 0.5f, 0.7f, 0.8f, 1.0F});
        bgCircleProgressPaint.setShader(shader);
//        canvas.drawArc(centerWith - CIRCLE_RADIUS_OUT - Utils.dip2px(getContext(),CIRCLE_PROGRESS_WITH), centerHeight - CIRCLE_RADIUS_OUT- Utils.dip2px(getContext(),CIRCLE_PROGRESS_WITH), centerWith + CIRCLE_RADIUS_OUT+ Utils.dip2px(getContext(),CIRCLE_PROGRESS_WITH), centerHeight + CIRCLE_RADIUS_OUT+ Utils.dip2px(getContext(),CIRCLE_PROGRESS_WITH), START_ANGLE, MAX_ANGLE, false, circleProgressPaint);
        canvas.drawArc(centerWith - radisCircle - radisCircle / 2f, centerHeight - radisCircle - radisCircle / 2f, centerWith + radisCircle + radisCircle / 2f, centerHeight + radisCircle + radisCircle / 2f, START_ANGLE, sweepAngle, false, bgCircleProgressPaint);
//        canvas.drawArc(centerWith ,centerHeight ,dip2px(getContext(),20),mCirclePaint);
    }

    public void setCenterText(String centerText) {
        this.centerText = centerText;
        invalidate();
    }

    public void setProgress(float progress) {
//        this.progress = progress;
        this.progress=50;
        invalidate();
    }

    public void setColorCircle(int colorCircle) {
        this.colorCircle = colorCircle;
    }

    public void setColorBottomCircle(int colorBottomCircle) {
        this.colorBottomCircle = colorBottomCircle;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    private int setColor(float val, int mCount) {

        int r = 0, g = 0, b = 0;

        if (val < 6) {
            r = 24 + (int) ((58 / 5.0f) * val);
            g = 146 + (int) ((27 / 5.0f) * val);
            b = 206 + (int) ((12 / 5.0f) * val);
        } else if (val >= 6 && val < 20) {
            r = 82 + (int) ((162 / 14.0f) * (val - 5));
            g = 173 + (int) ((7 / 14.0f) * (val - 5));
            b = 218 - (int) ((167 / 14.0f) * (val - 5));
        } else if (val >= 20 && val < 28) {
            r = 244 - (int) ((54 / 8.0f) * (val - 19));
            g = 180 - (int) ((152 / 8.0f) * (val - 19));
            b = 51 - (int) ((23 / 8.0f) * (val - 19));
        } else {
            r = 190;
            g = 28;
            b = 28;
        }

        return Color.rgb(r, g, b);

    }

    //设置最大最小范围文字
    public void setMaxMinText(String maxText, String minText) {
        this.maxText = maxText;
        this.minText = minText;
        invalidate();
    }

    //设置最大最小范围文字
    public void setCenterTipText(String centerTipText) {
        this.centerTipText=centerTipText;
        invalidate();
    }



    //设置最大最小范围文字
    public void setUnit(int unit) {
        if (unit == 0) {
            unitText = "℃";
        } else {
            unitText = "℉";
        }
        invalidate();
    }

    public void setIsDark(boolean isDark){
        if(isDark){
            bgColor=Color.parseColor("#111111");
            colorCircle=Color.parseColor("#3F3F3F");
            colorBottomCircle=Color.parseColor("#282828");
            mCirclePaint.setColor(Color.parseColor("#B3B3B3"));
        }else{
            bgColor=Color.WHITE;
            colorBottomCircle=Color.parseColor("#F5F5F5");
            colorCircle=Color.parseColor("#E6E6E6");
            mCirclePaint.setColor(Color.WHITE);
        }
        invalidate();
    }
}
