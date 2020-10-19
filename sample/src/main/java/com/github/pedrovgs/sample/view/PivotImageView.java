package com.github.pedrovgs.sample.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class PivotImageView extends AppCompatImageView {
    public PivotImageView(Context context) {
        super(context);
        init();
    }

    public PivotImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PivotImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint paint;

    public void init() {
        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(20f);
        paint.setAntiAlias(true);
    }

   float[] pivots = new float[2];

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(this.getPivotX(), 0, 0, this.getPivotY(), paint);
        canvas.drawPoint(this.getPivotX(), this.getPivotY(), paint);
        this.postInvalidate();
    }
}
