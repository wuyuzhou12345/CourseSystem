package org.javatribe.courseSystem.Activity.admin.user;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.adapter.AdminMemberExpandableListAdapter;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Notice;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.vo.DepartmentItem;
import org.javatribe.courseSystem.vo.PersonInfo;
import org.javatribe.courseSystem.vo.PersonItem;
import org.javatribe.courseSystem.vo.SessionInfo;

import com.ab.view.pullview.AbPullListView;
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
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
/**
 * 管理员界面中管理员可进行管理的部门及成员
 *@author zhou
 *2014/10/18
 */
public class AdminDeptMemberFragment extends Fragment{
	private static final String TAG="AdminDeptMember";
	private ExpandableListView expandableList;
	private AdminMemberExpandableListAdapter adapter;
	private List<SessionInfo> list_session;
	private List<org.javatribe.courseSystem.vo.DepartmentItem> deptList=new ArrayList<DepartmentItem>();
	private Handler load_student_handler;
	private String PREFS_NAME="org.javatribe.courseSystem";
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		View rootView=inflater.inflate(R.layout.fragment_admin_org_member,container,false);
		expandableList=(ExpandableListView)rootView.findViewById(R.id.faom_el_org_member);
		load_student_handler=new Handler(new Callback(){
			public boolean handleMessage(Message msg) {
				String result=msg.obj.toString();
				Type type=new TypeToken<List<SessionInfo>>(){}.getType();
				list_session=new Gson().fromJson(result,type);
				List<SessionInfo> get_sessions=new ArrayList<SessionInfo>();
				for(SessionInfo si:list_session){
					Log.i("aaaa","aaaa");
					String json=si.getJson();
					Type type2=new TypeToken<List<PersonInfo>>(){}.getType();
					List<PersonInfo> list_persons=new Gson().fromJson(json, type2);
					List<PersonInfo> get_persons=new ArrayList<PersonInfo>();
					for(PersonInfo per:list_persons){
						PersonInfo person=new PersonInfo(per.getId(),per.getName(),per.getImage_power(),per.getTele_short(),per.getTele_long(),per.getDept());
						get_persons.add(person);
					}
					SessionInfo sessionInfo=new SessionInfo(si.getName(),si.getName(),get_persons);
					get_sessions.add(sessionInfo);
					adapter=new AdminMemberExpandableListAdapter(getActivity(),get_sessions);
					expandableList.setAdapter(adapter);
				}
				return true;
			}
		});
		initData();
		
		expandableList.setOnChildClickListener(new OnChildClickListener(){
         //若点中某个item项则会进行界面跳转，显示该生的具体信息
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				PersonInfo choose_person=(PersonInfo)adapter.getChild(groupPosition,childPosition);
				Bundle bundle=new Bundle();
				String choose_person_name=choose_person.getName();
				bundle.putString("name", choose_person_name);
				bundle.putString("no", choose_person.getId());
				bundle.putInt("image_power",choose_person.getImage_power());
				bundle.putString("tele_short", choose_person.getTele_short());
				bundle.putString("tele_long", choose_person.getTele_long());
				bundle.putString("dept", choose_person.getDept());
				
				if(choose_person.getDept().equals("未设置")){
					AdminOrgMemberOnePersonIndoWithNoSessionFragment ncpif=new AdminOrgMemberOnePersonIndoWithNoSessionFragment();
					ncpif.setArguments(bundle);
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,ncpif).addToBackStack(null).commit();
				}
				else{
					AdminOrgMemberOnePersonInfoWithSessionFragment ncpif=new AdminOrgMemberOnePersonInfoWithSessionFragment();
					ncpif.setArguments(bundle);
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,ncpif).addToBackStack(null).commit();
				}
				return false;
			}
			
		});
		return rootView;
		
		
	}
	private void initData() {
		Thread thread=new Thread("LoadStudentByOrgId"){
			public void run(){
				Message message=load_student_handler.obtainMessage();
				String result = null;
				try{
					String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_STU_ORG_SESSION+"/"+Constant.ADMIN_STU_ORG_SESSION_LOAD_STUDENT_BY_ORGID_ACTION;
					SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
	    			final int orgId=setting.getInt("adminOrgId",0);
	    			Log.i(TAG,"ORGID="+orgId);
	    			Map<String,String> params=new HashMap<String,String>();
	    			params.put("orgId", orgId+"");
	    			result=GetDataWithJson.getDataWithJsonViaSimpleData(url, params);
	    			Log.i(TAG,"RESULT="+result);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				message.obj=result;
				load_student_handler.sendMessage(message);
			}
		};
		thread.start();
	}


	}




