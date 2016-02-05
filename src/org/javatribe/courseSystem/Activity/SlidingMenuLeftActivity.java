package org.javatribe.courseSystem.Activity;
import org.javatribe.courseSystem.*;
import org.javatribe.courseSystem.Activity.course.CourseCountOptionFragment;
import org.javatribe.courseSystem.Activity.course.SearchCourseFragment;
import org.javatribe.courseSystem.Activity.course.SearchNoClassPersonFragment;
import org.javatribe.courseSystem.Activity.course.SearchStudentCourseFragment;
import org.javatribe.courseSystem.Activity.organization.AddOrgFragment;
import org.javatribe.courseSystem.Activity.organization.LeaveForDetailFragment;
import org.javatribe.courseSystem.Activity.organization.LeaveForListFragment;
import org.javatribe.courseSystem.Activity.organization.MyAssignmentFragment;
import org.javatribe.courseSystem.Activity.organization.MyMessageFragment;
import org.javatribe.courseSystem.Activity.organization.NoticeFragment;
import org.javatribe.courseSystem.Activity.organization.RegisterOrgFragment;
import org.javatribe.courseSystem.Activity.organization.YesMyOrganizationFragment3;
import org.javatribe.courseSystem.Activity.personalInfo.PersonalInformationFragment;
import org.javatribe.courseSystem.Activity.personalInfo.ResetPasswordFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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




/**普通用户主界面
 * @author qing
 *2014/9/28
 */

public class SlidingMenuLeftActivity extends AbActivity implements MenuFragment.OnMenuSelected,LeaveForListFragment.SelectLeaveForMessage{
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
		mAbTitleBar.setTitleText("用户");//设置标题文字
		mAbTitleBar.setLogo(R.drawable.button_selector_back);//设置logo的样式
		mAbTitleBar.setTitleLayoutBackground(R.color.btn_bg);//设置标题栏背景
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);//设置文字位置
		//mAbTitleBar.setLogoLine(R.drawable.line);
		mAbTitleBar.getLogoView().setBackgroundResource(R.drawable.button_selector_menu);//将logo设置成弹出菜单的logo
		
        //主视图的Fragment添加
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, new YesMyOrganizationFragment3())
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
		.replace(R.id.menu_frame, new MenuFragment())
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
	
		if(groupPosition==0){
			if(childPosition==0){
				menu.showContent();
			
			
				transaction.replace(R.id.content_frame, new YesMyOrganizationFragment3());
				transaction.addToBackStack(null);//将该事务放入栈中，提交后会被记住。
				transaction.commit();
				}
			if(childPosition==1){
				
				
				
				transaction.replace(R.id.content_frame, new AddOrgFragment()).addToBackStack(null)
				.commit();
			}
			if(childPosition==2){
				
			
			
				transaction.replace(R.id.content_frame, new RegisterOrgFragment()).addToBackStack(null)
				.commit();
			}
			if(childPosition==3){
				
				
				
				transaction.replace(R.id.content_frame, new MyAssignmentFragment()).addToBackStack(null)
				.commit();
			}
			if(childPosition==4){
				
			
			
				transaction.replace(R.id.content_frame, new LeaveForListFragment()).addToBackStack(null)
				.commit();
			}
			if(childPosition==5){
				
				
				
				transaction.replace(R.id.content_frame, new MyMessageFragment()).addToBackStack(null)
				.commit();
			}
				if(childPosition==6){
					menu.showContent();
				
					getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.content_frame, new NoticeFragment()).addToBackStack(null)
					.commit();
				}
			
			
		}
		if(groupPosition==1)
		{
			if(childPosition==0)
			{
				menu.showContent();
				
				getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, new CourseCountOptionFragment()).addToBackStack(null)
				.commit();
			}
			if(childPosition==1)
			{
menu.showContent();
				
				getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, new SearchNoClassPersonFragment()).addToBackStack(null)
				.commit();
			}
			if(childPosition==2)
			{
menu.showContent();
				
				getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, new SearchCourseFragment()).addToBackStack(null)
				.commit();
			}
			if(childPosition==3)
			{
menu.showContent();
				
				getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, new SearchStudentCourseFragment()).addToBackStack(null)
				.commit();
			}
		}
		if(groupPosition==2)
		{
			if(childPosition==0)
			{
				menu.showContent();
				
				getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, new PersonalInformationFragment()).addToBackStack(null)
				.commit();
			}
			if(childPosition==1)
			{
				menu.showContent();
				
				getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, new ResetPasswordFragment()).addToBackStack(null)
				.commit();
			}
			if(childPosition==2)
			{
				Intent intent=new Intent();
				intent.setClass(SlidingMenuLeftActivity.this,LoginActivity.class);
				startActivity(intent);
				
			}
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
