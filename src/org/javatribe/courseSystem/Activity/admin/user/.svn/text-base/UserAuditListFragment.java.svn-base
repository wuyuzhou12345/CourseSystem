package org.javatribe.courseSystem.Activity.admin.user;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;




import org.javatribe.courseSystem.model.Student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * 用户审核
 * @author zhong
 * 2014/10/20
 */
public class UserAuditListFragment extends Fragment {
	public ListView lv_list;
	List<Map<String, String>> users;
	ListAdapter adapter;
	List<Student> list;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_user_audit_list,
				container, false);
		Bundle bundle = getArguments();
		lv_list = (ListView) rootView.findViewById(R.id.fua_lv_users_list);
		// fau_list_style_add = (Button)
		// rootView.findViewById(R.id.fau_list_btn_add);
		users = loadUsers();
		list = loadUserList();

		if (users.size() > 0) {
			adapter = new SimpleAdapter(getActivity(), users,
					R.layout.fragment_user_audit_list_style, new String[] { "id",
							"name" }, new int[] { R.id.fua_list_id,
							R.id.fua_list_name });
			/*
			 * fau_list_style_add.setOnClickListener(new OnClickListener() {
			 * 
			 * @Override public void onClick(View arg0) {
			 * Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT); } });
			 */
			lv_list.setAdapter(adapter);
			lv_list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getActivity(), "你选择的是:" + position, 2000)
							.show();
					Bundle bundle_user_detail = new Bundle();
					bundle_user_detail.putString("name",
							((Student) list.get(position)).getName());
					bundle_user_detail.putString("no",
							((Student) list.get(position)).getStuNo());
					bundle_user_detail.putString("dept",
							((Student) list.get(position)).getDept());
					//bundle_user_detail.putString("org",
//							((Student) list.get(position)).getOrganization());
//					bundle_user_detail.putString("section",
//							((Student) list.get(position)).getSection());
					// bundle_user_detail.putSerializable("bundle_user_detail",
					// (Serializable) users.get(position));
					// bundle_user_detail.putParcelable("user_detail",
					// (Parcelable) users.get(position));
//					UserAuditDetailFragment userAuditDetailFragment = new UserAuditDetailFragment();
//					userAuditDetailFragment.setArguments(bundle_user_detail);
//					getActivity().getSupportFragmentManager()
//							.beginTransaction()
//							.replace(R.id.content_frame, userAuditDetailFragment)
//							.addToBackStack(null).commit();
				}
			});
		} else {
			rootView = inflater.inflate(R.layout.fragment_user_audit_nolist,
					container, false);
		}
		return rootView;
	}

	private List<Map<String, String>> loadUsers() {
		String path = "/storage/sdcard1/users";
		try {		
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			String line = null;
			while((line = br.readLine())!=null){
				String[] data = line.split(" ");
			//	Map<String,String> stus = Student.newStudent(data[0],data[1]);
				//list.add(stus);
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private List<Student> loadUserList() {
		String path = "/storage/sdcard1/users";
		try {		
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
			List<Student> list = new ArrayList<Student>();
			String line = null;
			while((line = br.readLine())!=null){
				String[] data = line.split(" ");
				Student stu =new Student();
						//new Student(data[0], data[1]);
				stu.setGender(data[2]);
				stu.setDept(data[3]);
//				stu.setSection(data[5]);
//				stu.setOrganization(data[4]);
				list.add(stu);
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 	
	}
}
