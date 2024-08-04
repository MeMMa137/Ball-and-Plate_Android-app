package com.example.javaapplication;

import static java.lang.Math.PI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class GameView extends View {
    private Paint paint;
    private float r=50;
    float x, y;
    public GameView(Context context) {
        super(context);
        paint = new Paint();
        x = -1;
        y = -1;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        float w = getWidth(), h = getHeight();
        if(y<0) {
            x=w/2;
            y=h/2;
            System.out.println(x+" "+y);
        }
        canvas.drawLine(0,h/2,w,h/2,paint);
        canvas.drawArc(x-r, y-2*r,
                x+r,y,
                0,
                (float)(360),
                true,paint);
        System.out.println(x);
    }
}

