package org.javatribe.courseSystem.Activity.admin.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.adapter.AdminDeptAdapter;
import org.javatribe.courseSystem.adapter.AdminDeptMultibleChoiceAdapter;
import org.javatribe.courseSystem.adapter.AdminMemberExpandableListAdapter;
import org.javatribe.courseSystem.ui.MyDialog;
import org.javatribe.courseSystem.ui.SlideCutListView;
import org.javatribe.courseSystem.ui.SlideCutListView.RemoveDirection;
import org.javatribe.courseSystem.ui.SlideCutListView.RemoveListener;















import org.javatribe.courseSystem.vo.DepartmentItem;
import org.javatribe.courseSystem.vo.PersonItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
/**
 * 管理员
 * 部门设置
 *@author qing
 *2014/10/18
 */public class AdminSessionOptionFragment extends Fragment implements OnClickListener,OnItemClickListener{
	 private ListView listViewDeparts;
	 private Button btnDelete;
	 private Button btnAdd;
	 private ViewStub mViewStub;//一开始隐藏的按钮
	 private LinearLayout layoutOptions;//一开始出现的按钮
	private AdminDeptAdapter adapter;//点击删除前的adapter
	private List<String> departments;//显示所有的部门名称
	private LayoutInflater mInflater;
	private AdminDeptMultibleChoiceAdapter afterDeleteAdapter;//点击删除以后的adapter
	private Button btnSelectAll;
	private Button btnRemove;
	private Button btnCancel;
	private Map<Integer,Boolean> checkboxState;//存储多选框的选择状态
	private List<Integer> checkedNum;//被选中的多选框的下标集合
	private boolean stubHasInflated;//判断viewstub是否被填充过了
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		View rootView=inflater.inflate(R.layout.fragment_admin_department_option,container,false);
		listViewDeparts=(ListView)rootView.findViewById(R.id.fado_lv_departs);
		btnDelete=(Button)rootView.findViewById(R.id.fado_btn_delete);
		btnAdd=(Button)rootView.findViewById(R.id.fado_btn_add);
		layoutOptions=(LinearLayout)rootView.findViewById(R.id.fado_ll_options);
		mViewStub=(ViewStub)rootView.findViewById(R.id.fado_stub_afterdelete);
	
		listViewDeparts.setEmptyView(rootView.findViewById(R.id.fado_tv_empty));//设置list为空时的界面
		btnDelete.setOnClickListener(this);
		btnAdd.setOnClickListener(this);
		initData();
		adapter=new AdminDeptAdapter(getActivity(),departments);
		listViewDeparts.setAdapter(adapter);
		return rootView;
		
		
	}
	private void initData() {
		// TODO Auto-generated method stub
		departments=new ArrayList<String>();
		departments.add("秘书部");
		departments.add("宣传部");
		departments.add("外联部");
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.fado_btn_delete://当按下删除部门时
			deleteDepartments();
			break;
		case R.id.fado_btn_add://按下添加部门时
		{
			showAddDepartDialog();
			break;
		}
		case R.id.fado_btn_selectAll://按下删除部门后，按下全选时
		{
			checkboxState=afterDeleteAdapter.getCheckboxState();
			for(int i=0;i<checkboxState.size();i++)//将所有的多选框状态设为true
			{
				checkboxState.put(i, true);
			}
			afterDeleteAdapter.setCheckboxState(checkboxState);
			afterDeleteAdapter.notifyDataSetChanged();
			listViewDeparts.setAdapter(afterDeleteAdapter);
			break;
		}
		case R.id.fado_btn_cancel:
		{
			checkboxState=afterDeleteAdapter.getCheckboxState();
			for(int i=0;i<checkboxState.size();i++)
			{
				checkboxState.put(i, false);//将所有的状态设为不选中
			}
			afterDeleteAdapter.setCheckboxState(checkboxState);
			afterDeleteAdapter.notifyDataSetChanged();
			listViewDeparts.setAdapter(afterDeleteAdapter);
			mViewStub.setVisibility(View.GONE);//隐藏三个按钮
			layoutOptions.setVisibility(View.VISIBLE);//显示原来的按钮
			listViewDeparts.setAdapter(adapter);//设置为原来的adapter
			break;
		}
		case R.id.fado_btn_remove:
		{
			checkedNum=new ArrayList<Integer>();
			checkboxState=afterDeleteAdapter.getCheckboxState();
			for(int i=checkboxState.size()-1;i>=0;i--)
			{
				if(checkboxState.get(i)==true)
				{
					checkedNum.add(i);
			
				}
			}
			for(int i=0;i<checkedNum.size();i++)
			{
				Toast.makeText(getActivity(), checkedNum.get(i).intValue()+"", Toast.LENGTH_SHORT).show();
				departments.remove(checkedNum.get(i).intValue());//删除掉被选中的部门，注意要取得int类型的数据，否则无法删除
		
			}
			if(departments.isEmpty())//如果全部都删除了，就返回原来的adapter
			{
				mViewStub.setVisibility(View.GONE);//隐藏三个按钮
				layoutOptions.setVisibility(View.VISIBLE);//显示原来的按钮
				listViewDeparts.setAdapter(adapter);//设置为原来的adapter
			}
				//Toast.makeText(getActivity(), "departments.size()="+departments.size()+"", Toast.LENGTH_SHORT).show();
			else
			{
			afterDeleteAdapter.setCheckboxState(checkboxState);
			afterDeleteAdapter.notifyDataSetChanged();
			//afterDeleteAdapter=new AdminDeptMultibleChoiceAdapter(getActivity(),departments);
			listViewDeparts.setAdapter(afterDeleteAdapter);
			}
			break;
		}
		
	}


	}
	public void showAddDepartDialog()//弹出对话框
	{
		MyDialog addDialog=new MyDialog(getActivity());
		addDialog.setTitle("添加部门");
		mInflater=LayoutInflater.from(getActivity());
		View dialog=mInflater.inflate(R.layout.dialog_add_depart_name, null);
		final EditText editTextDepartName=(EditText)dialog.findViewById(R.id.fadol_et_addDepart);
		addDialog.setView(dialog);
		AlertDialog.Builder builder=addDialog.getBuilder();
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface arg0, int which) {
				// TODO Auto-generated method stub
				String name=editTextDepartName.getText().toString();
				
				departments.add(name);
				adapter.notifyDataSetChanged();
				listViewDeparts.setAdapter(adapter);
				
			}
			
		});
		builder.setNegativeButton("取消", null);
		addDialog.setBuilder(builder);
	addDialog.show();
	}
	private void deleteDepartments()
	{
		if(!stubHasInflated)//如果没有填充过（ViewStub只能被填充一次，否则抛出异常）
		{
		View stub=mViewStub.inflate();
		btnSelectAll=(Button)stub.findViewById(R.id.fado_btn_selectAll);
		btnRemove=(Button)stub.findViewById(R.id.fado_btn_remove);
		btnCancel=(Button)stub.findViewById(R.id.fado_btn_cancel);
		btnSelectAll.setOnClickListener(this);
		btnRemove.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		stubHasInflated=true;
		}
		else//否则
		{
			mViewStub.setVisibility(View.VISIBLE);//显示viewstub
		}
		listViewDeparts.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//设置list为可多选
		//mViewStub.setVisibility(View.VISIBLE);
		layoutOptions.setVisibility(View.GONE);//隐藏原有的按钮
		afterDeleteAdapter=new AdminDeptMultibleChoiceAdapter(getActivity(),departments);
		listViewDeparts.setAdapter(afterDeleteAdapter);//设置成有多选按钮的adapter
		listViewDeparts.setOnItemClickListener(this);
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		AdminDeptMultibleChoiceAdapter.ViewHolder viewHolder=(AdminDeptMultibleChoiceAdapter.ViewHolder)view.getTag();//获取该view的viewHolder对象
		viewHolder.cbCheck.toggle();//设置checkbox为其原有状态的相反状态
	checkboxState=afterDeleteAdapter.getCheckboxState();//获取checkbox的状态
	checkboxState.put(position,viewHolder.cbCheck.isChecked());//改变其原有状态
	afterDeleteAdapter.setCheckboxState(checkboxState);
	afterDeleteAdapter.notifyDataSetChanged();
	Toast.makeText(getActivity(), position+"被选中了", Toast.LENGTH_SHORT).show();
	}
	
 }




