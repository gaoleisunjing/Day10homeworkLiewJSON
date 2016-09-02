package com.example.day10homeworklistviewjson;

import java.util.ArrayList;
import java.util.List;
import com.level.adpter.MyAdapter;
import com.level.aynctask.AyncTaskString;
import com.level.bean.News1;
import com.level.listeners.OnLoadStringFinish;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnLoadStringFinish,
		OnScrollListener {

	ListView listView;
	// List<>

	List<News1> list;
	// 该建适配器了
	MyAdapter myAdapter;

	private static final String PATH = "http://litchiapi.jstv.com/api/GetFeeds?column=0&PageSize=20&pageIndex=1";

	boolean isBottom;
	private int page;// 页数 到了底端就加一

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Prepare();
		loadData(page);
		myAdapter = new MyAdapter(getApplicationContext(), list);
		listView.setAdapter(myAdapter);

		listView.setOnScrollListener(this);
	}

	private void loadData(int page) {
		// 下载完第一次回来 该处理数据了 异步处理
		String string = String.format(PATH, 1);// 格式化网址字符串
		// 回来第二次，建立适配器
		new AyncTaskString(this).execute(string);

	}

	private void Prepare() {
		listView = (ListView) findViewById(R.id.listViewId);
		list = new ArrayList<News1>();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void OnloadString(List<News1> listNews) {
		// 接口回调的意义主要在于当数据完成时再进行set，如果不是明确的下载完成，set的话会得到空指针。
		Log.d("GL", "========下载的数据" + listNews);
		list.addAll(listNews);
		myAdapter.notifyDataSetChanged();// ???
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		switch (scrollState) {

		case SCROLL_STATE_IDLE:
			if (isBottom) {

				// ++page;
				loadData(++page);// 继续加载下一页数据
			}
			break;

		default:
			break;
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

		if (firstVisibleItem + visibleItemCount == totalItemCount) {
			isBottom = true;
		} else {
			isBottom = false;
		}
	}

}
