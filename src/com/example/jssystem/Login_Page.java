package com.example.jssystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login_Page extends Activity {
	private EditText yEditUserName;
	private EditText yEditPassword;
	private Button yLogin;
	private Button yReset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login__page);
		yEditUserName = (EditText) findViewById(R.id.editUserName);
		yEditPassword = (EditText) findViewById(R.id.editPassword);
		yLogin = (Button) findViewById(R.id.Login);
		yReset = (Button) findViewById(R.id.Reset);
		
		yLogin.setOnClickListener(LoginEvent);
	}

	// µ¥»÷µÇÂ¼°´Å¥µÄÊÂ¼þ
	OnClickListener LoginEvent = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String UserName = yEditUserName.getText().toString();
			String Password = yEditPassword.getText().toString();
			if ((UserName.equals("1410075040") || UserName.equals("1410075043") || UserName.equals("1410075044"))
					&& Password.equals("123")) {
				Intent i = new Intent(Login_Page.this,Center_Page.class);
				switch (UserName){
				case "1410075040" : i.putExtra("UserName", "Îâ¹Ú»ª");break;
				case "1410075043" : i.putExtra("UserName", "Ñî·á·¶");break;
				case "1410075044" : i.putExtra("UserName", "ÑîÐ¦");break;
				}				
				startActivity(i);
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login__page, menu);
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
