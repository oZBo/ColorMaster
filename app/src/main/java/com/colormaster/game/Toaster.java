package com.colormaster.game;

import android.content.Context;
import android.widget.Toast;

public class Toaster {
   private static Toaster mInstance = null;
   private Context context;
   private Toast currentToast;

   private Toaster(Context context) {
      this.context = context;
   }

   public static void init(Context context) {
      mInstance = new Toaster(context);
   }

   public static void toast(String message){
      if (mInstance.currentToast != null){
          mInstance.currentToast.cancel();
      }
      mInstance.currentToast = Toast.makeText(mInstance.context, message, Toast.LENGTH_SHORT);
      mInstance.currentToast.show();
   }
}