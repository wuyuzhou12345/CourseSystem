package org.javatribe.courseSystem.Activity.organization;

import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.Activity.Util.SerializableMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyAssignmentDetailFragment extends Fragment {

	private TextView textViewTitle;
	private TextView textViewSender;
	private EditText editTextContent;
	private Button buttonLeave;
	private Bundle mBundle;
	private SerializableMap serialMap;
	private Map<String,Object> data;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.fragment_my_assignment_detail, container, false);
		textViewTitle=(TextView)rootView.findViewById(R.id.fmad_tv_title);
		editTextContent=(EditText)rootView.findViewById(R.id.fmad_et_content);
		textViewSender=(TextView)rootView.findViewById(R.id.fmad_tv_sender);
	buttonLeave=(Button)rootView.findViewById(R.id.fmad_btn_leaveFor);
	
	mBundle=getArguments();
	if(mBundle!=null)
	{
		serialMap=(SerializableMap)mBundle.get("detail");
		data=serialMap.getMap();
		Log.i("messageId",data.get("messageId")+"");
	}
	
	textViewTitle.setText(data.get("messageTitle").toString());
	textViewSender.setText(data.get("senderName").toString());
	editTextContent.setText(data.get("messageContent").toString());
	buttonLeave.setOnClickListener(new OnClickListener()
	{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Bundle bundle=new Bundle();
			bundle.putSerializable("detail", serialMap);
			LeaveForInputFragment leaveForInputFragment=new LeaveForInputFragment();
			leaveForInputFragment.setArguments(bundle);
			getActivity().getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame,leaveForInputFragment).addToBackStack(null)
			.commit();
		}
		
	});
	return rootView;
	}

}
