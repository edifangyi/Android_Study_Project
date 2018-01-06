package com.mellow.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.mellow.data.QueryData;
import com.mellow.interfas.SocketRequest;
import com.mellow.tool.LogSwitch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;

public class SocketTCP {
    private final String TAG;
    private final Context context;
    // 变量
    private String address;
    private Socket socket;
    public boolean isConnected;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ExecutorService cachedThreadPool;
    // 回调
    private SocketRequest socketRequest;

    // 常量
    public final int Conn_CreateSucs = 0x00;
    public final int Conn_TimeOut = 0x01;
    public final int Conn_CreateFail = 0x02;
    public final int Conn_ReceiveDone = 0x03;
    public final int Conn_ServerClosed = 0x04;
    public final int Conn_LocalClosed = 0x05;
    public final int Error_NumberFormat = 0xFF01;
    public final int Error_UnknownHost = 0xFF02;
    public final int Error_IO = 0xFF03;
    public final int Error_NullPointer = 0xFF04;

    /**
     * @param context 传入上下文
     */
    public SocketTCP(Context context) {
        this.TAG = getClass().getSimpleName();
        this.context = context;
        this.cachedThreadPool = Executors.newCachedThreadPool();
        if (android.os.Build.VERSION.SDK_INT > 8) {
            Builder builder = new StrictMode.ThreadPolicy.Builder();
            builder.detectDiskReads();
            builder.detectDiskWrites();
            builder.detectNetwork();
            StrictMode.setThreadPolicy(builder.build());
            android.os.StrictMode.VmPolicy.Builder VMBuilder = new StrictMode.VmPolicy.Builder();
            VMBuilder.detectLeakedSqlLiteObjects();
            VMBuilder.penaltyDeath();
            StrictMode.setVmPolicy(VMBuilder.build());
        }
    }

    /**
     * @param address       服务器地址和端口
     * @param socketRequest 回调接口
     */
    public void initConnection(String address, SocketRequest socketRequest) {
        this.address = address;
        this.socketRequest = socketRequest;
        if (socket != null) {
            // 关闭连接
            closeConnection();
        }
        // 线程池里创建连接
        cachedThreadPool.execute(runnableSocket);
        int amount = ((ThreadPoolExecutor) cachedThreadPool).getActiveCount();
        LogSwitch.d(TAG, "initConnection", "Now the thread amount = " + amount);
    }

    // 创建Socket连接
    private Runnable runnableSocket = new Runnable() {
        @Override
        public void run() {
            String[] ads = address.split(":");
            boolean result = false;
            try {
                String[] ports = new QueryData(context).getPortInfo(null);
                boolean hasLocal = ports != null && ports[0] != null;
                if (hasLocal) {
                    String port = ports[0].replaceAll("[^0-9]", "");
                    if (port.length() > 0 && !port.equals("0")) {
                        int localPort = Integer.parseInt(port);
                        socket = new Socket();
                        socket.setReuseAddress(true);
                        socket.bind(new InetSocketAddress(localPort));
                        socket.connect(new InetSocketAddress(ads[0], Integer.parseInt(ads[1])));
                    }
                } else {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ads[0], Integer.parseInt(ads[1])));
                }
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                handler.sendEmptyMessage(Conn_CreateSucs);
                isConnected = true;
                result = true;
                byte[] buffer = new byte[8192];
                byte[] buff = null;
                while (inputStream != null) {
                    int length = inputStream.read(buffer);
                    if (length <= 0) {
                        // 服务器已断开
                        handler.sendEmptyMessage(Conn_ServerClosed);
                        // 置空数据
                        buffer = null;
                        buff = null;
                        // 关闭连接
                        closeConnection();
                        return;
                    } else {
                        buff = new byte[length];
                        System.arraycopy(buffer, 0, buff, 0, length);
                        Message msg = new Message();
                        msg.what = Conn_ReceiveDone;
                        msg.obj = buff;
                        handler.sendMessage(msg);
                    }
                }
            } catch (NumberFormatException e) {
                LogSwitch.e(TAG, "initConnection", "NumberFormatException", e);
            } catch (UnknownHostException e) {
                LogSwitch.e(TAG, "initConnection", "UnknownHostException", e);
            } catch (IOException e) {
                LogSwitch.e(TAG, "initConnection", "IOException", e);
            } catch (NullPointerException e) {
                LogSwitch.e(TAG, "initConnection", "NullPointerException", e);
            } catch (IllegalArgumentException e) {
                LogSwitch.e(TAG, "initConnection", "IllegalArgumentException", e);
            }
            if (!result) {
                handler.sendEmptyMessage(Conn_CreateFail);
            } else {
                handler.sendEmptyMessage(Conn_ServerClosed);
            }
            isConnected = false;
        }
    };

    /**
     * @param msg 需要发送的自己数组
     * @return 返回发送结果
     */
    public boolean sendBytes(byte[] buffer) {
        try {
            outputStream.write(buffer);
            outputStream.flush();
            return true;
        } catch (IOException e) {
            LogSwitch.e(TAG, "sendBytes", "IOException", e);
        } catch (NullPointerException e) {
            LogSwitch.e(TAG, "sendBytes", "NullPointerException", e);
        }
        isConnected = false;
        return false;
    }

    /**
     * 关闭连接
     */
    public void closeConnection() {
        // 已从本地关闭连接
        isConnected = false;
        handler.sendEmptyMessage(Conn_LocalClosed);
        if (socket != null) {
            try {
                socket.close();
                socket = null;
            } catch (IOException e) {
                LogSwitch.e(TAG, "closeConnection", "socket", e);
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
                inputStream = null;
            } catch (IOException e) {
                LogSwitch.e(TAG, "closeConnection", "inputStream", e);
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
                outputStream = null;
            } catch (IOException e) {
                LogSwitch.e(TAG, "closeConnection", "outputStream", e);
            }
        }
        // 置空回调接口
        socketRequest = null;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (socketRequest != null) {
                socketRequest.result(msg.what, (byte[]) msg.obj);
            }
            super.handleMessage(msg);
        }
    };
}
