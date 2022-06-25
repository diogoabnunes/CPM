package org.feup.apm.touch2dcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class TouchView extends View implements View.OnTouchListener {
  float x, y;
  Paint paint = new Paint();

  public TouchView(Context context) {
    super(context);
    paint.setColor(Color.YELLOW);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(4.0f);
    paint.setAntiAlias(true);
    setOnTouchListener(this);
  }

  public boolean onTouch(View view, MotionEvent me) {
    x = me.getX();
    y = me.getY();
    invalidate();
    return true;
  }

  public void changeColor(int newColor) {
    paint.setColor(newColor);
    invalidate();
  }

  @Override
  public void onDraw(Canvas canvas) {
    canvas.drawCircle(x, y, 20, paint);
  }
}