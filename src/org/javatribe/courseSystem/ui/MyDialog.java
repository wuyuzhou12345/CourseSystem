package org.javatribe.courseSystem.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class MyDialog{
	private AlertDialog.Builder builder;
	private AlertDialog dialog;
	private View view;
	private int position;
	

	public MyDialog(Context context)
	{
		builder=new AlertDialog.Builder(context);
	}
	public void setTitle(String title)
	{
		builder=builder.setTitle(title);
	}
	public void setView(View view)
	{
		builder.setView(view);
	}
	public void show()
	{
		 builder.create().show();
	}
	public AlertDialog.Builder getBuilder()
	{
		return builder;
	}
	public void setBuilder(AlertDialog.Builder builder)
	{
		this.builder=builder;
	}
	public void setPositionButton(String text,DialogInterface.OnClickListener listener)
	{
		builder.setPositiveButton(text, listener);
	}
	
	
}
