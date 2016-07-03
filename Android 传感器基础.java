Android中传感器的基本概念 24:11

Android平台支持的丰富的传感器是其亮点之一，虽然相比iPhone来说稍有逊色，但相对于原来占据智能市场的Synbian等手机平台有一个明显的飞跃，现在看到的旅游出行必备的指南针，
甩一甩就显示火苗的模拟打火机都是基于Android内置的传感器。本课时介绍Android中传感器的一些基本概念。



传感器种类

	传感器是第二代智能手机的重要标志之一。可以毫不客气地说，现在市 面上的Android手机和平板电脑（TV除外）都内置了传感器。否则很多游戏 和应用就无法使用了。Android SDK 支持的传感器并
是每一部Android设备都支持所有的传感器。大多数Android设备只支持一部分传感器。例如， 方向传感器（电子罗盘）、重力传感器（屏幕翻转、赛车游戏等）。
 
   动作（Motion）传感器
 环境（Environmental）传感器
 位置（Position）传感器


1.动作传感器

	这类传感器在三个轴（X、Y、Z）上测量加速度和旋转角度。包括如下几个传感器。
 
		加速（accelerometer）传感器
		重力（gravity）传感器
		陀螺仪（gyroscope）传感器
		旋转向量（rotational vector ）传感器


2.环境传感器

	这类传感器可以测量不同环境的参数，例如，周围环境的空气温度和压 强、光照强度和湿度。包括如下几个传感器。

		湿度（barometer）传感器
		光线（photometer）传感器
		温度（thermometer）传感器


3.位置传感器
	
	这类传感器可以测量设备的物理位置。包括如下几个传感器。
	
		方向（orientation）传感器
		磁力（magnetometer）传感器



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


Android Sensor Framework

	Android SDK提供了Android sensor framework，可以用来访问当前 Android设备内置的传感器。ASF提供了很多类和接口，可以帮助我们完成 各种与传感器有关的任务。例如，可以利用ASF完成下面的工作。


	确定当前Android设备内置了哪些传感器。  
	确定某一个传感器的技术指标。例如，该传感器能测量的最大范围、传感器的制造商、对电量的要求、测量的精度等。  
	获取传感器传回来的数据，以及定义传感器回传数据的精度。  
	注册和注销传感器事件监听器，这些监听器用于监听传感器的变化，通常从传感器回传的数据需要利用这些监听器完成。


硬件传感器和软件传感器
	
		ASF 允许我们访问很多传感器类型，这些传感器有一些是基于硬件的传感器，还有一些是基于软件的传感器。基于硬件的传感器就是直接以芯片形 式嵌入到Android设备中，这些传感器直接从外部环境
	获取数据，例如，加 速传感器、磁场传感器都属于硬件传感器。基于软件的传感器并不是实际的 硬件芯片，尽管这些传感器重使用上很像基于硬件的传感器。基于软件的传感器传回的数据本质上也来自于
	基于硬件的传感器，只是这些数据通常会经 过二次加工，也就是说，基于软件的传感器传回的数据可能来自一个或多个 基于硬件的传感器，并且有可能Android系统使用某些算法处理了这些数据。所以基于
	软件的传感器也可以称为虚拟（virtual）传感器或合成（synthetic） 传感器。



ASF支持的传感器

	所有的常量都在Sensor类中定义。

		1. TYPE_ACCELEROMETER：加速传感器（硬件传感器）
		2. TYPE_AMBIENT_TEMPERATURE：温度传感器（硬件传感器）
		3. TYPE_GRAVITY：重力传感器（硬件或软件传感器）
		4. TYPE_GYROSCOPE：陀螺仪传感器（硬件传感器）
		5. TYPE_LIGHT：光线传感器（硬件传感器）
		6. TYPE_LINEAR_ACCELERATION：线性加速传感器（硬件或软件传感器）
		7. TYPE_MAGNETIC_FIELD：磁场传感器（硬件传感器）
		8. TYPE_ORIENTATION：方向传感器（软件传感器），数据来自重力和磁场传感器
		9. TYPE_PRESSURE：压力传感器（硬件传感器）
		10.TYPE_PROXIMITY：临近传感器（硬件传感器）
		11.TYPE_RELATIVE_HUMIDITY：湿度传感器（硬件传感器）
		12.TYPE_ROTATION_VECTOR：旋转向量传感器（硬件或软件传感器）
		13. TYPE_TEMPERATURE：温度传感器（硬件传感器），从 Android4.0（API Level = 14）开始被 TYPE_AMBIENT_TEMPERATURE 取代。

/**
 
 */

ASF中的主要类和接口

 	SensorManager类：用于创建sensor service的实例。该类提供了很多用于访问和枚举传感器，注册和注销传感器监听器的方法。而且还提供了与传感器精度、扫描频率、校正有关的常量。

 	Sensor类：提供了一些用于获取传感器技术参数的方法。如版本、类型、生产商等。

 	SensorEvent类：系统使用该类创建传感器事件对象。该对象可以提供与传感器事件有关的信息。传感器事件对象包括的信息有原始的传感器回传数据、传感器类型、数据的精度以及触发事件的时间。

 	SensorEventListener接口：该接口包含两个回调方法，当传感器的回传值或精度发生变化时，系统会调用这两个回调方法。

/**
 
 */


    private TextView tvSensors;
    private SensorManager sensorManager;


        tvSensors = (TextView) findViewById(R.id.tvSensors);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor: sensors)
        {
            tvSensors.append(sensor.getName() + "\n");
        }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


动作传感器的组成及使用方法 35:40

Android平台支持一些用于监视设备动作的传感器（这样的传感器共有5个），其中两个（加速传感器和陀螺仪传感器）是纯的硬件传感器。本课时介绍动作传感器的组成及使用方法。




1.动作传感器

	Android 平台支持一些用于监视设备动作的传感器（这样的传感器共有5 个）。其中两个（加速传感器和陀螺仪传感器）是纯的硬件传感器。另外三 个（重力传感器、线性加速传感器和旋转向量传感器）
可能是硬件传感器， 也可能是软件传感器。例如，在一些Android设备中，这些基于软件的传感 器会从加速和磁力传感器中获取数据，但在另一些Android设备中也可能从 陀螺仪传感器中获取数据。也就是说，
同一种基于软件的传感器在不同的 Android 设备中回传的数据可能来自不同的硬件传感器。所以基于软件的同 一种传感器在不同的设备中可能精确度、使用范围有所不同。大多数高端 Android设备都会有加
速传感器，还有一些拥有陀螺仪传感器。



2.动作传感器的作用

	动作传感器对于监测设备的移动非常有用，例如，倾斜、震动、旋转和 摆动都属于动作传感器的监测范围。设备的移动通常是对用户输入的直接反应。例如，用户正在游戏中飙车，或控制游戏中的一个小球）。
除此之外，设备所处的物理环境也会反应在设备的动作上，例如，用户正常驾驶汽车，而这是Android设备正安静地躺在旁边的座椅上，尽管设备没有移动，但会 随着车的行驶而不断震动，而且设备也会随着汽车
的移动而移动。 对于第一种情况，可以对设备本身的相对位置进行监测。而对于第二种情况，需要考虑到设备以外的参照系。动作传感器本身一般并不会用于监测 设备的位置，关于设备的位置需要用其他类型的
传感器进行监测，例如，磁场传感器。



3.动作传感器的回传数据

	所有的动作传感器都会返回三个浮点数的值（通过长度为3的数组返回）， 但对于不同的传感器，这三个只的意义不同。例如，对于加速传感器，会返 回三个坐标轴的数据。对于陀螺仪传感器，会返回三个
坐标轴的旋转角速度。



4.加速传感器

	设备从左到右推动，X轴 加速度为正值。
	设备朝着自己推动，Y轴 加速度为正值。
	如果朝着天空以 A m/s^2 的加速度推动，那么Z轴的加速度为 A + 9.81， 所以如果计算实际的加速度（抵消重力加速度），需要减 9.81。






import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MotionSensorActivity extends Activity implements
		SensorEventListener {

	private TextView tvAccelerometer;
	private SensorManager mSensorManager;//传感器对象
	private float[] gravity = new float[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_motion_sensor);

		tvAccelerometer = (TextView) findViewById(R.id.tvAccelerometer);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER: // 加速度传感器
			final float alpha = (float) 0.8;
			gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
			gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
			gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

			String accelerometer = "加速度\n" + "X:"
					+ (event.values[0] - gravity[0]) + "\n" + "Y:"
					+ (event.values[1] - gravity[1]) + "\n" + "Z:"
					+ (event.values[2] - gravity[2]);
			Log.d("z", String.valueOf(event.values[2] - gravity[2]));//因为地球有重力加速度，所以减去才是我们用到的值
			tvAccelerometer.setText(accelerometer);
			
			// 9.81m/s^2
			break;
		case Sensor.TYPE_GRAVITY:
			gravity[0] = event.values[0];
			gravity[1] = event.values[1];
			gravity[2] = event.values[2];
			break;
		case Sensor.TYPE_PROXIMITY:
			setTitle(String.valueOf(event.values[0]));
			break;
		default:
			break;
		}

	}

	protected void onResume() {
		super.onResume();
		//第一个参数是实现前面接口的对象实例，第二个是注册哪一个传感器，第三个是采样率方式，SENSOR开头
		
		//加速度传感器
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
		//重力传感器，最快方式获取
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
				SensorManager.SENSOR_DELAY_FASTEST);
		//临近传感器
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		mSensorManager.unregisterListener(this);//卸载传感器，当窗口关闭时，不再接获传感器的数据

	}

}




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


传感器的类的结构：
1、包：android.hardware
2、SensorManager类：传感器的服务类
3、实例化：
    1、Context.getSystemServiece（String name）
    方法：
    1、Sensor getDefaultSensor(int type)
    根据传感器的类型获取具体的传感器对象

    type：传感器的类型
    值：Sensor.TYPE_ACCELEROMETER

	2、registerListener(SensorEventListener listener,
				Sensor sensor,
				int rate);
		listener:传感器事件的监听事件
		sensor:具体的传感器
		rate:数据延误的具体时间
			SENSOR_DELAY_FASTEST：延迟时间0微妙
			SENSOR_DELAY_GAME：延迟时间20000微妙
			SENSOR_DELAY_UI：延迟的时间66667微妙
			SENSOR_DELAY_NORMAL：延迟的时间200000微妙

	3、取消注册
		unregisterListener(SensorEventListener listener,
				Sensor sensor);
			listener:传感器事件的监听事件
			sensor:具体的传感器



















































