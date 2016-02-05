package org.javatribe.courseSystem.Activity.organization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.Util.SerializableMap;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.LeaveApplication;
import org.javatribe.courseSystem.net.GetDataWithJson;







import org.javatribe.courseSystem.util.JsonUtil;

import com.ab.task.AbTaskCallback;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**我的请假条界面
 * @author qing
 *请假条信息从SQLite数据库取得或服务器端取得
 */
/**
 * @author qing
 *
 */

public class LeaveForListFragment extends Fragment implements OnItemClickListener{
public  final static String TAG="LeaveForListFragment";
	private AbPullListView leaveForList;
	private AbTaskQueue mAbTaskQueue = null;
	private SimpleAdapter adapter;//适配器
	private List<Map<String,Object>> list;
	private List<Map<String,Object>> newList;
	private SelectLeaveForMessage mCallBack;
	private String json;
	private List<LeaveApplication> leaveApplications;
	private final String PREFS_NAME="org.javatribe.courseSystem";
	private Handler leaveApplicationHandler;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try
		{
			mCallBack=(SelectLeaveForMessage)activity;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root=inflater.inflate(R.layout.fragment_leave_for_list, null);
		leaveForList=(AbPullListView)root.findViewById(R.id. fll_lv_leaveForList);
		mAbTaskQueue=AbTaskQueue.getInstance();
		list=new ArrayList<Map<String,Object>>();
		initLeaveList2();
	
		 leaveForList.setOnItemClickListener(this);
			leaveApplicationHandler=new Handler(){
				public void handleMessage(Message msg)
				{
					//�����Ϣ�Ǳ����͵�
					
					if(msg.what==1)
					{
						Map<String,Object> map;
						//Toast.makeText(RegisterActivity.this,"success",Toast.LENGTH_SHORT).show();
						for(LeaveApplication leaveApplication:leaveApplications)
						{
							map=new HashMap<String,Object>();
							map.put("leaveEvent", leaveApplication.getLeaveEvent());
							map.put("leaveReason",leaveApplication.getLeaveReason());
							//map.put("sendTime", leaveApplication.getSendTime());
							String json=leaveApplication.getJson();
							String sendTime=JsonUtil.getStringFromJson(json, "sendTime");
							map.put("sendTime", sendTime);
							map.put("leaveId", leaveApplication.getLeaveId());
							 list.add(map);
							 Log.i("LeaveFor", "leaveApplication!!");
						}
						adapter=new SimpleAdapter(getActivity(),list,R.layout.fragment_leave_for_list_item,new String[]{"leaveEvent","sendTime"},new int[]{R.id.fll_item_leaveForEvent,R.id.fll_item_date});
						
						 leaveForList.setAdapter(adapter);
					}
					else if(msg.what==-1)
					{
						Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
					}
					
				}
			
			};
			
		 return root;

	}
	/*
	 * 本来应该是从数据库中取得的。。。
	 */
	
	@Override
	public void onItemClick(AdapterView<?>  parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Log.i("click",position+"");
			Bundle info=new Bundle();
//			info.putString("event", list.get(position).get("leaveEvent").toString());
//			info.putString("reason",  list.get(position).get("leaveReason").toString());
			info.putSerializable("detail", new SerializableMap(list.get(position-1)));
			//mCallBack.onSelectLeaveForMessage(info);
			LeaveForDetailFragment leaveForDetailFragment=new LeaveForDetailFragment();
			leaveForDetailFragment.setArguments(info);
			getActivity().getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame,leaveForDetailFragment).addToBackStack(null)
			.commit();
		
		
		
	}
	public interface SelectLeaveForMessage
	{
		public void onSelectLeaveForMessage(Bundle argument);
		
	}
	public void initLeaveList2()
	{
		Thread thread=new Thread()
		{
			public void run()
			{
				SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
				String sno=setting.getString("stuNo", "121542104");
				int orgId =setting.getInt("orgId",1);
		Map<String,String> data=new HashMap<String,String>();
		data.put("sno", sno);
		data.put("orgId",Integer.toString(orgId));
		Message msg=leaveApplicationHandler.obtainMessage();
		json = GetDataWithJson.getDataWithJsonViaSimpleData(Constant.BASE_URL+"/"+Constant.NAMESPACE_LEAVEAPPLICATION+"/"+Constant.LEAVEAPPLICATION_ALL_MY_LEAVEAPPLICATION_ACTION,data);
		Log.i("LeaveFor",json);
		if(json!="error")
		{
			 Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		Type type=new TypeToken<List<LeaveApplication>>(){}.getType();
		leaveApplications=gson.fromJson(json,type);
		
		msg.what=1;
		
		}
		else
		{
			msg.what=-1;
		}
		leaveApplicationHandler.sendMessage(msg);
			}
		};
		thread.start();
	}
	public void initLeaveList()
	{
		list=new ArrayList<Map<String,Object>>();
        //打开关闭下拉刷新加载更多功能
		leaveForList.setPullRefreshEnable(true); 
		leaveForList.setPullLoadEnable(true);
		final AbTaskItem refreshItem= new AbTaskItem();
		refreshItem.callback = new AbTaskCallback() {

			@Override
			public void update() {
				//removeProgressDialog();
				list.clear();
				
				if(newList!=null && newList.size()>0){
	                list.addAll(newList);
	                adapter.notifyDataSetChanged();
	                newList.clear();
   		    	}
				leaveForList.stopRefresh();
			}

			@Override
			public void get() {
				try
				{
					Thread.sleep(1000);
					 Log.i("LeaveFor", "getData!!");
					Map<String,String> data=new HashMap<String,String>();
					SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
					String sno=setting.getString("stuNo", "121542100");
					int orgId =setting.getInt("orgId",0);
					data.put("sno", "121542117");
					data.put("orgId", Integer.toString(2));
					json = GetDataWithJson.getDataWithJsonViaSimpleData(Constant.BASE_URL+"/"+Constant.NAMESPACE_LEAVEAPPLICATION+"/"+Constant.LEAVEAPPLICATION_ALL_MY_LEAVEAPPLICATION_ACTION,data);
					Log.i("LeaveFor",json);
					//Toast.makeText(getActivity(), json, Toast.LENGTH_SHORT).show();
					if (json != null && !json.equals(Constant.GET_DATA_ERROR)){
						Log.i("LeaveFor","json not null");
					Type type=new TypeToken<List<LeaveApplication>>(){}.getType();
					leaveApplications=new Gson().fromJson(json,type);
					newList=new ArrayList<Map<String,Object>>();
					Map<String,Object> map;
					Log.i("LeaveFor", "leaveApplication.size="+leaveApplications.size());
					for(LeaveApplication leaveApplication:leaveApplications)
					{
						map=new HashMap<String,Object>();
						map.put("leaveEvent", leaveApplication.getLeaveEvent());
						map.put("leaveReason",leaveApplication.getLeaveReason());
						map.put("sendTime", leaveApplication.getSendTime());
						map.put("leaveId", leaveApplication.getLeaveId());
						 newList.add(map);
						 Log.i("LeaveFor", "leaveApplication!!");
					}
				}
					Log.i("LeaveFor", "GO OUT OF IF");
				}
				catch(Exception e)
				{
					
				}
		  };
		};
		
//		final AbTaskItem scrollItem = new AbTaskItem();
//		scrollItem.callback = new AbTaskCallback() {
//
//			@Override
//			public void update() {
//				if(newList!=null && newList.size()>0){
//					list.addAll(newList);
//					adapter.notifyDataSetChanged();
//					newList.clear();
//					leaveForList.stopLoadMore(true);
//                }else{
//                	//没有新数据了
//                	leaveForList.stopLoadMore(false);
//                }
//				
//			}
//
//			@Override
//			public void get() {
//	   		    try {
//	   		    	
//	   		    	Thread.sleep(1000);
//	   		    	newList = new ArrayList<Map<String, Object>>();
//	   		    	Map<String, Object> map = null;
//	   		    	
//	   		    	for (int i = 0; i < 10; i++) {
//	   		    		map = new HashMap<String, Object>();
//	   					map.put("itemsIcon",mPhotoList.get(new Random().nextInt(mPhotoList.size())));
//		   		    	map.put("itemsTitle", "item上拉"+i);
//		   		    	map.put("itemsText", "item上拉..."+i);
//		   		    	newList.add(map);
//	   				}
//	   		    	
//	   		    } catch (Exception e) {
//	   		    	currentPage--;
//	   		    	newList.clear();
//	   		    	showToastInThread(e.getMessage());
//	   		    }
//		  };
//		};
	leaveForList.setAbOnListViewListener(new AbOnListViewListener(){

			@Override
			public void onRefresh() {
				mAbTaskQueue.execute(refreshItem);
			}

			@Override
			public void onLoadMore() {
//				mAbTaskQueue.execute(item2);
			}
			
		});
		
    	//第一次下载数据
		mAbTaskQueue.execute(refreshItem);
	}
}
