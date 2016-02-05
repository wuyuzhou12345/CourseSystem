package org.javatribe.courseSystem.Activity.organization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.R.drawable;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Notice;
import org.javatribe.courseSystem.model.Organization;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.util.JsonUtil;

import com.ab.task.AbTaskCallback;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**公告栏
 * @author zhou
 *2014/9/28
 */
public class NoticeFragment extends Fragment{

	private AbPullListView fn_lv_notice=null;
	private List<Notice> list_notice=new ArrayList<Notice>();
	private List<Map<String,Object>> fn_notice;
	private List<Map<String,Object>> new_fn_notice;
	private SimpleAdapter adapter=null;
	private AbTaskQueue mAbTaskQueue=null;
	private int currentPage=1;
	private int orgId;
	private String PREFS_NAME="org.javatribe.courseSystem";
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_notice, container, false);
		fn_lv_notice=(AbPullListView)rootView.findViewById(R.id.fn_lv_notice);
		fn_lv_notice.setAdapter(adapter);
		mAbTaskQueue=AbTaskQueue.getInstance();
		
		//打开关闭下拉刷新加载更多功能
		fn_lv_notice.setPullRefreshEnable(true); 
		fn_lv_notice.setPullLoadEnable(false);
		
		initNotice();
		return rootView;
	}
	private void initNotice() {
		fn_notice=new ArrayList<Map<String,Object>>();
		adapter=new SimpleAdapter(getActivity(), fn_notice, R.drawable.fn_lv_item, new String[]{"title","date_person","content"}, new int[]{R.id.fn_title,R.id.fn_date_person,R.id.fn_content});
		fn_lv_notice.setAdapter(adapter);
		fn_lv_notice.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				View view=parent.getChildAt((int)(id+1));
				TextView textview_title=(TextView)view.findViewById(R.id.fn_title);
				TextView textview_date_person=(TextView)view.findViewById(R.id.fn_date_person);
				TextView textview_content=(TextView)view.findViewById(R.id.fn_content);
				String title=textview_title.getText().toString();
				String date_person=textview_date_person.getText().toString();
				String content=textview_content.getText().toString();
				Log.i("Click title",title);
				Log.i("Click date_person",date_person);
				Log.i("Click content",content);
				Bundle bundle=new Bundle();	
				bundle.putString("title",title);
				bundle.putString("date_person",date_person);
				bundle.putString("content",content);
				NoticeDetailFragment noticeDetailFragment=new NoticeDetailFragment();
				noticeDetailFragment.setArguments(bundle);
				getActivity().getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame,noticeDetailFragment ).addToBackStack(null)
				.commit();
				
			}
			
		});
		SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
		orgId=setting.getInt("orgId", 0);
		Log.i("NoticeFragment getSharedPreferences orgId",""+orgId);
		Toast.makeText(getActivity(),"Notice SelectedOrgId="+orgId,Toast.LENGTH_LONG).show();
		
		final AbTaskItem item1=new AbTaskItem();
		item1.callback=new AbTaskCallback(){
			public void update(){
				fn_notice.clear();
				if(new_fn_notice!=null&&new_fn_notice.size()>0){
					fn_notice.addAll(new_fn_notice);
					adapter.notifyDataSetChanged();
					new_fn_notice.clear();
				}
				fn_lv_notice.stopRefresh();
			}
			public void get(){
				try{
					Thread.sleep(1000);
					new_fn_notice=new ArrayList<Map<String,Object>>();

						String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_NOTICE+"/"+Constant.NOTICE_BY_ORGID_ACTION;
						Map<String,String> params=new HashMap<String,String>();
						params.put("orgId",orgId+"");
						String js=GetDataWithJson.getDataWithJsonViaSimpleData(url,params);
						String result=js;
						if(result.equals("[]")){
							Toast.makeText(getActivity(), "当前组织还为发布过公告", Toast.LENGTH_SHORT);
						}
						else{
						Type type=new TypeToken<List<Notice>>(){}.getType();
						list_notice=new Gson().fromJson(result,type);
						for(Notice notice:list_notice){
							Map<String,Object> item=new HashMap<String,Object>();
							Log.i("notice  title",notice.getNoticeTitle());
							Log.i("notice content",notice.getNoticeContent());
							item.put("title",notice.getNoticeTitle());
							item.put("content",notice.getNoticeContent());
							String json=notice.getJson();
							String date=JsonUtil.getStringFromJson(json,"sendTime");
							String senderName=JsonUtil.getStringFromJson(json,"senderName");
							item.put("date_person",senderName+"  "+date);
					    	
							new_fn_notice.add(item);
							}
						}
					
				}
				catch(Exception e){
					
				}
			}
		};
		fn_lv_notice.setAbOnListViewListener(new AbOnListViewListener(){

			public void onRefresh() {
				mAbTaskQueue.execute(item1);
			}

			@Override
			public void onLoadMore() {	
				mAbTaskQueue.execute(item1);
			}	
		});
		
		//第一次下载数据
		mAbTaskQueue.execute(item1);
		
	}
	
}
