package com.stopcar.web.impl;

import com.stopcar.services.impl.A10ServicesImpl;
import com.stopcar.web.support.ControllerSupport;

public abstract class A10Support extends ControllerSupport {

	public  A10Support() 
	{
		//Ϊ�������ṩServicesʵ����---��֤������ֻҪ��ʵ����,�ڲ��ͻ��Զ�������Ӧ��Services,
    	//����Services��û�а���ҳ������,Ϊ��Ӧ��Services��������Ѱ�Ұ취,����ҳ������
		this.services = new A10ServicesImpl();
	}

}
