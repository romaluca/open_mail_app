package com.shuttertop.open_mail_app;

import android.content.Context;
import android.content.Intent;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class OpenMailAppPlugin implements MethodCallHandler {
  private final Context context;

  private OpenMailAppPlugin(Context context) {
    this.context = context;
  }

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "com.shuttertop.open_mail_app");
    channel.setMethodCallHandler(new OpenMailAppPlugin(registrar.context()));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("openMailApp")) {
      openMailApp(result);
    }
    else {
      result.notImplemented();
    }
  }

  private void openMailApp(Result result) {
    try {
      Intent intent = new Intent(Intent.ACTION_MAIN);
      intent.addCategory(Intent.CATEGORY_APP_EMAIL);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(intent);
      result.success(true);
    }
    catch (Exception ex){
      result.error("UNAVAILABLE", "Cannot open mail app: " + ex.getMessage(), ex);
    }
  }
}
