package org.javatribe.courseSystem.Activity.course;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;


import org.javatribe.courseSystem.Activity.Util.SerializableList;
import org.javatribe.courseSystem.Activity.Util.Validator;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Message;
import org.javatribe.courseSystem.model.Session;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.ui.ChooseTimeDialog;
import org.javatribe.courseSystem.util.JsonUtil;

import com.ab.task.AbTaskCallback;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**根据部门和时间检索无课的人
 * @author qing
 *2014年12月10日
 */
public class SearchCourseFragment extends Fragment implements OnClickListener{

	private Button btnChooseSession;
	private Button btnChooseTime;
	private AbTaskQueue mAbTaskQueue = null;
	private EditText editTextSession;
	private EditText editTextTime;
	private Button btnSearch;
	private Bundle bundle;
	private String[] sessionNames={"秘书部","组织部","培训部","外联部","宣传部"};//可选择的部门名称
	private List chosenSession;
	private int orgId;
	private String json;
	private int weekday;//要查找星期几
	private int weekType;//查找的类型
	private int startCourse;//开始节数
	private int endCourse;//结束节数 
	private List<Session> sessions;
	private SharedPreferences setting;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.fragment_search_course, null);
		
		orgId=setting.getInt("orgId", 1);
		btnChooseSession=(Button)rootView.findViewById(R.id.fsc_btn_chooseSession);
		btnChooseTime=(Button)rootView.findViewById(R.id.fsc_btn_chooseTime);
		btnSearch=(Button)rootView.findViewById(R.id.fsc_btn_search);
		mAbTaskQueue=AbTaskQueue.getInstance();
		editTextSession=(EditText)rootView.findViewById(R.id.fsc_dt_searchSession);
		editTextTime=(EditText)rootView.findViewById(R.id.fsc_dt_searchTime);
		btnChooseSession.setOnClickListener(this);
		btnChooseTime.setOnClickListener(this);
	btnSearch.setOnClickListener(this);
		chosenSession=new ArrayList();
		
		return rootView;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.fsc_btn_chooseTime:
		ChooseTimeDialog dialog=new ChooseTimeDialog(getActivity(),this);
		
		dialog.show(getActivity().getSupportFragmentManager(),"dialog");
			break;
		case R.id.fsc_btn_chooseSession:
			initSession();
			showChooseSessionDialog();
			break;
		case R.id.fsc_btn_search:
			if(Validator.isEmpty(editTextSession)&&Validator.isEmpty(editTextTime))
			{
				Toast.makeText(getActivity(), "查找内容不能为空！", Toast.LENGTH_SHORT).show();
				
			}
			else
			{
				Bundle bundle=new Bundle();
				bundle.putInt("startCourse",startCourse);
				bundle.putInt("endCourse", endCourse);
				bundle.putInt("weekday", weekday);
				bundle.putInt("weekType",weekType);
				if(chosenSession!=null&&!chosenSession.isEmpty())
				{
					SerializableList list=new SerializableList(chosenSession);
					bundle.putSerializable("chosenSession", list);
				}
				AfterSearchCourseFragment afterSearchCourseFragment=new AfterSearchCourseFragment();
				afterSearchCourseFragment.setArguments(bundle);
				getActivity().getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, afterSearchCourseFragment).addToBackStack(null)
				.commit();
			}
		}
	}
	public void chooseTimeClickPositiveButton(Bundle bundle)//响应选择时间对话框的确定按钮。
	{
		String day=bundle.getString("day");
		weekday=Integer.parseInt(day);
		String week=bundle.getString("week");
		if(week.equals("双周"))
		weekType=1;
		else if (week.equals("单周"))
		{
			weekType=2;
		}
		else
			weekType=3;
		startCourse=bundle.getInt("startCourse");
		endCourse=bundle.getInt("endCourse");
		
		String str=day+"从第"+startCourse+"节到第"+endCourse+"节"+week;
		
		
		editTextTime.setText(str);
	}
	public void showChooseSessionDialog()
	{
		chosenSession.clear();
		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		builder=builder.setTitle("请选择部门");
		builder.setMultiChoiceItems(sessionNames, null,new DialogInterface.OnMultiChoiceClickListener() {
				
			
			@Override	
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
					if(isChecked)
					
						chosenSession.add(sessionNames[which]);
			
					
					
				}});
		builder=builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// TODO Auto-generated method stub
				String str="";
				for(int i=0;i<chosenSession.size();i++)
				{
					str+=chosenSession.get(i)+",";
				}
				str=str.substring(0, str.length()-1);
				editTextSession.setText(str);
			}
		});
		builder=builder.setNegativeButton("取消", null);
			
	
		AlertDialog chooseSessionDialog=builder.create();
		chooseSessionDialog.show();

	}
	//获取组织中的所有部门
	private void initSession()
	{
		final AbTaskItem refreshItem= new AbTaskItem();
		refreshItem.callback = new AbTaskCallback() {

			@Override
			public void update() {
				for(int i=0;i<sessions.size();i++)
				{
					sessionNames[i]=((Session)sessions.get(i)).getSessionName();
				}
			}

			@Override
			public void get() {
				try
				{
					Thread.sleep(1000);
					 
					Map<String,String> data=new HashMap<String,String>();
				
			
					data.put("orgId", Integer.toString(orgId));
					json = GetDataWithJson.getDataWithJsonViaSimpleData(Constant.BASE_URL+"/"+Constant.NAMESPACE_COURSE+"/"+Constant.ORGANIZATION_ALL_SESSION_BY_ORGID_ACTION,data);
					//Log.i("LeaveFor",json);
					
					if (json != null && !json.equals(Constant.GET_DATA_ERROR)){
						Log.i("LeaveFor","json not null");
					Type type=new TypeToken<List<Session>>(){}.getType();
					Log.i("hahaha","hahahaha");
					Gson g=new Gson();
					sessions=g.fromJson(json, type);
					
					
			
					
				}
					
				}
				catch(Exception e)
				{
					
				}
		  };
		};
		mAbTaskQueue.execute(refreshItem);
	}


}
