package com.shuttertop.open_mail_app;

import android.content.Context;
import android.content.Intent;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** OpenMailAppPlugin */
public class OpenMailAppPlugin implements MethodCallHandler {
  private final Context context;

  private OpenMailAppPlugin(Context context) {
    this.context = context;
  }

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "com.shuttertop.open_mail_app");
    channel.setMethodCallHandler(new OpenMailAppPlugin(registrar.context()));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("openMailApp")) {
      boolean ret = openDefaultMailApp();

      if (ret) {
        result.success(true);
      } else {
        result.error("UNAVAILABLE", "Cannot open mail app.", null);
      }
    }
    else {
      result.notImplemented();
    }
  }

  private boolean openDefaultMailApp() {
    try {
      Intent intent = new Intent(Intent.ACTION_MAIN);
      intent.addCategory(Intent.CATEGORY_APP_EMAIL);
      context.startActivity(intent);
      return true;
    }
    catch (Exception ex){
      return false;
    }
  }
}
