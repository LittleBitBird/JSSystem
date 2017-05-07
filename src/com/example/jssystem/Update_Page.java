package com.example.jssystem;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Update_Page extends Activity {
	private TextView yTextUserNameUp;
	private Button yButtonBack;
	private TextView yTextUserNameDown;
	private EditText yEditUserName;
	private TextView yTextUserID;
	private EditText yEditUserID;
	private TextView yTextStartDate;
	private EditText yEditStartDate;
	private TextView yTextUserAge;
	private EditText yEditUserAge;
	private TextView yTextEndDate;
	private EditText yEditEndDate;
	private TextView yTextMessage;
	private Button yButtonUpdate;
	private Button yButtonSure;
	DBAdapter db;
	String UserName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update__page);
		db = new DBAdapter(Update_Page.this);
		bindViews();
		getIntent1();
	}

	private void bindViews() {

		yTextUserNameUp = (TextView) findViewById(R.id.UserName);
		yButtonBack = (Button) findViewById(R.id.buttonBack);
		yTextUserNameDown = (TextView) findViewById(R.id.textUserName);
		yEditUserName = (EditText) findViewById(R.id.editUserName);
		yTextUserID = (TextView) findViewById(R.id.textUserID);
		yEditUserID = (EditText) findViewById(R.id.editUserID);
		yTextStartDate = (TextView) findViewById(R.id.textStartDate);
		yEditStartDate = (EditText) findViewById(R.id.editStartDate);
		yTextUserAge = (TextView) findViewById(R.id.textUserAge);
		yEditUserAge = (EditText) findViewById(R.id.editUserAge);
		yTextEndDate = (TextView) findViewById(R.id.textEndDate);
		yEditEndDate = (EditText) findViewById(R.id.editEndDate);
		yTextMessage = (TextView) findViewById(R.id.textMessage);
		yButtonUpdate = (Button) findViewById(R.id.buttonUpdate);
		yButtonSure = (Button) findViewById(R.id.buttonSure);

		yButtonSure.setOnClickListener(Sure);
		yButtonBack.setOnClickListener(Back);
		yButtonUpdate.setOnClickListener(Update);
	}
	


	private OnClickListener Back = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Update_Page.this, Center_Page.class);
			i.putExtra("UserName", UserName);
			startActivity(i);
		}
	};

	private OnClickListener Update = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			db.open();
			int id = Integer.parseInt(yEditUserID.getText().toString());
			String name = yEditUserName.getText().toString();
			int age = Integer.parseInt(yEditUserAge.getText().toString());
			String startDate = yEditStartDate.getText().toString();
			String endDate = yEditEndDate.getText().toString();
			db.UpdateMember(id, name, age, startDate, endDate);
			yTextMessage.setText("更新成功");
			db.close();
		}
	};

	private OnClickListener Sure = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			db.open();
			try {
				List<Member> MemberList = new ArrayList<Member>();
				Member[] member = db.getAllDate();
				for (Member member2 : member) {
					if (member2.getName().equals(yEditUserName.getText().toString())) {
						MemberList.add(member2);
					}
				}
				if (MemberList.size() == 1) {// 无同名情况
					Log.e("???", "1");
					Log.e("???", MemberList.get(0).getAge() + "");
					yEditUserAge.setText(MemberList.get(0).getAge() + "");
					yEditUserID.setText(MemberList.get(0).getID() + "");
					yEditStartDate.setText(MemberList.get(0).getBeginDate());
					yEditEndDate.setText(MemberList.get(0).getEndDate());
				} else if (MemberList.size() > 1) {
					if (yEditUserID.getText().toString().equals("")) {// 如果ID未输入
						yTextMessage.setText("有重复姓名的用户，请输入用户ID");
					} else {
						for (int i = 0; i < MemberList.size(); i++) {
							if (MemberList.get(i).getID() == Integer.parseInt(yEditUserID.getText().toString())) {
								yEditUserAge.setText(MemberList.get(i).getAge() + "");
								yEditStartDate.setText(MemberList.get(i).getBeginDate());
								yEditEndDate.setText(MemberList.get(i).getEndDate());
								yTextMessage.setText("");
							}
						}
					}
				} else {
					yTextMessage.setText("无此会员");
				}
			} catch (Exception e) {

			}
			db.close();
		}
	};

	private void getIntent1() {
		Intent i = getIntent();
		String UserName = i.getStringExtra("UserName");
		yTextUserNameUp.setText("当前登录用户：" + UserName);
		this.UserName=UserName;
	}
	

	private TextWatcher UserNameChange = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
