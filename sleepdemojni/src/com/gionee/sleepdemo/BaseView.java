package com.gionee.sleepdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class BaseView extends View {
    float mInnerRadius;
    float mOuterRadius;
    float mInnerStrokeWidth;
    float mOuterStrokeWidth;
    float mCenterX;
    float mCenterY;
    int mInnerCircleAlpha;
    int mInnerCircleBgAlpha;
    int mOuterCircleAlpha;
    float mInnerStartAngle;
    float mInnerSweepAngle;
    float mOuterStartAngle;
    float mOuterSweepAngle;
    int mInnerCircleColor = Color.WHITE;
    int mOuterCircleColor = 0xeef2f7;
    int ratio;
    Paint mPaint;
    RectF mInnerRectF;
    RectF mOuterRectF;
    boolean mOuterCircelVibility = true;

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInnerRadius =  3 * 106f;
        mOuterRadius =  3 * 106f;
        mInnerStrokeWidth =3 * 6f;
        mOuterStrokeWidth =  2f;
        mInnerCircleAlpha = 51;
        mOuterCircleAlpha =  51;
        mInnerStartAngle = 120;
        mInnerSweepAngle = 300;
        mOuterStartAngle =  0;
        mOuterSweepAngle =  360;
        mCenterX = 3 * 180;
        mCenterY = 3 * 126;

        mPaint = new Paint();
        mInnerRectF = new RectF(mCenterX - mInnerRadius, mCenterY - mInnerRadius, mCenterX + mInnerRadius,
                mCenterY + mInnerRadius);
        mOuterRectF = new RectF(mCenterX - mOuterRadius, mCenterY - mOuterRadius, mCenterX + mOuterRadius,
                mCenterY + mOuterRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);

        drawInnerCircle(canvas, mPaint);

        drawOuterCircle(canvas, mPaint);

        super.onDraw(canvas);
    }

    private void drawInnerCircle(Canvas canvas, Paint paint) {
        paint.setColor(mInnerCircleColor);
        paint.setAlpha(mInnerCircleBgAlpha);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mInnerStrokeWidth);
        canvas.drawArc(mInnerRectF, -450, 360, false, paint);

        paint.setColor(mInnerCircleColor);
        paint.setAlpha(mInnerCircleAlpha);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mInnerStrokeWidth);
        canvas.drawArc(mInnerRectF, mInnerStartAngle, mInnerSweepAngle, false, paint);
    }

    private void drawOuterCircle(Canvas canvas, Paint paint) {
        if (mOuterCircelVibility) {
            paint.setColor(mOuterCircleColor);
            paint.setAlpha(51);
            paint.setStrokeWidth(mOuterStrokeWidth);
            canvas.drawArc(mOuterRectF, -450, 360, false, paint);

            paint.setColor(mOuterCircleColor); 
            paint.setAlpha(mOuterCircleAlpha);
            paint.setStrokeWidth(mOuterStrokeWidth);
            canvas.drawArc(mOuterRectF, mOuterStartAngle, mOuterSweepAngle, false, paint);
        }
    }

    public void updateInnerCircleAlpha(int alpha) {
        mInnerCircleAlpha = alpha;
    }

    public void updateInnerCircleBgAlpha(int alpha) {
        mInnerCircleBgAlpha = alpha;
    }

    public void updateOuterCircleVisibility(boolean visible) {
        mOuterCircelVibility = visible;
    }

    public void updateRatio(int ratio) {
        this.ratio = ratio;
        invalidate();
    }

    public void updateViews() {
        invalidate();
    }

}
