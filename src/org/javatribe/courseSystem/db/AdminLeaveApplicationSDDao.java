/**
 * 
 */
package org.javatribe.courseSystem.db;

import org.javatribe.courseSystem.db.model.AdminLeaveApplication;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ab.db.orm.dao.AbDBDaoImpl;

/**
 * @author zhou
 * @version 创建时间：2014年11月29日 上午11:05:13
 * AdminLeaveApplicationSDDao
 */

public class AdminLeaveApplicationSDDao extends AbDBDaoImpl<AdminLeaveApplication>{
	public AdminLeaveApplicationSDDao(Context context) {
		super(new DBSDHelper(context),AdminLeaveApplication.class);
	}
}
