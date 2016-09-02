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

	Context context;// ΪʲôҪ�������Ľ���
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

		Log.d("GL", "============AyncTask+����" + listNews);
		// JSON����ʱ������[]ʱ����List<>��{}ʱ���á�List һ����Map<String,Class<List�ķ���>>

		return listNews;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		dialog = new ProgressDialog(context);
		dialog.setTitle("���ھ����ڰ�������");
		dialog.setMessage("ƴ��������,���Ժ�...");
		dialog.show();

	}

	@Override
	protected void onPostExecute(List<News1> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		// ������Ҫ֪ͨһ��

		dialog.dismiss();
		onLoadStringFinish.OnloadString(result);// ccc

	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
