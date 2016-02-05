package org.javatribe.courseSystem.Activity.admin.notice;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.SlidingMenuLeftActivity;
import org.javatribe.courseSystem.Activity.admin.organization.AdminMyManageOrgFragment;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.Notice;
import org.javatribe.courseSystem.model.Organization;
import org.javatribe.courseSystem.model.Student;
import org.javatribe.courseSystem.net.GetDataWithJson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 管理员界面中发送公告部分，在对话框中可以选择想要发送的对象即什么部门
 *@author zhou
 *2014/12/8
 */
public class AdminBoardManageFragment extends Fragment{
	private TextView textViewTitle;
	private EditText editTextTitle;
	private TextView textViewContent;
	private EditText editTextContent;
	private Button btn_send;
	private Button btn_cancel;
	private final static int DIALOG=1;
	private Handler notice_add_handler;
	private String PREFS_NAME="org.javatribe.courseSystem";
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		View rootView=inflater.inflate(R.layout.fragment_admin_board_manage,container,false);
		textViewTitle=(TextView)rootView.findViewById(R.id.fabm_tv_title);
		editTextTitle=(EditText)rootView.findViewById(R.id.fabm_et_title);
		textViewContent=(TextView)rootView.findViewById(R.id.fabm_tv_content);
		editTextContent=(EditText)rootView.findViewById(R.id.fabm_et_content);
		btn_send=(Button)rootView.findViewById(R.id.fabm_btn_send);
		btn_cancel=(Button)rootView.findViewById(R.id.fabm_btn_cancel);
		notice_add_handler=new Handler(new Callback(){

			public boolean handleMessage(Message msg) {
				 
					String result=msg.obj.toString();
					Log.i("sendAdminNotice result",result);
	    		    AdminNoticeSearchFragment ansf=new AdminNoticeSearchFragment();
	    		    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,ansf).addToBackStack(null).commit();
				    return true;
			}
			
		});

		btn_send.setOnClickListener(new View.OnClickListener(){
	    
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("zhou","111111");
				AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
				builder.setTitle("确定发送信息？");
				/*对话框中含有俩个按钮，一个取消，一个确定*/
				builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog,int which){
						dialog.dismiss();
					}
				});
				builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
		    		   public void onClick(DialogInterface dialog, int which)
		    		   {
		    			SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
		    			final String name=setting.getString("name", "zhou");
		    			final String sno=setting.getString("stuNo", "121542105");
		    			final int orgId=setting.getInt("adminOrgId",0);
		    			if(orgId==0){
		    				AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
					    	builder.setTitle("Error");
					    	builder.setMessage("还没有选中需要管理的组织");
					    	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					    		   public void onClick(DialogInterface dialog, int which)
					    		   {
					    			   AdminMyManageOrgFragment adminMyManageOrgFragment=new AdminMyManageOrgFragment();
					    			   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,adminMyManageOrgFragment).addToBackStack(null).commit();
					    			   dialog.dismiss();

					    		   }
					    		  });
					    	builder.show();
		    			}
		    			else{
			    			Thread thread=new Thread("addnewNotice"){
			    				Message message=notice_add_handler.obtainMessage();
			    				String temp;
			    				public void run(){
			    					try{
			    						String url=Constant.BASE_URL+"/"+Constant.NAMESPACE_ADMIN_NOTICE+"/"+Constant.ADMIN_NOTICE_ADD_ACTION;
			    						Log.i("SerachNoClassPersonFragment url",url);
			    						Notice notice=new Notice();
			    						notice.setNoticeTitle(editTextTitle.getText().toString());
			    						notice.setNoticeContent(editTextContent.getText().toString());
			    						Date date=new Date();
			    					    DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			    					    String nowTime=format.format(date);
			    					    notice.setNoticeTime(format.parse(nowTime));
			    					    Organization org=new Organization();
			    					    org.setOrgId(orgId);
			    					    Student student=new Student();
			    					    student.setName(name);
			    					    student.setStuNo(sno);
			    					    notice.setOrganization(org);
			    					    notice.setStudent(student);
			    					    Map<String,Object> params=new HashMap<String,Object>();
			    					    params.put("notice", notice);
			    					    temp=GetDataWithJson.getDataWithJsonViaObjectAndStringList(url, params, null,null);
			    					    Log.i("=====temp=======",temp);
			    					 }
			    					catch(Exception e){
			    						e.printStackTrace();
			    					}
			    					message.obj = temp;
			    					notice_add_handler.sendMessage(message);
			    				}
			    			};
			    			dialog.dismiss();
			    			thread.start();
			    			
		    		   }
		    		   }
		    	});
		
		    	builder.show();
		  }
	  });

		btn_cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return rootView;
	}
	}


