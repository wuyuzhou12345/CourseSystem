package org.javatribe.courseSystem.ui;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.course.SearchCourseFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ChooseTimeDialog extends DialogFragment implements DialogInterface.OnClickListener{

	private Spinner spChooseDay;//选择星期几
	private Spinner spChooseWeek;//选择周数
	private Spinner spChooseStartCourse;//选择开始节数
	private Spinner spChooseEndCourse;//选择结束节数
	private int mCourseCount;//一天的课时数
	private static final String TAG="AddOrg";
	private ArrayAdapter<Integer> startAdapter;
	private ArrayAdapter<Integer> endAdapter;
	private int mStartCourse;//记录开始节数
	private int mEndCourse;//记录结束节数
	private String mWeekOption;//记录选择单双周
	private String mDayOption;//记录选择了星期几
	private Integer[] startContent;
	private Integer[] endContent;
	private Context context;
	private SearchCourseFragment attachFragment;
	public ChooseTimeDialog(Context context,SearchCourseFragment attachFragment)
	{
		
		this.context=context;
		this.attachFragment=attachFragment;
		mCourseCount=12;
		startContent=new Integer[mCourseCount];
		 endContent=new Integer[mCourseCount];
		 for(int i=0;i<mCourseCount;i++)
		 {
			 startContent[i]=i+1;
		 }
		 for(int i=0;i<mCourseCount;i++)
		 {
			 endContent[i]=i+1;
		 }
		 startAdapter=new ArrayAdapter<Integer>(context,R.layout.spinner,R.id.sp_tv,startContent);
		 endAdapter=new ArrayAdapter<Integer>(context,R.layout.spinner,R.id.sp_tv,endContent);
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("dialog","onCreateDialog");
		LayoutInflater inflater=getActivity().getLayoutInflater();
		 View rootView=inflater.inflate(R.layout.fragment_choose_time,null);
		 spChooseDay=(Spinner)rootView.findViewById(R.id.fct_sp_chooseDay);
		 spChooseWeek=(Spinner)rootView.findViewById(R.id.fct_sp_chooseWeek);
		 spChooseStartCourse=(Spinner)rootView.findViewById(R.id.fct_sp_chooseStartCourse);
		 spChooseEndCourse=(Spinner)rootView.findViewById(R.id.fct_sp_chooseEndCourse);

		
		 spChooseStartCourse.setAdapter(startAdapter);
		 spChooseEndCourse.setAdapter(endAdapter);
		 spChooseStartCourse.setOnItemSelectedListener(new OnItemSelectedListener()
			{
			

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					mStartCourse=(Integer)spChooseStartCourse.getItemAtPosition(position);
					Toast.makeText(getActivity(), mStartCourse+"",Toast.LENGTH_SHORT).show();
					endContent=new Integer[mCourseCount-mStartCourse+1];
					Toast.makeText(getActivity(), "CourseCount="+mCourseCount,Toast.LENGTH_SHORT).show();
					for(int i=0;i<endContent.length;i++)
					{
						endContent[i]=mStartCourse+i;
					}
					 endAdapter=new ArrayAdapter<Integer>(context,R.layout.spinner,R.id.sp_tv,endContent);
					 spChooseEndCourse.setAdapter(endAdapter);
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		 AlertDialog.Builder builder=new AlertDialog.Builder(context);
		 builder=builder.setView(rootView).setTitle("选择时间");
		builder=builder.setPositiveButton("确定",this);
		builder=builder.setNegativeButton("取消",this);
		Dialog dialog=builder.create();
		
	
		return dialog;
	}



		
	
	@Override
	public void onClick(DialogInterface arg0, int which) {
		// TODO Auto-generated method stub
		switch(which)
		{
		case DialogInterface.BUTTON_POSITIVE:
		{
			mDayOption=(String)spChooseDay.getSelectedItem();
			mWeekOption=(String)spChooseWeek.getSelectedItem();
			mStartCourse=(Integer)spChooseStartCourse.getSelectedItem();
			mEndCourse=(Integer)spChooseEndCourse.getSelectedItem();
			Bundle bundle=new Bundle();
			bundle.putString("day", mDayOption);
			bundle.putString("week", mWeekOption);
			bundle.putInt("startCourse", mStartCourse);
			bundle.putInt("endCourse", mEndCourse);
			attachFragment.chooseTimeClickPositiveButton(bundle);
		}
	
		}
	}
	

}
