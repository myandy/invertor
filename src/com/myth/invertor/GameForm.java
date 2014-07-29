package com.myth.invertor;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameForm extends View
{

    private int[][] color;

    int step = 3;

    private int height;

    private int width;

    public GameForm(Context context, int num)
    {
        super(context);

        init();

    }

    public GameForm(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init()
    {
        color = new int[step][step];
        for (int i = 0; i < step; i++)
        {
            color[i] = new int[step];
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int) (event.getX() / width);
        int y = (int) (event.getY() / height);
        reverseColor(x, y);
        reverseColor(x + 1, y);
        reverseColor(x - 1, y);
        reverseColor(x, y + 1);
        reverseColor(x, y - 1);

        postInvalidate();
        return super.onTouchEvent(event);
    }

    private void reverseColor(int x, int y)
    {
        if (x > 0 && y > 0 && x < step && y < step)
        {
            if (color[x][y] == 0)
            {
                color[x][y] = 1;

            }
            else
            {
                color[x][y] = 0;
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint bgPaint = new Paint();
        bgPaint.setColor(getResources().getColor(R.color.game_yellow));
        canvas.drawRect(0, 0, getWidth(), getWidth(), bgPaint);
        // 线条画笔
        Paint darkPaint = new Paint();
        darkPaint.setColor(getResources().getColor(R.color.game_black));
        Paint hilitePaint = new Paint();
        hilitePaint.setColor(getResources().getColor(R.color.game_blue));

        height = getWidth() / step;

        width = height;

        // 绘制线条
        for (int i = 0; i <= step; i++)
        {
            canvas.drawRect(0, i * height, getWidth(), i * height + 2, darkPaint);

            canvas.drawRect(i * width, 0, i * width + 2, getWidth(), darkPaint);
        }
        // 绘制数字
        Paint numberPaint = new Paint();
        numberPaint.setColor(Color.BLACK);
        numberPaint.setStyle(Paint.Style.STROKE);
        numberPaint.setTextSize(height * 0.75f);
        numberPaint.setTextAlign(Align.CENTER);

        // 调节文字居中
        FontMetrics fMetrics = numberPaint.getFontMetrics();
        float x = width / 2;
        float y = height / 2 - (fMetrics.ascent + fMetrics.descent) / 2;
        for (int i = 0; i < step; i++)
        {
            for (int j = 0; j < step; j++)
            {
                if (color[i][j] != 0)
                {
                    canvas.drawRect(i * width + 2, height * j + 2, i * width + width - 2, height * j + height - 2,
                            hilitePaint);
                }
            }
        }
    }
}
