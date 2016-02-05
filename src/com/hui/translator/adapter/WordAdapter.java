package com.hui.translator.adapter;

import java.util.ArrayList;
import java.util.List;

import com.hui.translator.R;
import com.hui.translator.info.WordInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * µ•¥ ±Ì  ≈‰∆˜
 */
public class WordAdapter extends BaseAdapter {

		List<WordInfo> datas;
		LayoutInflater inflater;

		public WordAdapter(Context context, List<WordInfo> datas) {
			if(datas == null) {
				this.datas = new ArrayList<WordInfo>();
			} else {
				this.datas = datas;
			}
			
			this.inflater = LayoutInflater.from(context);
		}

		public void onDateChange(List<WordInfo> datas) {
			
			if(datas == null) {
				this.datas = new ArrayList<WordInfo>();
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
			WordInfo entity = datas.get(position);
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.item_word, null);
				holder.word = (TextView) convertView.findViewById(R.id.item_word_tv);
				holder.trans = (TextView) convertView.findViewById(R.id.item_trans_tv);
				
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.word.setText(entity.getWord());
			holder.trans.setText(entity.getTrans());
			
			
			return convertView;
		}

		class ViewHolder {
			TextView word;
			TextView trans;
		}
}
