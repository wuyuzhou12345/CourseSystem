package org.javatribe.courseSystem.Activity.organization;


import java.util.HashMap;
import java.util.Map;

import org.javatribe.courseSystem.R;


import org.javatribe.courseSystem.Activity.Util.SerializableMap;
import org.javatribe.courseSystem.Activity.Util.Validator;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.LeaveApplication;
import org.javatribe.courseSystem.model.Message;
import org.javatribe.courseSystem.model.Organization;
import org.javatribe.courseSystem.model.Student;
import org.javatribe.courseSystem.net.GetDataWithJson;

import com.ab.util.AbStrUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**输入请假条
 * @author qing
 *2014/9/28
 */
public class LeaveForInputFragment extends Fragment implements OnClickListener{

	private Button fli_btn_ok;
	private	   Button fli_btn_reset;
	private EditText fli_et_leaveReason;
	private EditText fli_et_leaveEvent;
	private SerializableMap serialMap;
	private Map<String,Object> data;
	private String leaveEvent;
	private String leaveReason;
	private Integer messageId;
	private String stuNo;
	private int orgId;
	private Handler leaveApplicationHandler;
	private final static String TAG="LeaveForInputFragment";
	private final String PREFS_NAME="org.javatribe.courseSystem";
	private Bundle mBundle;
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root=inflater.inflate(R.layout.fragment_leave_for_input, null);
		fli_et_leaveReason=(EditText)root.findViewById(R.id. fli_et_leaveReason);
		 fli_et_leaveEvent=(EditText)root.findViewById(R.id. fli_et_leaveEvent);
		mBundle=getArguments();
		//Toast.makeText(getActivity(), (mBundle==null)+"", Toast.LENGTH_SHORT).show();
		if(mBundle!=null&&mBundle.getSerializable("detail")!=null)
		{
			serialMap=(SerializableMap)mBundle.getSerializable("detail");
			data=serialMap.getMap();
			 leaveEvent=(String)data.get("messageTitle");
			fli_et_leaveEvent.setText(leaveEvent);
			Log.i("LeaveForInput messageId",data.get("messageId")+"");
			messageId=(Integer)data.get("messageId");
			Log.i("LeaveForInput messageId",messageId+"");
		}
		else
		{
			
		}
		SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
		stuNo=setting.getString("stuNo", "121542104");
		orgId =setting.getInt("orgId",1);
		fli_btn_ok=(Button)root.findViewById(R.id.fli_btn_ok);
		fli_btn_reset=(Button)root.findViewById(R.id.fli_btn_reset);
		fli_btn_ok.setOnClickListener(this);
		fli_btn_reset.setOnClickListener(this);
		 leaveApplicationHandler=new Handler() {
			
			@Override
			public void handleMessage(android.os.Message msg) {
				// TODO Auto-generated method stub
				if(msg.what==1)
				{
					Toast.makeText(getActivity(),"请假成功！",Toast.LENGTH_SHORT).show();
					//getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new LeaveForListFragment()).addToBackStack(null).commit();
				}
				else if(msg.what==-1)
				{
					Toast.makeText(getActivity(),"请假失败！请稍后重试！",Toast.LENGTH_SHORT).show();
				}
			}
		};
		 
	return root;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.fli_btn_ok:
			
		
			 leaveReason=fli_et_leaveReason.getText().toString();
			if(Validator.isEmpty(fli_et_leaveEvent)||Validator.isEmpty(fli_et_leaveReason))
			{
				Toast.makeText(getActivity(), "内容不能为空！", Toast.LENGTH_SHORT).show();
			}
			else
			{
				LeaveApplication leaveApplication=new LeaveApplication();
				
				leaveApplication.setLeaveEvent(leaveEvent);
				leaveApplication.setLeaveReason(leaveReason);
				leaveApplication.setMessage(new Message(messageId));
				leaveApplication.setOrganization(new Organization(orgId));
				leaveApplication.setStudent(new Student(stuNo));
				leaveApplication.setSendTime(new java.util.Date());
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("leaveApplication", leaveApplication);
				sendLeaveApplication(map);
				
			
				
		
			}
			break;
		case R.id.fli_btn_reset:
			fli_et_leaveReason.setText("");
			 fli_et_leaveEvent.setText("");
			 break;
		
		}
	}
	public void sendLeaveApplication(final Map<String,Object> data)
	{
		Thread thread=new Thread(){
		public void run()
		{
				String result=GetDataWithJson.getDataWithJsonViaObjectAndIntegerList(Constant.BASE_URL+"/"+Constant.NAMESPACE_LEAVEAPPLICATION+"/"+Constant.LEAVEAPPLICATION_SEND_MY_LEAVEAPPLICATION_ACTION, data, null, null);
				android.os.Message msg=leaveApplicationHandler.obtainMessage();
				Log.i("message",result);
				if(result.equals("error")||result.equals("false"))
				{
					msg.what=-1;
				}
				else
				{
					msg.what=1;
				}
				leaveApplicationHandler.sendMessage(msg);
		}
	};
	thread.start();
	}
	
		
}
