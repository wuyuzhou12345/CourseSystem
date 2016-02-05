﻿package org.javatribe.courseSystem.Activity;


import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.db.StudentSDDao;
import org.javatribe.courseSystem.db.model.Student;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.net.GetDataWithJson;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ab.activity.AbActivity;
import com.ab.db.orm.AbSDSQLiteOpenHelper;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;


/**
 * @author zhou
 *2014年11月28日
 */
public class LoginActivity extends Activity {

	private StudentSDDao studentSDDao = null;
	private Handler handler;
	private Student student;
	private String PREFS_NAME="org.javatribe.courseSystem";
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        modity();
        
    }
	private void modity() {
		ImageView img_login=(ImageView)findViewById(R.id.img_login);           
		final EditText editText_username=(EditText)findViewById(R.id.editText_username);    
		final EditText editText_password=(EditText)findViewById(R.id.editText_password);     
		final Button btn_login=(Button)findViewById(R.id.btn_login);
		
		handler=new Handler(new Callback(){

			public boolean handleMessage(Message msg) {
				Log.i("======a=======","========a========");
				btn_login.setClickable(true);
				if (msg.obj != null) {
					if (Boolean.parseBoolean((String) msg.obj)) {
						Log.i("Login Student success!",(String)msg.obj);
						
						SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences存放数据
						SharedPreferences.Editor editor=settings.edit();
						editor.putString("stuNo",student.getStuNo());
						editor.putString ("schoolName",student.getSchoolName());
						editor.commit();
						
						
						SharedPreferences setting=getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
						String sno=setting.getString("stuNo", "121542100");
						String school_name=setting.getString("schoolName","syzx");
						Log.i("======SharedPreference sno=====",sno);
						Log.i("======SharedPreference  schoolName======",school_name);
						
						
						
						Intent intent=new Intent();
						intent.setClass(LoginActivity.this,SlidingMenuLeftActivity.class);
						startActivity(intent);
					} 
					else{
						Toast.makeText(LoginActivity.this, "填写的学号或密码错误", Toast.LENGTH_LONG).show();
						Log.i("Login Error","Login Error");
					}
				} 
				else
				{
					Log.i("connect failure!","connect failure");
				}
				return true;
			}
			
		});

		btn_login.setOnClickListener(new OnClickListener(){

		public void onClick(View arg0) {			
			btn_login.setClickable(false);
			if(editText_username.getText().toString().equals("")||editText_password.getText().toString().equals("")){
				 Toast.makeText(LoginActivity.this, "学号和用户名不能为空", Toast.LENGTH_LONG).show();
			}
			else{
			Thread thread=new Thread("LoginStudent") {
				public void run() {
					Log.i("thread start!","thread start!");
					Message message = handler.obtainMessage();
					String state = null;
					try {
						String url=Constant.BASE_URL+"/student/loginStudent";
						String sno=editText_username.getText().toString();
						String password=editText_password.getText().toString();
						String schoolName = null;
						
						studentSDDao = new StudentSDDao(LoginActivity.this);    //初始化studentSDDao
						studentSDDao.startReadableDatabase(false);            //获取数据库，只读方式，若为写入模式，更改为startWritableDatabase(false)
						List<Student> list_stu=studentSDDao.queryList();
						for(Student student:list_stu){
							if(student.getStuNo().equals(sno)){
								schoolName=student.getSchoolName();
								Log.i("schoolName",schoolName);
							}
						}
						studentSDDao.closeDatabase(false);              //关闭数据库sqlite
						student=new Student();
						student.setStuNo(sno);
						student.setPassword(password);
						//student.setSchoolName("gduf");
						student.setSchoolName(schoolName);
						Log.i("url",url);
						Map<String,Object> params=new HashMap<String,Object>();
						params.put("student", student);
						state=GetDataWithJson.getDataWithJsonViaObjectAndStringList(url, params, null,null);
					} catch (Exception e) {
						e.printStackTrace();
					}
					message.obj = state;
					handler.sendMessage(message);
				}
			};
			thread.start();
			}
			}
		});
		Button btn_forget_password=(Button)findViewById(R.id.btn_forget_password);
		Button btn_register=(Button)findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
			
		});
			
		
	}

}
