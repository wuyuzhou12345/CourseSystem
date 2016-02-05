package org.javatribe.courseSystem.Activity;
import java.util.HashMap;
import java.util.Map;















import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.Util.Validator;
import org.javatribe.courseSystem.db.StudentSDDao;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Student;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.util.HttpUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class RegisterActivity extends Activity {

	private EditText username;
	private EditText password;
	private EditText confirmPassword;
	private EditText sno;
	private EditText schoolName;
	private EditText studentName;
	private Spinner dept;
	private RadioGroup gender;
	private Button submit;
	private EditText major;
	private EditText longPhone;
	private EditText shortPhone;
	private Handler registerHandler;
	private RadioButton male;
	private RadioButton female;
	private StudentSDDao studentDao;
	private Student s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_student);
		studentDao=new StudentSDDao(RegisterActivity.this);
		initValues();
		registerHandler=new Handler(){
			public void handleMessage(Message msg)
			{
				//�����Ϣ�Ǳ����͵�
				
				if(msg.what==1)
				{
					Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
					
					studentDao.startWritableDatabase(false);
					org.javatribe.courseSystem.db.model.Student stu=new  org.javatribe.courseSystem.db.model.Student();
					stu.setDailyCourseCount(0);
					stu.setDept(s.getDept());
					stu.setGender(s.getGender());
					stu.setLongTele(s.getLongTele());
					stu.setLongTelePublic(s.isLongTelePublic());
					stu.setMajor(s.getMajor());
					stu.setName(s.getName());
					stu.setPassword(s.getPassword());
					stu.setSchoolName(s.getSchoolName());
					stu.setShortTele(s.getShortTele());
					stu.setShortTelePublic(s.isShortTelePublic());
					stu.setStuNo(s.getStuNo());
					stu.setUsername(s.getUsername());
					
					studentDao.insert(stu);
					studentDao.closeDatabase(false);
				}
				else if(msg.what==-1)
				{
					Toast.makeText(RegisterActivity.this,"注册失败！请稍后重试",Toast.LENGTH_SHORT).show();
				}
				
			}
		};
	}
	private void initValues()
	{

		username=(EditText)(findViewById(R.id.frs_tv_username));
		password=(EditText)(findViewById(R.id.frs_et_password));
		confirmPassword=(EditText)(findViewById(R.id.frs_et_confirmPassword));
		dept=(Spinner)(findViewById(R.id.frs_et_dept));
		sno=(EditText)(findViewById(R.id.frs_et_sno));
		schoolName=(EditText)(findViewById(R.id.frs_et_schoolName));
		studentName=(EditText)(findViewById(R.id.frs_et_name));
		gender=(RadioGroup)(findViewById(R.id.frs_rg_gender));
		submit=(Button)(findViewById(R.id.frs_btn_ok));
		major=(EditText)(findViewById(R.id.frs_et_major));
		gender=(RadioGroup)(findViewById(R.id.frs_rg_gender));
	 longPhone=(EditText)(findViewById(R.id.frs_et_longPhone));
		shortPhone=(EditText)(findViewById(R.id.frs_et_shortPhone));
		male=(RadioButton)(findViewById(R.id.frs_rb_male));
		female=(RadioButton)(findViewById(R.id.frs_rb_female));
		
		submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(validateData())
				{
					String sex="";
					if(female.isChecked())
					{
						sex="女";
					}
					else
					{
						sex="男";
					}
				 s=new Student();
				 s.setStuNo(sno.getText().toString());
				 s.setSchoolName(schoolName.getText().toString());
				 s.setLongTelePublic(false);
				 s.setShortTelePublic(false);
				 s.setLongTele(longPhone.getText().toString());
				 s.setName(studentName.getText().toString());
				 s.setPassword(password.getText().toString());
				 s.setGender(sex);
				 s.setShortTele(shortPhone.getText().toString());
				 s.setUsername(username.getText().toString());
				 s.setDept(dept.getSelectedItem().toString());
				 s.setMajor(major.getText().toString());
				 
				Map<String,Object> data=new HashMap<String,Object>();
			    data.put("student",s);
			    register(data);
			}
		}});
		
	}
	public  boolean validateData()
	{
		boolean flag=false;
		if(!username.getText().toString().matches("^\\w{6,15}$"))
		{
			//System.out.println(username.getText().toString());
			Toast.makeText(RegisterActivity.this, "用户名不规范！", Toast.LENGTH_SHORT).show();
		}
		else if(!password.getText().toString().matches("^\\w{6,16}$"))
		{
			Toast.makeText(RegisterActivity.this, "密码不规范！", Toast.LENGTH_SHORT).show();
		}
		else if(!password.getText().toString().equals(confirmPassword.getText().toString()))
		{
			Toast.makeText(RegisterActivity.this, "密码不一致！", Toast.LENGTH_SHORT).show();
		}
		else if(Validator.isEmpty(schoolName))
		{
			Toast.makeText(RegisterActivity.this, "学校名称不能为空！", Toast.LENGTH_SHORT).show();
		}
		else if(Validator.isEmpty(studentName))
		{
			Toast.makeText(RegisterActivity.this, "姓名不能为空！", Toast.LENGTH_SHORT).show();
		}
			
		else if(!Validator.isEmpty(longPhone)&&!longPhone.getText().toString().matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"))
		{
	
		
			Toast.makeText(RegisterActivity.this, "长号不符合规范！", Toast.LENGTH_SHORT).show();
		}
		else if(!Validator.isEmpty(shortPhone)&&!shortPhone.getText().toString().matches("^6[0-9]{3,6}$"))
		{
			Toast.makeText(RegisterActivity.this, "短号不符合规范！", Toast.LENGTH_SHORT).show();
		}
		else 
		{
			flag=true;
		}
		return flag;
	}
	public void register(final Map<String,Object> data)
	{
		Thread thread=new Thread(){
		public void run()
		{
				String result=GetDataWithJson.getDataWithJsonViaObjectAndIntegerList(Constant.BASE_URL+"/student/addStudent", data, null, null);
				Message msg=registerHandler.obtainMessage();
				Log.i("message",result);
				if(result.equals("error")||result.equals("false"))
				{
					msg.what=-1;
				}
				else
				{
					msg.what=1;
				}
				registerHandler.sendMessage(msg);
		}
	};
	thread.start();
	}
}
