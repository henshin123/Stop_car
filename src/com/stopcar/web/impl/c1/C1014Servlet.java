package com.stopcar.web.impl.c1;

import com.stopcar.web.impl.C10Support;

public class C1014Servlet extends C10Support{
	@Override
	public String execute() throws Exception {
		//ɾ������
		this.update("delete");
		//���¼���
		this.deleteLastQuery();
		return "allpark";
	}
}
