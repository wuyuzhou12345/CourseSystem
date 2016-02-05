package org.javatribe.courseSystem.adapter;

import java.util.List;

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

public class AdminDeptAdapter extends BaseAdapter
{

	private Context context;
	private List<String> departments;//存放部门名称的List
	private LayoutInflater mInflater;
	public AdminDeptAdapter(Context context,List departments)
	{
		this.context=context;
		this.departments=departments;
		mInflater=LayoutInflater.from(context);
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
			convertView=mInflater.inflate(R.layout.fragment_admin_deparment_normal_listitem, parent,false);
			viewHolder=new ViewHolder();
			viewHolder.textViewName=(TextView)convertView.findViewById(R.id.fado_et_departName);
			viewHolder.btnModify=(Button)convertView.findViewById(R.id.fado_btn_modify);
			convertView.setTag(viewHolder);
			viewHolder.btnModify.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//弹出一个修改部门名称的对话框
				showDialog(position,viewHolder);
					
				
			
				
				}
				
			});
		}
		else
		{
			viewHolder=(ViewHolder)convertView.getTag();
		}
		viewHolder.textViewName.setText(departments.get(position));
		return convertView;
	}
	static class ViewHolder
	{
		private TextView textViewName;
		private Button		btnModify;
	}

	public void showDialog(final int position,ViewHolder viewHolder)
	{
		MyDialog modifyDialog=new MyDialog(context);
		modifyDialog.setTitle("修改部门的名称");
		View dialog=mInflater.inflate(R.layout.dialog_modify_depart_name, null);
	final EditText editTextDeptName=(EditText)dialog.findViewById(R.id.fadol_et_departName);
	editTextDeptName.setText(viewHolder.textViewName.getText());
		modifyDialog.setView(dialog);
		AlertDialog.Builder builder=modifyDialog.getBuilder();
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface arg0, int which) {
				// TODO Auto-generated method stub
				String name=editTextDeptName.getText().toString();
				departments.remove(position);
				departments.add(position, name);
				notifyDataSetChanged();
			}
			
		});
		builder.setNegativeButton("取消", null);
		modifyDialog.setBuilder(builder);
		modifyDialog.show();
	}
			
		
	
	
	
}
