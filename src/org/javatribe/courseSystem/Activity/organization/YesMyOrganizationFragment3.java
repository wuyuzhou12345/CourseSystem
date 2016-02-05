package org.javatribe.courseSystem.Activity.organization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Organization;
import org.javatribe.courseSystem.model.Student;
import org.javatribe.courseSystem.net.GetDataWithJson;

import com.ab.activity.AbActivity;
import com.ab.global.AbConstant;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskCallback;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.task.AbTaskQueue;
import com.ab.util.AbStrUtil;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**我的组织界面
 * @author zhou
 *我的组织列表中的内容从服务器端获得
 */

public class YesMyOrganizationFragment3 extends Fragment implements OnItemClickListener{
	private Handler exitOrg_handler;
    private String PREFS_NAME="org.javatribe.courseSystem";
    private List<Organization> list_org=new ArrayList<Organization>();
    private List<Map<String,Object>> my_organizations;
    private List<Map<String,Object>> new_my_organizations;
    private View edit_dialog;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button fymo_btn_enter;
    private Button fymo_btn_exit;
    private Button fymo_btn_cancel;
    private SimpleAdapter adapter=null;
    private View progressbar;
    private AlertDialog.Builder  barBuilder;
    private AlertDialog barDialog;
    private ProgressBar fymo_progressBar;
    private String sno="";
	private AbPullListView mAbPullListView=null;
	private AbTaskQueue mAbTaskQueue=null;
	private int currentPage=1;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mAbTaskQueue=AbTaskQueue.getInstance();
		View rootView=inflater.inflate(R.layout.fragment_yes_my_organization,container, false);
		edit_dialog=inflater.inflate(R.layout.fragment_my_org_dialog, null);
		progressbar=inflater.inflate(R.layout.fragment_my_org_progressbar, null);
		fymo_progressBar=(ProgressBar)progressbar.findViewById(R.id.fymo_progressBar);
		builder=new AlertDialog.Builder(getActivity());
		builder.setView(edit_dialog);
		dialog=builder.create();
		barBuilder=new AlertDialog.Builder(getActivity());
		barBuilder.setView(progressbar);
		barDialog=barBuilder.create();
		
		//获取ListView对象
		mAbPullListView = (AbPullListView)rootView.findViewById(R.id.mListView);
		
		//打开关闭下拉刷新加载更多功能
        mAbPullListView.setPullRefreshEnable(true); 
        mAbPullListView.setPullLoadEnable(false);
		
		initMyOrganizationsList();		
		return rootView;
	}


	private void initMyOrganizationsList() {
		my_organizations=new ArrayList<Map<String,Object>>();
		adapter=new SimpleAdapter(getActivity(),my_organizations,R.layout.fragment_my_org_list_item,new String[]{"image","org","detail"},new int[]{R.id.fymo_img_name,R.id.fymo_tv_org_name,R.id.fymo_tv_org_details});
		mAbPullListView.setAdapter(adapter);
		mAbPullListView.setOnItemClickListener(this);
		SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
		sno=setting.getString("stuNo", "121542104");
		
		
    	//查询的事件
    	final AbTaskItem item1 = new AbTaskItem();
		item1.callback = new AbTaskCallback() {
			public void update() {
				my_organizations.clear();
				if(new_my_organizations!=null&&new_my_organizations.size()>0){
					my_organizations.addAll(new_my_organizations);
					adapter.notifyDataSetChanged();
					new_my_organizations.clear();
				}
				mAbPullListView.stopRefresh();
			}
			public void get() {
	   		    try {
	   		    	Thread.sleep(1000);
	   		    	new_my_organizations=new ArrayList<Map<String,Object>>();
	   		    	String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ORGANIZATION+"/"+Constant.ORGANIZATION_ENTER_ORG_BY_SNO_ACTION;
					SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
					sno=setting.getString("stuNo", "121542104");
					Map<String,String> params=new HashMap<String,String>();
					params.put("sno",sno);
					String js=GetDataWithJson.getDataWithJsonViaSimpleData(url,params);
					String result=js;
					if(result.equals("[]")){
						Map<String,Object> item=new HashMap<String,Object>();
						item.put("image", R.drawable.fymo_img_04);
						item.put("org", "加入组织");
						item.put("", "");
						new_my_organizations.add(item);
						Toast.makeText(getActivity(),"您还未加入组织，请加入新组织",Toast.LENGTH_LONG).show();
					}
					else{
						Type type=new TypeToken<List<Organization>>(){}.getType();
						list_org=new Gson().fromJson(result,type);
						for(Organization org:list_org){
							Map<String,Object> item=new HashMap<String,Object>();
							Log.i("====org_name=====",org.getOrgName());
							item.put("image",R.drawable.fymo_img_01 );
							item.put("org",org.getOrgName());
							item.put("detail",org.getOrgIntroduction());
							new_my_organizations.add(item);

						}	
						Map<String,Object> item=new HashMap<String,Object>();
						item.put("image", R.drawable.fymo_img_04);
						item.put("org", "加入组织");
						item.put("", "");
						new_my_organizations.add(item);
					}
		   		    	
	   				}
	   		    catch (Exception e) {
	   		    }
		  };
		};
		

	mAbPullListView.setAbOnListViewListener(new AbOnListViewListener(){

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
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			View view=parent.getChildAt((int)(id+1));
			TextView text=(TextView)view.findViewById(R.id.fymo_tv_org_name);
			String org_name=text.getText().toString();		
			if(org_name.equals("加入组织"))
			{
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new AddOrgFragment()).addToBackStack(null).commit();
			}
			else
			{
				showDialog((int)(id+1));
			}
		}
			
		
		private void showDialog(final int position) {
			dialog.show();
			fymo_btn_enter=(Button)edit_dialog.findViewById(R.id.fymo_btn_enter);
			fymo_btn_exit=(Button)edit_dialog.findViewById(R.id.fymo_btn_exit);
			fymo_btn_cancel=(Button)edit_dialog.findViewById(R.id.fymo_btn_cancel);
	
			
		 fymo_btn_enter.setOnClickListener(new OnClickListener(){
		
			@Override
			public void onClick(View arg0) {
				SharedPreferences settings=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences存放数据
				SharedPreferences.Editor editor=settings.edit();
				int orgId=list_org.get(position-1).getOrgId();
				editor.putInt("orgId",orgId);
				editor.commit();
				
				SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
				int orgId2=setting.getInt("orgId2", 1);
				
				//加载进度条。
				dialog.dismiss();
				//getActivity().showProgressDialog();
				barDialog.show();
			
			
				//有服务器端后要进行异步加载。。
				if(getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new NoticeFragment()).addToBackStack(null).commit()>0)
				{
					barDialog.dismiss();
				}
				
				
			}
			 
		 });
		 fymo_btn_exit.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//弹出对话框，你确定要退出组织吗？
					dialog.dismiss();
					new AlertDialog.Builder(getActivity()).setTitle("你确定要退出组织吗？")
					.setPositiveButton("是", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int which) {
							// TODO Auto-generated method stub
							switch(which)
							{
							case DialogInterface.BUTTON_POSITIVE:
								
								my_organizations.remove(position-1);
								mAbPullListView.setAdapter(adapter);
								Thread thread=new Thread("ExitOrg"){
									public void run(){
										Log.i("ExitOrgThreadStart!","ExitOrgThreadStart!");
										Message message=exitOrg_handler.obtainMessage();
										try{		
											int orgId=list_org.get(position-1).getOrgId();
											org.javatribe.courseSystem.model.Organization organization=new org.javatribe.courseSystem.model.Organization();
											organization.setOrgId(orgId);
											org.javatribe.courseSystem.model.Student student=new org.javatribe.courseSystem.model.Student();
											student.setStuNo(sno);
											String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ORGANIZATION+"/"+Constant.ORGANIZATION_EXIT_ORG_ACTION;
											Map<String,Object> params=new HashMap<String,Object>();
											params.put("student",student);
											params.put("organization",organization);

											String state=GetDataWithJson.getDataWithJsonViaObjectAndStringList(url, params, null,null);
											Log.i("ExitOrgThread_state",state);
											message.obj=state;
										}
										catch(Exception e){
											e.printStackTrace();
										}
										exitOrg_handler.sendMessage(message);
									}
			
								};
								thread.start();
								break;

							}
								
							
						}
					}).setNegativeButton("否", null).create().show();
					
				}
				 
			 });
			exitOrg_handler=new Handler(new Callback(){
				public boolean handleMessage(Message msg) {	
					String state=msg.obj.toString();
					if(msg.obj!=null){
						if(Boolean.parseBoolean(msg.obj.toString())){
							Toast.makeText(getActivity(),"已成功退出该组织",Toast.LENGTH_LONG).show();
						}
					}
					return true;
				}
				
			});
		 fymo_btn_cancel.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					dialog.dismiss();
				}
				 
			 });
		
		}	
		}

		
		
	
	

