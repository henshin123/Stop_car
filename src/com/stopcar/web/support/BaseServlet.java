package com.stopcar.web.support;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stopcar.system.tools.Tools;

@WebServlet("*.html")
public class BaseServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String toPath = null ;
		try {
			String uri = request.getRequestURI();
			int index = uri.lastIndexOf("/");
			String baseName = uri.substring(index+1).replace(".html", "");
			String packageName = baseName.substring(0,2);
			String controlerName = "com.stopcar.web.impl."+packageName+"."+baseName.toUpperCase()+"Servlet";
			BaseController controller = (BaseController)Class.forName(controlerName).newInstance();
			/**
			 * ����ע��
			 */
			request.setCharacterEncoding("GBK");
			Map<String,Object> dto = this.creatDto(request);
			controller.setDto(dto);
			toPath=controller.execute();
			/**
			 * �������̿��Ʒ���
			 * ֯�����̿�����Ƭ
			 */
			//�������Դ���--��ȡ���������
			Map<String,Object> attributes=controller.getAttributes();
			//֯�����Դ洢��Ƭ
			this.saveAttributes(attributes, request);
			/**
			 * �����android�˵�����
			 */
			if(toPath==null)
			{
				response.setCharacterEncoding("gbk");
				response.setContentType("text/html;charset=gbk");
				ToAndroid(request,response);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		if(toPath!=null)
		{
			request.getRequestDispatcher("/jsp/"+toPath+".jsp").forward(request, response);
		}
	}
	/**
	 * ��ҵ����Ƶ������Ŀ��д��request
	 * @param attributes
	 * @param request
	 */
	private void saveAttributes(Map<String,Object> attributes,HttpServletRequest request)
	{
		//��ȡket����
		Set<String> KeySet=attributes.keySet();
		//ѭ�����е�key
		for(String key:KeySet)
		{
			//����key��ȡ��Ӧ��velue
			Object value = attributes.get(key);
			//�����Դ���request
			request.setAttribute(key, value);
		}
	}
	/**
	 * ����DTO����װҳ������
	 * @param request
	 * @return
	 */
	private Map<String,Object> creatDto(HttpServletRequest request)
	{
		Map<String,String[]> tem = request.getParameterMap();
		Map<String,Object> dto = new HashMap<>();
		Set<String> keySet = tem.keySet();
		for(String key:keySet)
		{
			String val[] = tem.get(key);
			if(val.length==1)
			{
				dto.put(key, val[0]);
			}
			else
			{
				dto.put(key, val);
			}
		}
		return dto;
	}
	private void ToAndroid(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			 String	result = null;
			if(request.getAttribute("msg")!=null)
			{
				result = request.getAttribute("msg")+"#"
				+request.getAttribute("id")+"#"
				+request.getAttribute("sex")+"#"
				+request.getAttribute("new")+"#"
				+request.getAttribute("yue")+"#"
				+request.getAttribute("email")+"#"
				+request.getAttribute("tellphono")+"#"
				+request.getAttribute("personal")+"#"
				+request.getAttribute("PicUri");
			}
			else if(request.getAttribute("state")!=null)
			{//���Ǵ��ݵ�android�˵ĳ�λ״̬���
				result = request.getAttribute("state").toString();
			}
			else if(request.getAttribute("update")!=null)
			{
				result = request.getAttribute("update").toString();
			}
			else if(request.getAttribute("selectpark")!=null)
			{
				result = request.getAttribute("selectpark").toString();
			}
			else if(request.getAttribute("selectparkname")!=null)
			{
				result = request.getAttribute("selectparkname").toString();
			}
			else if(request.getAttribute("parkoneid")!=null)
			{
				result = request.getAttribute("parkoneid").toString();
			}
			else if(request.getAttribute("loginmanager")!=null)
			{
				result = request.getAttribute("loginmanager").toString();
			}
			PrintWriter out = response.getWriter();
			out.println(result);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
