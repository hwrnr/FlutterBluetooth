import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_bluetooth/flutter_bluetooth.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  List<String> _bluetoothDevices = new List<String>();

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  Future<void> initPlatformState() async {


  }

  Future<void> bluetoothPaired() async {
    List<String> bluetoothDevices;

    isEnabled();

    bluetoothDevices = await FlutterBluetooth.getPairedDevices();

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _bluetoothDevices = bluetoothDevices;
    });
  }

  Future<void> isEnabled() async{
    print(await FlutterBluetooth.isEnabled());
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
            child: Column( children:[
              RaisedButton(onPressed:(){FlutterBluetooth.turnOn();}),
              RaisedButton(onPressed:(){FlutterBluetooth.turnOff();}),
              RaisedButton(onPressed: (){bluetoothPaired();},),
              Column(children:
                _bluetoothDevices.map((i) => Text(i)).toList()

              )
            ]
            )
        ),
      ),
    );
  }
}
