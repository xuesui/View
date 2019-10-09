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

public class PathAnim extends View {
    Paint paint;
    Path dstPath;
    Path path;
    PathMeasure pathMeasure;
    float curAnimValue;

    public PathAnim(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);

        path=new Path();
        dstPath=new Path();
        path.addCircle(100,100,50,Path.Direction.CW);

        pathMeasure=new PathMeasure(path,true);

        ValueAnimator animator=ValueAnimator.ofFloat(0,1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                curAnimValue= (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        float stop=pathMeasure.getLength() * curAnimValue;
        float start=(float)(stop - ((0.5-Math.abs(curAnimValue-0.5))*pathMeasure.getLength()));
        dstPath.reset();
        pathMeasure.getSegment(start,stop,dstPath,true);
        canvas.drawPath(dstPath,paint);
    }

}
