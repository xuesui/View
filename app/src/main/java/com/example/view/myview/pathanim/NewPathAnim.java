package com.example.view.myview.pathanim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.view.R;

public class NewPathAnim extends View {
    private Paint paint;
    private Path dstPath;
    private Path path;
    private PathMeasure pathMeasure;
    private float curAnimValue;
    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private Bitmap bitmap;

    public NewPathAnim(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);

        path = new Path();
        dstPath = new Path();
        path.addCircle(100, 100, 50, Path.Direction.CW);

        pathMeasure = new PathMeasure(path, true);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                curAnimValue = (float) valueAnimator.getAnimatedValue();
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
        float stop = pathMeasure.getLength() * curAnimValue;
        float start = (float) (stop - ((0.5 - Math.abs(curAnimValue - 0.5)) * pathMeasure.getLength()));
        dstPath.reset();
        pathMeasure.getSegment(start, stop, dstPath, true);
        canvas.drawPath(dstPath, paint);

        pathMeasure.getPosTan(stop, pos, tan);
        float degree = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        Matrix matrix = new Matrix();
        matrix.postRotate(degree, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, matrix, paint);
    }
}
