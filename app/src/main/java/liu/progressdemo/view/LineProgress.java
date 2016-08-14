package liu.progressdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import liu.progressdemo.R;

/**
 * Created by 刘楠 on 2016/8/14 0014.10:25
 */
public class LineProgress extends View {

    private static final String TAG = "vivi";
    private int mFontgroundColor; //前景色
    private int mBackgroundColor; //背景色

    private int mProgress = 10;

    private Paint mPaint;

    private  int mTextSize =40;
    /**
     * 控件的宽度
     */
    private int mWidth;
    /**
     * 控件的高度
     */
    private int mHeight;

    private int mMax=100;

    public LineProgress(Context context) {
        this(context, null);
    }

    public LineProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        //获取自定的所有属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineProgress);


        mFontgroundColor = typedArray.getColor(R.styleable.LineProgress_foregroundColor, Color.RED);

        mBackgroundColor = typedArray.getColor(R.styleable.LineProgress_backgroundColor, Color.GRAY);

        typedArray.recycle();

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 获取宽度与高度
         */
        int width  = getWidth();
        int height =getHeight();

        int radio = height / 2;

        Log.d(TAG, "onDraw: width " + width);
        Log.d(TAG, "onDraw: height " + height);

        mPaint = new Paint();
        mPaint.setColor(mBackgroundColor);

        mPaint.setStrokeWidth(20);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);


        /**
         * 画背景色
         */
        RectF backRectF = new RectF(0, 0, width, height);

        canvas.drawRoundRect(backRectF, radio, radio, mPaint);


        /**
         * 画前景色
         */
        mPaint.setColor(mFontgroundColor);
        //进度
        //int progress =mProgress / width * 100;
        int widthUp= Math.round(((float)width*(float) mProgress/100)+0.5f);
//        int percent = (int) (((float) mProgress / (float) width) * 100);
        RectF fontRectF = new RectF(0, 0, widthUp, height);

        canvas.drawRoundRect(fontRectF, radio, radio, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(Math.round(widthUp/10+0.5f)+"%",widthUp/2,height/2+mTextSize/2,mPaint);




    }



    /**
     * 设置进度的方法
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        mProgress = progress;
        //postInvalidate();
        invalidate();
    }


}
