package org.javatribe.courseSystem.Activity.course;


import java.util.ArrayList;
import java.util.HashMap;

import org.javatribe.courseSystem.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

/**选择课时数界面
 * @author qing
 *2014/10/2
 */
public class CourseCountOptionFragment extends Fragment implements OnClickListener{

	Button btnOk;
	Spinner spinnerCourseCount;
	private String mCourseCount;
	private static final String TAG="AddOrg";
	private SharedPreferences setting;
	private final String PREFS_NAME="org.javatribe.courseSystem";
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View rootView=inflater.inflate(R.layout.fragment_course_count_option,null);
		btnOk=(Button)rootView.findViewById(R.id.fcc_btn_ok);
		btnOk.setOnClickListener(this);
		 spinnerCourseCount=(Spinner)rootView.findViewById(R.id.fcc_sp_courseCount);
			spinnerCourseCount.setOnItemSelectedListener(new OnItemSelectedListener()
			{
			

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					mCourseCount=(String)spinnerCourseCount.getItemAtPosition(position);
					Toast.makeText(getActivity(), mCourseCount+"",Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		

	 
			

	    	


		return rootView;
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Bundle bundle=new Bundle();
		bundle.putInt("courseCount", Integer.parseInt(mCourseCount));
		setting=getActivity().getSharedPreferences(PREFS_NAME, 0);
		Editor editor=setting.edit();
		editor.putInt("dailyCourseCount",  Integer.parseInt(mCourseCount));
		MyCourseFragment fragment=new MyCourseFragment();
		fragment.setArguments(bundle);
		getActivity().getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment).addToBackStack(null)
		.commit();
	}
	

}
