package com.example.todo.Utilities;

import android.app.Dialog;
import android.content.Context;

import com.example.todo.R;

public class ProgressBar extends Dialog {
    public ProgressBar(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.progressbar);
        super.setCancelable(false);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
