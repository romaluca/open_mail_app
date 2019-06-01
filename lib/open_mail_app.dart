import 'dart:async';

import 'package:flutter/services.dart';

class OpenMailApp {
  static const MethodChannel _channel =
      const MethodChannel('com.shuttertop.open_mail_app');

  static Future<bool> openMailApp() async {
    try {
      return await _channel.invokeMethod('openMailApp');
    } on PlatformException catch (e) {
      print("Error en openDefaultMailApp: " + e.message);
    }
    return false;
  }
}
