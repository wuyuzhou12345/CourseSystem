package org.javatribe.courseSystem.Activity.admin.organization;


import java.util.ArrayList;
import java.util.List;

import org.javatribe.courseSystem.R;




import org.javatribe.courseSystem.Activity.admin.user.UserListFragment;
import org.javatribe.courseSystem.model.Student;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**搜索用户
 * @author zhong
 *2014/10/11
 */
public class AddUserFragment extends Fragment{

	Spinner sp_dept;
	EditText et_name_or_id;
	Button btn_search;
	SimpleAdapter adapter;
	Student stu1 = new Student("123", "木头人");
	Student stu2 = new Student("321", "牛头人");
	Student stu3 = new Student("456", "马头人");
	Student stu4 = new Student("654", "猪头人");
	private static final String TAG="AddUser";
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View rootView=inflater.inflate(R.layout.fragment_add_user,container, false);
		sp_dept=(Spinner)rootView.findViewById(R.id.fau_sp_user_dept);
		et_name_or_id=(EditText)rootView.findViewById(R.id.fau_et_user_name_or_id);
		btn_search=(Button)rootView.findViewById(R.id.fau_btn_search);


	    btn_search.setOnClickListener(new OnClickListener(){
	    	@SuppressWarnings("unchecked")
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
	    		String dept =sp_dept.getSelectedItem().toString();
				String temp=et_name_or_id.getText().toString();
				Log.i(TAG,"dept="+dept);
				Log.i(TAG,"temp="+temp);
				int j=0,k=0,l=0;					
				if((dept.equals("")||dept.equals(null))&&(temp.equals("")||temp.equals(null))){
				    	AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
				    	builder.setTitle("Error");
				    	builder.setMessage("搜索内容不能为空！");
				    	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				    		   public void onClick(DialogInterface dialog, int which){
				    			   dialog.dismiss();

				    		   }
				    	});
				    	builder.show();
				}
				else{
					Bundle bundle_user=new Bundle();
					bundle_user.putString("dept", dept);
					bundle_user.putString("temp", temp);
					UserListFragment userListFragment=new UserListFragment();
					userListFragment.setArguments(bundle_user);
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,userListFragment).addToBackStack(null).commit();
				}			
			}    	
	    });

		return rootView;
	}
	
}
