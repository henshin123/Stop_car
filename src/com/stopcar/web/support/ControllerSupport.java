package com.stopcar.web.support;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.stopcar.services.support.BaseServices;

public abstract class ControllerSupport implements BaseController {

	/****************************************************************
	 *                ��Services�Ĵ���
	 ****************************************************************/
	
	protected BaseServices services=null;
	
	public BaseServices getServices()
	{
		return this.services;
	}
	/*******************************************************************
	 *                      ҵ�񷽷���װ
	 *******************************************************************/
     /**
      * ���ݲ�ѯ
      * @throws Exception
      */
	 protected final void queryData()throws Exception
	 {
		    //1.���ò�ѯ����
			List<Map<String,String>> rows=this.services.query();
			if(rows.size()>0)
			{
				this.addAttrobute("rows", rows);
			}
			else
			{
				this.addAttrobute("msg", "û�з�������������!");
			}
	 }
	 
	 /**
	  * ��һʵ����ѯ����
	  * @throws Exception
	  */
	 protected final void findById()throws Exception
	 {
			Map<String,String> ins=this.services.findById();
			if(ins!=null)
			{
				this.addAttrobute("ins", ins);
			}
			else
			{
				this.addAttrobute("msg", "��������ɾ�����ֹ����!");
			}
	 }
	 protected final void getstate()throws Exception
	 {
		 List<Map<String,String>> result = this.services.getstate();
		 if(result!=null)
		 {
			 StringBuilder string = new StringBuilder();
			 for(Map<String,String> park:result)
			 {
				 string.append(park.get("cb001")+"/"+park.get("cb002")+"#");
			 }
			 this.addAttrobute("state", string.toString());
		 }
	 }
	 protected final void updatestate01()throws Exception
	 {
		 boolean result = this.services.updatestate01();
		 if(result)
		 {
			 this.addAttrobute("update", 1);
		 }
		 else
		 {
			 this.addAttrobute("update", 0);
		 }
	 }
	 protected final void guiling2()throws Exception
	 {
		 boolean result = this.services.guiling2();
		 if(result)
		 {
			 this.addAttrobute("update", 1);
		 }
		 else
		 {
			 this.addAttrobute("update", 0);
		 }
	 }
	 protected final void guiling1()throws Exception
	 {
		 boolean result = this.services.guiling1();
		 if(result)
		 {
			 this.addAttrobute("update", 1);
		 }
		 else
		 {
			 this.addAttrobute("update", 0);
		 }
	 }
	 protected final void updatestate02()throws Exception
	 {
		 boolean result = this.services.updatestate02();
		 if(result)
		 {
			 this.addAttrobute("update", 1);
		 }
		 else
		 {
			 this.addAttrobute("update", 0);
		 }
	 }
	 protected final void selectparkname()throws Exception
	 {
		 List<Map<String, String>> result = this.services.query();
		 if(result!=null)
		 {
			 StringBuilder name = new StringBuilder();
			 for(Map<String, String> namemap:result)
			 {
				 name.append(namemap.get("name")+"#");
			 }
			 this.addAttrobute("selectparkname", name.toString());
		 }
		 else
		 {
			 this.addAttrobute("selectparkname", 0);
		 }
	 }
	 protected final void selectpark()throws Exception
	 {
		 Map<String,String> result = this.services.selectparkbyuserid();
		 if(result!=null)
		 {
			 StringBuilder string = new StringBuilder();
			 string.append("1"+"#");
			for(Iterator i = result.values().iterator(); i.hasNext();)
			{
				Object obj = i.next(); 
				string.append(obj+"#");
			}
			 this.addAttrobute("selectpark", string.toString());
		 }
		 else
		 {
			 this.addAttrobute("selectpark", 0);
		 }
	 }
	 protected final void login()throws Exception
	 {
		 Map<String,String> login = this.services.login();
		 if(login!=null)
		 {
			 this.addAttrobute("msg", "1");
			 this.addAttrobute("id", login.get("aa001"));
			 this.addAttrobute("sex", login.get("aa004"));
			 this.addAttrobute("new", login.get("aa005"));
			 this.addAttrobute("yue", login.get("aa006"));
			 this.addAttrobute("email", login.get("aa007"));
			 this.addAttrobute("tellphono", login.get("aa008"));
			 this.addAttrobute("personal", login.get("aa009"));
			 this.addAttrobute("PicUri", login.get("aa010"));
		 }
		 else
		 {
			 this.addAttrobute("msg", "0");
		 }
	 }
	 protected final void loginmanager()throws Exception
	 {
		 Map<String,String> login = this.services.login();
		 if(login!=null)
		 {
			 this.addAttrobute("loginmanager", login.get("ca002"));
		 }
		 else
		 {
			 this.addAttrobute("loginmanager", "0");
		 }
	 }
	 protected final void parkoneid()throws Exception
	 {
		 
		 Map<String,String> login = this.services.parkoneid();
		
		 if(login!=null)
		 {
			StringBuilder result = new StringBuilder();
			for(Iterator i = login.values().iterator(); i.hasNext();)
			{
				Object obj = i.next(); 
				result.append(obj+"#");
			}
			this.addAttrobute("parkoneid", result.toString());
		 }
		 else
		 {
			 this.addAttrobute("parkoneid", "0");
		 }
	 }
	 protected final void parkid()throws Exception
	 {
		 Map<String,String> login = this.services.parkid();
		 if(login!=null)
		 {
			StringBuilder result = new StringBuilder();
			for(Iterator i = login.values().iterator(); i.hasNext();)
			{
				Object obj = i.next(); 
				result.append(obj+"#");
			}
			this.addAttrobute("parkoneid", result.toString());
		 }
		 else
		 {
			 this.addAttrobute("parkoneid", "0");
		 }
	 }
	 /**
	  * ͨ�ø��²���
	  * @param type
	  * @param msgName
	  * @throws Exception
	  */
	 protected final void update(String type)throws Exception
	 {
		String msg=this.services.update(type)?"1":"0";
		this.addAttrobute("msg", msg);
	 }
	 
	 /**
	  * ɾ�����������
	  */
	 protected final void deleteLastQuery()throws Exception
	 {
		 List<Map<String,String>> rows=this.services.query();
		 if(rows.size()>0)
		 {
			 this.addAttrobute("rows", rows);
		 }
	 }

	
	/*****************************************************
	 *          DTO���ݴ���
	 *****************************************************/
	private Map<String,Object> dto=null;
	@Override
	public void setDto(Map<String, Object> dto)
	{
		this.dto=dto;
		//��DTO���ݸ�Services
		this.services.setMapDto(dto);
	}
	
	protected Map<String,Object> getDto()
	{
		return this.dto;
	}

	/*****************************************************
	 *          �����������
	 *****************************************************/
	//���е�request��attribute����ʱ�洢��----���Ի�����
	private Map<String,Object> attribute=new HashMap<>();
	//�����Ի�����������������
	protected void addAttrobute(String key,Object value)
	{
		this.attribute.put(key, value);
	}
	public Map<String, Object> getAttributes() 
	{
		return this.attribute;
	}

}
