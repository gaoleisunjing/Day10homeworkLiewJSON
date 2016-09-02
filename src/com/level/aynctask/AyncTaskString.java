package com.level.aynctask;

import java.util.List;
import com.google.gson.Gson;
import com.level.bean.New4;
import com.level.bean.News;
import com.level.bean.News1;
import com.level.downloadsourse.OkhttpParse;
import com.level.listeners.OnLoadStringFinish;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AyncTaskString extends AsyncTask<String, Integer, List<News1>> {

	Context context;// 为什么要穿上下文进来
	List<News1> listNews;
	ProgressDialog dialog;
	OnLoadStringFinish onLoadStringFinish;

	public AyncTaskString(Context context) {
		super();
		this.context = context;
		if (context instanceof OnLoadStringFinish) {
			onLoadStringFinish = (OnLoadStringFinish) context;

		}
	}

	@Override
	protected List<News1> doInBackground(String... params) {
		// TODO Auto-generated method stub

		String string = OkhttpParse.downLoadData(params[0]);
		Gson gson = new Gson();
		// Type type = new TypeToken<List<News>>(){}.getType();
		// listNews = gson.fromJson(string,
		// new TypeToken<List<News>>() {
		// }.getType());
		News news = gson.fromJson(string, News.class);
		listNews = ((New4) news.getParamz()).getFeeds();

		Log.d("GL", "============AyncTask+数据" + listNews);
		// JSON解析时，遇到[]时才用List<>，{}时不用。List 一般用Map<String,Class<List的泛型>>

		return listNews;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		dialog = new ProgressDialog(context);
		dialog.setTitle("高磊君正在帮您下载");
		dialog.setMessage("拼命加载中,请稍后...");
		dialog.show();

	}

	@Override
	protected void onPostExecute(List<News1> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		// 下载完要通知一下

		dialog.dismiss();
		onLoadStringFinish.OnloadString(result);// ccc

	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
