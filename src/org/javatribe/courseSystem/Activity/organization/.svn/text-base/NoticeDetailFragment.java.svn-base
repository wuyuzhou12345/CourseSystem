package org.javatribe.courseSystem.Activity.organization;

import org.javatribe.courseSystem.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**公告栏详细
 * @author zhou
 *2014/9/28
 */
public class NoticeDetailFragment extends Fragment{	
	TextView fdn_tv_title;
	TextView fdn_tv_date_person;
	TextView fdn_tv_content;
	ImageView fdn_iv_thread;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
 		Bundle bundle=getArguments();
		//Log.i("123456",bundle.getString("title"));
		View rootView=inflater.inflate(R.layout.fragment_detail_notice, container, false);
		fdn_tv_title=(TextView)rootView.findViewById(R.id.fdn_tv_title);
		fdn_tv_date_person=(TextView)rootView.findViewById(R.id.fdn_tv_date_person);
		fdn_tv_content=(TextView)rootView.findViewById(R.id.fdn_tv_content);
		fdn_iv_thread=(ImageView)rootView.findViewById(R.id.fdn_iv_thread);
		fdn_tv_title.setText(bundle.getString("title"));
		fdn_tv_date_person.setText(bundle.getString("date_person"));
		fdn_tv_content.setText(bundle.getString("content"));
		return rootView;
	}
	

}
