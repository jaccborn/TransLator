package com.hui.translator.db;

import java.util.ArrayList;
import java.util.List;

import com.hui.translator.info.WordInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * 单词数据库管理
 */
public class WordDao {

	
	private static final String TABLE_WORD = "hui_word";
	private Context mContext;

	public WordDao(Context context) {
		this.mContext = context;

	}

	/**
	 * 保存单词信息
	 * @param list
	 * @return
	 */
	public long saveWord(List<WordInfo> list) {
		SQLiteDatabase db = SqliteOpenhelper.getInstance(mContext);
		long row = 0;
		for (WordInfo info : list) {
			ContentValues cv = new ContentValues();
			cv.put("word", info.getWord());
			cv.put("trans", info.getTrans());
			row = db.insert(TABLE_WORD, null, cv);
		}
		return row;

	}

	/**
	 * 单词信息
	 * @return
	 */
	public List<WordInfo> getSearchInfo() {
		SQLiteDatabase db = SqliteOpenhelper.getInstance(mContext);
		List<WordInfo> list = new ArrayList<WordInfo>();
		String sql = "select * from " + TABLE_WORD;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				WordInfo searchInfo = new WordInfo();
				searchInfo.setId(cursor.getInt(cursor.getColumnIndex("_id")));
				searchInfo.setWord(cursor.getString(cursor.getColumnIndex("word")));
				searchInfo.setTrans(cursor.getString(cursor.getColumnIndex("trans")));
				list.add(searchInfo);
			}
			cursor.close();
			return list;
		} else {
			cursor.close();
			return null;
		}
	}




}
