package com.colormaster.game;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.colormaster.game.views.CustomFontTextView;

public class Toaster {
    private static Toaster mInstance = null;
    private Context context;
    private Toast currentToast;

    private Toaster(Context context) {
        this.context = context;
    }

    public static Toaster init(Context context) {
        if (mInstance == null) {
            mInstance = new Toaster(context);
        } else {
            return mInstance;
        }
        return mInstance;
    }

    public void toast(String message) {
        if (mInstance.currentToast != null) {
            mInstance.currentToast.cancel();
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_toast, null);
        CustomFontTextView text = (CustomFontTextView) layout.findViewById(R.id.toast_text);
        text.setText(message);
        mInstance.currentToast = new Toast(context);
        mInstance.currentToast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 0);
        mInstance.currentToast.setDuration(Toast.LENGTH_SHORT);
        mInstance.currentToast.setView(layout);
        mInstance.currentToast.show();
    }
}