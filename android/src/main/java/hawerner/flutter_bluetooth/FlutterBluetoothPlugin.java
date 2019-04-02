package hawerner.flutter_bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterBluetoothPlugin */
public class FlutterBluetoothPlugin implements MethodCallHandler {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_bluetooth");
    channel.setMethodCallHandler(new FlutterBluetoothPlugin());
  }

  private static void turnOn()
  {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if(mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled())
    {
      mBluetoothAdapter.enable();
    }
  }

  private static void turnOff()
  {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if(mBluetoothAdapter != null && mBluetoothAdapter.isEnabled())
    {
      mBluetoothAdapter.disable();
    }
  }

  private static List<String> getPairedDevices(){
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> bluetoothDevices;
    if (mBluetoothAdapter != null) {
      bluetoothDevices = mBluetoothAdapter.getBondedDevices();
      List<String> arrayOfDevices = new ArrayList<>(bluetoothDevices.size());

      for(BluetoothDevice device : bluetoothDevices){
        arrayOfDevices.add(device.getAddress());
      }

      return arrayOfDevices;
    }
    return null;
  }

  private static Boolean isEnabled(){
      try {
          Log.i("Bluetooth", String.valueOf(BluetoothAdapter.getDefaultAdapter().isEnabled()));
          return BluetoothAdapter.getDefaultAdapter().isEnabled();
      }
      catch (NullPointerException e){ //bluetooth isn't available
          Log.i("Bluetooth","Null pointer exception");
          return false;
      }
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    switch (call.method) {
      case "getPlatformVersion":
        result.success("Android " + Build.VERSION.RELEASE);
        break;
      case "turnOn":
        turnOn();
        result.success(null);
        break;
      case "turnOff":
        turnOff();
        result.success(null);
        break;
      case "getPairedDevices":
        result.success(getPairedDevices());
        break;
      case "isEnabled":
        Log.i("Bluetooth", "Checkpoint");
        result.success(isEnabled());
        break;

      default:
        result.notImplemented();
        break;
    }
  }
}
