package com.example.jssystem;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Add_Page extends Activity {

    private TextView yUserName;
    private Button yButtonBack;
    private TextView yTextUserName;
    private EditText yEditUserName;
    private TextView yTextStartDate;
    private EditText yEditStartDate;
    private TextView yTextUserAge;
    private EditText yEditUserAge;
    private TextView yTextEndDate;
    private EditText yEditEndDate;
    private TextView yTextMessage;
    private Button yButtonAdd;
    String UserName;
    DBAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__page);
		bindViews();
		getIntent1();
	}
	 private void bindViews() {

	        yUserName = (TextView) findViewById(R.id.UserName);
	        yButtonBack = (Button) findViewById(R.id.buttonBack);
	        yTextUserName = (TextView) findViewById(R.id.textUserName);
	        yEditUserName = (EditText) findViewById(R.id.editUserName);
	        yTextStartDate = (TextView) findViewById(R.id.textStartDate);
	        yEditStartDate = (EditText) findViewById(R.id.editStartDate);
	        yTextUserAge = (TextView) findViewById(R.id.textUserAge);
	        yEditUserAge = (EditText) findViewById(R.id.editUserAge);
	        yTextEndDate = (TextView) findViewById(R.id.textEndDate);
	        yEditEndDate = (EditText) findViewById(R.id.editEndDate);
	        yTextMessage = (TextView) findViewById(R.id.textMessage);
	        yButtonAdd = (Button) findViewById(R.id.buttonAdd);
	        
	        yButtonAdd.setOnClickListener(Add);
	        yButtonBack.setOnClickListener(Back);
	        
	        db = new DBAdapter(Add_Page.this);
	    }
	 
	 private void getIntent1() {
			Intent i = getIntent();
			String UserName = i.getStringExtra("UserName");
			yUserName.setText("当前登录用户：" + UserName);
			this.UserName=UserName;
		}
	 
		private OnClickListener Back = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent (Add_Page.this,Center_Page.class);
				i.putExtra("UserName", UserName);
				startActivity(i);
			}
		};
	 
	 OnClickListener Add = new OnClickListener() {
		
		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String Name = yEditUserName.getText().toString();
			String age = yEditUserAge.getText().toString();
			String StartDate = yEditStartDate.getText().toString();
			String EndDate = yEditEndDate.getText().toString();
			int Age	= Integer.parseInt(age);		
			Member m = new Member();
			m.setAge(Age);
			m.setName(Name);
			m.setBeginDate(StartDate);
			m.setEndDate(EndDate);
			db.open();
			try{
				Age	= Integer.parseInt(age);//检查样式是否正确
				Date b = new  Date(StartDate);
				b = new  Date(EndDate);
				db.insert(m);
				yTextMessage.setText("添加成功");
			}catch(Exception e){
				yTextMessage.setText("添加失败，\n请检查输入数据格式是否正确");				
			}
			
			db.close();
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
