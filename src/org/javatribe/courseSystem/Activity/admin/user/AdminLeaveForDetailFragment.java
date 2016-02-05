package org.javatribe.courseSystem.Activity.admin.user;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.Util.SerializableMap;
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
 * 查看请假人数
 * @author qing
 *2014/9/28
 */
public class AdminLeaveForDetailFragment extends Fragment implements OnChildClickListener{


	private ExpandableListView listLeavePerson; 
	private TextView textViewLeaveEvent;
	private final static String TAG="LeaveForInputFragment";
	private BaseExpandableListAdapter adapter;
	private String[] sessionName;//请假的部门名称
	private String[][] personName;//请假的人的名字
	private Map<String,Object> map;//从上一个Fragment中获取的数据
	private String event;//请假事件
	private int askForLeavePersonCount;//请假人数
	private Map<Integer,List<LeaveApplication>> reasons;//所有人的请假原因
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Toast.makeText(getActivity(),"请假条。。。。",Toast.LENGTH_SHORT).show();
		View root=inflater.inflate(R.layout.fragment_admin_leave_for_detail, null);
		Bundle info=getArguments();
		
		map=((SerializableMap)info.getSerializable("info")).getMap();
	
	 event=(String) map.get("event");
	 askForLeavePersonCount=(Integer)map.get("askForLeavePersonCount");
		sessionName=(String[])map.get("sessionNames");
		personName=(String[][])map.get("personName");
		reasons=(Map)map.get("reasons");
		textViewLeaveEvent=(TextView)root.findViewById(R.id. falfd_tv_content);
		 listLeavePerson=(ExpandableListView)root.findViewById(R.id.falfd_elv_list);
		adapter=new ExpandableListViewAdapter(getActivity(),sessionName,personName);
		listLeavePerson.setOnChildClickListener(this); 
		textViewLeaveEvent.setText(event);
		 listLeavePerson.setAdapter(adapter);
		 Toast.makeText(getActivity(),"请假条。。。。",Toast.LENGTH_SHORT).show();
		 return root;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		
		LeaveApplication info=(LeaveApplication)reasons.get(groupPosition).get(childPosition);
		info.setLeaveReason(event);
	
	
		Bundle bundle=new Bundle();
		bundle.putSerializable("detail", info);
		AdminLeaveForPersonalDetailFragment fragment=new AdminLeaveForPersonalDetailFragment();
		fragment.setArguments(bundle);
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();
		return true;
	}

	
	
		
}
