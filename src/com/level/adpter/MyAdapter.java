package com.level.adpter;

import java.util.List;
import com.example.day10homeworklistviewjson.R;
import com.level.aynctask.AyncTaskBitMap;
import com.level.bean.News1;
import com.level.listeners.OnLoadBitMapFinish;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	Context context;
	List<News1> list;

	public MyAdapter(Context context, List<News1> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		final ViewHolder viewHolder;
		final String temp = list.get(position).getData().getCover();// 解决图片错位问题，····没有解决

		Log.d("zsp", "temp11----"+temp);
		if (convertView == null) {

			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_10homework, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.textView1 = (TextView) convertView
					.findViewById(R.id.textView1Id);
			viewHolder.textView2 = (TextView) convertView
					.findViewById(R.id.textView2Id);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.imageViewId);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();// 打包强反转，智能初始化

		}

		viewHolder.textView1.setText(list.get(position).getData().getSubject());
		viewHolder.textView2.setText(list.get(position).getData().getSummary());
		// viewHolder.textView2.setText(list.get(position).getParamz().get(position).getOid());
		// viewHolder.textView2.setText(list.get(position).getParamz().get(position).getCategory());
		// viewHolder.textView2.setText(list.get(position).getParamz().get(position).getData().get(position).getChanged());
		// viewHolder.textView2.setText(list.get(position).getParamz().get(position).getData().get(position).getFormat());
		// viewHolder.textView2.setText(list.get(position).getParamz().get(position).getData().get(position).getPic());
		// viewHolder.textView2.setText(list.get(position).getParamz().get(position).getData().get(position).getSubject());
		// viewHolder.textView2.setText(list.get(position).getParamz().get(position).getData().get(position).getSummary());

		// temp=
		// "http://litchiapi.jstv.com"+list.get(position).getData().getCover();//图片的地址
		viewHolder.imageView.setTag(temp);// 位置要放对,这个一直不对。
		viewHolder.imageView.setImageResource(R.drawable.ic_launcher);
		Log.d("zsp", "temp333----"+position);
		new AyncTaskBitMap(new OnLoadBitMapFinish() {

			@Override
			public void OnLoadBitmap(Bitmap bitmap) {
				
				Log.d("zsp", "temp22----"+viewHolder.imageView.getTag());
				if (viewHolder.imageView.getTag() != null
						&& viewHolder.imageView.getTag().equals(temp)) {
					viewHolder.imageView.setImageBitmap(bitmap);
					//viewHolder.imageView.getTag().equals(temp);没有加getTag，浪费了一天
					
				}
			}
		}).execute("http://litchiapi.jstv.com"
				+ list.get(position).getData().getCover());

		return convertView;
	}

	class ViewHolder {

		TextView textView1, textView2;
		// textView2
		ImageView imageView;
	}

}
