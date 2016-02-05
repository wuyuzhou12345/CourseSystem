package org.javatribe.courseSystem.Activity.admin.user;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;



import org.javatribe.courseSystem.Activity.admin.organization.AddUserFragment;
import org.javatribe.courseSystem.entity.User;
import org.javatribe.courseSystem.model.Student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**搜索出的用户列表
 * @author zhong
 *2014/10/15
 */
public class UserListFragment extends Fragment {
	public ListView lv_list;
	private Button btn_back;
	String dept,temp;
	List<Map<String,String>> users;
	ListAdapter adapter;
	public static ArrayList<Student> list;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.fragment_add_user_list, container, false);
		Bundle bundle=getArguments();
		dept = bundle.getString("dept");
		temp = bundle.getString("temp");
		list = new ArrayList<Student>();
		Student stu1 = new Student("123", "木头人");
		Student stu2 = new Student("321", "牛头人");
		Student stu3 = new Student("456", "马头人");
		Student stu4 = new Student("654", "猪头人");
		stu1.setDept("计科系");
		stu1.setGender("男");
		list.add(stu1);
		stu2.setDept("计科系");
		stu2.setGender("女");
		list.add(stu2);
		stu3.setDept("外语系");
		stu3.setGender("男");
		list.add(stu3);
		stu4.setDept("应数系");
		stu4.setGender("女");
		list.add(stu4);
		Log.i("UserList", "UserList");

		lv_list = (ListView)rootView.findViewById(R.id.fau_lv_users_list);
	//	fau_list_style_add = (Button) rootView.findViewById(R.id.fau_list_btn_add);
		users = new ArrayList<Map<String,String>>();
		list = (ArrayList<Student>) findByNo(findByName(findByMajor(list)));

		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String,String> user = User.newUser(((Student) list.get(i)).getStuNo(), ((Student) list.get(i)).getName());
				users.add(user);
			}
			adapter = new SimpleAdapter(getActivity(), users, R.layout.fragment_add_user_list_style, new String[]{"id","name"}, new int[]{R.id.fau_list_id,R.id.fau_list_name});
/*			fau_list_style_add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT);
				}
			});
		*/lv_list.setAdapter(adapter);
			lv_list.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getActivity(), "你选择的是:"+position, 2000).show();
					Bundle bundle_user_detail=new Bundle();
					bundle_user_detail.putString("temp",temp);
					bundle_user_detail.putString("name", ((Student)list.get(position)).getName());
					bundle_user_detail.putString("no", ((Student)list.get(position)).getStuNo());
					bundle_user_detail.putString("dept", ((Student)list.get(position)).getDept());
					bundle_user_detail.putString("sex", ((Student)list.get(position)).getGender());
				//  bundle_user_detail.putSerializable("bundle_user_detail", (Serializable) users.get(position));
				//	bundle_user_detail.putParcelable("user_detail", (Parcelable) users.get(position));
					UserDetailFragment userDetailFragment=new UserDetailFragment();
					userDetailFragment.setArguments(bundle_user_detail);
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,userDetailFragment).addToBackStack(null).commit();
				}
			});
		}else{
			rootView=inflater.inflate(R.layout.fragment_add_user_nolist, container, false);
			btn_back = (Button) rootView.findViewById(R.id.fau_btn_back);
			btn_back.setOnClickListener(new OnClickListener() {			
				@Override
				public void onClick(View arg0) {
					AddUserFragment addUserFragment=new AddUserFragment();
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,addUserFragment).addToBackStack(null).commit();				
				}
			});
		}
		return rootView;
	}
	public List<Student> findByMajor(List<Student> list){
		List<Student> match = new ArrayList<Student>();
		if(dept.trim().equals("")){
			return list;
		}
		else{
			for(int i = 0; i < list.size(); i++){
				Student stu = list.get(i);
				if(stu.getDept().equals(dept.trim())){
					match.add(stu);
				}
			}
		}
		return match;
	}
	public List<Student> findByName(List<Student> users){
		List<Student> match = new ArrayList<Student>();
		String name = temp;
		if(name.trim().equals("")|| name == null||name.matches("\\d+")){
			return users;
		}
		else{
			for(int i = 0; i < users.size(); i++){
				Student stu = users.get(i);
				if(stu.getName().indexOf(name) != -1){
					match.add(stu);
				}
			}
		}
		return match;
	}
	public List<Student> findByNo(List<Student> users){
		List<Student> match = new ArrayList<Student>();
		String id = temp;
		if(id.trim().equals("") || id == null||(!id.matches("\\d+"))){
			return users;
		}
		else{
			for(int i = 0; i < users.size(); i++){
				Student stu = users.get(i);
				if(stu.getStuNo().indexOf(id) != -1){
					match.add(stu);
				}
			}
		}
		return match;
	}
}
