package org.javatribe.courseSystem.Activity.Util;

import com.ab.util.AbStrUtil;

import android.widget.EditText;

/**进行数据验证
 * @author qing
 *
 */
public class Validator {
	public static boolean isEmpty(EditText text)
	{
		String str=text.getText().toString();
		if(AbStrUtil.isEmpty(str))
		{
			return true;
		}
		return false;
	}
}
