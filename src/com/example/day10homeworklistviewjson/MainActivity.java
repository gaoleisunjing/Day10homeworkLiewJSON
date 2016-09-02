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
	// �ý���������
	MyAdapter myAdapter;

	private static final String PATH = "http://litchiapi.jstv.com/api/GetFeeds?column=0&PageSize=20&pageIndex=1";

	boolean isBottom;
	private int page;// ҳ�� ���˵׶˾ͼ�һ

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
		// �������һ�λ��� �ô��������� �첽����
		String string = String.format(PATH, 1);// ��ʽ����ַ�ַ���
		// �����ڶ��Σ�����������
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
		// �ӿڻص���������Ҫ���ڵ��������ʱ�ٽ���set�����������ȷ��������ɣ�set�Ļ���õ���ָ�롣
		Log.d("GL", "========���ص�����" + listNews);
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
				loadData(++page);// ����������һҳ����
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
