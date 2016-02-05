package org.javatribe.courseSystem.Activity.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.*;
import org.javatribe.courseSystem.Activity.SlidingMenuLeftActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;

/**管理员
 *主界面中的侧滑菜单
 * @author qing
 *2014/9/28
 */
public class AdminMenuFragment extends Fragment {

	private ExpandableListView listViewMenu;
	private int[] drawable;
	//SimpleAdapter adapter;
	private ArrayAdapter temp;
	private ExpandableListAdapter adapter;
	private OnAdminMenuSelected callBack;
	private Button btnSwitchToUser;//可切换至管理员通道的按钮
	private Boolean mIsAdmin;//判断该用户是否为管理员
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try
		{
			callBack=(OnAdminMenuSelected)activity;
		}
		catch(ClassCastException e)
		{
			throw e;
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root=inflater.inflate(R.layout.fragment_admin_menu, null);
		 listViewMenu=(ExpandableListView)root.findViewById(R.id.mfa_lv_menu);
		btnSwitchToUser=(Button)root.findViewById(R.id.mfa_btn_switch);
		
		//adapter=new SimpleAdapter(this.getActivity(),)
		//temp=new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,texts);
		buildList();
		//mIsAdmin=true;
		btnSwitchToUser.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),SlidingMenuLeftActivity.class);
				startActivity(intent);
			}
			
		});
		return root;
	}
	public static interface OnAdminMenuSelected//Fragment与Activity进行通信的接口
	{
		public boolean onMenuSelected(int groupPosition,int childPosition);
		
	}
	public void buildList()
	{
		adapter=new BaseExpandableListAdapter()
		{
			private int[] mainMenu=new int[]{R.string.org_manage,R.string.member_manage,R.string.notice_manage,R.string.right_transform,R.string.log_out_menu};
			private int[][] subMenu=new int[][]
					{
			
						{R.string.session_manage,R.string.my_org_menu},
						{R.string.member_add,R.string.member_check,R.string.check_record,R.string.member_delete,R.string.leave_application_manage},
			
						{R.string.post_notice,R.string.delete_notice},
						
						
					};
			@Override
			//返回子列表项数据
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				if(groupPosition==3||groupPosition==4)
				{
					return null;
				}
				
				return subMenu[groupPosition][childPosition];
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
				TextView text=new TextView(getActivity());
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
				if(groupPosition==3||groupPosition==4)
				{
					return null;
				}
				TextView textView=new TextView(getActivity());
				textView.setEms(20);
				textView.setPadding(60, 10, 10, 10);
				textView.setText(subMenu[groupPosition][childPosition]);
				
				return textView;
			}

			@Override
			//返回子列表项数目
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				if(groupPosition==3||groupPosition==4)
				{
					return 0;
				}
				return subMenu[groupPosition].length;
			}

			@Override
			//返回指定组的组数据
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return mainMenu[groupPosition];
			}

			@Override
			//返回组的数目
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return mainMenu.length;
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
				LinearLayout layout=new LinearLayout(getActivity());
				layout.setOrientation(LinearLayout.HORIZONTAL);
				//ImageView imageView;还没加图标
				TextView textView=new TextView(getActivity());
				textView.setText(mainMenu[groupPosition]);
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
			
		};
	listViewMenu.setAdapter(adapter);
	listViewMenu.expandGroup(0);
	listViewMenu.expandGroup(1);
	listViewMenu.expandGroup(2);
	listViewMenu.setOnChildClickListener(new OnChildClickListener
		(){

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				return	callBack.onMenuSelected(groupPosition,childPosition);
				
			}
			
		});
	
	}
			
}
