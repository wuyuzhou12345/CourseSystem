package org.javatribe.courseSystem.Activity.admin.user;


import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.adapter.ExpandableListViewAdapter;




import org.javatribe.courseSystem.model.LeaveApplication;

import com.ab.util.AbStrUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**管理员请假条查看详细
 * 查看具体某个人的请假条
 * @author qing
 *2014/9/28
 */
public class AdminLeaveForPersonalDetailFragment extends Fragment {



	private TextView textViewLeaveEvent;
	private TextView textViewLeaveReason;
	private TextView textViewSession;
	private TextView textViewName;

	
	private String sessionName;
	
	private Map<String,Object> map;
	private LeaveApplication leaveForApplication;
	private String name;
	private String event;
	private String reason;
	private String session;
	private List<Map<String,Object>> reasons;//所有人的请假原因
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Toast.makeText(getActivity(),"请假条。。。。",Toast.LENGTH_SHORT).show();
		View root=inflater.inflate(R.layout.fragment_admin_leave_for_personal_detail, null);
		Bundle info=getArguments();
		if(info!=null)
		{
		leaveForApplication=((LeaveApplication)info.getSerializable("detail"));
//	 event=leaveForApplication.getEvent();
//	 reason=leaveForApplication.getLeaveReason();
//	 name=leaveForApplication.getName();
//	 session=leaveForApplication.getSession();
		}
	
		textViewLeaveEvent=(TextView)root.findViewById(R.id.falfpd_tv_content);
		textViewLeaveReason=(TextView)root.findViewById(R.id. falfpd_tv_reason);
		textViewSession=(TextView)root.findViewById(R.id. falfpd_tv_session);
		textViewName=(TextView)root.findViewById(R.id. falfpd_tv_name);
		 textViewLeaveEvent.setText(event);
		 textViewLeaveReason.setText(reason);
		 textViewName.setText(name);
		 textViewSession.setText(session);
		 return root;
	}

	
	

	
	
		
}
