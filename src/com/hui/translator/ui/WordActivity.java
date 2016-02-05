package com.hui.translator.ui;

import java.util.List;

import com.hui.translator.R;
import com.hui.translator.adapter.WordAdapter;
import com.hui.translator.db.WordDao;
import com.hui.translator.info.WordInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * ���ʱ����
 */
public class WordActivity extends Activity implements OnClickListener {
	
	private ImageView mBack;
	private ListView mListView;
	private WordDao mWordDao;//�������ݿ�
	private WordAdapter mWordAdapter;
//	private 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_word);
		
		mWordDao = new WordDao(WordActivity.this);
		initView();
		initData();
	}
	
	/**
	 * ��ʼ��view
	 */
	private void initView() {
		
		mBack = (ImageView) findViewById(R.id.back);
		mListView = (ListView) findViewById(R.id.word_lv);
		mBack.setOnClickListener(this);
	}
	
	/**
	 * ��ʼ������
	 */
	private void initData() {
		
		//���ݿ��������
		List<WordInfo> datas = mWordDao.getSearchInfo();
		if(null != datas) {//���ݲ�Ϊ�յ�ʱ��
			mWordAdapter = new WordAdapter(WordActivity.this, datas);
			mListView.setAdapter(mWordAdapter);
		} else {
			Toast.makeText(WordActivity.this, "���ʱ���û�е���Ŷ!", Toast.LENGTH_SHORT).show();
		}
		
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		}
	}
}
