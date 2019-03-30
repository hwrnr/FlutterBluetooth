package hawerner.flutter_bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.os.Build;

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

  @TargetApi(Build.VERSION_CODES.ECLAIR)
  private static void turnOn()
  {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if(!mBluetoothAdapter.isEnabled())
    {
      mBluetoothAdapter.enable();
    }
  }

  @TargetApi(Build.VERSION_CODES.ECLAIR)
  private static void turnOff()
  {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if(mBluetoothAdapter.isEnabled())
    {
      mBluetoothAdapter.disable();
    }
  }


  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }
    else if (call.method.equals("turnOn")){
      turnOn();
      result.success(null);
    }
    else if (call.method.equals("turnOff")){
      turnOff();
      result.success(null);
    }


    else {
      result.notImplemented();
    }
  }
}
