package com.hui.translator.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库
 */
public class SqliteOpenhelper extends SQLiteOpenHelper {
	private static SQLiteDatabase mDb;
	private static SqliteOpenhelper mHelper;
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "hui_train.db";
	//单词表
	private static final String TABLE_WORD = "hui_word";

	/**
	 * 单例
	 * 
	 * @param context
	 * @return
	 */
	public static SQLiteDatabase getInstance(Context context) {
		if (mDb == null) {
			mDb = getHelper(context).getWritableDatabase();
		}
		return mDb;
	}

	public static SqliteOpenhelper getHelper(Context context) {
		if (mHelper == null) {
			mHelper = new SqliteOpenhelper(context);
		}
		return mHelper;
	}

	public SqliteOpenhelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub+ " userId INTEGER,"
		db.execSQL("create table "
				+ TABLE_WORD
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " word varchar(20), trans varchar(50))");
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		if (newVersion > oldVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
			onCreate(db);
		}

	}
}
