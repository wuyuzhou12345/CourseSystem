package org.javatribe.courseSystem.Activity.admin.user;



import java.util.ArrayList;

import org.javatribe.courseSystem.R;




import org.javatribe.courseSystem.Activity.admin.organization.AddUserSectionFragment;
import org.javatribe.courseSystem.model.Student;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**查看详情
 * @author zhong
 *2014/10/18
 */
public class UserDetailFragment extends Fragment {

	TextView tv_name;
	TextView tv_id;
	TextView tv_sex;
	TextView tv_dept;
	Button btn_add;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.fragment_detail_user, container, false);
		final Bundle bundle=getArguments();
	    tv_name=(TextView)rootView.findViewById(R.id.fdu_tv_user_name);
		tv_id=(TextView)rootView.findViewById(R.id.fdu_tv_user_id);
		tv_sex=(TextView)rootView.findViewById(R.id.fdu_tv_user_sex);
		tv_dept=(TextView)rootView.findViewById(R.id.fdu_tv_user_dept);
		btn_add=(Button)rootView.findViewById(R.id.fdu_btn_add);
		btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Student stu = new Student();
						//new Student(bundle.getString("id"), bundle.getString("name"));
				stu.setDept(bundle.getString("dept"));
				stu.setGender(bundle.getString("sex"));
				Bundle bundle_user=new Bundle();
				bundle_user.putString("temp",bundle.getString("temp"));
				bundle_user.putString("name", stu.getName());
				bundle_user.putString("no", stu.getStuNo());
				bundle_user.putString("dept", stu.getDept());
				bundle_user.putString("sex", stu.getGender());
				AddUserSectionFragment addUserSectionFragment=new AddUserSectionFragment();
				addUserSectionFragment.setArguments(bundle_user);
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,addUserSectionFragment).addToBackStack(null).commit();
			//	Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT);
			}
		});
		tv_name.setText("姓名： "+bundle.getString("name"));
		tv_id.setText("学号： "+bundle.getString("no"));
		tv_sex.setText("性别： "+bundle.getString("sex"));
		tv_dept.setText("系别： "+bundle.getString("dept"));
		return rootView;
	}
	
}
