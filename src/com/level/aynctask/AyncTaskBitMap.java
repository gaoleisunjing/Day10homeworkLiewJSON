package com.level.aynctask;

import com.level.downloadsourse.OkhttpParse;
import com.level.listeners.OnLoadBitMapFinish;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class AyncTaskBitMap extends AsyncTask<String, Integer, Bitmap> {

	OnLoadBitMapFinish onLoadBitMapFinish;

	// ���췽��

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
		Log.d("GL", "==========����ͼƬ");

	}

	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		onLoadBitMapFinish.OnLoadBitmap(result);
		// ��Ҫ֪ͨʲô ���ڶ���ӿڵĳ��󷽷�ʱ��ʲô����

	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
