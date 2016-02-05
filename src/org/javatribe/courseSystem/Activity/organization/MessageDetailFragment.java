package org.javatribe.courseSystem.Activity.organization;



import org.javatribe.courseSystem.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageDetailFragment extends Fragment{
	TextView textViewTitle;
	TextView textViewDate;
	TextView textViewContent;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.fragment_message_detail, container, false);
		Bundle bundle=getArguments();
		textViewTitle=(TextView)rootView.findViewById(R.id.fmd_tv_title);
		textViewDate=(TextView)rootView.findViewById(R.id.fmd_tv_date);
		textViewContent=(TextView)rootView.findViewById(R.id.fmd_tv_content);
		
		textViewTitle.setText(bundle.getString("msg_title"));
		textViewDate.setText(bundle.getString("msg_date"));
		textViewContent.setText(bundle.getString("msg_content"));
		return rootView;
	}
}
