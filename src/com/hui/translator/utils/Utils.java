package com.hui.translator.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.hui.translator.info.SentenceInfo;
import com.hui.translator.info.TransInfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 工具类 
 */
public class Utils {


	/**
	 * 流转化
	 * @param is
	 * @return
	 */
	public static String readStream(InputStream is) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			byte[] date = new byte[1024];
			while((len = is.read(date)) != -1) {
				bos.write(date, 0, len);
			}
			
			is.close();
			bos.close();
			byte[] result = bos.toByteArray();
			String temp = new String(result);
			return temp;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	

	/**
	 * 判断网络状态
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * 解析翻译信息
	 * @param str
	 * @return
	 */
	public static List<TransInfo> getTransInfos(String str) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			JSONObject dataSet = jsonObject.getJSONObject("DataSet");
			JSONObject diffgrDiffgram = dataSet
					.getJSONObject("diffgr:diffgram");
			JSONObject dictionary = diffgrDiffgram.getJSONObject("Dictionary");
			JSONObject trans = dictionary.getJSONObject("Trans");

			TransInfo transInfo = new TransInfo();

			List<TransInfo> list = new ArrayList<TransInfo>();

			transInfo.setWordKey(trans.getString("WordKey"));
			transInfo.setPron(trans.getString("Pron"));
			transInfo.setTranslation(trans.getString("Translation"));

			list.add(transInfo);

			return list;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 解析例句信息
	 * @param str
	 * @return
	 */
	public static List<SentenceInfo> getSentence(String str) {

		try {
			List<SentenceInfo> list = new ArrayList<SentenceInfo>();
			JSONObject jsonObject = new JSONObject(str);
			JSONObject dataSet = jsonObject.getJSONObject("DataSet");
			JSONObject diffgrDiffgram = dataSet
					.getJSONObject("diffgr:diffgram");
			JSONObject dictionary = diffgrDiffgram.getJSONObject("Dictionary");
			JSONArray jsonArray = dictionary.getJSONArray("Sentence");
			for (int i = 0; i < jsonArray.length(); i++) {
				SentenceInfo sentenceInfo = new SentenceInfo();
				JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
				sentenceInfo.setOrig(jsonObject2.getString("Orig"));
				sentenceInfo.setTrans(jsonObject2.getString("Trans"));
				list.add(sentenceInfo);

			}

			return list;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * XML转为JSON字符串
	 * 
	 * 
	 * json字符串比较好操作
	 * @param xml
	 * @return
	 */
	public static String xmlToJson(String xml) {

		try {
			JSONObject jsonObject = XML.toJSONObject(xml);
			if (null != jsonObject) {
				return jsonObject.toString();
			} else {
				return null;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
