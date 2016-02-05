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
import org.javatribe.courseSystem.model.Message;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.ui.SlideCutListView;
import org.javatribe.courseSystem.ui.SlideCutListView.RemoveDirection;
import org.javatribe.courseSystem.ui.SlideCutListView.RemoveListener;
import org.javatribe.courseSystem.util.JsonUtil;

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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyMessageFragment extends Fragment implements OnItemClickListener,RemoveListener{
	private static final String TAG="MyMessage";
	private AbPullListView listViewAllAssgin;
	private SimpleAdapter adapter;
	private List<Map<String,Object>> data;
	private List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
	private List<Message> myMessages=new ArrayList<Message>();
	private String json;
	private SimpleDateFormat format;
	private AbTaskQueue mAbTaskQueue = null;
	private Map<Integer,String> title_content;
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
		 listViewAllAssgin.setOnItemClickListener(this);
		return rootView;
	
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		Bundle bundle =new Bundle();
		View view=parent.getChildAt((int)(id+1));
		TextView textView_title=(TextView)view.findViewById(R.id.fma_tv_title);
		TextView textView_date=(TextView)view.findViewById(R.id.fma_tv_date);
		TextView textView_sender=(TextView)view.findViewById(R.id.fma_tv_sender);
		if(textView_title.getText().equals("-------发送信息--------")){
			SendMessageFragment sendMessageFragment=new SendMessageFragment();
			getActivity().getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame,sendMessageFragment).addToBackStack(null)
			.commit();
		}
		else{
			bundle.putString("msg_title",textView_title.getText().toString());
			bundle.putString("msg_date",textView_date.getText().toString());
			String content=title_content.get(position);
			Log.i(TAG,"Content="+content);
			bundle.putString("msg_content",content);
			MessageDetailFragment messageDetailFragment=new MessageDetailFragment();
			messageDetailFragment.setArguments(bundle);
			getActivity().getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame,messageDetailFragment).addToBackStack(null)
			.commit();
		}
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
						Map<String,String> data=new HashMap<String,String>();
						SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
						String sno=setting.getString("stuNo", "121542100");
						int orgId =setting.getInt("orgId",0);
						data.put("sno", sno);
						data.put("orgId", Integer.toString(orgId));
						json = GetDataWithJson.getDataWithJsonViaSimpleData(Constant.BASE_URL+"/"+Constant.NAMESPACE_MESSAGE+"/"+Constant.MESSAGE_ALL_MY_MESSAGE_ACTION,data);
						Log.i(TAG,json);
						
						if (json != null && !json.equals(Constant.GET_DATA_ERROR)){
						Type type=new TypeToken<List<Message>>(){}.getType();
						Gson g=new Gson();
						myMessages=g.fromJson(json, type);
						
				
						Map<String,Object> map;
						Log.i(TAG, "MyMessages.size="+myMessages.size());
						title_content=new HashMap<Integer,String>();
						int i=0;
						for(Message message:myMessages)
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
							title_content.put(i++, message.getMessageContent());
							 newList.add(map);
							
						}
						map=new HashMap<String,Object>();
						map.put("messageTitle","-------发送信息--------");
						newList.add(map);
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
