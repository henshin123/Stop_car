package com.stopcar.services.support;

import java.sql.PreparedStatement;

import com.stopcar.system.db.DBUtils;



final class PstmManagerBean 
{
	private PreparedStatement pstm = null;
	private boolean isBatch = false;
	/**
	 * 
	 * @param pstm   ------��Ҫͨ������ִ�е�������
	 * @param isBatch  ----�������true---executeBatch��flash---executeUpdate
	 */
	public PstmManagerBean(PreparedStatement pstm,boolean isBatch)
	{
		this.pstm = pstm;
		this.isBatch = isBatch;
	}
	/**
	 * ִ��������
	 * @param pstm
	 * @param isBatch
	 */
	public void executePreparedStatement()throws Exception
	{
		if(this.isBatch)
		{
			this.pstm.executeBatch();
		}
		else
		{
			this.pstm.executeUpdate();
		}
	}
	public void close()
	{
		DBUtils.close(this.pstm);
	}

}
