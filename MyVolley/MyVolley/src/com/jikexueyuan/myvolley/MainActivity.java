package com.jikexueyuan.myvolley;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * 
 * Volley是Android平台网络通信库：更快。更简单。更健壮 volley提供的功能： 1.JSON、图片（异步） 2.网络请求的排序
 * 3.网络请求的优先级处理 4.缓存 5.多级别的取消请求 6.与Activity生命周期联动
 * 
 * 
 * 获取Volley git clone
 * https://android.googlesource.com/platform/frameworks/volley
 * 
 * 
 * 
 * 
 * 
 */

public class MainActivity extends Activity {

	private ImageView iv1;
	private NetworkImageView iv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		getJSONVolley();
	}

	public void init() {
		iv1 = (ImageView) findViewById(R.id.iv);
		iv2 = (NetworkImageView) findViewById(R.id.imageView1);
		loadImageVolley();
		NetWorkImageViewVolley();
	}

	// 获取json字符串
	public void getJSONVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		String JSONDateUrl = "http://www.wwtliu.com/jsondata.html";
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, JSONDateUrl, null,
				new Response.Listener<JSONObject>() {
					public void onResponse(JSONObject response) {
						System.out.println("response=" + response);
					}
				}, new Response.ErrorListener() {
					public void onErrorResponse(
							com.android.volley.VolleyError arg0) {
						System.out.println("对不起，有问题");
					}
				});
		requestQueue.add(jsonObjectRequest);
	}

	// http://localhost/lesson-img.png
	public void loadImageVolley() {
		String imageurl = "http://10.0.0.52/lesson-img.png";
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		final LruCache<String, Bitmap> lurcache = new LruCache<String, Bitmap>(
				20);
		ImageCache imageCache = new ImageCache() {

			@Override
			public void putBitmap(String key, Bitmap value) {
				lurcache.put(key, value);
			}

			@Override
			public Bitmap getBitmap(String key) {

				return lurcache.get(key);
			}
		};
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		ImageListener listener = imageLoader.getImageListener(iv1,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(imageurl, listener);
	}
	
	public void NetWorkImageViewVolley(){
		String imageUrl = "http://10.0.0.52/lesson-img.png";
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
		ImageCache imageCache = new ImageCache() {
			
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}
			
			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		iv2.setTag("url");
		iv2.setImageUrl(imageUrl, imageLoader);
	}
}
