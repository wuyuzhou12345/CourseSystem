package org.javatribe.courseSystem.Activity.organization;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.model.Organization;
import org.javatribe.courseSystem.model.Session;
import org.javatribe.courseSystem.model.Student;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.util.JsonUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**组织简介
 * @author zhou
 *2014/9/28
 */
public class OrgDetailFragment extends Fragment {

	ImageView fdo_iv_org;
	TextView fdo_tv_org_name;
	Button fdo_btn_join;
	TextView fdo_org_title;
	TextView fdo_information;
	Spinner spinner_org_dept;
	ArrayAdapter choose_org_dept;
	Handler handler;
	Handler handler_enterOrg;
	Handler handler_enterOrgWithSession;
	Bundle bundle;
	List<Session> list_session=new ArrayList<Session>();
	List<String> list_session_name=new ArrayList<String>();
	Map<String,String> session_name_id=new HashMap<String,String>();
    String PREFS_NAME="org.javatribe.courseSystem";
    int orgId;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View rootView=inflater.inflate(R.layout.fragment_detail_org, container, false);
		bundle=getArguments();
		fdo_iv_org=(ImageView)rootView.findViewById(R.id.fdo_iv_org);
		fdo_tv_org_name=(TextView)rootView.findViewById(R.id.fdo_tv_org_name);
		fdo_btn_join=(Button)rootView.findViewById(R.id.fdo_btn_join);
		fdo_org_title=(TextView)rootView.findViewById(R.id.fdo_org_title);
		fdo_information=(TextView)rootView.findViewById(R.id.fdo_information);		
		fdo_iv_org.setImageResource(bundle.getInt("img"));
		fdo_tv_org_name.setText(bundle.getString("org_name"));
		fdo_information.setText(bundle.getString("info"));

		getSessionInfo();
		handler_enterOrg=new Handler(){
			public void handlerMessage(android.os.Message msg){
				fdo_btn_join.setClickable(true);
				if(msg.obj!=null){
					if(Boolean.parseBoolean((String) msg.obj)){
						Log.i("provide success","provide success");
						Toast.makeText(getActivity(),"已提交申请，请耐心等待！",Toast.LENGTH_LONG).show();
					}
					else{
						Log.i("provide failure",msg.obj.toString());
						Toast.makeText(getActivity(),"还未成功提交，请重新尝试！",Toast.LENGTH_LONG).show();
					}
				}
				else{
					Log.i("not connect","not connect");
					Toast.makeText(getActivity(),"null not connect!",Toast.LENGTH_LONG).show();
				}
			}
		};
		handler_enterOrgWithSession=new Handler(){
			public void handleMessage(android.os.Message msg){
				fdo_btn_join.setClickable(true);
				if(msg.obj!=null){
					if(Boolean.parseBoolean((String) msg.obj)){
						Log.i("provide success","provide success");
						Toast.makeText(getActivity(),"已提交申请，请耐心等待！",Toast.LENGTH_LONG).show();
					}
					else{
						Log.i("provide failure",msg.obj.toString());
						Toast.makeText(getActivity(),"还未成功提交，晴重新尝试！",Toast.LENGTH_LONG).show();
					}
				} 
				else{
					Log.i("not connect","not connect");
					Toast.makeText(getActivity(),"null not connect!",Toast.LENGTH_LONG).show();
				}
			
			}
		};
	    handler = new Handler(){
	        public void handleMessage(android.os.Message msg) {
	            if(msg.obj != null){
	            		String result=msg.obj.toString();
	            		if(result.equals("[]")){
	            			Log.i("no session","no session");
	            			String[] session_name=new String[1];
	            			session_name[0]="还未添加任何部门";
	            			spinner_org_dept=(Spinner)rootView.findViewById(R.id.fdo_org_dept);
		            		choose_org_dept=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,session_name);
		            		spinner_org_dept.setAdapter(choose_org_dept);
	            			Toast.makeText(getActivity(), "当前组织还未设置任何部门！", Toast.LENGTH_LONG).show();
	            			SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
							final String sno=setting.getString("stuNo", "121542100");
							final String school_name=setting.getString("schoolName","syzx");
							Log.i("======SharedPreference sno=====",sno);
							Log.i("======SharedPreference  schoolName======",school_name);
	            			fdo_btn_join.setOnClickListener(new OnClickListener(){
	            				public void onClick(View arg0) {
	            					fdo_btn_join.setClickable(false);
	            					Thread thread=new Thread("enterOrg"){
	            						public void run(){
	            							Message message=handler_enterOrg.obtainMessage();
	            							String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ORGANIZATION+"/"+Constant.ORGANIZATION_ENTER_ORG_ACTION;
	    									Log.i("url",url);
	    									Student student=new Student();
	    									student.setStuNo(sno);
	    									Organization organization=new Organization();
	    									organization.setOrgId(orgId);
	    									int isAdminChecked=0;
	    									int permission=0;
	    									Map<String,Object> objects=new HashMap<String,Object>();
	    									objects.put("student", student);
	    									objects.put("organization", organization);
	    									Map<String,Object> data=new HashMap<String,Object>();
	    									data.put("isAdminChecked",isAdminChecked);
	    									data.put("permission",permission);
	    									try{
	    										String js=GetDataWithJson.getDataWithJsonViaObjectAndSimpleData(url,objects,data);
	    										Log.i("======js======",js);
	    										message.obj=js;
	    										
	    									}
	    									catch(Exception e){
	    										e.printStackTrace();
	    									}
	    								    handler_enterOrg.sendMessage(message);
	            						}
	            					};
	            					thread.start();
	            					
	            				}
	            				
	            			});
	            		}
	            		else{
	            		Log.i("result",result);
	            		JsonUtil jsonUtil=new JsonUtil<Session>(){};
	            		Type type=new TypeToken<List<Session>>(){}.getType();
						list_session=new Gson().fromJson(result,type);
	            		for(Session session:list_session){
	            			String sessionName=session.getSessionName();
	            			list_session_name.add(sessionName);
	            			Log.i("list_session_name",sessionName);
	            			session_name_id.put(session.getSessionName(),session.getSessionId()+"");
	            		}
	            		String[] session_name=new String[list_session.size()];
	            		for(int i=0;i<session_name.length;i++){
	            			session_name[i]=list_session_name.get(i);
	            		}
	            		spinner_org_dept=(Spinner)rootView.findViewById(R.id.fdo_org_dept);
	            		choose_org_dept=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,session_name);
	            		spinner_org_dept.setAdapter(choose_org_dept);
            			fdo_btn_join.setOnClickListener(new OnClickListener(){
            				public void onClick(View arg0) {
            					fdo_btn_join.setClickable(false);
    	            			SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
    							final String sno=setting.getString("stuNo", "121542100");
    							final String school_name=setting.getString("schoolName","syzx");
            					Thread thread=new Thread("enterOrgWithSession"){
            						public void run(){
            							Message message=handler_enterOrgWithSession.obtainMessage();
            							String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ORGANIZATION+"/"+Constant.ORGANIZATION_ENTER_ORG_WITH_SESSION_ACTION;
    									Log.i("url",url);
    									Student student=new Student();
    									student.setStuNo(sno);
    									Organization organization=new Organization();
    									organization.setOrgId(orgId);
    									Session session=new Session();
    									Log.i("spinner_org_session",spinner_org_dept.getSelectedItem().toString());
    									String sessionId=session_name_id.get(spinner_org_dept.getSelectedItem().toString());
    									Log.i("======sessionId=========",sessionId);
    									session.setSessionId(Integer.parseInt(sessionId));
    									int isAdminChecked=0;
    									int permission=0;
    									Map<String,Object> objects=new HashMap<String,Object>();
    									objects.put("student", student);
    									objects.put("organization", organization);
    									objects.put("session",session);
    									Map<String,Object> data=new HashMap<String,Object>();
    									data.put("isAdminChecked",isAdminChecked);
    									data.put("permission",permission);
    									try{
    										String js=GetDataWithJson.getDataWithJsonViaObjectAndSimpleData(url,objects,data);
    										Log.i("======js======",js);
    										message.obj=js;
    										
    									}
    									catch(Exception e){
    										e.printStackTrace();
    									}
    								    handler_enterOrg.sendMessage(message);
            						}
            					};
            					thread.start();
            				}
            				
            			});
	            		}
	            }
	            else
	            	Log.i("not connect","not connect");
	        };
	    };

		return rootView;
		
	}

	private void getSessionInfo() {
			Thread thread=new Thread("getSessionInfo"){
			public void run(){
				String state=null;
				Message message=handler.obtainMessage();
				try{
				String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ORGANIZATION+"/"+Constant.ORGANIZATION_ALL_SESSION_BY_ORGID_ACTION;
				orgId=bundle.getInt("orgId");
				Organization org=new Organization();
				org.setOrgId(orgId);
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("organization",org);
				state=GetDataWithJson.getDataWithJsonViaObjectAndStringList(url, params, null,null);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				message.obj=state;
				handler.sendMessage(message);
			}
		};
		thread.start();
		
	}
	
}