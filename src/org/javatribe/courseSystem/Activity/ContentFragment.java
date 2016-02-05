package org.javatribe.courseSystem.Activity;

import org.javatribe.courseSystem.*;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**主界面中的content
 * @author qing
 *2014/9/28
 */
public class ContentFragment extends Fragment {

	ListView mf_lv_menu;
	int[] drawable;
	SimpleAdapter adapter;
	ArrayAdapter temp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root=inflater.inflate(R.layout.fragment_content, null);
	
		return root;
	}
	/*public List<? Map<String,int>> convertToList()
	{
		
	}*/
			
}
