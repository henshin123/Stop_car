package com.stopcar.web.impl.a1;

import com.stopcar.web.impl.A10Support;

public class A1010Servlet extends A10Support
{
	@Override
	public String execute() throws Exception {
		this.queryData();
		return "alluser";
	}

}
