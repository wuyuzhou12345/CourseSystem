package org.javatribe.courseSystem.Activity.organization;




import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.javatribe.courseSystem.R;
import org.javatribe.courseSystem.global.Constant;
import org.javatribe.courseSystem.model.LeaveApplication;
import org.javatribe.courseSystem.model.Organization;
import org.javatribe.courseSystem.model.Student;
import org.javatribe.courseSystem.net.GetDataWithJson;
import org.javatribe.courseSystem.util.EncodeBase64File;
import org.javatribe.courseSystem.util.JsonUtil;

import com.ab.util.AbFileUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.AbViewUtil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**组织注册界面
 * @author qing
 *
 */
public class RegisterOrgFragment extends Fragment {

	AutoCompleteTextView fro_tv_orgName;
	EditText fro_et_mainAdminNo;
	EditText fro_et_orgInfo;
	Spinner fro_sp_orgKind;
	Button fro_btn_kok;
	String[] autoCompleteContent={"红十字会","青年志愿者协会","学生会","团总支","党支部"};//从服务器端获取
	ArrayAdapter<String> adapter;
	ImageView fro_img_addImg;
	View avatarView;
	AlertDialog.Builder dialogBuilder;
	private Handler registerHandler;
	private Organization org;
	private final String PREFS_NAME="org.javatribe.courseSystem";
	String[] spinnerContent={
		"校级","系级"
	};
	/* 用来标识请求照相功能的activity */
	private static final int CAMERA_WITH_DATA = 3023;
	/* 用来标识请求gallery的activity */
	private static final int PHOTO_PICKED_WITH_DATA = 3021;
	/* 用来标识请求裁剪图片后的activity */
	private static final int CAMERA_CROP_DATA = 3022;
	/* 拍照的照片存储位置 */
	private  File PHOTO_DIR = null;
	// 照相机拍照得到的图片
	private File mCurrentPhotoFile;
	private String mFileName;
	private AlertDialog dialog;
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root=inflater.inflate(R.layout.fragment_register_org, null);
		fro_tv_orgName=(AutoCompleteTextView)root.findViewById(R.id.fro_tv_orgName);
		fro_et_mainAdminNo=(EditText)root.findViewById(R.id.fro_et_mainAdminNo);
	 fro_sp_orgKind=(Spinner)root.findViewById(R.id. fro_sp_orgKind);
	 fro_btn_kok=(Button)root.findViewById(R.id.fro_btn_ok);
	 fro_et_orgInfo=(EditText)root.findViewById(R.id.fro_et_orgInfo);
	 registerHandler=new Handler(){
			public void handleMessage(Message msg)
			{
				//�����Ϣ�Ǳ����͵�
				
				if(msg.what==1)
				{
					Toast.makeText(getActivity(),"success",Toast.LENGTH_SHORT).show();
				}
				else if(msg.what==0)
				{
					Toast.makeText(getActivity(),"already exists!",Toast.LENGTH_SHORT).show();
				}
				else if(msg.what==-1)
				{
					Toast.makeText(getActivity(),"fail!",Toast.LENGTH_SHORT).show();
				}
				
			}
		
		};
	 initOkBtn();
	 ArrayAdapter<String> sAdapter=new ArrayAdapter<String>(getActivity(),R.layout.spinner,R.id.sp_tv,spinnerContent);
	fro_sp_orgKind.setAdapter(sAdapter);	
	 fro_img_addImg=(ImageView)root.findViewById(R.id.fro_img_addImg);
		fro_img_addImg.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				avatarView=inflater.inflate(R.layout.choose_avatar, null);
				//getActivity().showDialog(1,avatarView);//显示对话框
				dialogBuilder=new AlertDialog.Builder(getActivity());
				dialogBuilder.setView(avatarView);
				
				dialog=dialogBuilder.create();
				dialog.show();
				setDialog();
				
			}
			
		});
		setAutoCompleteTextView();
		//初始化图片保存路径
	    String photo_dir = AbFileUtil.getDefaultImageDownPathDir();
	    if(AbStrUtil.isEmpty(photo_dir)){
	    	Toast.makeText(getActivity(),"存储卡不存在！",Toast.LENGTH_SHORT).show();
	    }else{
	    	PHOTO_DIR = new File(photo_dir);
	    }
		return root;
	}
	private void setAutoCompleteTextView()
	{
		adapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,autoCompleteContent);
		 fro_tv_orgName.setAdapter(adapter);
	
			
							
			
	}
	private void setDialog()
	{
		Button albumButton = (Button)avatarView.findViewById(R.id.choose_album);
		Button camButton = (Button)avatarView.findViewById(R.id.choose_cam);
		Button cancelButton = (Button)avatarView.findViewById(R.id.choose_cancel);
		
		albumButton.setOnClickListener(new OnClickListener(){//如果点击的是本地相册

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				//dialogBuilder.dismiss();
				// 从相册中去获取
				try {
					Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
					intent.setType("image/*");//设置type
					startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);//返回的数据保存在intent中
				} catch (ActivityNotFoundException e) {
					Toast.makeText(getActivity(),"没有找到照片",Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
		camButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				doPickPhotoAction();
			}
			
		});
		
		cancelButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
			
		});
		
		
	
		
	}
	
@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)  {
		// TODO Auto-generated method stub
	if (resultCode != getActivity().RESULT_OK){
		return;
	}
	switch (requestCode) {
		case PHOTO_PICKED_WITH_DATA:
			Uri uri =data.getData();//获取图片的uri
			String currentFilePath =getPath(uri);
			if(!AbStrUtil.isEmpty(currentFilePath)){//如果路径存在
				Intent intent1 = new Intent(getActivity(), CropImageActivity.class);
				intent1.putExtra("PATH", currentFilePath);
				startActivityForResult(intent1, CAMERA_CROP_DATA);
	        }else{
	        	Toast.makeText(getActivity(),"未在存储卡找到文件",Toast.LENGTH_SHORT).show();
	        }
			break;
		case CAMERA_WITH_DATA:
			//if(D)Log.d(TAG, "将要进行裁剪的图片的路径是 = " + mCurrentPhotoFile.getPath());
			String currentFilePath2 = mCurrentPhotoFile.getPath();
			Intent intent2 = new Intent(getActivity(), CropImageActivity.class);
			intent2.putExtra("PATH",currentFilePath2);
			startActivityForResult(intent2,CAMERA_CROP_DATA);
			break;
		case CAMERA_CROP_DATA:
			String path = data.getStringExtra("PATH");
	    	//if(D)Log.d(TAG, "裁剪后得到的图片的路径是 = " + path);
	    	//mImagePathAdapter.addItem(mImagePathAdapter.getCount()-1,path);
	     	//camIndex++;
	    	//AbViewUtil.setAbsListViewHeight(mGridView,3,25);
		try {
			String str=	(String)EncodeBase64File.encodeBase64File(path);
			Log.d("RegisterOrgFragment",str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Bitmap  bm=BitmapFactory.decodeFile(path);//通过解析路径取得一个Bitmap对象
			fro_img_addImg.setImageBitmap(bm);//设置这个imageView。
			fro_img_addImg.setAdjustViewBounds(true);//根据设置的图片调整边距。
			
			break;
	}
	}
/**
 * 拍照获取图片
 */
protected void doTakePhoto() {
	try {
		mFileName = System.currentTimeMillis() + ".jpg";
		mCurrentPhotoFile = new File(PHOTO_DIR, mFileName);
		//ACTION_IMAGE_CAPTURE是一个能使系统自带的Camera拍摄一张照片并返回。
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
		//EXTRA_OUTPUT是一个Extra type类型，表明这个Uri是用来存储image数据的。
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
		startActivityForResult(intent, CAMERA_WITH_DATA);
	} catch (Exception e) {
		Toast.makeText(getActivity(),"没有找到存储卡！",Toast.LENGTH_SHORT).show();
	}
}
/**
 * 描述：从照相机获取
 */
private void doPickPhotoAction() {
	String status = Environment.getExternalStorageState();
	//判断是否有SD卡,如果有sd卡存入sd卡在说，没有sd卡直接转换为图片
	if (status.equals(Environment.MEDIA_MOUNTED)) {
		doTakePhoto();
	} else {
		Toast.makeText(getActivity(),"没有找到存储卡！",Toast.LENGTH_SHORT).show();
	}
}
/**
 * 从相册得到的url转换为SD卡中图片路径
 */
@SuppressLint("NewApi")
public String getPath(Uri uri) {
	if(AbStrUtil.isEmpty(uri.getAuthority())){//判断这个uri的authority部分是否为空值
		return null;
	}
	String[] projection = { MediaStore.Images.Media.DATA };//DATA是MediaColumn里面的一个列.
	//Cursor cursor =getActivity().managedQuery(uri, projection, null, null, null);已过时
	//projection表示要取得的列。
	CursorLoader loader=new CursorLoader(getActivity(),uri,projection,null,null,null);//从ContentResolver中获取Cursor对象
	Cursor cursor=loader.loadInBackground();
	int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);//通过列名获取column的index
	cursor.moveToFirst();//cursor的下标移到第一行
	String path = cursor.getString(column_index);//获取该列的转化成String的内容
	return path;
}
private void initOkBtn()
{
	fro_btn_kok.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 String orgName=fro_tv_orgName.getText().toString();
			int  level=  fro_sp_orgKind.getSelectedItemPosition();
			SharedPreferences setting=getActivity().getSharedPreferences(PREFS_NAME, 0);  //SharedPreferences读取数据
			String sno=setting.getString("stuNo", "121542104");
			String dept=setting.getString("dept", "cs"); 
			Map<String,String> data=new HashMap<String,String>();
			data.put("orgName", orgName);
			data.put("level", Integer.toString(level));
			data.put("dept", dept);
			//先请求数据看该组织是否已注册
			String json = GetDataWithJson.getDataWithJsonViaSimpleData(Constant.BASE_URL+"/organization/isOrganizationRegistered",data);
			Boolean hasRegister=false;
			Message msg=registerHandler.obtainMessage();
			if(json != null && !json.equals(Constant.GET_DATA_ERROR))
			 {
				 hasRegister=Boolean.parseBoolean(json);
				 Log.i("hasRegister",json);
				 if(hasRegister)//若已经注册
				 {
					 msg.what=0;//传送信息
				 }
				 else//若没有注册
				 {
					 org=new Organization();
					 org.setOrgLevel(level);
					 org.setOrgName(orgName);
					 org.setOrgIntroduction(fro_et_orgInfo.getText().toString());
					 org.setStarter(new Student(sno));
					 Map<String,Object> map=new HashMap<String,Object>();
					 map.put("organization", org);
					 json=GetDataWithJson.getDataWithJsonViaObjectAndIntegerList(Constant.BASE_URL+"/"+Constant.NAMESPACE_ORGANIZATION+"/"+Constant.ORGANIZATION_ADD_ORG_ACTION,map,null,null);
						if(json != null && !json.equals(Constant.GET_DATA_ERROR))
						{
							if(Boolean.parseBoolean(json))
							{
								msg.what=1;
							}
							else
							{
								msg.what=-1;
							}
						}
				 }
			 }
			registerHandler.sendMessage(msg);
		}
		
	});

}
}
