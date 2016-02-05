package com.hui.translator.adapter;

import java.util.ArrayList;
import java.util.List;

import com.hui.translator.R;
import com.hui.translator.info.SentenceInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 例句适配器
 * 
 * 使用baseadapter
 */
public class SentenceAdapter extends BaseAdapter {
	
	List<SentenceInfo> datas;
	LayoutInflater inflater;

	public SentenceAdapter(Context context, List<SentenceInfo> datas) {
		if (datas == null) {
			this.datas = new ArrayList<SentenceInfo>();
		} else {
			this.datas = datas;
		}

		this.inflater = LayoutInflater.from(context);
	}

	
	/**
	 * 刷新数据时调用
	 * @param datas
	 */
	public void onDateChange(List<SentenceInfo> datas) {

		if (datas == null) {
			this.datas = new ArrayList<SentenceInfo>();
		} else {
			this.datas = datas;
		}
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SentenceInfo entity = datas.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_setence, null);
			holder.orig = (TextView) convertView.findViewById(R.id.orig_tv);
			holder.trans = (TextView) convertView.findViewById(R.id.trans_tv);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.orig.setText(entity.getOrig());
		holder.trans.setText(entity.getTrans());

		return convertView;
	}

	class ViewHolder {
		TextView orig;
		TextView trans;
	}
}
