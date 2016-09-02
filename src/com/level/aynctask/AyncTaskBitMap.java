package com.level.aynctask;

import com.level.downloadsourse.OkhttpParse;
import com.level.listeners.OnLoadBitMapFinish;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class AyncTaskBitMap extends AsyncTask<String, Integer, Bitmap> {

	OnLoadBitMapFinish onLoadBitMapFinish;

	// 构造方法

	public AyncTaskBitMap(OnLoadBitMapFinish onLoadBitMapFinish) {
		super();
		this.onLoadBitMapFinish = onLoadBitMapFinish;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		// TODO Auto-generated method stub

		byte[] bt = OkhttpParse.downLoadImage(params[0]);

		return BitmapFactory.decodeByteArray(bt, 0, bt.length);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		Log.d("GL", "==========加载图片");

	}

	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		onLoadBitMapFinish.OnLoadBitmap(result);
		// 我要通知什么 就在定义接口的抽象方法时传什么参数

	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
