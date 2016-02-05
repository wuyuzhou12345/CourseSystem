package org.javatribe.courseSystem.util;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class JsonUtil<T> {
		protected Class<T> entity;
	
		public JsonUtil()
		{
			super();
			Class  c=getClass();
			System.out.println(c);
			Type type=c.getGenericSuperclass();
			//System.out.println(type);
			if(type instanceof ParameterizedType)
			{
				Type[] parameterizedType=((ParameterizedType)type).getActualTypeArguments();
				//System.out.println(parameterizedType[0]);
				this.entity=(Class<T>) parameterizedType[0];
				//System.out.println(entity);
			}

//			this.entity=entity;
//			System.out.println(entity);
		
			
		}
		public T getObjectFromJson(String json)
		{
			return new Gson().fromJson(json,entity);
		}

	
		public  String getJsonFromObject(T object)
		{
			return new Gson().toJson(object);
		}
		public List<T> getListFromJson(String json,Class<T> clazz)
		{
			Type type=new TypeToken<List<T>>(){}.getType();
			System.out.println(type);
			return new Gson().fromJson(json, type);
		}
		public String getJsonFromList(List<T> list)
		{
			Type type=new TypeToken<List<T>>(){}.getType();
			System.out.println(type);
			return new Gson().toJson(list,type);
		}
		public static JSONObject setStringToJson(JSONObject jsonObject,String key,String value)
		{
			
			try {
				return jsonObject.accumulate(key, value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		public static String getStringFromJson(String json,String key)
		{
			
			JSONObject jsonObject;
			
			try {
				jsonObject = new JSONObject(json);
				return jsonObject.getString(key);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return null;
		}
		public static JSONObject setIntegerToJson(JSONObject jsonObject,String key,Integer value)
		{
			try {
				return jsonObject.accumulate(key, value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		public static Integer getIntFromJson(String json,String key)
		{
			
			JSONObject jsonObject;
			
			try {
				jsonObject = new JSONObject(json);
				return jsonObject.getInt(key);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return null;
		}
		public  static JSONObject setBooleanToJson(JSONObject jsonObject,String key,Boolean value)
		{
			try {
				return jsonObject.accumulate(key, value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		public static Boolean getBooleanFromJson(String json,String key)
		{
			
			JSONObject jsonObject;
			
			try {
				jsonObject = new JSONObject(json);
				return jsonObject.getBoolean(key);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return null;
		}
		
	}


