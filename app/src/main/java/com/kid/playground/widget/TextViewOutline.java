package com.kid.playground.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;
import android.widget.TextView;

public class TextViewOutline extends androidx.appcompat.widget.AppCompatTextView {
    int outline_color;
    float outline_width; //relative to font size
    Shader shader;

    public TextViewOutline(Context context, int outline_color, float outline_width, Bitmap texture) {
        super(context);
        this.outline_color = outline_color;
        this.outline_width = outline_width;
        shader = new BitmapShader(texture, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw outline
        Paint paint = getPaint();
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(128f);
        int color_tmp = paint.getColor();
        setTextColor(outline_color);
        super.onDraw(canvas);
        //restore
        paint.setShader(shader);
        setTextColor(color_tmp);
        paint.setStyle(Paint.Style.FILL);
        //draw standard text
        super.onDraw(canvas);
    }
}