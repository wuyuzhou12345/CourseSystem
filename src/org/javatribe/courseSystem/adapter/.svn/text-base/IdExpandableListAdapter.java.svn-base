package org.javatribe.courseSystem.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class IdExpandableListAdapter extends BaseExpandableListAdapter {

	private Context mContext = null;

	private String[] groups = { "第一小组","第二小组","第三小组" };
	private String[] familis = { "小组成员1","小组成员2","小组成员3" };
	private String[] friends = { "小组成员1","小组成员2","小组成员3" };
	private String[] colleagues = { "小组成员1","小组成员2","小组成员3" };
	private List<String> groupList = null;

	private List<List<String>> itemList = null;

	public IdExpandableListAdapter(Context context) {
		this.mContext = context;
		groupList = new ArrayList<String>();
		itemList = new ArrayList<List<String>>();
		initData();
	}
	public IdExpandableListAdapter(Context context,String[] parent,String[] son1,String[] son2,String[] son3) {
		this.mContext = context;
		groups = parent;
		familis = son1;
		friends = son2;
		colleagues = son3;
		groupList = new ArrayList<String>();
		itemList = new ArrayList<List<String>>();
		initData();
	}
	public IdExpandableListAdapter(Context context,List<String> groupName,List<List<String>> list) {
		this.mContext = context;
		groupList = groupName;
		itemList = list;
	}

	private void initData() {
		for (int i = 0; i < groups.length; i++) {
			groupList.add(groups[i]);
		}
		List<String> item1 = new ArrayList<String>();
		for (int i = 0; i < familis.length; i++) {
			item1.add(familis[i]);
		}
		List<String> item2 = new ArrayList<String>();
		for (int i = 0; i < friends.length; i++) {
			item2.add(friends[i]);
		}
		List<String> item3 = new ArrayList<String>();
		for (int i = 0; i < colleagues.length; i++) {
			item3.add(colleagues[i]);
		}
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
	}

	public boolean areAllItemsEnabled() {
		return false;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return itemList.get(groupPosition).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView text = null;
		if (convertView == null) {
			text = new TextView(mContext);
		} else {
			text = (TextView) convertView;
		}
		// 获取子节点要显示的名称
		String name = (String) itemList.get(groupPosition).get(childPosition);
		// 设置文本视图的相关属性
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 40);
		text.setLayoutParams(lp);
		text.setTextSize(18);
		text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		text.setPadding(45, 0, 0, 0);
		text.setText(name);
		return text;
	}

	public int getChildrenCount(int groupPosition) {
		return itemList.get(groupPosition).size();
	}

	public Object getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	public int getGroupCount() {
		return groupList.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView text = null;
		if (convertView == null) {
			text = new TextView(mContext);
		} else {
			text = (TextView) convertView;
		}
		String name = (String) groupList.get(groupPosition);
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 40);
		text.setLayoutParams(lp);
		text.setTextSize(18);
		text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		text.setPadding(36, 0, 0, 0);
		text.setText(name);
		return text;
	}

	public boolean isEmpty() {
		return false;
	}

	public void onGroupCollapsed(int groupPosition) {
	}

	public void onGroupExpanded(int groupPosition) {
	}

	public boolean hasStableIds() {
		return false;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}