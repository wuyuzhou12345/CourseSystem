package org.javatribe.courseSystem.Activity.course;


import java.util.ArrayList;

import org.javatribe.courseSystem.Activity.Util.*;

import java.util.HashMap;
import java.util.List;

import org.javatribe.courseSystem.R;




import org.javatribe.courseSystem.model.Course;
import org.javatribe.courseSystem.ui.MyImageView;
import org.javatribe.courseSystem.model.*;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.GridLayout;

/**某个学生的无课表界面
 * @author qing
 *2014/11/8
 */
public class StudentCourseFragment extends Fragment implements OnClickListener{

	GridLayout gridlayoutCourse;
	private Bundle bundle;
	private List<Course> courses;
	int rowNum;
	int colNum;
	int[][] press;
	private int mCourseCount;
	private static final String TAG="";
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View rootView=inflater.inflate(R.layout.fragment_student_course,null);
		 gridlayoutCourse=(GridLayout)rootView.findViewById(R.id.fsc_gl_course);
		 bundle=getArguments();
		 mCourseCount=10;
		 //mCourseCount=bundle.getInt("courseCount");
		 courses=((SerializableList)bundle.getSerializable("courses")).getList();
		 rowNum=mCourseCount+2;//行数等于课时数加1
		 colNum=6;//上课时间为5天，所以设置了6列
		 gridlayoutCourse.setRowCount(rowNum+1);
		 gridlayoutCourse.setColumnCount(colNum);
		 press=new int[colNum-1][mCourseCount];//实际的所有课数是colNum
		 for(int i=0;i<press.length;i++)
		 {
			 for(int j=0;j<press[i].length;j++)
			 press[i][j]=1;
		 }
		 
		 for(int i=0;i<courses.size();i++)
		 {
			 Course course=(Course)courses.get(i);
			 int course_no=course.getCourseNo();
			 int week_type=course.getWeekType();
			 int week_day=course.getWeekday();
			 switch(week_type)
				{
				case 0:
					press[week_day-1][course_no-1]=0;
					break;
				case 1:
					press[week_day-1][course_no-1]=2;
					break;
				case 2:
					press[week_day-1][course_no-1]=3;
					break;
				}
			
		 }

		 //Toast.makeText(getActivity(), "i="+, Toast.LENGTH_SHORT).show();
		 for(int i=0;i<press.length;i++)
		 {
			 for(int j=0;j<press[i].length;j++)
			 {
				 GridLayout.Spec rowSpec=GridLayout.spec(j+1);
					GridLayout.Spec colSpec=GridLayout.spec(i+1);
					GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec,colSpec);
					MyImageView img=new MyImageView(getActivity());
					switch(press[i][j])
					{
					case 0:
						img.setBackgroundResource(R.drawable.top_bg);
						break;
					case 1:
						img.setBackgroundResource(R.drawable.ic_launcher);
						img.setAdjustViewBounds(true);
						break;
					case 2:
						img.setBackgroundResource(R.drawable.back_f);
						img.setAdjustViewBounds(true);
						break;
					case 3:
						img.setBackgroundResource(R.drawable.back_n);
						img.setAdjustViewBounds(true);
						break;
					}
					img.setRow(j);
					img.setCol(i);
					//img.setOnClickListener(this);
					gridlayoutCourse.addView(img,params);
			 } 
		 }
		 for(int i=1;i<=mCourseCount;i++)
		 {
			 GridLayout.Spec rowSpec=GridLayout.spec(i);
				GridLayout.Spec colSpec=GridLayout.spec(0);
				GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec,colSpec);
			 TextView tv=new TextView(getActivity());
				tv.setText(i+"");
				gridlayoutCourse.addView(tv,params);
		 }
//		 for(int i=0;i<mCourseCount*colNum;i++)
//			{
//				//Toast.makeText(getActivity(), "i="+i, Toast.LENGTH_SHORT).show();
//				GridLayout.Spec rowSpec=GridLayout.spec(i/colNum+1);
//				GridLayout.Spec colSpec=GridLayout.spec(i%colNum);
//				GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec,colSpec);
//			
//				if(i%colNum!=0)
//				{ 
//				MyImageView img=new MyImageView(getActivity());
//			
//				img.setBackgroundResource(R.drawable.ic_launcher);
//				img.setRow(i/colNum);
//				img.setCol(i%colNum-1);
//				img.setOnClickListener(this);
//				gridlayoutCourse.addView(img,params);
//				}
//				else
//				{
//					TextView tv=new TextView(getActivity());
//					tv.setText(i/colNum+1+"");
//					gridlayoutCourse.addView(tv,params);
//				}
//			
//				
//			 // params.width=
//				//params.setGravity(Gravity.FILL);//如果不想要拉伸就不要设置gravity.
//		
//			
//			}
		
		 return rootView;
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v instanceof MyImageView)//如果点击的是MyImageView的对象
		{
			MyImageView myView=(MyImageView)v;
			int row=myView.getRow();
			int col=myView.getCol();
			int press_time=press[col][row];
			press_time++;
			if(press_time>3)
			{
				press_time=0;
			}
			
			switch(press_time)
			{
			case 0:
				myView.setBackgroundResource(R.drawable.top_bg);
				break;
			case 1:
				myView.setBackgroundResource(R.drawable.ic_launcher);
				myView.setAdjustViewBounds(true);
				break;
			case 2:
				myView.setBackgroundResource(R.drawable.back_f);
				myView.setAdjustViewBounds(true);
				break;
			case 3:
				myView.setBackgroundResource(R.drawable.back_n);
				myView.setAdjustViewBounds(true);
				break;
			}
			press[col][row]=press_time;
			Toast.makeText(getActivity(), "row="+row+"col="+col+"presstime="+press_time, Toast.LENGTH_SHORT).show();
		}
	}

	

}
