package org.javatribe.courseSystem.Activity.personalInfo;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Organization;
import org.javatribe.courseSystem.model.Student;
import org.javatribe.courseSystem.net.GetDataWithJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**修改信息
 * @author qing
 *2014/9/28
 */
public class PersonalInformationFragment extends Fragment{

	private Handler resetInfo_handler;
	private Handler getStuInfo_handler;
	private EditText editTextName;
	private EditText editTextNo;
	private EditText editTextDepartment;
	private EditText editTextSchoolName;
	private EditText editTextLongNumber;
	private EditText editTextShortNumber;
	private EditText editTextCourseCount;
	private Button btnModify;
	private Button btnOk;
	private ToggleButton isLongPhonePublic;
	private String PREFS_NAME="org.javatribe.courseSystem";
	private int orgId;
	private String stuNo;
	private int mCourseCount;
	private Student s;
	private RadioGroup radioGroup_sex;
	private RadioButton radioButton_male;
	private RadioButton radioButton_female;
	private static final String TAG="AddOrg";
	private boolean is_Check_Modify=false;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView=inflater.inflate(R.layout.fragment_personal_info,null);
		editTextName=(EditText)rootView.findViewById(R.id.fpi_et_name);
		editTextNo=(EditText)rootView.findViewById(R.id.fpi_et_no);
		editTextDepartment=(EditText)rootView.findViewById(R.id.fpi_et_department);
		editTextLongNumber=(EditText)rootView.findViewById(R.id.fpi_et_longPhoneNumber);
		editTextShortNumber=(EditText)rootView.findViewById(R.id.fpi_et_shortPhoneNumber);
		editTextSchoolName=(EditText)rootView.findViewById(R.id.fpi_et_schoolName);
		editTextCourseCount=(EditText)rootView.findViewById(R.id.fpi_et_courseCount);
		isLongPhonePublic=(ToggleButton)rootView.findViewById(R.id.fpi_sw_isLongPhonePublic);
		radioGroup_sex=(RadioGroup)rootView.findViewById(R.id.fpi_rg_gender);
		radioButton_male=(RadioButton)rootView.findViewById(R.id.fpi_rb_male);
		radioButton_female=(RadioButton)rootView.findViewById(R.id.fpi_rb_female);

		resetInfo_handler=new Handler(new Callback(){
			public boolean handleMessage(Message msg) {	
				String result=msg.obj.toString();
				Log.i("Reset Result","==="+result+"====");
				return true;
			}
			
		});
		
		getStuInfo_handler=new Handler(new Callback(){
			public boolean handleMessage(Message msg) {	
				String result=msg.obj.toString();
				if (msg.obj != null) {
						Log.i("===PersonInfo msg.obj====",msg.obj.toString());
						Type type=new TypeToken<Student>(){}.getType();
						Log.i("type_organization=====",type+"");
						s=new Gson().fromJson(result,type);
						Log.i("s.getLongTele",s.getLongTele());
						editTextName.setText(s.getName());
						editTextNo.setText(s.getStuNo());
						editTextDepartment.setText(s.getDept());
						editTextLongNumber.setText(s.getLongTele());
						editTextShortNumber.setText(s.getShortTele());
						editTextSchoolName.setText(s.getSchoolName());
						Log.i("====isLongTelePublic====",s.isLongTelePublic()+"");
						editTextCourseCount.setText(s.getDailyCourseCount()+"");
						if(s.isLongTelePublic()){           
							isLongPhonePublic.setText("公开");
						}
						else{
							isLongPhonePublic.setText("不公开"); 
						}
						if(s.getGender().equals("nv")){      //此处判断条件要根据服务器端数据库实际情况来，要更改
							radioButton_female.setChecked(true);
						}
						else{
							radioButton_male.setChecked(true);
						}
					
				}
				return true;
			}
			
		});
		
		setData();
		
		
		//只有在设置了文本后设置inputType为null才有效。
		setEditTextUnEditable();//设置文本框不可编辑
	    btnOk=(Button)rootView.findViewById(R.id.fpi_btn_ok);
	    btnOk.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Log.i("is_Check_Modify",is_Check_Modify+"");
				if(is_Check_Modify){
					final Student student=s;
					student.setDailyCourseCount(Integer.parseInt(editTextCourseCount.getText().toString()));
					if(radioButton_female.isChecked()){
						student.setGender("nv");
					}
					else{
						student.setGender("na");               //此处需要修改
					}
					student.setLongTele(editTextLongNumber.getText().toString());
					if(isLongPhonePublic.getText().toString().equals("公开")){
						student.setLongTelePublic(true);
					}
					else{
						student.setLongTelePublic(false);
					}
					student.setDept(editTextDepartment.getText().toString());
					student.setName(editTextName.getText().toString());
					student.setSchoolName(editTextSchoolName.getText().toString());
					student.setStuNo(editTextNo.getText().toString());
					student.setShortTele(editTextShortNumber.getText().toString());
					student.setMajor(student.getMajor());
					Thread thread=new Thread("PostReset"){
						Message message=resetInfo_handler.obtainMessage();
						String temp;
						public void run(){
							try{
								String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_STUDENT+"/"+Constant.STUDENT_UPDATE_STUDENT_ACTION;
								Map<String,Object> params=new HashMap<String,Object>();
								params.put("student",student);
								temp=GetDataWithJson.getDataWithJsonViaObjectAndStringList(url, params, null, null);
							}catch(Exception e){
								e.printStackTrace();
							}
							message.obj=temp;
							Log.i("temp",temp);
							resetInfo_handler.sendMessage(message);
						}
						
					};
					thread.start();
				}
			}
	    	
	    });
	    btnModify=(Button)rootView.findViewById(R.id.fpi_btn_modify);
	    btnModify.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				is_Check_Modify=true;
				setEditTextEditable();	
			}
	    	
	    });
	    
		return rootView;
	}
	private void setData()
	{
		SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
		String sno=setting.getString("stuNo", "121542100");
		String schoolname=setting.getString("schoolName","syzx");
		Log.i("======PersonalInfo sno=====",sno);
		Log.i("======PersonalInfo schoolName======",schoolname);
		final String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_STUDENT+"/"+Constant.STUDENT_LOAD_STUDENT_BY_SNO_ACTION;
		Log.i("====url====",url);
		final Map<String,String> data=new HashMap<String,String>();
		data.put("sno", sno);
		data.put("schoolname",schoolname);
		Thread thread=new Thread("getStuInfo"){
			Message message=getStuInfo_handler.obtainMessage();
			String state;
			public void run(){
				try{
					state=GetDataWithJson.getDataWithJsonViaSimpleData(url, data);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				message.obj=state;
				Log.i("state",state);
				getStuInfo_handler.sendMessage(message);
			}
		};
		thread.start();	
	}
	private void setEditTextEditable()//设置文本框可编辑的方法
	{
		editTextName.setEnabled(true);
		editTextNo.setEnabled(true);
		editTextDepartment.setEnabled(true);
		editTextLongNumber.setEnabled(true);
		editTextShortNumber.setEnabled(true);
		editTextSchoolName.setEnabled(true);
		isLongPhonePublic.setEnabled(true);
		editTextCourseCount.setEnabled(true);
		radioGroup_sex.setEnabled(true);
		radioButton_male.setEnabled(true);
		radioButton_female.setEnabled(true);
	}
	private void setEditTextUnEditable()//设置文本框不可编辑的方法
	{
		editTextName.setEnabled(false);
		editTextNo.setEnabled(false);
		editTextDepartment.setEnabled(false);
		editTextLongNumber.setEnabled(false);
		editTextShortNumber.setEnabled(false);
		editTextSchoolName.setEnabled(false);
		isLongPhonePublic.setEnabled(false);
		editTextCourseCount.setEnabled(false);
		radioGroup_sex.setEnabled(false);
		radioButton_male.setEnabled(false);
		radioButton_female.setEnabled(false);
	}


}
