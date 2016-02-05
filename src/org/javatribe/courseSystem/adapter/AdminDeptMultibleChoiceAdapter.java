package org.javatribe.courseSystem.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.ui.MyDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**用于删除部门的适配器，可多选。
 * @author qing
 *2014/10/25
 */
public class AdminDeptMultibleChoiceAdapter extends BaseAdapter
{

	private Context context;
	private List<String> departments;//存放部门名称的List
	private LayoutInflater mInflater;
	private Map<Integer,Boolean> checkboxState;
	public AdminDeptMultibleChoiceAdapter(Context context,List departments)
	{
		this.context=context;
		this.departments=departments;
		mInflater=LayoutInflater.from(context);
		checkboxState=new HashMap<Integer,Boolean>();
		for(int i=0;i<departments.size();i++)
		{
			checkboxState.put(i, false);
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return departments.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return departments.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if(convertView==null)
		{
			convertView=mInflater.inflate(R.layout.fragment_admin_deparment_delete_listitem, parent,false);
			viewHolder=new ViewHolder();
			viewHolder.textViewName=(TextView)convertView.findViewById(R.id.fadod_et_departName);
			viewHolder.cbCheck=(CheckBox)convertView.findViewById(R.id.fadod_cb_check);
			viewHolder.cbCheck.setChecked(checkboxState.get(position));
			convertView.setTag(viewHolder);
		
		}
		else
		{
			viewHolder=(ViewHolder)convertView.getTag();
		}
		viewHolder.textViewName.setText(departments.get(position));
		return convertView;
	}
	public static class ViewHolder
	{
		public TextView textViewName;
		public CheckBox		cbCheck;
	}
	public Map<Integer, Boolean> getCheckboxState() {
		return checkboxState;
	}
	public void setCheckboxState(Map<Integer, Boolean> checkboxState) {
		this.checkboxState = checkboxState;
	}


	
	}
			
		
	
	
	

