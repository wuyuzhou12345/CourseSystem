package org.javatribe.courseSystem.Activity.admin.power;

import java.util.ArrayList;
import java.util.List;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.adapter.IdExpandableListAdapter;




import org.javatribe.courseSystem.model.Student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

/**
 * 组织简介
 * 
 * @author zhong 2014/11/01
 */
public class AdminIdTransferFragment extends Fragment {
	private ExpandableListView expandableListView;
	IdExpandableListAdapter ideasExpandableListAdapter;
	Student stu1,stu2,stu3,stu4;
	
	private List<String> groupName ;
	private List<String> group1 ;
	private List<String> group2 ;
	private List<String> group3 ;
	private List<List<String>> groups ;
	private ArrayList<Student> list;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_id_transfer,
				container, false);
		Bundle bundle = getArguments();
		list = new ArrayList<Student>();
//		stu1 = new Student("牛头人","123");
//		stu1.setDept("计科系");
//		stu1.setOrganization("红会");
//		stu1.setSection("宣传部");
//		stu2 = new Student("马头人","124");
//		stu2.setDept("计科系");
//		stu2.setOrganization("红会");
//		stu2.setSection("组织部");
//		stu3 = new Student("狗头人","125");
//		stu3.setDept("计科系");
//		stu3.setOrganization("红会");
//		stu3.setSection("组织部");
//		stu4 = new Student("猪头人","126");
//		stu4.setDept("计科系");
//		stu4.setOrganization("红会");
//		stu4.setSection("组织部");
		list.add(stu1);
		list.add(stu2);
		list.add(stu3);
		list.add(stu4);
		loadGroups();
		expandableListView = (ExpandableListView) rootView
				.findViewById(R.id.fit_elv);
		ideasExpandableListAdapter = new IdExpandableListAdapter(
				getActivity(), groupName,groups);
		expandableListView.setAdapter(ideasExpandableListAdapter);
		OnChildClickListener childListener = new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Bundle bundle = new Bundle();
				bundle.putString("no", groups.get(groupPosition).get(childPosition));
				bundle.putString("name", groups.get(groupPosition).get(childPosition));
				bundle.putString("dept", groups.get(groupPosition).get(childPosition));
				bundle.putString("org", groups.get(groupPosition).get(childPosition));
				bundle.putString("section", groups.get(groupPosition).get(childPosition));
				AdminTransferDetailFragment transferDetailFragment = new AdminTransferDetailFragment();
				transferDetailFragment.setArguments(bundle);
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,transferDetailFragment).addToBackStack(null).commit();
				return false;
			}
		};
		expandableListView.setOnChildClickListener(childListener);
		return rootView;
	}

	private void loadGroups() {
		groupName = new ArrayList<String>();
		groupName.add("宣传部");
		groupName.add("组织部");
		groupName.add("外联部");
		
		groups = new ArrayList<List<String>>();
		group2 = new ArrayList<String>();
		group1 = new ArrayList<String>();
		group3 = new ArrayList<String>();
		
		groups.add(group1);
		groups.add(group2);
		groups.add(group3);
		
//		for (int j = 0; j < groupName.size(); j++) {
//			for (int i = 0; i < list.size(); i++) {
//				if (list.get(i).getSection().equals(groupName.get(j))) {
//					List<String> l = groups.get(j);
//					l.add(list.get(i).getName());
//					groups.set(j,l);
//				}
//			}
//		}
	}
}
