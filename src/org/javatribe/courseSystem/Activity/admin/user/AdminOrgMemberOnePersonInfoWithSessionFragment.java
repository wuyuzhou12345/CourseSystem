package org.javatribe.courseSystem.Activity.admin.user;

import java.util.HashMap;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.vo.PersonInfo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
//import org.javatr
/**
 * 某生的具体信息，例如长短号，学号，部门，姓名等信息
 *@author zhou
 *2014/10/18
 */
public class AdminOrgMemberOnePersonInfoWithSessionFragment extends Fragment{
	
	private static final String TAG="AdminOrgMemberOnePersonInfoWithSession";
	private String PREFS_NAME="org.javatribe.courseSystem";
	private Handler delete_stu_handler;
	private Handler setStudentToAdminHandler;
	private Handler setAdminToStudentHandler;
	private int orgId;
	private String sno;
	private TextView textViewPersonName;
	private TextView textViewDept;
	private TextView textViewDeptName;
	private TextView textViewLongTele;
	private TextView textViewLongTeleDetail;
	private TextView textViewShortTele;
	private TextView textViewShortTeleDetail;
	private TextView textViewPersonNo;
	private TextView textViewPersonNoDetail;

	private Button buttonDeleteStu;
	private Button buttonSetAdmin;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.fragment_no_class_person_infomation,container, false);
		Bundle bundle=getArguments();
		
		textViewPersonName=(TextView)rootView.findViewById(R.id.fncpi_tv_person_name);
		textViewDept=(TextView)rootView.findViewById(R.id.fncpi_tv_dept);
		textViewPersonNo=(TextView)rootView.findViewById(R.id.fncpi_tv_stu_no);
		textViewPersonNoDetail=(TextView)rootView.findViewById(R.id.fncpi_tv_stu_no_detail);	
		textViewLongTele=(TextView)rootView.findViewById(R.id.fncpi_tv_long_tele);
		textViewLongTeleDetail=(TextView)rootView.findViewById(R.id.fncpi_tv_long_tele_detail);		
		textViewShortTele=(TextView)rootView.findViewById(R.id.fncpi_tv_short_tele);
		textViewShortTeleDetail=(TextView)rootView.findViewById(R.id.fncpi_tv_short_tele_detail);
		textViewDeptName=(TextView)rootView.findViewById(R.id.fncpi_tv_dept_name);
		textViewDeptName.setText(bundle.getString("dept"));
		textViewPersonName.setText(bundle.getString("name"));
		textViewPersonNoDetail.setText(bundle.getString("no"));
		textViewLongTeleDetail.setText(bundle.getString("tele_long"));
		textViewShortTeleDetail.setText(bundle.getString("tele_short"));		
		buttonSetAdmin=(Button)rootView.findViewById(R.id.fncpi_btn_set_admin);
		buttonDeleteStu=(Button)rootView.findViewById(R.id.fncpi_btn_delete_stu);
		
		delete_stu_handler=new Handler(new Callback(){
			public boolean handleMessage(Message msg){
				String result=msg.obj.toString();
				Log.i(TAG,"deleteStuResult="+result);
				AdminDeptMemberFragment adminDeptMemberFragment=new AdminDeptMemberFragment();
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,adminDeptMemberFragment).addToBackStack(null).commit();
				return true;
			}
		});
		setStudentToAdminHandler=new Handler(new Callback(){
			public boolean handleMessage(Message msg){
				String result=msg.obj.toString();
				Log.i(TAG,"setStudentToAdminResult="+result);
				AdminDeptMemberFragment adminDeptMemberFragment=new AdminDeptMemberFragment();
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,adminDeptMemberFragment).addToBackStack(null).commit();
				return true;
			}
		});
		setAdminToStudentHandler=new Handler(new Callback(){
			public boolean handleMessage(Message msg){
				String result=msg.obj.toString();
				Log.i(TAG,"setAdminToStudentResult="+result);
				AdminDeptMemberFragment adminDeptMemberFragment=new AdminDeptMemberFragment();
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,adminDeptMemberFragment).addToBackStack(null).commit();
				return true;
			}
		});
		final PersonInfo per=new PersonInfo();
		per.setDept(bundle.getString("dept"));
		per.setName(bundle.getString("name"));
		per.setId(bundle.getString("no"));
		per.setTele_long(bundle.getString("tele_long"));
		per.setTele_short(bundle.getString("tele_short"));
		per.setImage_power(bundle.getInt("image_power"));
		
		if(per.getImage_power()==R.drawable.admin_power)
			buttonSetAdmin.setText("取消管理员");
		else
			buttonSetAdmin.setText("设为管理员");
		
		buttonSetAdmin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(buttonSetAdmin.getText().equals("取消管理员")){
					Thread thread=new Thread("setAdminToStudent"){
						public void run(){
							Message message=setAdminToStudentHandler.obtainMessage();
							String state=null;
							try{
								SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
								orgId=setting.getInt("adminOrgId", 0);
								sno=per.getId();
								Log.i(TAG,"orgId="+orgId);
								Log.i(TAG,"sno="+sno);
								String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_STU_ORG_SESSION+"/"+Constant.ADMIN_STU_ORG_SESSION_SET_VICE_ADMIN_AS_STUDENT_ACTION;
								Map<String,String> params=new HashMap<String,String>();
								params.put("orgId", orgId+"");
								params.put("sno",sno);
								state=GetDataWithJson.getDataWithJsonViaSimpleData(url, params);
								Log.i(TAG,"state="+state);
							}
							catch(Exception e){
								e.printStackTrace();
							}
							message.obj=state;
							setAdminToStudentHandler.sendMessage(message);
						}
					};
					thread.start();
				}
				else{
					Thread thread=new Thread("setStudentToAdmin"){
						public void run(){
							Message message=setStudentToAdminHandler.obtainMessage();
							String state=null;
							try{
								SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
								orgId=setting.getInt("adminOrgId", 0);
								sno=per.getId();
								Log.i(TAG,"orgId="+orgId);
								Log.i(TAG,"sno="+sno);
								String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_STU_ORG_SESSION+"/"+Constant.ADMIN_STU_ORG_SESSION_SET_STUDENT_AS_VICE_ADMIN_ACTION;
								Map<String,String> params=new HashMap<String,String>();
								params.put("orgId", orgId+"");
								params.put("sno",sno);
								state=GetDataWithJson.getDataWithJsonViaSimpleData(url, params);
								Log.i(TAG,"state="+state);
							}
							catch(Exception e){
								e.printStackTrace();
							}
							message.obj=state;
							setStudentToAdminHandler.sendMessage(message);
						}
					};
					thread.start();
				}

			}
			
		});
		buttonDeleteStu.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
				orgId=setting.getInt("adminOrgId", 0);
				sno=per.getId();
				Log.i(TAG,"orgId="+orgId);
				Log.i(TAG,"sno="+sno);
				final String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_STU_ORG_SESSION+"/"+Constant.ADMIN_STU_ORG_SESSION_DELETE_STUDENT_FROM_ORG_ACTION;
				Thread thread=new Thread("DeleteStudentFromOrg"){
					public void run(){
						Message message=delete_stu_handler.obtainMessage();
						String state = null;
						try{
							Map<String,String> params=new HashMap<String,String>();
							params.put("orgId",orgId+"");
							params.put("sno", sno);
							state=GetDataWithJson.getDataWithJsonViaSimpleData(url, params);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						message.obj=state;
						delete_stu_handler.sendMessage(message);
					}
				};
				thread.start();
			}
			
		});
		return rootView;
	}

}
