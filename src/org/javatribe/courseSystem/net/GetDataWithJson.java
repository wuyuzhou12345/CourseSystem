package org.javatribe.courseSystem.net;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatribe.courseSystem.model.Course;
import org.javatribe.courseSystem.util.HttpUtil;
import org.javatribe.courseSystem.util.JsonUtil;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**传递Json对象并与服务器交互的工具类
 * @author qing
 *2014年12月10日
 */
public class GetDataWithJson {

		/**
		 * 通过传递对象和List参数来与服务器交互
		 * @param url
		 * @param params
		 * @param listToSend
		 * @return
		 */
			public static String getDataWithJsonViaObjectAndStringList(String url,Map<String,Object> params,List<String> listToSend,String listName) 
			{
				Map<String,String> data=new HashMap<String,String>();
				JSONObject jsonObject=new JSONObject();
				if(params!=null)
				{
					for(String key:params.keySet())
					{
						Class c=params.get(key).getClass();
				
							String json=new Gson().toJson(params.get(key),c);
							jsonObject=JsonUtil.setStringToJson(jsonObject, key, json);
						System.out.println(json);
						
					}
				}
				if(listToSend!=null)
				{
				Type type=new TypeToken<List<String>>(){}.getType();
				String list=new Gson().toJson(listToSend,type);
				jsonObject=JsonUtil.setStringToJson(jsonObject, listName, list);
				}
				data.put("json",jsonObject.toString());
				String result;
				try {
					result = HttpUtil.postRequest(url, data);
					return result;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "error";
				}
				
			}
		/**
		 * 通过传送对象和基本数据类型与服务器端交互
		 * @param url
		 * @param objects
		 * @param simpleData
		 * @return 返回json的字符串
		 */
			public  static String getDataWithJsonViaObjectAndSimpleData(String url,Map<String,Object> objects,Map<String,Object> simpleData)
			{
				Map<String,String> data=new HashMap<String,String>();
				JSONObject jsonObject=new JSONObject();
				for(String key:objects.keySet())
				{
					Class c=objects.get(key).getClass();
			
						String json=new Gson().toJson(objects.get(key),c);
						jsonObject=JsonUtil.setStringToJson(jsonObject, key, json);
					System.out.println(json);
					
				}
				for(String key:simpleData.keySet())
				{
					if(simpleData.get(key) instanceof String)//如果数据类型是String
					{
						jsonObject=JsonUtil.setStringToJson(jsonObject, key, (String)simpleData.get(key));
					}
					if(simpleData.get(key) instanceof Integer)//如果数据类型是Integer
					{
						jsonObject=JsonUtil.setIntegerToJson(jsonObject, key, (Integer)simpleData.get(key));
					}
					if(simpleData.get(key) instanceof Boolean)//如果数据类型是Boolean
					{
						jsonObject=JsonUtil.setBooleanToJson(jsonObject, key, (Boolean)simpleData.get(key));
					}
				}
				data.put("json", jsonObject.toString());
				String result;
				try {
					result = HttpUtil.postRequest(url, data);
					return result;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "error";
				}
				
			}
			/**
			 * 通过传递对象和List与服务器端交互
			 * @param url
			 * @param params
			 * @param listToSend
			 * @param listName
			 * @return
			 */
			public static String getDataWithJsonViaObjectAndIntegerList(String url,Map<String,Object> params,List<Integer> listToSend,String listName) 
			{
				Map<String,String> data=new HashMap<String,String>();
				JSONObject jsonObject=new JSONObject();
				for(String key:params.keySet())
				{
					Class c=params.get(key).getClass();
			
						String json=new Gson().toJson(params.get(key),c);
						jsonObject=JsonUtil.setStringToJson(jsonObject, key, json);
					System.out.println(json);
					
				}
				if(listToSend!=null)
				{
				Type type=new TypeToken<List<Integer>>(){}.getType();
				String list=new Gson().toJson(listToSend,type);
				jsonObject=JsonUtil.setStringToJson(jsonObject, listName, list);
				}
				System.out.println(jsonObject.toString());
				data.put("json",jsonObject.toString());
				String result;
				try {
					result = HttpUtil.postRequest(url, data);
					return result;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "error";
				}
				
			}
			/**
			 * 通过传递list和基本数据类型与服务器端交互
			 * @param url
			 * @param listToSend 传送的list
			 * @param listName list的名字
			 * @param simpleData String,Integer或Boolean参数
			 * @return json字符串
			 */
			public static  String getDataWithJsonViaListAndSimpleData(String url,List<String> listToSend,String listName,Map<String,Object> simpleData)
			{
				Map<String,String> data=new HashMap<String,String>();
				JSONObject jsonObject=new JSONObject();
				for(String key:simpleData.keySet())
				{
					if(simpleData.get(key) instanceof String)//如果数据类型是String
					{
						jsonObject=JsonUtil.setStringToJson(jsonObject, key, (String)simpleData.get(key));
					}
					if(simpleData.get(key) instanceof Integer)//如果数据类型是Integer
					{
						jsonObject=JsonUtil.setIntegerToJson(jsonObject, key, (Integer)simpleData.get(key));
					}
					if(simpleData.get(key) instanceof Boolean)//如果数据类型是Boolean
					{
						jsonObject=JsonUtil.setBooleanToJson(jsonObject, key, (Boolean)simpleData.get(key));
					}
				}
				Type type=new TypeToken<List<String>>(){}.getType();
				String list=new Gson().toJson(listToSend,type);
				jsonObject=JsonUtil.setStringToJson(jsonObject, listName, list);
				data.put("json",jsonObject.toString());
				String result;
				try {
					result = HttpUtil.postRequest(url, data);
					return result;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "error";
				}
			}
			/**
			 * 通过传递参数与服务器端进行交互
			 * @param url
			 * @param simpleData
			 * @return json字符串
			 */
			public static String getDataWithJsonViaSimpleData(String url,Map<String,String> simpleData)
			{
				
				String result;
				try {
					result = HttpUtil.postRequest(url, simpleData);
					System.out.println("dddddd");
					System.out.println(result);
					return result;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "error";
				}
			}
			/**
			 * 通过传递参数与服务器端进行交互
			 * @param url
			 * @param simpleData
			 * @return json字符串
			 */
			public static String getDataWithJsonViaCourseList(String url,List<Course> courses,String listName)
			{
				Map<String,String> data=new HashMap<String,String>();
				JSONObject jsonObject=new JSONObject();
				if(courses!=null)
				{
				Type type=new TypeToken<List<Course>>(){}.getType();
				String list=new Gson().toJson(courses,type);
				jsonObject=JsonUtil.setStringToJson(jsonObject, "noClassInfo", list);
				}
				data.put("json",jsonObject.toString());
				String result;
				try {
					result = HttpUtil.postRequest(url, data);
					System.out.println("dddddd");
					System.out.println(result);
					return result;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "error";
				}
				
			}
	
			
}
