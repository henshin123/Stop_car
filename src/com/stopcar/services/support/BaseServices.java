package com.stopcar.services.support;

import java.util.List;
import java.util.Map;

public interface BaseServices 
{
	//��������ҳ������
		void setMapDto(Map<String,Object> dto);
		//default�ӿڷ�����Ĭ��ʵ��
		default List<Map<String,String>> query()throws Exception
		{
			return null;
		}
		default Map<String,String> findById()throws Exception
		{
			return null;
		}
		default boolean update(String utype)throws Exception
		{
			return false;
		}
		default boolean guiling2()throws Exception
		{
			return false;
		}
		default boolean guiling1()throws Exception
		{
			return false;
		}
		default boolean updatestate01()throws Exception
		{
			return false;
		}
		default boolean updatestate02()throws Exception
		{
			return false;
		}
		default List<Map<String,String>> getstate()throws Exception
		{
			return null;
		}
		default Map<String, String> login()throws Exception
		{
			return null;
		}
		default Map<String, String> selectparkbyuserid()throws Exception
		{
			return null;
		}
		default Map<String, String> parkoneid()throws Exception
		{
			return null;
		}
		default Map<String, String> parkid()throws Exception
		{
			return null;
		}
}
