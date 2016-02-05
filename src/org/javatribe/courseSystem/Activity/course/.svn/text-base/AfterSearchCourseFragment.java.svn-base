package org.javatribe.courseSystem.Activity.course;

import java.util.ArrayList;
import java.util.List;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.organization.SendMessageFragment;
import org.javatribe.courseSystem.adapter.ListViewAdapter;
import org.javatribe.courseSystem.vo.SessionInfo;
import org.javatribe.courseSystem.vo.PersonItem;
import org.javatribe.courseSystem.vo.SessionInfo;

import com.ab.task.AbTaskQueue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

/**
 * 根据部门和时间检索的无课人员。
 * 
 * @author qing 2014年12月9日
 */
public class AfterSearchCourseFragment extends Fragment {
	private ExpandableListView expandableList;
	private Button btn_send;
	private ListViewAdapter adapter;
	private List<SessionInfo> deptList = new ArrayList<SessionInfo>();
	private AbTaskQueue mAbTaskQueue = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(
				R.layout.fragment_choose_free_person_to_post, container, false);
      mAbTaskQueue=AbTaskQueue.getInstance();
		expandableList = (ExpandableListView) rootView
				.findViewById(R.id.fcfptp_elv_expandable_listview);
		btn_send = (Button) rootView.findViewById(R.id.fcfptp_btn_send);
		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				collectCheckedItems();
			}

		});
		// initData();
		adapter = new ListViewAdapter(getActivity(), deptList);
		expandableList.setAdapter(adapter);
		return rootView;
	}

	private void collectCheckedItems() {
		String checkedItems = "";
		List<String> checkedPerson = adapter.getCheckedPerson();
		if (checkedPerson != null && !checkedPerson.isEmpty()) {
			for (String person : checkedPerson) {
				if (checkedItems.length() > 0) {
					checkedItems += "、";
				}
				checkedItems += person;
			}
		}
		Bundle bundle = new Bundle();
		bundle.putString("persons_choose", checkedItems);
		SendMessageFragment sendMessageAfterFragment = new SendMessageFragment();
		sendMessageAfterFragment.setArguments(bundle);
		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, sendMessageAfterFragment).commit();

	}
public void initData()
{
	

}
}
