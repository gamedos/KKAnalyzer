/**
 * @author Yang.Park
 * @Time    2017.12.10
 * JDBC 最笨的使用方案
 */
package com.yang.park.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MysqlUtils {

	private static String Driver;
	private static String URL;// = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8"
	private static String USER;// = "root"
	private static String PASSWORD;// = ""

	private static Connection conn = null;

//	static {
//		try {
//			// 1.加载驱动程序
//			Class.forName("com.mysql.jdbc.Driver");
//			// 2.获得数据库的连接
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public static void initJDBC( String _driver,String _url,String _user,String _pwd ){
		Driver = _driver;
		URL = _url;
		USER = _user;
		PASSWORD = _pwd;
	}
	
	public static Connection getConnection() {
		if(conn==null){
			try {
				// 1.加载驱动程序
				Class.forName(Driver);
				// 2.获得数据库的连接
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void close(){
		try {
			MysqlUtils.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 返回多行数据
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("finally")
	public static List<Map<String,Object>> getAll(String sql){
		List<Map<String,Object>> retData = new ArrayList<Map<String,Object>>();
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			ResultSet rs =  preparedStatement.executeQuery();
			if(rs.next()){
				ResultSetMetaData   rsmd = rs.getMetaData();
				int fieldsCount = rsmd.getColumnCount();
				List<String> fields = new ArrayList<String>();
				for(int column=1;column<=fieldsCount;column++){
					fields.add(rsmd.getColumnName(column));
				}
				rs.beforeFirst();
				int fieldIndex = 0;
				while(rs.next()){
					Map<String,Object> item = new HashMap<String,Object>();
					for(fieldIndex=0;fieldIndex<fieldsCount;fieldIndex++){
						String fieldName = fields.get(fieldIndex);
						item.put(fieldName, rs.getObject(fieldName));
					}
					retData.add(item);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return retData;
		}
	}
	/**
	 * 返回总数， 分页的是会用到
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("finally")
	public static Integer getTotalCount(String sql){
		Integer retData = 0;
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			ResultSet rs =preparedStatement.executeQuery();
			if(rs.next()){
				retData = rs.getInt("rowcount");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return retData;
		}
	}
	/**
	 * 获得一个整型类型字段一个值
	 * @param tableName
	 * @param field
	 * @param where
	 * @return
	 */
	@SuppressWarnings("finally")
	public static Integer oneIntegerValue(String tableName , String field, String where){
		String sql = "SELECT " +field+ " FROM "+tableName+" WHERE " + where + " LIMIT 0,1";
		Integer retData = null;
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			ResultSet rs =preparedStatement.executeQuery();
			if(rs.next()){
				retData = rs.getInt(field);
			}
			rs.close();
		} catch (SQLException e) {
//			e.printStackTrace();
		}finally {
			return retData;
		}
	}

	/**
	 * 获得一个字符串类型字段一个值
	 * @param tableName
	 * @param field
	 * @param where
	 * @return
	 */
	@SuppressWarnings("finally")
	public static String oneStringValue(String tableName , String field, String where){
		String sql = "SELECT " +field+ " FROM "+tableName+" WHERE " + where + " LIMIT 0,1";
		String retData = null;
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			ResultSet rs =preparedStatement.executeQuery();
			if(rs.next()){
				retData = rs.getString(field);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return retData;
		}
	}
	
	/**
	 * 获得一条记录多个字段。
	 * @param tableName
	 * @param fields
	 * @param where
	 * MysqlUtils.someFieldsValues("tbTopics", new String[]{"id","gid","title"}, " id = 7");
	 */
	public static Map<String,Object> someFieldsValues(String tableName, String fieldsStr, String where){
		String[] fields = fieldsStr.split(",");
		for(int i=0,l=fields.length;i<l;i++){
			fields[i] = fields[i].trim();
		}
		return MysqlUtils.someFieldsValues(tableName, fields, where);
	}
	@SuppressWarnings("finally")
	public static Map<String,Object> someFieldsValues(String tableName, String[] fields, String where){
		String sql = "SELECT " + JStringUtils.join(fields,",") + " FROM "+tableName+" WHERE " + where;
		Map<String,Object> retData = null;
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			ResultSet rs =preparedStatement.executeQuery();
			if(rs.next()){
				retData = new HashMap<String,Object>();
				for(int i=0,l=fields.length;i<l;i++){
					retData.put(fields[i], rs.getObject(fields[i]));
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			return retData;
		}
	}
	/**
	 * 插入一条数据
	 * @param tableName
	 * @param datas
	 * @throws SQLException 
	 */
	public static boolean insert(String tableName, Map<String,Object> data) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<String> fields = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		String key = "";
		Iterator<String> iterator = data.keySet().iterator();
		while(iterator.hasNext()){
			key = iterator.next();
			fields.add(key);
			Object value = data.get(key);
			if(value instanceof String){
				values.add("'"+value+"'");
			}else{
				values.add(""+value);
			}
		}
		//
		String sql = "INSERT INTO "+tableName+" ("+JStringUtils.join(fields, ",")+") VALUES("+JStringUtils.join(values, ",")+") ";
		//
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			return preparedStatement.execute();
		} catch (SQLException e) {
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 插入多条数据
	 * @param tableName
	 * @param datas
	 */
	public static boolean insert(String tableName, List<Map<String,Object>> datas){
		boolean isInitedFields = false;
		List<String> fields = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		List<String> rows = new ArrayList<String>();
		String key = "";
		
		for(Map<String,Object> data:datas){
			Iterator<String> iterator = data.keySet().iterator();
			while(iterator.hasNext()){
				key = iterator.next();
				if(isInitedFields==false){
					fields.add(key);
				}
				Object value = data.get(key);
				if(value instanceof String){
					values.add("'"+value+"'");
				}else{
					values.add(""+value);
				}
			}
			rows.add("("+JStringUtils.join(values, ",")+") ");
			values.clear();
			//
			isInitedFields = true;
		}
		
		//
		String sql = "INSERT INTO "+tableName+" ("+JStringUtils.join(fields, ",")+") VALUES " + JStringUtils.join(rows, ",");
		System.out.println(sql);
		//
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			return preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 更新数据
	 * @param tableName
	 * @param data
	 * @param where
	 */
	public static boolean update(String tableName, Map<String,Object> data, String where){
		//
		List<String> fieldValue = new ArrayList<String>();
		Iterator<String> iterator = data.keySet().iterator();
		String key = "";
		while(iterator.hasNext()){
			key = iterator.next();
			Object value = data.get(key);
			if(value instanceof String){
//				if( ((String) value).matches("[+-]+") ){
				if(  ((String) value).indexOf("+")>0 ||  ((String) value).indexOf("-")>0 ){
				}else{
					value = "'" + value + "'";
				}
			}
			fieldValue.add(key + " = " + value + "");
		}
		//
		String sql = "UPDATE "+tableName+" SET " + JStringUtils.join(fieldValue, ",") ;
		if(where==null || "".equals(where)){
			where = " 1=1 ";
		}
		sql = sql  + " WHERE " + where;
		//
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			return preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void update(String tableName, Map<String,Object> data){
		MysqlUtils.update(tableName, data, "");
	}
	
	/**
	 * 删除数据
	 * @param tableName
	 * @param where
	 */
	public static boolean delete(String tableName, String where){
		//
		String sql = "DELETE FROM "+tableName;
		if(where==null || "".equals(where)){
			where = " 1=1 ";
		}
		sql = sql  + " WHERE " + where;
		//
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			return preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//
	public static void truncate(String tableName){
		//
		String sql = "TRUNCATE "+tableName;
		//
		try {
			PreparedStatement preparedStatement = MysqlUtils.getConnection().prepareStatement(sql);
			preparedStatement.execute();
		} catch (SQLException e) {
		}
	}
	
}
