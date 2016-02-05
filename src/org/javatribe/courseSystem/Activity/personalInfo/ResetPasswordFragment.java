/**
 * 
 */
package org.javatribe.courseSystem.Activity.personalInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Student;
import org.javatribe.courseSystem.net.GetDataWithJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author zhou
 * @version 创建时间：2014年12月4日 下午11:45:18
 * 重新设置密码（学生用户）
 */
public class ResetPasswordFragment extends Fragment{

	private Handler getPassword_handler;
	private Handler updatePassword_handler;
	private TextView textViewOldPassword;
	private EditText editTextOldPassword;
	private TextView textViewNewPassword;
	private EditText editTextNewPassword;
	private TextView textViewConfirmPassword;
	private EditText editTextConfirmPassword;
	private Button btnConfirm;
	private Button btnCancel;
	private String PREFS_NAME="org.javatribe.courseSystem";
	private String getPasswordFromNet;
	private String sno;
	private String schoolname;
	private boolean yn=false;
	private String new_password;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View rootView=inflater.inflate(R.layout.fragment_reset_password,container, false);
		textViewOldPassword=(TextView)rootView.findViewById(R.id.frp_tv_old_password);
		editTextOldPassword=(EditText)rootView.findViewById(R.id.frp_et_old_password);
		textViewNewPassword=(TextView)rootView.findViewById(R.id.frp_tv_new_password);
		editTextNewPassword=(EditText)rootView.findViewById(R.id.frp_et_new_password);
		textViewConfirmPassword=(TextView)rootView.findViewById(R.id.frp_tv_confirm_password);
		editTextConfirmPassword=(EditText)rootView.findViewById(R.id.frp_et_confirm_password);
		btnConfirm=(Button)rootView.findViewById(R.id.frp_btn_confirm);
		btnCancel=(Button)rootView.findViewById(R.id.frp_btn_cancel);
		
		btnConfirm.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				
				new_password=editTextNewPassword.getText().toString().trim();
				Log.i("new_password",new_password);
				String confirm_password=editTextConfirmPassword.getText().toString().trim();
				Log.i("confirm_password",confirm_password);
				if(!new_password.equals(confirm_password)){
			    	AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
			    	builder.setTitle("Error");
			    	builder.setMessage("重置密码必须与确认密码必须相同");
			    	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			    		   public void onClick(DialogInterface dialog, int which)
			    		   {
			    		    dialog.dismiss();

			    		   }
			    		  });
			    	builder.show();
					//Toast.makeText(getActivity(),"重置密码与确认密码不一致，请重新输入", Toast.LENGTH_SHORT);
			    	editTextOldPassword.setText("");
			    	editTextNewPassword.setText("");
			    	editTextConfirmPassword.setText("");
					Log.i("not the same","not the same");
				}
				else{
					SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
					sno=setting.getString("stuNo", "121542100");
					schoolname=setting.getString("schoolName","syzx");
					Log.i("======PersonalInfo sno=====",sno);
					Log.i("======PersonalInfo schoolName======",schoolname);
					final String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_STUDENT+"/"+Constant.STUDENT_LOAD_STUDENT_BY_SNO_ACTION;
					Log.i("====url====",url);
					final Map<String,String> data=new HashMap<String,String>();
					data.put("sno", sno);
					data.put("schoolname",schoolname);
					Thread thread=new Thread("getStuInfo"){
						Message message=getPassword_handler.obtainMessage();
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
							getPassword_handler.sendMessage(message);
						}
					};
					thread.start();	
				}
			}
			
		});
		
		updatePassword_handler=new Handler(new Callback(){
			public boolean handleMessage(Message msg) {	
				String result=msg.obj.toString();
				Log.i("====updatePassword=====",result);
				return true;
			}
			
		});
		getPassword_handler=new Handler(new Callback(){
			public boolean handleMessage(Message msg) {	
				String result=msg.obj.toString();
				if (msg.obj != null) {
						Log.i("===PersonInfo msg.obj====",msg.obj.toString());
						Type type=new TypeToken<Student>(){}.getType();
						Student s=new Gson().fromJson(result,type);
						getPasswordFromNet=s.getPassword();
						Log.i("getPasswordFromNet",getPasswordFromNet);
						if(getPasswordFromNet.equals(editTextOldPassword.getText().toString().trim())){
							yn=true;
						}
					    Log.i("yn","======="+yn+"======");
						if(yn){
							Log.i("UpdateThreadStart","UpdateThreadStart");
							Thread th=new Thread("updatePassword"){
								Message msg=updatePassword_handler.obtainMessage();
								String st;
								public void run(){
									try{
										String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_STUDENT+"/"+Constant.STUDENT_UPDATE_PASSWORD_ACTION;
										Map<String,String> data=new HashMap<String,String>();
										data.put("password",new_password);
										data.put("sno",sno);
										data.put("schoolname",schoolname);
										st=GetDataWithJson.getDataWithJsonViaSimpleData(url, data);
									}
									catch(Exception e){
										e.printStackTrace();
									}
									Log.i("=====st====",st);
									msg.obj=st;
									updatePassword_handler.sendMessage(msg);
								}
							};
							th.start();
							
						}
						else{
							Toast.makeText(getActivity(), "您输入的原始密码不正确", Toast.LENGTH_SHORT);
					    	AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
					    	builder.setTitle("Error");
					    	builder.setMessage("您输入的原始密码不正确");
					    	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					    		   public void onClick(DialogInterface dialog, int which)
					    		   {
					    		    dialog.dismiss();

					    		   }
					    		  });
					    	builder.show();
					    	editTextOldPassword.setText("");
					    	editTextNewPassword.setText("");
					    	editTextConfirmPassword.setText("");
						}
				}
				return true;
			}
			
		});
		
		return rootView;
	}

}
