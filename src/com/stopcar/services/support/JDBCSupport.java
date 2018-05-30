package com.stopcar.services.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stopcar.system.db.DBUtils;


public class JDBCSupport implements BaseServices {

	/************************************************************************
	 *   DTO��ط���
	 ************************************************************************/
    //���������ô��ݺ�ֵ���ݣ��������ݾ���˵�����ж��ٵĶ���
	//���е�ֵ�������ڴ���ֻ��һ�������ٶ��ٶ���ֻ�Ƕ���һ��������ڴ��ַ�����ö���һ��
	//����ֵ�������ڴ����в����м������Ὺ�ٿռ䡣
	//ֵ�����ǰ˴����� int float֮��ģ������ڷ������޸Ĳ���ı䱾���ֵ���ı�����ڷ�����
	//���ô�����ָ���󣬷������޸�Ҳ��Բ����޸�
	private Map<String,Object> dto = null;
	/**
	 * ����DTO
	 */
	public void setMapDto(Map<String,Object> dto)
	{
		this.dto = dto;
	}
	/**
	 * ��ȡ��һ����
	 * @param val
	 * @return
	 */
	protected final Object getVal(String key)
	{
		return this.dto.get(key);
	}
	/**
	 * ��DTOд������
	 * @param key
	 * @param value
	 */
	protected final void addEntry(String key,Object value)
	{
		this.dto.put(key, value);
	}
	/************************************************************************
	 *   ��������
	 ************************************************************************/
	/**
	 * �˷����ǽ��������ַ��������ַ������鶼����ַ�������
	 * @param key
	 * @return
	 */
	protected final String[] getArray(String key)
	{
		Object val=this.dto.get(key);
		if(val==null||val.equals(""))
		{
			return new String[] {};//�����ַ�������
		}
		if(val instanceof java.lang.String[])
		{
			return (String[])val;
		}
		else
		{
			return new String[] {val.toString()};
		}
	}
	/**
	 * �ж��ַ����ǲ��ǿ�ֵ������null
	 * @param val
	 * @return
	 */
	protected final boolean isNotNUll(Object val)
	{
		return val!=null&&!val.equals("");
	}
	/************************************************************************
	 *   ��ѯ����
	 ************************************************************************/
	/**
	 * ����������ѯ
	 * <
	 * 1.��ԭҳ������ΪObject����
	 * 2.ƴ��SQL���,��֤:
	 *    2.1 ?��?ֵ,ͬ������
	 *    2.1 ?������˳��,��?ֵ���������˳��,�ϸ�һһƥ��
	 * 3.�����,������Ϊ?��ֵ   
	 * >
	 * @return
	 * @throws Exception
	 */
	protected final List<Map<String,String>> queryForList(final String sql,final Object...val)throws Exception
	{
		//1������JDBC�ӿڱ���
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try
		{
			//��ȡ������Դ
			conn=DBUtils.getConnection();
			//����SQL���
			pstm = conn.prepareStatement(sql);
			//��ȡ�������飬Ϊpstm��ֵ
			int index = 1;
			for(Object param:val)
			{
				pstm.setObject(index++, param);
			}
			//ִ��SQL���:ͨ��������ִ��SQL�����ؽ��������
		    rs=pstm.executeQuery();
		    //��ȡ�������������
		    ResultSetMetaData rsmd = rs.getMetaData();
		    //�����ѯ����е�����
		    int count = rsmd.getColumnCount();
		    //�����ʼ����
		    int initsize=((int)(count/0.75))+1;
		    
		    //����Listװ��ȫ����ѯ���
		    List<Map<String,String>> rows = new ArrayList<>();
		    //����Mapװ�ص�ǰ������
		    Map<String,String> ins = null;
		    //while����ѭ����rs����ѭ��
		    while(rs.next())
		    {
		    	//ʵ������ǰ��HashMap
		    	ins = new HashMap<>(initsize);
		    	//����ÿ���и��еĶ�ȡ
		    	for(int i=1;i<=count;i++)
		    	{
		    		//����ǰ�е��У�����Map���б�����key����������value
		    		ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
		    	}
		    	rows.add(ins);
		    }
		    return rows;
		    
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
			DBUtils.close(conn);  
			}
		}
	/**
	 * ��һʵ����ѯ  ��λ����
	 * @param sql --  ִ�е�sql��� 
	 * @param val --  ����ֵ����
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> queryForMapOpen(final String sql,final Object...val)throws Exception
	{
		//1.����JDBC�ӿ�
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//2.��������
			conn=DBUtils.getConnection();
			//3.����SQL
			pstm=conn.prepareStatement(sql);
			//4.������ֵ
			int index=1;
			for(Object param:val)
			{
				pstm.setObject(index++, param);
			}
			//5.ִ��SQL
			rs=pstm.executeQuery();
			
			//6.����Map����,װ�ص�ǰ������
			Map<String,String> ins=null;
			//7.�ж��Ƿ���ڲ�ѯ���
			if(rs.next())
			{
				//8.��ȡ�������������
				ResultSetMetaData rsmd=rs.getMetaData();
				//9.�����ѯ����е�����
				int count=rsmd.getColumnCount();
				//10.���㰲ȫ�ĳ�ʼ����
				int initSize=((int)(count/0.75))+1;
				//11.ʵ����HashMap,����װ�ص�ǰ������
				ins=new HashMap<>(initSize);
				//12.ѭ��������ǰ��ÿ��
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				
			}
			
			return ins;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	}
	/**
	 * ��һʵ����ѯ
	 * @param sql --  ִ�е�sql��� 
	 * @param val --  ����ֵ����
	 * @return
	 * @throws Exception
	 */
	protected final Map<String,String> queryForMap(final String sql,final Object...val)throws Exception
	{
		//1.����JDBC�ӿ�
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//2.��������
			conn=DBUtils.getConnection();
			//3.����SQL
			pstm=conn.prepareStatement(sql);
			//4.������ֵ
			int index=1;
			for(Object param:val)
			{
				pstm.setObject(index++, param);
			}
			//5.ִ��SQL
			rs=pstm.executeQuery();
			
			//6.����Map����,װ�ص�ǰ������
			Map<String,String> ins=null;
			//7.�ж��Ƿ���ڲ�ѯ���
			if(rs.next())
			{
				//8.��ȡ�������������
				ResultSetMetaData rsmd=rs.getMetaData();
				//9.�����ѯ����е�����
				int count=rsmd.getColumnCount();
				//10.���㰲ȫ�ĳ�ʼ����
				int initSize=((int)(count/0.75))+1;
				//11.ʵ����HashMap,����װ�ص�ǰ������
				ins=new HashMap<>(initSize);
				//12.ѭ��������ǰ��ÿ��
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				
			}
		
			return ins;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	}
	/************************************************************************
	 *   ���������
	 ************************************************************************/
	private final List<PstmManagerBean> pstmList = new ArrayList<>();
	/**
	 * ����������ӷ����������
	 * @param sql
	 * @param args
	 * @throws Exception
	 */
	protected void appendSql(String sql,Object...args)throws Exception
	{
		//���������󲢱��븳ֵ
		PreparedStatement pstm = DBUtils.preparedStatement(sql);
		int index=1;
		for(Object param:args)
		{
			pstm.setObject(index++,param);
		}
		//Ϊ�������ṩ������
		PstmManagerBean pm = new PstmManagerBean(pstm, false);
		//��pstmbean����pstmList
		this.pstmList.add(pm);
	}
	/**
	 * Ϊ����������������
	 * <
	 *   ����ʽ����:
	 *   delete from table where id=?
	 * >
	 * @param sql
	 * @param args
	 * @throws Exception
	 */
	protected void appendSqlForBatch(final String sql,final Object...args)throws Exception
	{
		//1.����SQL���
		PreparedStatement pstm=DBUtils.preparedStatement(sql);
		for(Object param:args)
		{
			pstm.setObject(1, param);
			pstm.addBatch();
		}
		
		//����Pstm����bean
		PstmManagerBean pm=new PstmManagerBean(pstm, true);
		//����pstmlist
		this.pstmList.add(pm);
	}
	
	/**
	 * Ϊ�������SQL���
	 * 
	 * <
	 *   ��һ״̬�޸����
	 *   ����ʽ����:
	 *   update table 
	 *      set columns=?
	 *    where id=?  
	 * >
	 * @param sql        ---- SQL���
	 * @param newState   ---- ��һ״ֵ̬(set�б����ֶε�Ŀ��ֵ)
	 * @param args       ---- ��������
	 * @throws Exception
	 */
	protected void appendSqlForBatch(final String sql,final Object newState,final Object...args)throws Exception
	{
		PreparedStatement pstm=DBUtils.preparedStatement(sql);
		//��Ϊ��һ��������ֵ
		pstm.setObject(1, newState);
		//ѭ��Ϊ�ڶ���������ֵ,�����뻺����
		for(Object param:args)
		{ 
		   pstm.setObject(2, param);	
		   pstm.addBatch();
		}
		//��ȡpstm������
		PstmManagerBean pm=new PstmManagerBean(pstm, true);
		//����pstmList
		this.pstmList.add(pm);
	}
	/**
	 * Ϊ�������SQL���
	 * <
	 *   ��״̬�޸����
	 *   ����ʽ����:
	 *   update table 
	 *      set col1=?,col2=?,col3=?.....coln=? 
	 *    where id=?
	 * >
	 * @param sql        ---- SQL���
	 * @param states     ---- set�б��и�?�Ķ�Ӧֵ
	 * @param args       ---- ��������
	 * @throws Exception
	 */
	protected void appendSqlForBatch(final String sql,final Object[] states ,final Object...args)throws Exception
	{
		PreparedStatement pstm=DBUtils.preparedStatement(sql);
		//1.�����set�б�ĸ�ֵ
		int index=1;
		for(Object s:states)
		{
			pstm.setObject(index++, s);
		}
		for(Object e:args)
		{
			pstm.setObject(index, e);
			pstm.addBatch();
		}
		//��ȡpstm������
		PstmManagerBean pm=new PstmManagerBean(pstm, true);
		//����pstmList
		this.pstmList.add(pm);
	}

	
	/**
	 * ����ִ�з���
	 * @return
	 * @throws Exception
	 */
	protected boolean executeTransaction()throws Exception
	{
		//1.�������񷵻�ֵ
		boolean tag=false;
		//2.��������
		DBUtils.beginTransaction();
		try
		{
			//������ʽִ��SQL���
			for(PstmManagerBean pm:pstmList)
			{
				pm.executePreparedStatement();
			}
			//�ύ����
			DBUtils.commit();
			tag=true;
			
		}
		catch(Exception ex)
		{
			//����ع�
			DBUtils.rollback();
		    ex.printStackTrace();
		    
		}
		finally
		{
			//��������
			DBUtils.endTransaction();
			//�ر�����������
			this.closePreparedStatement();
			//��������������
			this.pstmList.clear();
		}
		return tag;
	}
	
	/**
	 * �ر������ڼ������������
	 */
	private void closePreparedStatement()
	{
		for(PstmManagerBean pm:pstmList)
		{
			pm.close();
		}
	}

	
	
	
	/************************************************************************
	 *   ��һ�����ݸ��� 
	 ************************************************************************/
	
	/**
	 * ��һ��������
	 * <
	 *   �ʺϵ�SQL����ʽ����:
	 *   DELETE FROM TABLE WHERE ID=?
	 * >
	 * @param sql  --- ��Ҫִ�е�SQL���
	 * @param val  --- checkbox����
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,final Object...val)throws Exception
	{
		//1.����JDBC�ӿ�
		Connection conn=null;
		PreparedStatement pstm=null;
		try
		{
			//2.��������
			conn=DBUtils.getConnection();
			//3.����SQL
			pstm=conn.prepareStatement(sql);
			//4.������ֵ
			for(Object param:val)
			{
				pstm.setObject(1, param);
				//��ӻ�����
				pstm.addBatch();
			}
			
			//�������񷵻�ֵ
			boolean tag=false;
			//��������
			conn.setAutoCommit(false);
			try
			{
				//��������ִ�л����������
				pstm.executeBatch();
				//�ύ����
				conn.commit();
				
				//�޸ķ���ֵ
				tag=true;
			}
			catch(Exception ex)
			{
				conn.rollback();
				ex.printStackTrace();
			}
			finally
			{
			    conn.setAutoCommit(true);
			}
			return tag;
		}
		finally
		{
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	} 
	/**
	 * ��һ�����ݸ��·���  ���⿪��
	 * @param sql
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public  boolean executeUpdateOpen( String sql,  Object...val)throws Exception
	{
		//1.����JDBC�ӿ�
		Connection conn=null;
		PreparedStatement pstm=null;
		try
		{
			//2.��������
			conn=DBUtils.getConnection();
			//3.����SQL���
			pstm=conn.prepareStatement(sql);
			//4.������ֵ
			int index=1;
			for(Object param:val)
			{
				pstm.setObject(index++, param);
			}
			//5.ִ��
			return pstm.executeUpdate()>0;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
		return false;
	}
	
	/**
	 * ��һ�����ݸ��·���
	 * @param sql
	 * @param val
	 * @return
	 * @throws Exception
	 */
	protected final boolean executeUpdate(final String sql, final Object...val)throws Exception
	{
		//1.����JDBC�ӿ�
		Connection conn=null;
		PreparedStatement pstm=null;
		try
		{
			//2.��������
			conn=DBUtils.getConnection();
			//3.����SQL���
			pstm=conn.prepareStatement(sql);
			//4.������ֵ
			int index=1;
			for(Object param:val)
			{
				pstm.setObject(index++, param);
			}
			//5.ִ��
			return pstm.executeUpdate()>0;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
		return false;
	}
	
}
