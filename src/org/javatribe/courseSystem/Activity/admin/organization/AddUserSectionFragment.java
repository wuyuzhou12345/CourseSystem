package org.javatribe.courseSystem.Activity.admin.organization;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;




import org.javatribe.courseSystem.Activity.admin.user.UserListFragment;
import org.javatribe.courseSystem.model.Student;

import android.annotation.SuppressLint;
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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**加入组织
 * @author zhong
 *2014/10/20
 */
public class AddUserSectionFragment extends Fragment{

	Spinner sp_section;
	Button btn_add;
	SimpleAdapter adapter;
	Student stu;
	Map<String,List<Student>> RedCross;
	List<Student> publicRelationsDepartment;
	List<Student> secretaryDepartment;
	List<Student> organizationDepartment;
	List<Student> propagandaDepartment;
	List<Student> trainingDepartment;
	List<Student> users = new ArrayList<Student>();
	Student stu1 = new Student("123", "木头人");
	Student stu2 = new Student("321", "牛头人");
	Student stu3 = new Student("456", "马头人");
	Student stu4 = new Student("654", "猪头人");
	private static final String TAG="AddUser";
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final Bundle bundle_user = getArguments();
		stu = new Student(bundle_user.getString("no"), bundle_user.getString("name"));
		stu.setDept(bundle_user.getString("dept"));
		stu.setGender(bundle_user.getString("sex"));
		final View rootView=inflater.inflate(R.layout.fragment_add_user_section,container, false);
		sp_section=(Spinner)rootView.findViewById(R.id.fauc_sp_section);
		btn_add=(Button)rootView.findViewById(R.id.fauc_btn_add);
		RedCross = new HashMap<String, List<Student>>();
		publicRelationsDepartment = new ArrayList<Student>();
		publicRelationsDepartment.add(stu1);
		publicRelationsDepartment.add(stu2);
		RedCross.put("publicRelationsDepartment", publicRelationsDepartment);
		secretaryDepartment = new ArrayList<Student>();
		secretaryDepartment.add(stu3);
		RedCross.put("secretaryDepartment", secretaryDepartment);
		organizationDepartment = new ArrayList<Student>();
		RedCross.put("organizationDepartment", organizationDepartment);
		propagandaDepartment = new ArrayList<Student>();
		RedCross.put("propagandaDepartment", propagandaDepartment);
		trainingDepartment = new ArrayList<Student>();
		RedCross.put("trainingDepartment", trainingDepartment);
	    btn_add.setOnClickListener(new OnClickListener(){
			@SuppressLint("ShowToast")
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
	    		String section = sp_section.getSelectedItem().toString();
				if((section.trim().equals("")||section.equals(null))){
				    	AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
				    	builder.setTitle("Error");
				    	builder.setMessage("请选择加入的部门！");
				    	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				    		   public void onClick(DialogInterface dialog, int which){
				    			   dialog.dismiss();

				    		   }
				    	});
				    	builder.show();
				}
				else{
					boolean isAdd = false;
					String message = "添加 "+stu.getName()+"到";
					if(section.equals("秘书部")){
						if(!secretaryDepartment.contains(stu)){
							message+="secretaryDepartment";
							secretaryDepartment.add(stu);
							RedCross.put("secretaryDepartment", secretaryDepartment);
							isAdd = true;
						}
					}
					if(section.equals("宣传部")){
						if(!propagandaDepartment.contains(stu)){
							message+="propagandaDepartment";
							propagandaDepartment.add(stu);
							RedCross.put("propagandaDepartment", propagandaDepartment);
							isAdd = true;
						}	
					}
					if(section.equals("组织部")){
						if(!organizationDepartment.contains(stu)){
							message+="organizationDepartment";
							organizationDepartment.add(stu);
							RedCross.put("organizationDepartment", organizationDepartment);
							isAdd = true;
						}
					}
					if(section.equals("外联部")){
						if(!publicRelationsDepartment.contains(stu)){
							message+="publicRelationsDepartment";
							publicRelationsDepartment.add(stu);
							RedCross.put("publicRelationsDepartment", publicRelationsDepartment);
							isAdd = true;
						}
					}
					if(section.equals("培训部")){
						if(!trainingDepartment.contains(stu)){
							message+="trainingDepartment";
							trainingDepartment.add(stu);
							RedCross.put("trainingDepartment", trainingDepartment);			
							isAdd = true;
						}
					}
					if (isAdd) {
						message+="成功！";
					}else{
						message = stu.getName()+"已在该部门！不需要重复添加。";
					}
					
					Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();;
			//		ArrayList<? extends Parcelable> users_list = UserListFragment.list;
					Bundle bundle=new Bundle();
			//		users_list.remove(user);
			//		bundle_user.putParcelableArrayList("list_user", users_list);
					bundle.putString("dept", stu.getDept());
					bundle.putString("temp", bundle_user.getString("temp"));
					UserListFragment userListFragment=new UserListFragment();
					userListFragment.setArguments(bundle);
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,userListFragment).addToBackStack(null).commit();
				
				}			
			}    	
	    });

		return rootView;
	}
}
