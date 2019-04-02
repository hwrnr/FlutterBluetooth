import 'dart:async';

import 'package:flutter/services.dart';

class FlutterBluetooth {
  static const MethodChannel _channel =
      const MethodChannel('flutter_bluetooth');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
  
  static void turnOn(){
    _channel.invokeMethod('turnOn');
  }

  static void turnOff(){
    _channel.invokeMethod('turnOff');
  }

  static Future<List<String>> getPairedDevices() async {
    final List<String> devices = (await _channel.invokeMethod('getPairedDevices')).cast<String>();
    return devices;
  }

  static Future<bool> isEnabled() async {
    final bool isEnabled = (await _channel.invokeMethod('isEnabled')) as bool;
    return isEnabled;
  }

}
