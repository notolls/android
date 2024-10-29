package com.example.ismanusis8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class ShapeView extends View {
    private Shape shape;
    private Paint circlePaint;
    private float circleX, circleY, circleRadius;

    public ShapeView(Context context, Shape shape) {
        super(context);
        this.shape = shape;
        circlePaint = new Paint();
        circlePaint.setColor(Color.GREEN);
    }

    public void setShape(Shape shape) {
        this.shape = shape;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (shape != null) {
            shape.draw(canvas);
        }
        if (circleRadius > 0) {
            canvas.drawCircle(circleX, circleY, circleRadius, circlePaint);
        }
    }

    public void drawCircle(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            circleX = (event.getX(0) + event.getX(1)) / 2;
            circleY = (event.getY(0) + event.getY(1)) / 2;
            circleRadius = calculateDistance(event) / 2;
            invalidate();
        }
    }

    public boolean checkIfFits(float diameter) {
        return diameter >= shape.getDiameter();
    }

    private float calculateDistance(MotionEvent event) {
        float x1 = event.getX(0);
        float y1 = event.getY(0);
        float x2 = event.getX(1);
        float y2 = event.getY(1);
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
