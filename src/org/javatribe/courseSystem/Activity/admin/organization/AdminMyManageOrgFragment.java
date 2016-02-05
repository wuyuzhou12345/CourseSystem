/**
 * 
 */
package org.javatribe.courseSystem.Activity.admin.organization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.organization.AddOrgFragment;
import org.javatribe.courseSystem.Activity.organization.NoticeFragment;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Organization;
import org.javatribe.courseSystem.net.GetDataWithJson;

import com.ab.task.AbTaskCallback;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author zhou
 * @version 创建时间：2014年12月5日 下午9:29:20
 * 某学生作为管理员的组织列表
 */
public class AdminMyManageOrgFragment extends Fragment implements OnItemClickListener{
	private Handler exitOrg_handler;
    private String PREFS_NAME="org.javatribe.courseSystem";
    private List<Organization> list_org=new ArrayList<Organization>();
    private List<Map<String,Object>> my_organizations;
    private List<Map<String,Object>> new_my_organizations;
    private View edit_dialog;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button fymo_btn_enter;
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
		edit_dialog=inflater.inflate(R.layout.fragment_admin_my_manage_org, null);
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
		sno=setting.getString("stuNo", "121542100");
		
		
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
	   		    	String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_STU_ORG_SESSION+"/"+Constant.ADMIN_STU_ORG_SESSION_GET_ORG_BY_SNO_ACTION;
	   		    	Log.i("=====url======",url);
					SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
					sno=setting.getString("stuNo", "121542100");
					Map<String,String> params=new HashMap<String,String>();
					params.put("sno",sno);
					String js=GetDataWithJson.getDataWithJsonViaSimpleData(url,params);
					String result=js;

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
				}
	   		    catch (Exception e) {
	   		    	e.printStackTrace();
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
			showDialog((int)(id+1));
			
		}
			
		
		private void showDialog(final int position) {
			dialog.show();
			fymo_btn_enter=(Button)edit_dialog.findViewById(R.id.fammo_btn_enter);
			//fymo_btn_exit=(Button)edit_dialog.findViewById(R.id.fymo_btn_exit);
			fymo_btn_cancel=(Button)edit_dialog.findViewById(R.id.fammo_btn_cancel);
	
			
		 fymo_btn_enter.setOnClickListener(new OnClickListener(){
		
			@Override
			public void onClick(View arg0) {				
				//加载进度条。
				dialog.dismiss();
				//getActivity().showProgressDialog();
				//barDialog.show();
		    	AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		    	builder.setTitle("Info");
		    	builder.setMessage("您确定进入该组织进行管理？");
		    	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
		    		   public void onClick(DialogInterface dialog, int which)
		    		   {
		   					SharedPreferences settings=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences存放数据
		   					SharedPreferences.Editor editor=settings.edit();
		   					int orgId=list_org.get(position-1).getOrgId();
		   					Log.i("===Admin orgId===",orgId+"");
		   					editor.putInt("adminOrgId",orgId);
		   					editor.commit();
						
		   					SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
		   					int orgId2=setting.getInt("orgId2", 0);
		   					dialog.dismiss();

		    		   }
		    		  });
		    	builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface arg0, int arg1) {
						dialog.dismiss();
					}
		    		
		    	});
		    	builder.show();
			
//				//有服务器端后要进行异步加载。。
//				if(getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new NoticeFragment()).addToBackStack(null).commit()>0)
//				{
//					barDialog.dismiss();
//				}
				
				
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