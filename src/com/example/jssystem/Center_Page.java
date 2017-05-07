package com.example.jssystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Center_Page extends Activity {
	private TextView yTextUserName;
	private Button yButtonQuit;
	private Button yButtonAddUser;
	private Button yButtonDeleteUser;
	private Button yButtonUpdateUser;
	private Button yButtonLoginMessage;
	String UserName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_center__page);
		bindViews();
		getIntent1();
	}

	private void bindViews() {

		yTextUserName = (TextView) findViewById(R.id.TextUserName);
		yButtonQuit = (Button) findViewById(R.id.buttonQuit);
		yButtonAddUser = (Button) findViewById(R.id.buttonAddUser);
		yButtonDeleteUser = (Button) findViewById(R.id.buttonDeleteUser);
		yButtonUpdateUser = (Button) findViewById(R.id.buttonUpdateUser);
		yButtonLoginMessage=(Button)findViewById(R.id.buttonLoginMessage);
		
		registerForContextMenu(yButtonLoginMessage);

		yButtonAddUser.setOnClickListener(Add);
		yButtonDeleteUser.setOnClickListener(Delete);
		yButtonUpdateUser.setOnClickListener(Update);
		yButtonQuit.setOnClickListener(Quit);
	}

	private void getIntent1() {
		Intent i = getIntent();
		String UserName = i.getStringExtra("UserName");
		yTextUserName.setText("当前登录用户：" + UserName);
		this.UserName=UserName;
	}

	OnClickListener Quit = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Center_Page.this, Login_Page.class);
			startActivity(i);
		}
	};

	OnClickListener Add = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Center_Page.this, Add_Page.class);
			i.putExtra("UserName", UserName);
			startActivity(i);
		}
	};

	OnClickListener Update = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Center_Page.this, Update_Page.class);
			i.putExtra("UserName", UserName);
			startActivity(i);
		}
	};

	OnClickListener Delete = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Center_Page.this, Delete_Page.class);
			i.putExtra("UserName", UserName);
			startActivity(i);
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
	
	public void onCreateContextMenu(ContextMenu menu, View v,  
			   ContextMenuInfo menuInfo) {  
			  // 向上下文菜单中添加菜单项  
			  // 注意此处的menu是ContextMenu  
			  menu.add(0, 1, 0, UserName);  
			  menu.add(0, 2, 0, "性别：男");  
			  menu.add(0, 3, 0, "年龄：23");  
			  menu.add(0,4,0,"教练号：23333");
			}  
}
