package com.example.project_9_2_photoshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    ImageButton i1, i2, i3, i4, i5, i6, i7, i8, i9;
    MyGraphicView graphicView;
    static float scaleX=1, scaleY=1, angle=0, color=1, satu=1, sa=1, ta=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니 포토샵");

        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        graphicView = (MyGraphicView) new MyGraphicView(this);
        pictureLayout.addView(graphicView);
        clickIcons();
    }

    private void clickIcons() {
        i1 = (ImageButton) findViewById(R.id.i1);
        i1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                graphicView.invalidate();
            }
        });

        i2 = (ImageButton) findViewById(R.id.i2);
        i2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                graphicView.invalidate();
            }
        });

        i3 = (ImageButton) findViewById(R.id.i3);
        i3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                angle = angle + 20;
                graphicView.invalidate();
            }
        });

        i4 = (ImageButton) findViewById(R.id.i4);
        i4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                color = color + 0.2f;
                graphicView.invalidate();
            }
        });

        i5 = (ImageButton) findViewById(R.id.i5);
        i5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                color = color - 0.2f;
                graphicView.invalidate();
            }
        });

        i6 = (ImageButton) findViewById(R.id.i6);
        i6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (satu == 0) satu = 1;
                else satu = 0;
                graphicView.invalidate();
            }
        });

        i7 = (ImageButton) findViewById(R.id.i7);
        i7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (sa == 0) sa = 1;
                else sa = 0;
                graphicView.invalidate();
            }
        });

        i8 = (ImageButton) findViewById(R.id.i8);
        i8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ta = ta + 0.1f;
                graphicView.invalidate();
            }
        });

        i9 = (ImageButton) findViewById(R.id.i9);
        i9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ta = ta - 0.1f;
                graphicView.invalidate();
            }
        });
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.rotate(angle, cenX, cenY);
            Paint paint = new Paint();
            float[] array = { color , 0 , 0 , 0 , 0 ,
                              0 , color , 0 , 0 , 0 ,
                              0 , 0 , color , 0 , 0 ,
                              0 , 0 , 0 , 1 , 0 };
            ColorMatrix cm = new ColorMatrix(array);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            BlurMaskFilter bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
            if (satu == 0) paint.setMaskFilter(bMask);

            EmbossMaskFilter eMask = new EmbossMaskFilter(new float[] {3,3,3}, 0.5f, 5, 10);
            if (sa == 0) paint.setMaskFilter(eMask);

            cm.setSaturation(ta);

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.ca);

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, paint);

            picture.recycle();

        }
    }
}