package org.javatribe.courseSystem.adapter;

import java.util.List;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.vo.DepartmentItem;
import org.javatribe.courseSystem.vo.PersonItem;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;
import android.widget.ExpandableListView.OnChildClickListener;

public class ExpandableListViewAdapter extends  BaseExpandableListAdapter{
	private String[] mainMenu;
	private String[][] subMenu;
	private Context context;
	private List<DepartmentItem> list;
	public ExpandableListViewAdapter(Context context,String[] mainMenu,String[][] subMenu)
	{
		this.mainMenu=mainMenu;
		this.subMenu=subMenu;
		this.context=context;
	}
	public ExpandableListViewAdapter(Context context,List<DepartmentItem> list)
	{
		this.context=context;
		this.list=list;
	}

	
	@Override
	//返回子列表项数据
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition).getPersonItems().get(childPosition);
	}

	@Override
	//返回子列表项的id
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}
	//设置子列表项的TextView
	private TextView getTextView(String str)
	{
		AbsListView.LayoutParams lp=new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 60);
		TextView text=new TextView(context);
		text.setLayoutParams(lp);
		text.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
		text.setPadding(36,0,0,0);
		text.setTextSize(20);
		text.setText(str);
		return text;
	}
	@Override
	//该方法决定每个子选项外观
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView=new TextView(context);
		textView.setEms(20);
		textView.setPadding(60, 10, 10, 10);
		textView.setText(((PersonItem)getChild(groupPosition,childPosition)).getName());
		
		return textView;
	}

	@Override
	//返回子列表项数目
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition).getPersonItems().size();
	}

	@Override
	//返回指定组的组数据
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition);
	}

	@Override
	//返回组的数目
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	//返回指定组位置的id
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	//该方法决定每个组选项的外观
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout layout=new LinearLayout(context);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		
		TextView textView=new TextView(context);
		textView.setText(((DepartmentItem)getGroup(groupPosition)).getName());
		textView.setEms(50);
		textView.setPadding(50, 10, 10, 10);
		layout.addView(textView);
		return layout;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	


}
