package com.example.ismanusis8;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleShape extends Shape {
    private float diameter;
    private Paint paint;

    public CircleShape() {
        this.diameter = 100 + (float) Math.random() * 200; // Atsitiktinis skersmuo
        this.paint = new Paint();
        this.paint.setColor(Color.BLUE);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, diameter / 2, paint);
    }

    @Override
    public void draw() {

    }

    @Override
    public float getDiameter() {
        return diameter;
    }
}
