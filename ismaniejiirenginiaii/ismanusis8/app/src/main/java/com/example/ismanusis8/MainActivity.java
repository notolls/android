package com.example.ismanusis8;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private ShapeView shapeView;
    private ArrayList<Shape> shapes;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);
        random = new Random();

        // Sukurti figūras
        shapes = new ArrayList<>();
        shapes.add(new CircleShape());
        shapes.add(new SquareShape());
        // Galite pridėti daugiau figūrų

        shapeView = new ShapeView(this, getRandomShape());
        frameLayout.addView(shapeView);

        shapeView.setOnTouchListener(new View.OnTouchListener() {
            private float initialDistance = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getPointerCount() == 1) {
                    // Vieno piršto paspaudimas - pakeisti figūrą
                    shapeView.setShape(getRandomShape());
                } else if (event.getPointerCount() == 2) {
                    // Dviejų pirštų paspaudimas - nupiešti apskritimą
                    if (event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
                        initialDistance = calculateDistance(event);
                        shapeView.drawCircle(event);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Tikrinimas ar figūra telpa į apskritimą
                    boolean fits = shapeView.checkIfFits(initialDistance);
                    playSound(fits);
                }
                return true;
            }
        });
    }

    private Shape getRandomShape() {
        return shapes.get(random.nextInt(shapes.size()));
    }

    private float calculateDistance(MotionEvent event) {
        float x1 = event.getX(0);
        float y1 = event.getY(0);
        float x2 = event.getX(1);
        float y2 = event.getY(1);
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private void playSound(boolean fits) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, fits ? R.raw.success_sound : R.raw.fail_sound);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mp -> mp.release());
    }
}
