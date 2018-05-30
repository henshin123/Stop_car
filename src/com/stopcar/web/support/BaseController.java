package com.stopcar.web.support;

import java.util.Map;

public interface BaseController 
{
	/**
	 * DTOע�뷽��
	 * <
	 *   ���ݵ�����
	 *   BaseServletͨ���÷�����Ϊҵ�����������ҳ����������
	 * >
	 * @param dto
	 */
	void setDto(Map<String,Object> dto);
	/**
	 * ҵ��������н������̿��Ƶķ���
	 * @return ����ҵ���������תҳ��
	 * @throws Exception
	 */
	String execute()throws Exception;
	/**
	 * ���Զ���
	 * <
	 *    ��������
	 *    Baseservletͨ���÷�������ȡ��servlet���ݵ�ҳ����������
	 * >
	 * @return
	 */
	Map<String,Object> getAttributes();
	
	
}
