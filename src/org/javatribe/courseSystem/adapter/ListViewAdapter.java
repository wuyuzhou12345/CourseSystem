package org.javatribe.courseSystem.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




















import org.javatribe.courseSystem.R;


import org.javatribe.courseSystem.Activity.Util.DepartmentViewHolder;
import org.javatribe.courseSystem.Activity.Util.PersonViewHolder;
import org.javatribe.courseSystem.vo.DepartmentItem;
import org.javatribe.courseSystem.vo.PersonInfo;
import org.javatribe.courseSystem.vo.SessionInfo;

import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewAdapter extends BaseExpandableListAdapter{

	private List<SessionInfo> deptList;
	private LayoutInflater inflater;
	private List<String> checkedPerson =new ArrayList<String>();
	private List<String> checkedPersonName=new ArrayList<String>();
	private Map<String,Integer> deptCheckedStateMap=new HashMap<String,Integer>();
	
	public ListViewAdapter(Context context,List<SessionInfo> deptList2){
		this.deptList=deptList2;
		inflater=LayoutInflater.from(context);
		int deptCount=getGroupCount();
		for(int deptPosition=0;deptPosition<deptCount;deptPosition++){
			try{
				SessionInfo departmentItem=deptList2.get(deptPosition);
				if(departmentItem==null||departmentItem.getPersonInfos()==null||
						departmentItem.getPersonInfos().isEmpty()){
					deptCheckedStateMap.put(departmentItem.getId(),3);
					continue;
				}
				 
				deptCheckedStateMap.put(departmentItem.getId(), 1);
				List<PersonInfo> personItems=departmentItem.getPersonInfos();
				for(PersonInfo personItem:personItems){
					checkedPerson.add(personItem.getId());
					checkedPersonName.add(personItem.getName());
				}
			}
			catch(Exception e){
				
			}
		}
	}
	@Override
	public Object getChild(int deptPosition, int perPosition) {
		final SessionInfo departmentItem=deptList.get(deptPosition);
		if(departmentItem==null||departmentItem.getPersonInfos()==null||departmentItem.getPersonInfos().isEmpty()){
			return null;
			
		}
		return departmentItem.getPersonInfos().get(perPosition);
	}
	public String getPersonName(int deptPosition, int perPosition) {
		final SessionInfo departmentItem=deptList.get(deptPosition);
		if(departmentItem==null||departmentItem.getPersonInfos()==null||departmentItem.getPersonInfos().isEmpty()){
			return null;
		}
		return departmentItem.getPersonInfos().get(perPosition).getName();
	}
	@Override
	public long getChildId(int deptPosition, int perPosition) {
		// TODO Auto-generated method stub
		return perPosition;
	}

	@Override
	public View getChildView(final int deptPosition,final int perPosition,boolean isLastPerson,View convertView,final ViewGroup parent) {
		
		// TODO Auto-generated method stub
		
		final PersonInfo personItem=(PersonInfo)getChild(deptPosition,perPosition);
		PersonViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new PersonViewHolder();
			convertView=inflater.inflate(R.layout.fragment_person_item,null);
			viewHolder.textViewPersonName=(TextView)convertView.findViewById(R.id.fcptp_tv_person_name);
			viewHolder.checkBoxPerson=(CheckBox)convertView.findViewById(R.id.fcptp_cb_person);
			viewHolder.textViewTeleShort=(TextView)convertView.findViewById(R.id.fcptp_tv_person_short_tele);
			convertView.setTag(viewHolder);
			
		}
		else{
			viewHolder=(PersonViewHolder)convertView.getTag();
		}
		
		
		viewHolder.textViewPersonName.setText(personItem.getName());
		viewHolder.textViewTeleShort.setText(personItem.getTele_short());
		final String personId=personItem.getId();
		viewHolder.checkBoxPerson.setOnCheckedChangeListener(new OnCheckedChangeListener(){
		 
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					if((!checkedPerson.contains(personId))&&(!checkedPersonName.contains(personItem.getName()))){
						checkedPerson.add(personId);
						checkedPersonName.add(personItem.getName());
					}
				}
				else{
					checkedPerson.remove(personId);
					checkedPersonName.remove(personItem.getName());
				}
				setDeptItemCheckedState(deptList.get(deptPosition));
				ListViewAdapter.this.notifyDataSetChanged();
			}
			
		});
		if(checkedPerson.contains(personId)&&checkedPersonName.contains(personItem.getName())){
			viewHolder.checkBoxPerson.setChecked(true);
		}
		else{
			viewHolder.checkBoxPerson.setChecked(false);
		}
		//convertView.setFocusable(false);
		
		return convertView;
	}

	protected void setDeptItemCheckedState(SessionInfo departmentItem) {
		// TODO Auto-generated method stub
		List<PersonInfo> personItems=departmentItem.getPersonInfos();
		if(personItems==null||personItems.isEmpty()){
			deptCheckedStateMap.put(departmentItem.getId(),3);
			return;
		}
		int checkedCount=0;
		for(PersonInfo personItem:personItems){
			if(checkedPerson.contains(personItem.getId())&&checkedPersonName.contains(personItem.getName())){
				checkedCount++;
			}
		}
		int state=1;
		if(checkedCount==0){
			state=3;
		}
		else if(checkedCount==personItems.size()){
			state=1;
		}
		else{
			state=2;
		}
		deptCheckedStateMap.put(departmentItem.getId(), state);
	}
	@Override
	public int getChildrenCount(int deptPosition) {
		// TODO Auto-generated method stub
		final SessionInfo departmentItem=deptList.get(deptPosition);
		if(departmentItem==null||departmentItem.getPersonInfos()==null||
				departmentItem.getPersonInfos().isEmpty()){
			return 0;
		}
		return departmentItem.getPersonInfos().size();
	}

	public int  getPersonGroup(int deptPosition) {
		// TODO Auto-generated method stub
		final SessionInfo departmentItem=deptList.get(deptPosition);
		if(departmentItem==null||departmentItem.getPersonInfos()==null||departmentItem.getPersonInfos().isEmpty()){
			return 0;
			
		}
		return departmentItem.getPersonInfos().size();
	}

	public 	Object getGroup(int deptPosition) {
		// TODO Auto-generated method stub
		if(deptList==null){
			return null;
		}
		return deptList.get(deptPosition);
	}

	public int getGroupCount() {
		// TODO Auto-generated method stub
		if(deptList==null)
			return 0;
		return deptList.size();
	}

	public long getGroupId(int deptPosition){
		return deptPosition;
	}
	@Override
	public View getGroupView(int deptPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		try{
			SessionInfo departmentItem=deptList.get(deptPosition);
			DepartmentViewHolder viewHolder=null;
			if(viewHolder==null){
				viewHolder=new DepartmentViewHolder();
				convertView=inflater.inflate(R.layout.fragment_department_item, null);
				viewHolder.textViewDeptName=(TextView)convertView.findViewById(R.id.fcptp_tv_dept_name);
				viewHolder.imageViewDept=(ImageView)convertView.findViewById(R.id.fcptp_cb_img);
				viewHolder.deptCheckBoxLinearLayout=(LinearLayout)convertView.findViewById(R.id.cb_layout);
				convertView.setTag(viewHolder);
			}
			else{
				viewHolder=(DepartmentViewHolder)convertView.getTag();
			}
			viewHolder.deptCheckBoxLinearLayout.setOnClickListener(new DeptCheckBoxLinearLayoutOnClickListener(departmentItem));
			viewHolder.textViewDeptName.setText(departmentItem.getName());
			int state=deptCheckedStateMap.get(departmentItem.getId());
			switch(state){
			case 1:
				viewHolder.imageViewDept.setImageResource(R.drawable.ck_checked);
				break;
			case 2:
				viewHolder.imageViewDept.setImageResource(R.drawable.ck_partial_checked);
				break;
			case 3:
				viewHolder.imageViewDept.setImageResource(R.drawable.ck_unchecked);
				break;
			default:
				break;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		//convertView.setFocusable(false);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int deptPosition, int perPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public class DeptCheckBoxLinearLayoutOnClickListener implements OnClickListener{
		private SessionInfo departmentItem;
		public DeptCheckBoxLinearLayoutOnClickListener(SessionInfo departmentItem){
			this.departmentItem=departmentItem;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			List<PersonInfo> personItems=departmentItem.getPersonInfos();
			if(personItems==null||personItems.isEmpty()){
				deptCheckedStateMap.put(departmentItem.getId(),3);
				return;
			}
			int checkedCount=0;
			for(PersonInfo personItem:personItems){
				if(checkedPerson.contains(personItem.getId())&&checkedPersonName.contains(personItem.getName())){
					checkedCount++;
				}
			}
			boolean checked=false;
			if(checkedCount==personItems.size()){
				checked=false;
				deptCheckedStateMap.put(departmentItem.getId(),3);
				
			}
			else{
				checked=true;
				deptCheckedStateMap.put(departmentItem.getId(),1);
			}
			for(PersonInfo personItem:personItems){
				String holderKey=personItem.getId();
				String holderName=personItem.getName();
				if(checked){
					if((!checkedPerson.contains(holderKey))&&(!checkedPersonName.contains(holderName))){
						checkedPerson.add(holderKey);
						checkedPersonName.add(holderName);
					}
				}
				else{
					checkedPerson.remove(holderKey);
					checkedPersonName.remove(holderName);
				}
			}
			ListViewAdapter.this.notifyDataSetChanged();
		}
		
	}
	public List<String> getCheckedPerson(){
		return checkedPerson;
	}
	public List<String> getCheckedPersonName(){
		return checkedPersonName;
	}

}
