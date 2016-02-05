package org.javatribe.courseSystem.Activity.organization;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.Util.SerializableMap;
import org.javatribe.courseSystem.db.StudentMyAssignSDDao;
import org.javatribe.courseSystem.db.model.StudentMyAssign;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.LeaveApplication;
import org.javatribe.courseSystem.model.Message;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.ui.SlideCutListView;
import org.javatribe.courseSystem.ui.SlideCutListView.*;
import org.javatribe.courseSystem.util.JsonUtil;
import org.json.JSONObject;

import com.ab.task.AbTaskCallback;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**我的任务
 * @author qing
 *2014/9/28
 */
public class MyAssignmentFragment extends Fragment implements OnItemClickListener,RemoveListener{
	private AbPullListView listViewAllAssgin;
	private SimpleAdapter adapter;
	private List<Map<String,Object>> data;
	private List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
	private List<Message> assignments=new ArrayList<Message>();
	private String json;
	private SimpleDateFormat format;
	private AbTaskQueue mAbTaskQueue = null;
	private final String PREFS_NAME="org.javatribe.courseSystem";
	private StudentMyAssign myAssigment;
	private StudentMyAssignSDDao assignDao;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View rootView=inflater.inflate(R.layout.fragment_my_assign,container, false);
		 listViewAllAssgin=(AbPullListView)rootView.findViewById(R.id.fma_lv_myassign);
		format=new SimpleDateFormat("yyyy-MM-dd");
		mAbTaskQueue=AbTaskQueue.getInstance();
		initAllAssginList();
		 //list=getData();
		// adapter=new SimpleAdapter(getActivity(),data,R.layout.fragment_my_assignment_item,new String[]{"sender","title","date"},new int[]{R.id.fma_tv_sender,R.id.fma_tv_title,R.id.fma_tv_date});
		 //listViewAllAssgin.setAdapter(adapter);
		// listViewAllAssgin.setRemoveListener(this);
		 listViewAllAssgin.setOnItemClickListener(this);
		return rootView;
	
	}
//	public List<Map<String,String>> getData()
//	{
//		data=new ArrayList<Map<String,String>>();
//		Map<String,String> map1=new HashMap<String,String>();
//		map1.put("sender", "晴非");
//		map1.put("event", "项目开会");
//		map1.put("date", format.format(new java.util.Date()));
//		map1.put("content","hahahah");
//		data.add(map1);
//		Map<String,String> map2=new HashMap<String,String>();
//		map2.put("sender", "周二");
//		map2.put("event", "爪哇部落活动");
//		map2.put("date", format.format(new java.util.Date()));
//		map2.put("content","一起来玩");
//		data.add(map2);
//		Map<String,String> map3=new HashMap<String,String>();
//		map3.put("sender", "勇洁");
//		map3.put("event", "聚餐");
//		map3.put("date", format.format(new java.util.Date()));
//		map3.put("content","一起吃。。。");
//		data.add(map3);
//		Map<String,String> map4=new HashMap<String,String>();
//		map4.put("sender", "大欣");
//		map4.put("event", "旅游");
//		map4.put("date", format.format(new java.util.Date()));
//		map4.put("content","一起去旅游！");
//		data.add(map4);
//		return data;
//	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Bundle bundle =new Bundle();
		bundle.putSerializable("detail",new SerializableMap( data.get(position-1)));
		MyAssignmentDetailFragment detailFragment=new MyAssignmentDetailFragment();
		detailFragment.setArguments(bundle);
		getActivity().getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame,detailFragment).addToBackStack(null)
		.commit();
	}

	@Override
	public void removeItem(RemoveDirection direction, int position) {
		// TODO Auto-generated method stub
		Log.d("MyAssignmentFragment",position+"");
		data.remove(position);
			//adapter.remove(adapter.getItem(position));
		adapter.notifyDataSetChanged();
		listViewAllAssgin.setAdapter(adapter);
		Log.d("MyAssignmentFragment",position+"");
		switch (direction) {
		case RIGHT:
			Toast.makeText(getActivity(), "向右删除  "+ position, Toast.LENGTH_SHORT).show();
			break;
		case LEFT:
			Toast.makeText(getActivity(), "向左删除  "+ position, Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
	public void initAllAssginList()
	{
		 data=new ArrayList<Map<String,Object>>();
		adapter=new SimpleAdapter(getActivity(),data,R.layout.fragment_my_assignment_item,new String[]{"senderName","messageTitle","sendTime"},new int[]{R.id.fma_tv_sender,R.id.fma_tv_title,R.id.fma_tv_date});
		 listViewAllAssgin.setAdapter(adapter);
		
	        //打开关闭下拉刷新加载更多功能
		 listViewAllAssgin.setPullRefreshEnable(true); 
			
			final AbTaskItem refreshItem= new AbTaskItem();
			refreshItem.callback = new AbTaskCallback() {

				@Override
				public void update() {
					//removeProgressDialog();
					data.clear();
					Log.i("=====update==","start update!!");
					Log.i("LeaveFor", "newList.size="+newList.size());
					if(newList!=null && newList.size()>0){
						data.clear();
		                data.addAll(newList);
		                adapter.notifyDataSetChanged();
		                newList.clear();
		            
	   		    	}
					listViewAllAssgin.stopRefresh();
				}

				@Override
				public void get() {
					try
					{
						Thread.sleep(1000);
						 Log.i("LeaveFor", "getData!!");
						Map<String,String> data=new HashMap<String,String>();
						SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
						String sno=setting.getString("stuNo", "121542104");
						int orgId =setting.getInt("orgId",1);
						data.put("receiverNo", sno);
						data.put("orgId", Integer.toString(orgId));
						json = GetDataWithJson.getDataWithJsonViaSimpleData(Constant.BASE_URL+"/"+Constant.NAMESPACE_MYASSIGN+"/"+Constant.MYASSIGN_ALL_MY_ASSIGN_ACTION,data);
						Log.i("LeaveFor",json);
						
						if (json != null && !json.equals(Constant.GET_DATA_ERROR)){
							Log.i("LeaveFor","json not null");
						Type type=new TypeToken<List<Message>>(){}.getType();
						Log.i("hahaha","hahahaha");
						Gson g=new Gson();
						assignments=g.fromJson(json, type);
						
				
						Map<String,Object> map;
						Log.i("LeaveFor", "assigment.size="+assignments.size());
						for(Message message:assignments)
						{
							map=new HashMap<String,Object>();
							map.put("messageId",new Integer(message.getMessageId()));
							Log.i("messageId",message.getMessageId()+"");
							map.put("messageTitle",message.getMessageTitle());
							map.put("messageContent", message.getMessageContent());
							String json=message.getJson();
							String sendTime=JsonUtil.getStringFromJson(json, "sendTime");
							String senderName=JsonUtil.getStringFromJson(json, "senderName");
							map.put("senderName", senderName);
							map.put("sendTime", sendTime);
							
							 newList.add(map);
							
						}
					}
						
					}
					catch(Exception e)
					{
						
					}
			  };
			};
			listViewAllAssgin.setAbOnListViewListener(new AbOnListViewListener(){

				@Override
				public void onRefresh() {
					mAbTaskQueue.execute(refreshItem);
				}

				@Override
				public void onLoadMore() {
//					mAbTaskQueue.execute(item2);
				}
				
			});
			
	    	//第一次下载数据
			mAbTaskQueue.execute(refreshItem);
	
		 
	}

}
