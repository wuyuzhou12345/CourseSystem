/**
 * 
 */
package org.javatribe.courseSystem.Activity.admin.user;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;





import org.javatribe.courseSystem.model.Session;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.vo.SessionInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * @author zhou
 * @version 创建时间：2014年11月11日 下午8:12:28
 * 类说明
 */
public class AdminOrgMemberOnePersonIndoWithNoSessionFragment extends Fragment{

	private static final String TAG="AdminOrgMemberOnePersonInfoWithNoSession";
	private TextView textViewPersonName;
	private TextView textViewDept;
	private TextView textViewLongTele;
	private TextView textViewLongTeleDetail;
	private TextView textViewShortTele;
	private TextView textViewShortTeleDetail;
	private TextView textViewPersonNo;
	private TextView textViewPersonNoDetail;
	private Spinner spinnerSession;
	private Button buttonDeleteStu;
	private Button buttonSetAdmin;
	private Button buttonSubmitSession;
	private ArrayAdapter adapter;
	private String PREFS_NAME="org.javatribe.courseSystem";
	private Handler delete_stu_handler;
	private Handler setStudentToAdminHandler;
	private Handler setAdminToStudentHandler;
	private Handler sumbitSessionHandler;
	private Handler loadOrgSessionHandler;
	private int orgId;
	private String sno;
	private List<String> list_session_name=new ArrayList<String>();
	private Map<String,String> list_session_no=new HashMap<String,String>();
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View rootView=inflater.inflate(R.layout.fragment_person_info_extend,container, false);
		final Bundle bundle=getArguments();
		
		textViewPersonName=(TextView)rootView.findViewById(R.id.fpie_tv_person_name);
		
		textViewDept=(TextView)rootView.findViewById(R.id.fpie_tv_dept);
		
	
		
		textViewPersonNo=(TextView)rootView.findViewById(R.id.fpie_tv_stu_no);
		textViewPersonNoDetail=(TextView)rootView.findViewById(R.id.fpie_tv_stu_no_detail);
		
		textViewLongTele=(TextView)rootView.findViewById(R.id.fpie_tv_long_tele);
		textViewLongTeleDetail=(TextView)rootView.findViewById(R.id.fpie_tv_long_tele_detail);
		
		textViewShortTele=(TextView)rootView.findViewById(R.id.fpie_tv_short_tele);
		textViewShortTeleDetail=(TextView)rootView.findViewById(R.id.fpie_tv_short_tele_detail);

		textViewPersonName.setText(bundle.getString("name"));
		textViewPersonNoDetail.setText(bundle.getString("no"));
		textViewLongTeleDetail.setText(bundle.getString("tele_long"));
		textViewShortTeleDetail.setText(bundle.getString("tele_short"));
		
		sumbitSessionHandler=new Handler(new Callback(){
			public boolean handleMessage(Message msg) {
				String result=msg.obj.toString();
				Log.i(TAG,"sumbitSession="+result);
				AdminDeptMemberFragment adminDeptMemberFragment=new AdminDeptMemberFragment();
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,adminDeptMemberFragment).addToBackStack(null).commit();
				return false;
			}
			
		});
		loadOrgSessionHandler=new Handler(new Callback(){
			public boolean handleMessage(Message msg) {
				List<Session> list_session=new ArrayList<Session>();
				String result=msg.obj.toString();
				Type type=new TypeToken<List<Session>>(){}.getType();
				list_session=new Gson().fromJson(result,type);
				for(Session si:list_session){
					list_session_name.add(si.getSessionName());
					Log.i(TAG,"list_session_name.getName()"+si.getSessionName());
					list_session_no.put(si.getSessionName(),si.getSessionId()+"");
				}
				String[] str=new String[list_session_name.size()+1];
				str[0]="未设置";
				for(int i=1;i<list_session_name.size()+1;i++){
					str[i]=list_session_name.get(i-1);
					Log.i(TAG,"str["+i+"]="+str[i]);
				}
				spinnerSession=(Spinner)rootView.findViewById(R.id.fpie_sp_session_name);
				adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,str);
				spinnerSession.setAdapter(adapter);
				return true;
			}
			
		});
		
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
		
		initData();
		
		buttonSubmitSession=(Button)rootView.findViewById(R.id.fpie_btn_submit);
		buttonSetAdmin=(Button)rootView.findViewById(R.id.fpie_btn_set_admin);
		buttonDeleteStu=(Button)rootView.findViewById(R.id.fpie_btn_delete_stu);
		final org.javatribe.courseSystem.vo.PersonItem per=new org.javatribe.courseSystem.vo.PersonItem();
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
		
		buttonSubmitSession.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
				orgId=setting.getInt("adminOrgId", 0);
				sno=bundle.getString("no");
				Thread thread=new Thread("AddSession"){
					public void run(){
						Message message=sumbitSessionHandler.obtainMessage();
						String result=null;
						try{
							String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_STU_ORG_SESSION+"/"+Constant.ADMIN_STU_ORG_SESSION_ADD_STUDENT_TO_ORG_WITH_SESSION_ACTION;
							org.javatribe.courseSystem.model.Student student=new org.javatribe.courseSystem.model.Student();
							org.javatribe.courseSystem.model.Organization organization=new org.javatribe.courseSystem.model.Organization();
							org.javatribe.courseSystem.model.Session session=new org.javatribe.courseSystem.model.Session();
							student.setStuNo(sno);
							organization.setOrgId(orgId);
							String chosen_spinner=spinnerSession.getSelectedItem().toString();
							Log.i(TAG,"choen_spinner="+chosen_spinner);
							String chosen_session_id=list_session_no.get(chosen_spinner);
							Log.i(TAG,"chosen_spinner_sessionId="+chosen_session_id);
							session.setSessionId(Integer.parseInt(chosen_session_id));
							Map<String,Object> params=new HashMap<String,Object>();
							params.put("student",student);
							params.put("organization",organization);
							params.put("session",session);
							result=GetDataWithJson.getDataWithJsonViaObjectAndStringList(url,params,null,null);
							Log.i(TAG,"submitSessionSuccessfully="+result);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						message.obj=result;
						sumbitSessionHandler.sendMessage(message);
					}
				};
				thread.start();
				
			}
			
		});
		
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
								sno=setting.getString("stuNo", "121542100");
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
								sno=setting.getString("stuNo", "121542100");
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
				sno=setting.getString("stuNo", "121542100");
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
	public void initData(){
		Thread thread=new Thread("LoadOrgSession"){
			Message message=loadOrgSessionHandler.obtainMessage();
			String result=null;
			public void run(){
				try{
					String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_SESSION+"/"+Constant.ADMIN_SESSION_GET_BY_ORGID_ACTION;
					SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
					orgId=setting.getInt("adminOrgId", 0);
					Map<String,String> params=new HashMap<String,String>();
					params.put("orgId", orgId+"");
					result=GetDataWithJson.getDataWithJsonViaSimpleData(url, params);
					Log.i(TAG,"LOAD_SESSION_RESULT="+result);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				message.obj=result;
				loadOrgSessionHandler.sendMessage(message);
			}
			
			
			
		};
		thread.start();
	}
}
