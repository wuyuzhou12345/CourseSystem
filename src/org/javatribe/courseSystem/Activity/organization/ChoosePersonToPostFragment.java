package org.javatribe.courseSystem.Activity.organization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.javatribe.courseSystem.util.JsonUtil;
import org.javatribe.courseSystem.vo.*;
import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.adapter.ListViewAdapter;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Notice;
import org.javatribe.courseSystem.net.GetDataWithJson;





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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

/**
 * 在编辑我的信息中，选择在某组织中需要发送信息的联系人Fragment
 * @author zhou
 *2014年12月3日
 */
public class ChoosePersonToPostFragment extends Fragment { 
	
	private ExpandableListView expandableList; private Button btn_send;
	private ListViewAdapter adapter;
	private Handler load_student_handler;
	private String PREFS_NAME="org.javatribe.courseSystem";
	private List<SessionInfo> list_session=new ArrayList<SessionInfo>();
	private int orgId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.fragment_choose_person_to_post,container, false);
		
		expandableList=(ExpandableListView)rootView.findViewById(R.id.fcptp_elv_expandable_listview);
		btn_send=(Button)rootView.findViewById(R.id.fcptp_btn_send);
		SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
		orgId=setting.getInt("orgId",0);
		Log.i("======ChoosePersonToPostFragment orgId======",orgId+"");
		btn_send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				collectCheckedItems();
			}
			
		});
		load_student_handler=new Handler(new Callback(){
			public boolean handleMessage(Message msg) {	
				//JsonUtil jsonUtil=new JsonUtil<PersonInfo>(){};
				String state=msg.obj.toString();
				Type type=new TypeToken<List<SessionInfo>>(){}.getType();
				list_session=new Gson().fromJson(state,type);
				List<SessionInfo> get_sessions=new ArrayList<SessionInfo>();
				for(SessionInfo si:list_session){
					Log.i("aaaa","aaaa");
					String json=si.getJson();
					Type type2=new TypeToken<List<PersonInfo>>(){}.getType();
					List<PersonInfo> list_persons=new Gson().fromJson(json, type2);
					List<PersonInfo> get_persons=new ArrayList<PersonInfo>();
					for(PersonInfo per:list_persons){
						PersonInfo person=new PersonInfo(per.getId(),per.getName(),per.getTele_short());
						get_persons.add(person);
					}
					SessionInfo sessionInfo=new SessionInfo(si.getName(),si.getName(),get_persons);
					get_sessions.add(sessionInfo);
				}
				adapter=new ListViewAdapter(getActivity(),get_sessions);
				expandableList.setAdapter(adapter);
				return true;
			}
			
		});
		initData();
		return rootView;
	}
	private void collectCheckedItems(){
		String checkedItems="";
		//List<String> checkedPerson=adapter.getCheckedPerson();
		List<String> checkedPerson=adapter.getCheckedPersonName();
		List<String> checkedId=adapter.getCheckedPerson();
		ArrayList<String> choosen_person_id=new ArrayList<String>();
		for(String id:checkedId){
			choosen_person_id.add(id);
		}
		if(checkedPerson!=null&&!checkedPerson.isEmpty()){
			for(String person:checkedPerson){
				if(checkedItems.length()>0){
					checkedItems+="、";
				}
				checkedItems+=person;
			}
		}

		Bundle bundle=new Bundle();
		bundle.putString("persons_choose", checkedItems);
		bundle.putStringArrayList("choosen_person_id", choosen_person_id);
		
		SendMessageAfterFragment sendMessageAfterFragment=new  SendMessageAfterFragment();
		 sendMessageAfterFragment.setArguments(bundle);
		 getActivity().getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame,sendMessageAfterFragment )
			.commit();
		 
	}
	private void initData() {
		Thread thread=new Thread("Session_Student"){
			Message message=load_student_handler.obtainMessage();
			public void run(){
				String result = null;
				try{
					String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_STU_ORG_SESSION+"/"+Constant.ADMIN_STU_ORG_SESSION_LOAD_STUDENT_BY_ORGID_ACTION;
					Map<String,String> params=new HashMap<String,String>();
					params.put("orgId",orgId+"");
					result=GetDataWithJson.getDataWithJsonViaSimpleData(url,params);
					Log.i("=====result=====",result);
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
