<%@ page language="java" pageEncoding="GBK"%>
<%@taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path=request.getContextPath();%>
<html>
<head>
	<title>ͣ������̨����ϵͳ</title>
	<script type="text/javascript" src="<%=path %>/js/parkone.js"></script>
</head>
<body>
${msg }
	<form action="<%=path %>/e1001.html" method="post">
	<!-- ���ڲ�ѯ���� -->
		<table border="1" width="95%" align="center">
			<caption>
			ͣ��λ����
			<hr width="160">
			</caption>
			<tr>
				<td>��λ��</td>
				<td>
					<e:text name="qcb001" autofocus="true"/>
				</td>
				<td>��λ״̬</td>
				<td>
					<e:select name="qcb002" value="������:'',�г�:1,ԤԼ:2,�޳�:0"/>
				</td>
			</tr>
		</table>
		<!-- ���ݵ��� -->
		<table border="1" width="95%" align="center">
			<tr>
				<td>&nbsp;</td>
				<td>���</td>
      			<td>ͣ��λid</td>
      			<td>ͣ��λ����ͣ����</td>
      			<td>ͣ��λ�û�</td>
      			<td>ͣ��λ״̬</td>
      			<td>��ע</td>
      			<td>&nbsp;</td>
			</tr>
			<c:choose>
				<c:when test="${rows!=null }">
					<c:forEach items="${rows }" var="ins" varStatus="vs">
						<tr>
							<td>
								<input type="checkbox" name="idlist"
										value="${ins.cb001 }" onclick="onSelect(this.checked)">
							</td>
							<td>${vs.count }</td>
							<td>
								<a href="#" onclick="onEdit('${ins.cb001 }','<%=path %>')">${ins.cb001 }</a>
							</td>
							<td>${ins.ca002 }</td>
							<td>${ins.aa002 }</td>
							<td>${ins.cb002 }</td>
							<td>${ins.cb004 }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="1" step="1" end="10">
						<tr>
               				<td>&nbsp;</td>
               				<td>&nbsp;</td>
               				<td>&nbsp;</td>
               				<td>&nbsp;</td>
               				<td>&nbsp;</td>
               				<td>&nbsp;</td>
             			</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<!-- ���ܰ�ť -->
		<table border="1" width="95%" align="center"> 
			 <tr>
      			<td align="center">
        			<input type="submit" name="next" value="��ѯ" formaction="<%=path%>/e1001.html?ca001=${param.ca001 }"> 
        			<input type="submit" name="next" value="���" formaction="<%=path%>/jsp/addpark.jsp">
       				 <input type="submit" name="next" value="ɾ��" disabled="disabled" formaction="<%=path%>/c1015.html">
       				 <input type="submit" name="next" value="����" formaction="<%=path%>/c1012.html">
     			</td>
   			</tr>
		</table>
		 <input  name="ca001" type="hidden" value="${param.ca001 }">
	</form>	
</body>
</html>