package org.javatribe.courseSystem.Activity.admin;
import org.javatribe.courseSystem.Activity.admin.power.*;
import org.javatribe.courseSystem.*;
import org.javatribe.courseSystem.Activity.admin.AdminMenuFragment.OnAdminMenuSelected;
import org.javatribe.courseSystem.Activity.admin.notice.AdminBoardManageFragment;
import org.javatribe.courseSystem.Activity.admin.notice.AdminNoticeSearchFragment;
import org.javatribe.courseSystem.Activity.admin.organization.AddUserFragment;
import org.javatribe.courseSystem.Activity.admin.organization.AdminMyManageOrgFragment;
import org.javatribe.courseSystem.Activity.admin.organization.AdminSessionOptionFragment;
import org.javatribe.courseSystem.Activity.admin.user.AdminDeptMemberFragment;
import org.javatribe.courseSystem.Activity.admin.user.AdminLeaveForListFragment;
import org.javatribe.courseSystem.Activity.admin.user.UserAuditFragment;
import org.javatribe.courseSystem.Activity.organization.LeaveForDetailFragment;
import org.javatribe.courseSystem.Activity.organization.LeaveForListFragment;
import org.javatribe.courseSystem.Activity.organization.YesMyOrganizationFragment3;
import org.javatribe.courseSystem.Activity.organization.LeaveForListFragment.SelectLeaveForMessage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.view.slidingmenu.SlidingMenu;
import com.ab.view.titlebar.AbTitleBar;



/**管理员主界面
 * @author qing
 *
 */

public class AdminMainActivity extends AbActivity implements OnAdminMenuSelected,LeaveForListFragment.SelectLeaveForMessage{
	private SlidingMenu menu;
	private int pressback=0;
	private boolean isToastShow=false;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.sliding_menu_content);
		//getActionBar().hide();
		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText("管理员");//设置标题文字
		mAbTitleBar.setLogo(R.drawable.button_selector_back);//设置logo的样式
		mAbTitleBar.setTitleLayoutBackground(R.color.btn_bg);//设置标题栏背景
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);//设置文字位置
		//mAbTitleBar.setLogoLine(R.drawable.line);
		mAbTitleBar.getLogoView().setBackgroundResource(R.drawable.button_selector_menu);//将logo设置成弹出菜单的logo
		
        //主视图的Fragment添加
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, new AdminMyManageOrgFragment())
		.commit();

		//SlidingMenu的配置
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//设置弹出菜单的偏移量
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
		//menu视图的Fragment添加
		menu.setMenu(R.layout.sliding_menu_menu);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new AdminMenuFragment())
		.commit();
		
		mAbTitleBar.getLogoView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (menu.isMenuShowing()) {
 					menu.showContent();
 				} else {
 					menu.showMenu();
 				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		pressback++;
		if (menu.isMenuShowing()) {
			menu.showContent();
		}
		FragmentManager fragmentManager=getSupportFragmentManager();
		if(fragmentManager.getBackStackEntryCount()>0&&pressback<=2)//如果后台栈中的Entry对象大于零而且按下两次以下返回键
		{
			fragmentManager.popBackStack();//弹出该Fragment
		}
		else //否则
		{
			if(!isToastShow)//如果通知还没显示
			{
			//显示通知
			Toast.makeText(this,"再按一次退出程序！",Toast.LENGTH_SHORT).show();
			isToastShow=true;
			}
			else//否则finish
			{
				finish();
			}
		}
		
	}

	@Override
	public boolean onMenuSelected(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub	
		FragmentManager fragmentManager=getSupportFragmentManager();
		FragmentTransaction transaction=fragmentManager.beginTransaction();
		if(menu.isMenuShowing())
		{
		menu.showContent();
		}
		if(groupPosition==0)
		{
			if(childPosition==0)
			{
				transaction.replace(R.id.content_frame, new AdminSessionOptionFragment()).addToBackStack(null).commit();
			}
			if(childPosition==1){
				transaction.replace(R.id.content_frame,new AdminMyManageOrgFragment()).addToBackStack(null).commit();
			}
		}
		if(groupPosition==1)
		{
			if(childPosition==0)
			{
				transaction.replace(R.id.content_frame, new AddUserFragment()).addToBackStack(null).commit();
			}
			if(childPosition==1)
			{
				transaction.replace(R.id.content_frame, new UserAuditFragment()).addToBackStack(null).commit();
			}
			if(childPosition==3)
			{
				transaction.replace(R.id.content_frame, new AdminDeptMemberFragment()).addToBackStack(null).commit();
			}
			if(childPosition==4)
			{
				transaction.replace(R.id.content_frame, new AdminLeaveForListFragment()).addToBackStack(null).commit();
			}
		}
		if(groupPosition==2){
			if(childPosition==0){
				transaction.replace(R.id.content_frame, new AdminBoardManageFragment()).addToBackStack(null).commit();
			}
			if(childPosition==1){
				transaction.replace(R.id.content_frame, new AdminNoticeSearchFragment()).addToBackStack(null).commit();
			}
		}
		if(groupPosition==3)
		{
			transaction.replace(R.id.content_frame, new AdminIdTransferFragment()).addToBackStack(null).commit();
		}
			return true;
		}
	
		
 
/*
 * 实现了LeaveForListFragment中的interface
 * 实现传递信息给Fragment
 * 
 */
	@Override
	public void onSelectLeaveForMessage(Bundle info) {
		// TODO Auto-generated method stub
		LeaveForDetailFragment fragment=new LeaveForDetailFragment();
		fragment.setArguments(info);
		 //Toast.makeText(this,"到了Activity",Toast.LENGTH_SHORT).show();
		
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame,fragment).addToBackStack(null)
		.commit();
	}
	
}
