package org.javatribe.courseSystem.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.*;
import org.javatribe.courseSystem.Activity.admin.AdminMainActivity;

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

/**主界面中的侧滑菜单
 * @author qing
 *2014/9/28
 */
public class MenuFragment extends Fragment {

	private ExpandableListView mf_lv_menu;
	private int[] drawable;
	//SimpleAdapter adapter;
	private ArrayAdapter temp;
	private ExpandableListAdapter adapter;
	private OnMenuSelected callBack;
	private Button btnSwitchToAdmin;//可切换至管理员通道的按钮
	private Boolean mIsAdmin;//判断该用户是否为管理员
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try
		{
			callBack=(OnMenuSelected)activity;
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
		View root=inflater.inflate(R.layout.fragment_user_menu, null);
		mf_lv_menu=(ExpandableListView)root.findViewById(R.id.mf_lv_menu);
		btnSwitchToAdmin=(Button)root.findViewById(R.id.mf_btn_switch);
		
		//adapter=new SimpleAdapter(this.getActivity(),)
		//temp=new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,texts);
		buildList();
		mIsAdmin=true;
		if(!mIsAdmin)
		{
			btnSwitchToAdmin.setVisibility(View.GONE);
		}
		else
		{
			btnSwitchToAdmin.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(getActivity(),AdminMainActivity.class);
					startActivity(intent);
					
					
				}
				
			});
		}
		
		return root;
	}
	public static interface OnMenuSelected//Fragment与Activity进行通信的接口
	{
		public boolean onMenuSelected(int groupPosition,int childPosition);
		
	}
	public void buildList()
	{
		adapter=new BaseExpandableListAdapter()
		{
			private int[] mainMenu=new int[]{R.string.org_menu,R.string.course_menu,R.string.personal_menu};
			private int[][] subMenu=new int[][]
					{
						{R.string.my_org_menu,R.string.enter_org_menu,R.string.register_org_menu,R.string.my_mission_menu,
							R.string.leave_off_menu,R.string.send_msg_menu,R.string.notice_menu},
						{R.string.my_course_menu,R.string.watch_course_menu,R.string.search_course_menu,R.string.search_course_menu},
						{R.string.personal_msg_menu,R.string.change_pwd_menu,R.string.log_out_menu}
					};
			@Override
			//返回子列表项数据
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
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
		mf_lv_menu.setAdapter(adapter);
		mf_lv_menu.expandGroup(0);
		mf_lv_menu.expandGroup(1);
		mf_lv_menu.expandGroup(2);
		mf_lv_menu.setOnChildClickListener(new OnChildClickListener
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
