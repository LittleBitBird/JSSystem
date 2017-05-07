package com.example.jssystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	private static final String DB_Name = "	Fitness.db";
	private static final String DB_Table = "Member";

	private static final String Member_id = "_id";
	private static final String Member_name = "name";
	private static final String Member_age = "age";
	private static final String Member_sex = "sex";
	private static final String Member_beginDate = "beginDate";
	private static final String Member_endDate = "endDate";

	private Context context;
	private DBOpenHelper dbopenHelper;
	private SQLiteDatabase db;


	public DBAdapter(Context c) {
		context = c;
	}

	private static class DBOpenHelper extends SQLiteOpenHelper {

		private static final String db_create = "create table " + DB_Table + "(" + Member_id
				+ " integer primary key autoincrement, " + Member_name + " text not null, " + Member_sex
				+ " text , " + Member_age + " integer not null, " + Member_beginDate + " DATETIME," + Member_endDate
				+ " DATETIME);";

		public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(db_create);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}
	}

	public void open() throws SQLiteException {
		dbopenHelper = new DBOpenHelper(context, DB_Name, null, 1);
		try {
			db = dbopenHelper.getWritableDatabase();

		} catch (SQLiteException e) {
			db = dbopenHelper.getReadableDatabase();

		}
	}

	public void close() {
		if (db != null) {
			db.close();
			db = null;
		}
	}


	public long insert(Member member) {
		ContentValues value = new ContentValues();
		
		value.put(Member_name, member.getName());
		value.put(Member_age, member.getAge());
		value.put(Member_endDate, member.getEndDate());
		value.put(Member_beginDate, member.getBeginDate());
		
		return db.insert(DB_Table, null, value);
	}


	public Member[] getAllDate() {
		Cursor result = db.query(DB_Table,
				new String[] { Member_id, Member_name, Member_sex,Member_age, Member_beginDate, Member_endDate }, null, null, null,
				null, null);
		return ConverttoMember(result);
	}

	public Member[] ConverttoMember(Cursor cursor) {
		int counts = cursor.getCount();
		if (counts == 0 || !cursor.moveToFirst()) {
			return null;
		}
		Member[] Member = new Member[counts];
		for (int i = 0; i < counts; i++) {
			Member[i] = new Member();
			Member[i].setID(cursor.getInt(0));
			Member[i].setName(cursor.getString(1));
			Member[i].setSex(cursor.getString(2));
			Member[i].setAge(cursor.getInt(3));
			Member[i].setBeginDate(cursor.getString(4));
			Member[i].setEndDate(cursor.getString(5));
			cursor.moveToNext();
		}
		return Member;
	}
	public void UpdateMember(int id,String name,int age,String beginDate,String endDate){
		ContentValues value = new ContentValues();
		
		value.put(Member_name, name);
		value.put(Member_age, age);
		value.put(Member_beginDate, beginDate);
		value.put(Member_endDate, endDate);
		
		db.update(DB_Table, value,Member_id+" = "+ id, null);
	}
	
	public void MemberDeleteById(String id) {
		db.delete(DB_Table, Member_id + " = ?", new String[] { id });
	}
}
