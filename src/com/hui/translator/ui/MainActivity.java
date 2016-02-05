package com.hui.translator.ui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import com.hui.translator.Constants;
import com.hui.translator.R;
import com.hui.translator.adapter.SentenceAdapter;
import com.hui.translator.db.WordDao;
import com.hui.translator.info.SentenceInfo;
import com.hui.translator.info.TransInfo;
import com.hui.translator.info.WordInfo;
import com.hui.translator.utils.LoadingDialog;
import com.hui.translator.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 主界面
 * 
 * 实现搜索单词 显示单词翻译 和 例句 添加单词到单词本 查询添加的单词
 */
public class MainActivity extends Activity implements OnClickListener {
	private ListView mListView;
	private EditText mSearch;
	private LinearLayout mSearchLl;

	// listview头部的view
	private TextView mHeadPron;
	private TextView mHeadWord;
	private TextView mHeadAddword;
	private TextView mHeadTranslator;
	private TextView mHeadComeWord;

	// 单词信息
	private List<TransInfo> mTransInfos;
	// 例句信息
	private List<SentenceInfo> mSentenceInfos;

	private LoadingDialog mLoadingDialog;
	// 单词表数据库管理
	private WordDao mWordDao;

	// 适配器
	private SentenceAdapter mWordAdapter;

	private Handler Myhandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 200:
				mLoadingDialog.dismiss();
				getResponseData(msg);

				break;

			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		
		
		
		mLoadingDialog = new LoadingDialog(MainActivity.this);
		mWordDao = new WordDao(MainActivity.this);
		initView();
		setListener();

	}

	/**
	 * 初始化view
	 */
	private void initView() {
		mSearch = (EditText) findViewById(R.id.search_word_et);
		mSearchLl = (LinearLayout) findViewById(R.id.search_ll);
		mListView = (ListView) findViewById(R.id.lv);

		// 加载头部布局
		View headView = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.header_layout, null);

		mHeadPron = (TextView) headView.findViewById(R.id.Pron_tv);
		mHeadAddword = (TextView) headView.findViewById(R.id.head_add_word);
		mHeadWord = (TextView) headView.findViewById(R.id.head_word);
		mHeadTranslator = (TextView) headView.findViewById(R.id.translation_tv);
		mHeadComeWord = (TextView) headView.findViewById(R.id.come_in_word_tv);

		// 添加listiew头部布局
		mListView.addHeaderView(headView, null, false);

	}

	/**
	 * 设置事件监听
	 */
	private void setListener() {

		mSearchLl.setOnClickListener(this);
		mHeadAddword.setOnClickListener(this);
		mHeadComeWord.setOnClickListener(this);
	}

	/**
	 * 设置例句数据
	 * 
	 * @param datas
	 */
	private void setSentenceData(List<SentenceInfo> datas) {

		if (null == mWordAdapter) {
			mWordAdapter = new SentenceAdapter(MainActivity.this, datas);
			mListView.setAdapter(mWordAdapter);
		} else {
			mWordAdapter.onDateChange(datas);
		}
	}

	/**
	 * 获取网络返回的数据
	 * 
	 * @param msg
	 */
	private void getResponseData(Message msg) {
		String response = (String) msg.obj;
		if (null != response) {
			String string = Utils.xmlToJson(response);

			if (null != string) {
				List<TransInfo> transInfos = Utils.getTransInfos(string);
				List<SentenceInfo> sentenceInfos = Utils.getSentence(string);
				if (null != transInfos) {
					mTransInfos = transInfos;
					setWordData(mTransInfos);
				} else {
					Toast.makeText(MainActivity.this, "查找失败",
							Toast.LENGTH_SHORT).show();
				}

				if (null != sentenceInfos) {
					mSentenceInfos = sentenceInfos;

					setSentenceData(mSentenceInfos);
				} else {
					Toast.makeText(MainActivity.this, "查找失败",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(MainActivity.this, "查找失败", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(MainActivity.this, "查找失败", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/**
	 * 加载网络数据
	 * 
	 * @param word
	 */
	private void loadIngData(final String word) {
		if (!"".equals(word)) {

			if (Utils.isNetworkAvailable(MainActivity.this)) {

				mLoadingDialog.dialogShow();
				new Thread(new Runnable() {

					@Override
					public void run() {
						Message msg = Message.obtain();
						try {
							String result = httpGet(Constants.URL
									+ "Translator?wordKey=" + word);

							msg.what = 200;
							msg.obj = result;

						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							Myhandler.sendMessage(msg);

						}

					}
				}).start();
			} else {
				Toast.makeText(MainActivity.this, "没有网络连接", Toast.LENGTH_SHORT)
						.show();
			}

		}
	}

	/**
	 * 设置头部单词信息
	 * 
	 * @param datas
	 */
	private void setWordData(List<TransInfo> datas) {
		mHeadWord.setText(datas.get(0).getWordKey());
		mHeadPron.setText("音标: " + datas.get(0).getPron());
		mHeadTranslator.setText(datas.get(0).getTranslation());

	}

	/**
	 * 保存单词 当单词不在单词表的时候才去保存
	 * 
	 * @param content
	 */
	private void saveWordInfo(String content, String trans) {

		if (!"".equals(content) && !"".equals(trans)) {
			List<WordInfo> list = new ArrayList<WordInfo>();
			WordInfo wordInfo = new WordInfo();
			List<WordInfo> dbList = mWordDao.getSearchInfo();
			if (null != dbList) {
				boolean flag = true;
				for (WordInfo woInfo : dbList) {

					if (woInfo.getWord().equals(content)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					wordInfo.setWord(content);
					wordInfo.setTrans(trans);
					list.add(wordInfo);
					mWordDao.saveWord(list);
					Toast.makeText(MainActivity.this, "添加单词成功!",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "单词已在单词本啦!",
							Toast.LENGTH_SHORT).show();
				}

			} else {
				wordInfo.setWord(content);
				wordInfo.setTrans(trans);
				list.add(wordInfo);
				mWordDao.saveWord(list);
				Toast.makeText(MainActivity.this, "添加单词成功", Toast.LENGTH_SHORT)
						.show();
			}

		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.search_ll:
			//做空格的处理 传参数的时候空格为 %20 为了可以查到词组
			String str=mSearch.getText().toString().trim();
			String replacestr = str.replace(" ", "%20");
			loadIngData(replacestr);
			break;

		case R.id.head_add_word:
			saveWordInfo(mTransInfos.get(0).getWordKey(), mTransInfos.get(0)
					.getTranslation());
			break;
		case R.id.come_in_word_tv:
			startActivity(new Intent(MainActivity.this, WordActivity.class));
			break;
		}

	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 */
	public String httpGet(String url) {
		try {

			HttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setIntParameter(
					HttpConnectionParams.CONNECTION_TIMEOUT, 15 * 1000);
			httpClient.getParams().setIntParameter(
					HttpConnectionParams.SO_TIMEOUT, 15 * 1000);

			HttpGet httpGet = new HttpGet(url);
			// 敲回车 返回服务器请求的信息
			HttpResponse response = httpClient.execute(httpGet);
			// 得到状态码
			int code = response.getStatusLine().getStatusCode();
			String text = null;
			if (code == 200) {

				InputStream is = response.getEntity().getContent();
				text = Utils.readStream(is);

				return text;

			} else {
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
