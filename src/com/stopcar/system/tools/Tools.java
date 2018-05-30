package com.stopcar.system.tools;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.mywq.util.LabelValueBean;

import com.stopcar.system.db.DBUtils;

public class Tools {
	/**
	 * ���캯�����������������ӣ���������Ǿ�̬�� ����Ĺ����Ӿ�����˽�е�
	 */
	private Tools() {};
	//******************BEGIN MD5����********************
	
		public static final String initPwd="27d17aee7b69911d6946014e337932db";
		
		
		/**
		 * md5����
		 * @param pwd
		 * @return
		 * @throws Exception
		 */
		public static String getMd5Code(Object pwd)throws Exception
		{
			//��ȡmd5��һ�μ��ܵ�����
			String md5pwd1=Tools.MD5Encode(pwd.toString());
			//System.out.println("һ������:"+md5pwd1);
			//��д��������
			String pwd2=md5pwd1+"����������Ԥ������ئǦɤ�۟o�Ĥ�������ͨШۨܦ̦ͦ¨ݨƨӨ��з��줫�ͤ�����"+md5pwd1;
			//���λ�������
			String md5pwd2=Tools.MD5Encode(pwd2);
			//System.out.println("��������:"+md5pwd2);
			return md5pwd2;
		}
		
		
		 private final static String[] hexDigits = {
		     "0", "1", "2", "3", "4", "5", "6", "7",
		     "8", "9", "a", "b", "c", "d", "e", "f"};

		  /**
		   * ת���ֽ�����Ϊ16�����ִ�
		   * @param b �ֽ�����
		   * @return 16�����ִ�
		   */
		  private static String byteArrayToHexString(byte[] b)
		  {
		      StringBuffer resultSb = new StringBuffer();
		      for (int i = 0; i < b.length; i++)
		      {
		         resultSb.append(byteToHexString(b[i]));
		      }
		      return resultSb.toString();
		  }
		  /**
		   * ת���ֽ�Ϊ16�����ַ���
		   * @param b byte
		   * @return String
		   */
		  private static String byteToHexString(byte b)
		  {
		      int n = b;
		      if (n < 0)
		         {
		    	  n = 256 + n;
		         }
		      int d1 = n / 16;
		      int d2 = n % 16;
		      return hexDigits[d1] + hexDigits[d2];
		  }
		  /**
		   * �õ�MD5����������
		   * @param origin String
		   * @throws Exception
		   * @return String
		   */
		  public static String MD5Encode(String origin) throws Exception
		  {
		       String resultString = null;
		       try
		       {
		           resultString=new String(origin);
		           MessageDigest md = MessageDigest.getInstance("MD5");
		           resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
		           return resultString;
		       }
		       catch (Exception ex)
		       {
		          throw ex;
		       }
		  }
		//******************END  MD5******************
		
	
	private final static int MATCH_SCALE=2;         //��������Ĭ��С��λ��
	/**
	 * �����ĸ�����Ϊ����ת������
	 * @param dol double
	 * @param scale int
	 * @return String
	 */
	public static double ObjToDouble(Object dol, int scale)
	{
		  return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
	}
	public static double ObjToDouble(Object dol)
	{
	   return Tools.ObjToDouble(dol, MATCH_SCALE);	
	}
	
	public static String DoubleToStr(double dol, int scale)
	{
	    return Tools.ObjectToBigDecimal(dol, scale).toString();
	}
	public static String DoubleToStr(double dol)
	{
	    return Tools.DoubleToStr(dol, MATCH_SCALE);
	}

	public static double DoubleToDouble(double dol, int scale)
	{
	    return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
	}
	public static double DoubleToDouble(double dol)
	{
	    return Tools.DoubleToDouble(dol,  MATCH_SCALE);
	}

	public static double StrToDouble(String dol, int scale)
	{
	    return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
	}
	public static double StrToDouble(String dol)
	{
	    return Tools.StrToDouble(dol, MATCH_SCALE);
	}
	
    public static String StrToStr(String dol, int scale)
	{
	   return Tools.ObjectToBigDecimal(dol, scale).toString();
	}
	public static String StrToStr(String dol)
	{
	  return Tools.StrToStr(dol,MATCH_SCALE);
	}

	/**
	 * ���������������
	 * @param dol    ---  ��ֵ
	 * @param scale  ---  С��λ��(����)
	 * @return
	 */
	private static BigDecimal ObjectToBigDecimal(Object dol,int scale)
	{
		//����������ͱ���
		BigDecimal decimal=null;
		//�ж�dol����ǿջ�null,����0
		if(dol==null || dol.equals(""))
		{
			return new BigDecimal(0);
		}
		//����dol���BigDecimal��ʵ����
		decimal = new BigDecimal(dol.toString());
		//���վ�����������
		decimal = decimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return decimal;
	}

	/**
	 * ��ȡ������ˮ��
	 * @param sname
	 * @return
	 * @throws Exception
	 */
	public static int getSequenceForPk(final String sname)throws Exception
	{
		//����JDBC�ӿ�
		PreparedStatement pstm1=null;   //��ȡ���е�ǰֵ
		PreparedStatement pstm2=null;   //��������
		ResultSet rs=null;
		try
		{
			//��ȡ���е�ǰֵ
			StringBuilder sql1=new StringBuilder()
			.append("select a.svalue")
			.append("  from sequence a")
			.append(" where a.sname=?")	
			;
			pstm1=DBUtils.preparedStatement(sql1.toString());
			pstm1.setObject(1, sname);
			rs=pstm1.executeQuery();
			
			//�������з���ֵ
			int currVal=0;
			
			StringBuilder sql2=new StringBuilder();
			
			//�ж��Ƿ�������е�ǰֵ
			if(rs.next())
			{
				currVal=rs.getInt(1);
				
				//��������
				sql2.append("update sequence")
				    .append("  set svalue=?")
				    .append(" where sname=?")
				;
			}
			else
			{
				sql2.append("insert into sequence(svalue,sname,sdate)")
				    .append("            values(?,?,current_date)");		
			}	
			pstm2=DBUtils.preparedStatement(sql2.toString());
			pstm2.setObject(1, ++currVal);
			pstm2.setObject(2, sname);
			pstm2.executeUpdate();
			
			return currVal;
			
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm1);
			DBUtils.close(pstm2);
		}
	}
	/**
	 * ��ȡ������ˮ��
	 * @param sname
	 * @return
	 * @throws Exception
	 */
	public static String getSequence(String sname)throws Exception
	{
		//1������JDBC�ӿ�
		PreparedStatement pstm1 = null; //���ڶ�ȡ��ǰ����
		PreparedStatement pstm2 = null; //�������е�ǰֵ
		ResultSet rs = null;
		try
		{
			//����SQL����ȡ���е�ǰֵ
			StringBuilder sql = new StringBuilder()
			.append("select a.svalue") 
			.append("  from sequence a")
			.append(" where a.sname=?")
			.append("   and a.sdate=current_date")		
			;
			//3������sql���
			pstm1=DBUtils.preparedStatement(sql.toString());
			pstm1.setObject(1, sname);
			//4.ִ��SQL���
			rs = pstm1.executeQuery();
			//5.�������е�ǰֵ�ñ���
			int current_val = 0;
			//����������е�sql���
			StringBuilder sql2 = new StringBuilder();
			//�жϵ�ǰ�����Ƿ���ڵ�ǰֵ
			if(rs.next())
			{
				//���¸����еĵ�ǰֵ
				current_val=rs.getInt(1);
				//����������
				sql2.append("update sequence")
					.append("   set svalue=?")
					.append(" where sdate=current_date")
					.append("   and sname=?")		
				;
			}
			else
			{
				//¼����������,���ұ�֤��ǰֵ��1
				//����SQL���,��������е�¼��
				sql2.append("insert into sequence(svalue,sname,sdate)")
				.append("            values(?,?,current_date)")		
				;
			}
			pstm2=DBUtils.preparedStatement(sql2.toString());
			pstm2.setObject(1, ++current_val);
			pstm2.setObject(2, sname);
			pstm2.executeUpdate();
			
			
			//���岹λ��Ļ�����
			String baseCode="0000";
			//����������в�λ��Ľ�ȡ��ʼ�±�
			int beginIndex=String.valueOf(current_val).length();
			//��ȡ��λ��
			String bcode=baseCode.substring(beginIndex);
			 return null;			
				
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm1);
			DBUtils.close(pstm2);
		}
	}
	/**
	 * ��ȡ�����б�
	 * @param fname
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getOptions(String fname)throws Exception
	{
		//1.����JDBC�ӿ�
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//3.����SQL���
			StringBuilder sql=new StringBuilder()
			.append("select fvalue,fcode") 
			.append("  from syscode") 
			.append(" where fname=?")		
			;
			//4.����SQL���
			pstm=DBUtils.preparedStatement(sql.toString());
			pstm.setObject(1, fname);
			
			//5.ִ��SQL
			rs=pstm.executeQuery();
			//6.����װ�ز�ѯ�����List
			List<LabelValueBean> opts=new ArrayList<>();
			//7.����LabelValueBean ����,װ�ص�ǰ������
			LabelValueBean bean=null;
			//8.ѭ������rs������
			while(rs.next())
			{
				//9.ʵ����LabelValueBean
				bean=new LabelValueBean(rs.getString(1),rs.getString(2));
				//10.����List
				opts.add(bean);
			}
			return opts;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	/**
	 * ת������
	 * @param val
	 * @return
	 */
	public static String[] caseArray(Object val)
	{
		if(val==null||val.equals(""))
		{
			return new String[] {};//���������Object�Ǹ��յĻ����ǿյ��ַ��������һ���ַ������飬���ﶨ��ķ�ʽ��������
		}
		if(val instanceof java.lang.String[])//�������ж�val�Ƿ���String[]
		{
			return (String[])val;
		}
		else
		{
			return new String[] {val.toString()};
		}
	}
	public static String jojinArray(Object val)
	{
		//��ֵ����
		if(val==null||val.equals(""))
		{
			return "";
		}
		if(val instanceof java.lang.String[])
		{
			//�����յ��Ķ���ת�����ַ���
			String tem[] = (String[])val;
			//�������鳤��
			int size=tem.length;
			//�����ַ������������������Ԫ��
			StringBuilder text = new StringBuilder(tem[0]);
			//�������Ԫ��ƴ�ӳ��ַ���
			for(int i=1;i<size;i++)
			{
				text.append(",").append(tem[i]);
			}
			//����
			return text.toString();
		}
		else
		{
			return val.toString();
		}
	}
	/**
	 * ���ַ���ת�����ַ�������
	 * @param result
	 * @return
	 */
	public static String[] StringToArray(String result)
	{
		String[] string = result.split("#");
		return string;
	}

}
			
