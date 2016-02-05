package org.javatribe.courseSystem.Activity.course;

import java.util.ArrayList;
import java.util.List;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.adapter.ExpandableListViewAdapter;
import org.javatribe.courseSystem.adapter.ListViewAdapter;





import org.javatribe.courseSystem.model.Course;
import org.javatribe.courseSystem.vo.DepartmentItem;
import org.javatribe.courseSystem.vo.PersonItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;

import org.javatribe.courseSystem.Activity.Util.*;
/**显示搜索学号或姓名的界面
 * @author qingfei
 *
 */
public class AfterSearchStudentCourseFragment extends Fragment{
	private ExpandableListView expandableList;
	private Bundle mBundle;
	private ExpandableListViewAdapter adapter;
	private List<DepartmentItem> deptList=new ArrayList<DepartmentItem>();
	private List<Course> courses;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.fragment_after_search_student_course,container, false);
		mBundle=getArguments();
		if(mBundle!=null||mBundle.getSerializable("students")!=null)
		{
			deptList=((SerializableList)mBundle.getSerializable("departments")).getList();
		}
		expandableList=(ExpandableListView)rootView.findViewById(R.id.fassc_lv_students);
		adapter=new ExpandableListViewAdapter(getActivity(),deptList);
		expandableList.setAdapter(adapter);
		expandableList.setOnChildClickListener(new OnChildClickListener()
		{

			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) 
			{
				// TODO Auto-generated method stub
				
				PersonItem person=deptList.get(groupPosition).getPersonItems().get(childPosition);
				//调用搜索该学生的无课表,返回List<Course>
				courses=new ArrayList<Course>();
				Course course1=new Course();
				course1.setCourseNo(1);
				course1.setWeekday(1);
				course1.setWeekType(0);
				Course course2=new Course();
				course2.setCourseNo(2);
				course2.setWeekday(1);
				course2.setWeekType(0);
				Course course3=new Course();
				course3.setCourseNo(9);
				course3.setWeekday(4);
				course3.setWeekType(0);
				Course course4=new Course();
				course4.setCourseNo(2);
				course4.setWeekday(3);
				course4.setWeekType(1);
				courses.add(course1);
				courses.add(course2);
				courses.add(course3);
				courses.add(course4);
				Bundle bundle=new Bundle();
				bundle.putInt("courseCount", 10);
				bundle.putSerializable("courses", new SerializableList(courses));
				StudentCourseFragment fragment=new StudentCourseFragment();
				fragment.setArguments(bundle);
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();
				return true;
			}
			
			
		});
		
		
		return rootView;
	}

		
	
	
	
}
