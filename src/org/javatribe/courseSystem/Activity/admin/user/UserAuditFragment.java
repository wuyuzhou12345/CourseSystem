package org.javatribe.courseSystem.Activity.admin.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;


import org.javatribe.courseSystem.Activity.admin.organization.AddUserFragment;
import org.javatribe.courseSystem.entity.User;

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

/**
 * 组织简介
 * 
 * @author zhou 2014/9/28
 */
public class UserAuditFragment extends Fragment {
	public ListView fau_list;
	private Button fau_btn_back;
	String dept, temp;
	List<Map<String, String>> users;
	ListAdapter adapter;
	public static ArrayList<User> list;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_add_user_list,
				container, false);
		Bundle bundle = getArguments();
		dept = bundle.getString("dept");
		temp = bundle.getString("temp");
		list = new ArrayList<User>();
		User user1 = new User("123", "木头人");
		User user2 = new User("321", "牛头人");
		User user3 = new User("456", "马头人");
		User user4 = new User("654", "猪头人");
		user1.setDept("计科系");
		user1.setSex("男");
		list.add(user1);
		user2.setDept("计科系");
		user2.setSex("女");
		list.add(user2);
		user3.setDept("外语系");
		user3.setSex("男");
		list.add(user3);
		user4.setDept("应数系");
		user4.setSex("女");
		list.add(user4);
		Log.i("UserList", "UserList");

		fau_list = (ListView) rootView.findViewById(R.id.fau_lv_users_list);
		// fau_list_style_add = (Button)
		// rootView.findViewById(R.id.fau_list_btn_add);
		users = new ArrayList<Map<String, String>>();
		list = (ArrayList<User>) findById(findByName(findByMajor(list)));

		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> user = User.newUser(
						((User) list.get(i)).getId(),
						((User) list.get(i)).getName());
				users.add(user);
			}
			adapter = new SimpleAdapter(getActivity(), users,
					R.layout.fragment_add_user_list_style, new String[] { "id",
							"name" }, new int[] { R.id.fau_list_id,
							R.id.fau_list_name });
			/*
			 * fau_list_style_add.setOnClickListener(new OnClickListener() {
			 * 
			 * @Override public void onClick(View arg0) {
			 * Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT); } });
			 */fau_list.setAdapter(adapter);
			fau_list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getActivity(), "你选择的是:" + position, 2000)
							.show();
					Bundle bundle_user_detail = new Bundle();
					bundle_user_detail.putString("temp", temp);
					bundle_user_detail.putString("name",
							((User) list.get(position)).getName());
					bundle_user_detail.putString("id",
							((User) list.get(position)).getId());
					bundle_user_detail.putString("dept",
							((User) list.get(position)).getDept());
					bundle_user_detail.putString("sex",
							((User) list.get(position)).getSex());
					// bundle_user_detail.putSerializable("bundle_user_detail",
					// (Serializable) users.get(position));
					// bundle_user_detail.putParcelable("user_detail",
					// (Parcelable) users.get(position));
					UserDetailFragment userDetailFragment = new UserDetailFragment();
					userDetailFragment.setArguments(bundle_user_detail);
					getActivity().getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content_frame, userDetailFragment)
							.addToBackStack(null).commit();
				}
			});
		} else {
			rootView = inflater.inflate(R.layout.fragment_add_user_nolist,
					container, false);
			fau_btn_back = (Button) rootView.findViewById(R.id.fau_btn_back);
			fau_btn_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					AddUserFragment addUserFragment = new AddUserFragment();
					getActivity().getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content_frame, addUserFragment)
							.addToBackStack(null).commit();
				}
			});
		}
		return rootView;
	}

	public List<User> findByMajor(List<User> users) {
		List<User> match = new ArrayList<User>();
		if (dept.trim().equals("")) {
			return users;
		} else {
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				if (user.getDept().equals(dept.trim())) {
					match.add(user);
				}
			}
		}
		return match;
	}

	public List<User> findByName(List<User> users) {
		List<User> match = new ArrayList<User>();
		String name = temp;
		if (name.trim().equals("") || name == null || name.matches("\\d+")) {
			return users;
		} else {
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				if (user.getName().indexOf(name) != -1) {
					match.add(user);
				}
			}
		}
		return match;
	}

	public List<User> findById(List<User> users) {
		List<User> match = new ArrayList<User>();
		String id = temp;
		if (id.trim().equals("") || id == null || (!id.matches("\\d+"))) {
			return users;
		} else {
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				if (user.getId().indexOf(id) != -1) {
					match.add(user);
				}
			}
		}
		return match;
	}
}
