package com.example.view.myview.pathanim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PayAnim extends View {
    Paint paint;
    Path dstPath;
    Path path;
    PathMeasure pathMeasure;
    float curAnimValue;
    boolean next = false;

    public PayAnim(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);

        path = new Path();
        dstPath = new Path();
        path.addCircle(100, 100, 50, Path.Direction.CW);
        path.moveTo(75, 100);
        path.lineTo(100, 125);
        path.lineTo(125, 100 - 50 / 3);

        pathMeasure = new PathMeasure(path, false);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                curAnimValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        if (curAnimValue < 1) {
            float stop = pathMeasure.getLength() * curAnimValue;
            pathMeasure.getSegment(0, stop, dstPath, true);
        } else {
            if (!next) {
                next = true;
                pathMeasure.getSegment(0, pathMeasure.getLength(), dstPath, true);
                pathMeasure.nextContour();
            }
            float stop = pathMeasure.getLength() * (curAnimValue - 1);
            pathMeasure.getSegment(0, stop, dstPath, true);
        }
        canvas.drawPath(dstPath, paint);
    }
}
