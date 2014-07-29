package com.myth.invertor;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class MainActivity extends Activity
{

    GameForm gameForm;

    private FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (FrameLayout) findViewById(R.id.layout);
        gameForm = new GameForm(this, 3);
        gameForm.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        layout.addView(gameForm);

    }

}
