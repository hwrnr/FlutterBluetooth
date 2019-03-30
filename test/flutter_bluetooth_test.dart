import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_bluetooth/flutter_bluetooth.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_bluetooth');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await FlutterBluetooth.platformVersion, '42');
  });
}
