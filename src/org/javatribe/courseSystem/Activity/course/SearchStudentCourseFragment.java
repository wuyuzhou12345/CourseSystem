package org.javatribe.courseSystem.Activity.course;

import java.util.ArrayList;
import java.util.List;

import org.javatribe.courseSystem.R;


import org.javatribe.courseSystem.Activity.Util.SerializableList;
import org.javatribe.courseSystem.Activity.Util.Validator;

import org.javatribe.courseSystem.ui.MyDialog;
import org.javatribe.courseSystem.vo.DepartmentItem;
import org.javatribe.courseSystem.vo.PersonItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**搜索特定学生的无课表
 * @author admin
 *
 */
public class SearchStudentCourseFragment extends Fragment {

	
EditText editTextSearchContent;

	Button btnSearch;
	private Bundle bundle;
	String searchContent;
	MyDialog myDialog;
	List<DepartmentItem> departments;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.fragment_search_student_course, null);
		
	
		btnSearch=(Button)rootView.findViewById(R.id.fssc_btn_search);
		editTextSearchContent=(EditText)rootView.findViewById(R.id.fssc_et_search);
		
		
	btnSearch.setOnClickListener(new OnClickListener()
	{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			searchContent=editTextSearchContent.getText().toString();
			
			if(searchContent.matches("^[0-9]*$"))//如果输入的是数字
			{
				//调用搜索学号的方法,返回一个List
				initData();
				bundle=new Bundle();
				bundle.putSerializable("departments", new SerializableList(departments));
				bundle.putInt("courseCount", 10);
				AfterSearchStudentCourseFragment fragment=new AfterSearchStudentCourseFragment();
				fragment.setArguments(bundle);
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();
			}
			else if(searchContent.matches("^[\\u4e00-\\u9fa5]{0,}$"))//如果输入的是中文
			{
				//调用搜索姓名的方法,返回一个List
			initData();
			bundle=new Bundle();
				bundle.putSerializable("departments",  new SerializableList(departments));
				
				AfterSearchStudentCourseFragment fragment=new AfterSearchStudentCourseFragment();
				fragment.setArguments(bundle);
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();
			}
			else
			{
				myDialog=new MyDialog(getActivity());
				myDialog.setTitle("请不要输入非法字符！");
				myDialog.show();
			}
		}
		
	});
		
		return rootView;
	}
	private void initData() {
		// TODO Auto-generated method stub
		departments=new ArrayList<DepartmentItem>();
		List<PersonItem> list1=new ArrayList<PersonItem>();
		list1.add(new PersonItem("林凤文","林凤文"));
		list1.add(new PersonItem("周露颖","周露颖"));
		list1.add(new PersonItem("陈尾华","陈尾华"));
		
		DepartmentItem deptItem1=new DepartmentItem("学习部","学习部",list1);
		departments.add(deptItem1);
		
		List<PersonItem> list2=new ArrayList<PersonItem>();
		list2.add(new PersonItem("张碧茜","张碧茜"));
		list2.add(new PersonItem("林纯","林纯"));
		
		DepartmentItem deptItem2=new DepartmentItem("秘书部","秘书部",list2);
		departments.add(deptItem2);
		
		List<PersonItem> list3=new ArrayList<PersonItem>();
		list3.add(new PersonItem("林春燕","林春燕"));
		list3.add(new PersonItem("黄贝贝","黄贝贝"));
		
		DepartmentItem deptItem3=new DepartmentItem("生活部","生活部",list3);
		departments.add(deptItem3);
	}
	
	
}
