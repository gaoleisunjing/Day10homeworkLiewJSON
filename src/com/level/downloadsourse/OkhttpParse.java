package com.level.downloadsourse;

import java.io.IOException;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpParse {

	public static String downLoadData(String url) {

		String string = "";
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(url).build();

		try {
			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				string = response.body().string();
				Log.d("gl", "======string" + string);
				return string;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;

	}

	public static byte[] downLoadImage(String url) {

		byte[] bt = null;
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				bt = response.body().bytes();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d("Gl", "=======byte");
		return bt;
	}

}
