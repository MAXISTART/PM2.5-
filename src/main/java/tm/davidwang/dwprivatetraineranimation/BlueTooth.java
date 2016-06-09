package tm.davidwang.dwprivatetraineranimation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;
import java.util.Iterator;
import java.util.Set;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.*;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.*;
import android.bluetooth.*;
import android.content.*;

public class BlueTooth {

    private BluetoothAdapter btadapter;

    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final String NAME = "Bluetooth_Socket";

    private BluetoothSocket clientSocket;
    private BluetoothDevice device;
    private InputStream is;
    private OutputStream os;
    private Activity ac;
    private String resultString = "5.0";  //初始化先
    public String secondMsg = "3.0";
    private String mac = "20:14:10:29:02:81";   //这里是我们要连接设备的那个mac地址


    //下面是海涛的程序
    public static BluetoothSocket bluetoothSocket = null;
    public static Boolean connect_result = false;
    public byte[] buf_data;

    //定义构造方法，获取activity
    public BlueTooth(Activity ac){
        this.ac = ac;
    }



    //测试用的
    public String getSecondMsg(){
        return secondMsg;
    }
    //下面是海涛的连接程序
    public void connect() {
        // 得到Adapter对象
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        // 判断BluetoothAdapter对象是否为空，如果为空，则表明本机没有蓝牙设备
        if (adapter != null) {
            // 调用isEnabled()方法判断当前蓝牙设备是否可用
            if (!adapter.isEnabled()) {
                // 如果蓝牙设备不可用的话,创建一个intent对象,该对象用于启动一个Activity,提示用户启动蓝牙适配器
                Intent intent = new Intent(
                        BluetoothAdapter.ACTION_REQUEST_ENABLE);
                ac.startActivity(intent);
                Toast.makeText(ac, "已启动了蓝牙", Toast.LENGTH_SHORT).show();
            }
            // 得到所有已经配对的蓝牙对象。
            Set<BluetoothDevice> devices = adapter.getBondedDevices();
            Toast.makeText(ac, "获取得设备", Toast.LENGTH_SHORT).show();
            Toast.makeText(ac, "正在连接中设备中,请稍等", Toast.LENGTH_SHORT).show();
            if (devices.size() > 0) {
                Log.i("MainActivity", "test 遍历");
                // 遍历，迭代
                for (Iterator iterator = devices.iterator(); iterator
                        .hasNext();) {
                    Log.i("MainActivity", "test for");
                    BluetoothDevice device = (BluetoothDevice) iterator
                            .next(); // 得到BluetoothDevice对象,也就是说得到配对的蓝牙适配器
                    Log.i("MainActivity", device.getName());
                    // getName()是获取蓝牙名称。
                    // getAddress()方法是本地蓝牙设备的地址
                    Toast.makeText(ac, "正在连接中设备中,请稍等", Toast.LENGTH_SHORT).show();
                    UUID uuid = UUID
                            .fromString("00001101-0000-1000-8000-00805F9B34FB");
                    try {
                        //这里用来判断是否进行连接
                      //  if(device.getAddress().equals(mac))

                        Log.v("MainActivity", "test uuid");
                        bluetoothSocket = device
                                .createRfcommSocketToServiceRecord(uuid);

                        Log.v("MainActivity", "执行到blueSocket执行");
                        bluetoothSocket.connect();

                        Log.v("MainActivity", "connect");

                        Toast.makeText(ac, "你连接上了HC-05", Toast.LENGTH_SHORT).show();
                        connect_result = true;
                        // 表示连接上了远程的蓝牙

                    } catch (IOException e) {
                        Log.v("MainActivity", "有ioexceptiuon");
                    }
                }// for到此为止
            }
        } else {
            resultString = "没有蓝牙";
        }

    }

//    public String inData( ){
//        if (connect_result) {
//            try {
//                is = bluetoothSocket.getInputStream();
//                os = bluetoothSocket.getOutputStream();
//
//                try {
//                    os.write(97);
//                    os.flush();
//                    Log.i("MainActivity", "已发送");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                byte[] receive = new byte[1024];
//                int ii = 0;
//                try{
//                    if ((ii = is.read(receive)) >= 0) {
//                        buf_data = new byte[ii];
//                        for (int i = 0; i < ii; i++) {
//                            buf_data[i] = receive[i];
//                        }
//                    }
//                    resultString  = new String(buf_data);
//                    resultString = resultString.substring(0,5);
//                    Log.i("MainActivity", resultString);
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            Log.i("MainActivity", "你的程序很垃圾，并不能用");
//        }
//        return resultString;
//    }

    public String inData() {
        if (connect_result) {
            try {
                is = bluetoothSocket.getInputStream();
                os = bluetoothSocket.getOutputStream();
                 }catch (Exception e){
                e.printStackTrace();
            }
            byte[] receive = new byte[1024];
            try{
              int i =  is.read(receive);
                resultString = new String(receive);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(is!=null){
                   try{
                    is.close();
                   }catch (Exception e){e.printStackTrace();
                   }
                }
                if(os!=null){
                    try{os.close();}catch (Exception e){e.printStackTrace();}
                }
            }

        }

        return  resultString;
    }

}
