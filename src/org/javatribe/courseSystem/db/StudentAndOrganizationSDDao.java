/**
 * 
 */
package org.javatribe.courseSystem.db;

import org.javatribe.courseSystem.db.model.StudentAndOrganization;


import android.content.Context;

import com.ab.db.orm.dao.AbDBDaoImpl;

/**
 * @author zhou
 * @version 创建时间：2014年11月29日 上午11:08:59
 * StudentAndOrganizationSDDao
 */

public class StudentAndOrganizationSDDao extends AbDBDaoImpl<StudentAndOrganization> {
	public StudentAndOrganizationSDDao(Context context) {
		super(new DBSDHelper(context),StudentAndOrganization.class);
	}
}
