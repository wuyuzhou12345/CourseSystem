package org.javatribe.courseSystem.Activity.admin.notice;

import java.util.HashMap;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.SlidingMenuLeftActivity;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.net.GetDataWithJson;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**管理员公告栏详细，可对某个公告进行删除
 * @author zhou
 *2014/10/28
 */
public class AdminNoticeDetailFragment extends Fragment{	
	TextView fdn_tv_title;
	TextView fdn_tv_date_person;
	TextView fdn_tv_content;
	ImageView fdn_iv_thread;
	Button button_delete;
	Handler delete_notice_handler;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
 		final Bundle bundle=getArguments();
		View rootView=inflater.inflate(R.layout.fragment_admin_notice_detail, container, false);
		fdn_tv_title=(TextView)rootView.findViewById(R.id.fand_tv_title);
		fdn_tv_date_person=(TextView)rootView.findViewById(R.id.fand_tv_date_person);
		fdn_tv_content=(TextView)rootView.findViewById(R.id.fand_tv_content);
		fdn_iv_thread=(ImageView)rootView.findViewById(R.id.fand_iv_thread);
		button_delete=(Button)rootView.findViewById(R.id.fand_btn_delete);
		fdn_tv_title.setText(bundle.getString("title"));
		fdn_tv_date_person.setText(bundle.getString("date_person"));
		fdn_tv_content.setText(bundle.getString("content"));
		delete_notice_handler=new Handler(new Callback(){
			public boolean handleMessage(Message msg) {
				String isDeleteSuccess=msg.obj.toString();
				Log.i("isDeleteSuccess",isDeleteSuccess);
				AdminNoticeSearchFragment ansf=new AdminNoticeSearchFragment();
				getActivity().getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame,ansf).addToBackStack(null)
				.commit();
				return true;
			}	
		});
		button_delete.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Thread thread=new Thread("DeleteNotice"){
					Message message=delete_notice_handler.obtainMessage();
					String result;
					public void run(){
						String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_NOTICE+"/"+Constant.ADMIN_NOTICE_DELETE_NOTICE_BY_ID_ACTION;
						int notice_id=bundle.getInt("notice_id");
						Map<String,String> params=new HashMap<String,String>();
						params.put("noticeId", notice_id+"");
						try{
							result=GetDataWithJson.getDataWithJsonViaSimpleData(url, params);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						message.obj=result;
						delete_notice_handler.sendMessage(message);
					}
				};
				thread.start();
			}
			
		});
		return rootView;
	}
	

}
