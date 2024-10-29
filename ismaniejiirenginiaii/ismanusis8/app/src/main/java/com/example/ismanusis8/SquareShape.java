package com.example.ismanusis8;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SquareShape extends Shape {
    private float sideLength;
    private Paint paint;

    public SquareShape() {
        this.sideLength = 100 + (float) Math.random() * 200; // Atsitiktinis kraštinės ilgis
        this.paint = new Paint();
        this.paint.setColor(Color.RED);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(canvas.getWidth() / 2 - sideLength / 2,
                canvas.getHeight() / 2 - sideLength / 2,
                canvas.getWidth() / 2 + sideLength / 2,
                canvas.getHeight() / 2 + sideLength / 2, paint);
    }

    @Override
    public void draw() {

    }

    @Override
    public float getDiameter() {
        return (float) Math.sqrt(2 * Math.pow(sideLength, 2)); // Kvadrato įstrižainė
    }
}
