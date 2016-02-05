package org.javatribe.courseSystem.db;

import org.javatribe.courseSystem.db.model.AdminAndOrganization;
import org.javatribe.courseSystem.db.model.AdminLeaveApplication;
import org.javatribe.courseSystem.db.model.Notice;
import org.javatribe.courseSystem.db.model.Student;
import org.javatribe.courseSystem.db.model.StudentAndOrganization;
import org.javatribe.courseSystem.db.model.StudentCourse;
import org.javatribe.courseSystem.db.model.StudentLeaveApplication;
import org.javatribe.courseSystem.db.model.StudentMyAssign;

import android.content.Context;

import com.ab.db.orm.AbSDDBHelper;


public class DBSDHelper extends AbSDDBHelper {
	// 数据库名
	private static final String DBNAME = "course_system.db";
	// 数据库 存放路径
    private static final String DBPATH = "CSDB";
    
    // 当前数据库的版本
	private static final int DBVERSION = 1;
	// 要初始化的表
	private static final Class<?>[] clazz = {Student.class,AdminAndOrganization.class,AdminLeaveApplication.class,Notice.class,StudentAndOrganization.class,StudentCourse.class,StudentLeaveApplication.class,StudentMyAssign.class};

	public DBSDHelper(Context context) {
		super(context,DBPATH, DBNAME, null, DBVERSION, clazz);
	}

}



