import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:open_mail_app/open_mail_app.dart';

void main() {
  const MethodChannel channel = MethodChannel('com.shuttertop.open_mail_app');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return true;
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('openMailApp', () async {
    expect(await OpenMailApp.openMailApp(), true);
  });
}
