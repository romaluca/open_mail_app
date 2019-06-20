#import "OpenMailAppPlugin.h"

@implementation OpenMailAppPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"com.shuttertop.open_mail_app"
            binaryMessenger:[registrar messenger]];
  OpenMailAppPlugin* instance = [[OpenMailAppPlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"openMailApp" isEqualToString:call.method]) {
    bool ret = [self openMailApp];
    if (ret == false) {
        result([FlutterError errorWithCode:@"UNAVAILABLE"
                                   message:@"Cannot open mail app."
                                   details:nil]);
    } else {
        result(@(ret));
    }
  } else {
    result(FlutterMethodNotImplemented);
  }
}

- (bool)openDefaultMailApp {
    @try {
        NSURL* mailURL = [NSURL URLWithString:@"message://"];
        if ([[UIApplication sharedApplication] canOpenURL:mailURL]) {
            [[UIApplication sharedApplication] openURL:mailURL];
            return true;
        }
        else{
            return false;
        }
    }
    @catch (NSException *exception) {
        NSLog(@"%@", exception.reason);
        return false;
    }
}

@end
