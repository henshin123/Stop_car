package com.stopcar.system.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


import sun.font.BidiUtils;


public class DBUtils 
{
	//1������������---��������jar���У��������·��
	private static String driver = null;
	//2���������Ӵ�---���ݿ��λ�ü�����
	private static String url = null;
	private static String userName = null;
	private static String password = null;
	
	//Ϊ��ǰ�̴߳����̸߳�������
	private final static ThreadLocal<Connection> threadlocal = new ThreadLocal<>();
	
	/**
	 * ��̬��
	 * ���౻��һ�μ������ڴ�ʱ��ִ�У��Ժ���ִ��
	 */
	static
	{
		try
		{
			//��ȡ��Դ�ļ�������ʵ��
			ResourceBundle  bundle=ResourceBundle.getBundle("DBOPtions");
			//����Դ�ļ��л�ȡ����
			driver = bundle.getString("DRIVER");
			url = bundle.getString("URL");
			userName = bundle.getString("USERNAME");
			password = bundle.getString("PASSWORD");
			
			//3����������
			Class.forName(driver);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * ��һ���������еĳ�Ա(���Ժͷ���)����static����ô��ʱ������Ӧ����˽�е�
	 */
	private DBUtils() {};
	
	public static Connection getConnection()
		throws Exception
	{
		//1.����ǰ�̵߳��̸߳����л�ȡ���ݿ�����
		Connection conn  =threadlocal.get();
		//2.�ж������Ƿ����
		if(conn==null||conn.isClosed())
		{
			//3���������ݿ�����
		     conn = DriverManager.getConnection(url,userName,password);
		     
		     //4.�����뵱ǰ���̰߳󶨣����൱�ڽ�Ǯ������п�
		     threadlocal.set(conn);
		}
		return conn;
	}
	public static void close()
	{
		try
		{
			//��ȡ��ǰ�̰߳󶨵����Ӷ���
			Connection conn  = threadlocal.get();
			//�ж������Ƿ����
			if(conn != null || !conn.isClosed())
			{
				//����뵱ǰ�̵߳İ�
				threadlocal.remove();
				//�ر�����
				conn.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static void close(ResultSet rs)
	{
		try
		{
			if(rs!=null)
			{
				rs.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	
	public static void close(PreparedStatement pstm)
	{
		try
		{
			if(pstm!=null)
			{
				pstm.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void close(Connection conn)
	{
		try
		{
			if(conn!=null && !conn.isClosed())
			{
				conn.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	/*****************************************************
	 *     ����SQL���뷽��
	 *****************************************************/
	/**
	 * ����SQL���
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static PreparedStatement preparedStatement(String sql)throws Exception
	{
		return DBUtils.getConnection().prepareStatement(sql);
	}
	/*****************************************************
	 *     ����Ϊ������ط���
	 *****************************************************/
	/**
	 * ��������
	 * @throws Exception
	 */
	public static void beginTransaction()throws Exception
	{
		DBUtils.getConnection().setAutoCommit(false);
	}
	/**
	 * �ύ����
	 * @throws Exception
	 */
	public static void commit()throws Exception
	{
		DBUtils.getConnection().commit();
	}
	/**
	 * ����ع�
	 */
	public static void rollback()
	{
		try
		{
			DBUtils.getConnection().rollback();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * ��������
	 */
	public static void endTransaction()
	{
		try
		{
			DBUtils.getConnection().setAutoCommit(true);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
