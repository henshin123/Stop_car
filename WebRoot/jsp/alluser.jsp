<%@ page language="java" pageEncoding="GBK"%>
<%@taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path=request.getContextPath();%>
<html>
<head>
	<title>ͣ������̨����ϵͳ</title>
</head>
<body>
	<form action="<%=path %>/a1010.html" method="post">
	<!-- ���ڲ�ѯ���� -->
		<table border="1" width="95%" align="center">
			<caption>
			�û��������
			<hr width="160">
			</caption>
		</table>
		<!-- ���ݵ��� -->
		<table border="1" width="95%" align="center">
			<tr>
				<td>&nbsp;</td>
      			<td>�û�id</td>
      			<td>�û�����</td>
      			<td>�û��Ա�</td>
      			<td>�û���λ״̬</td>
      			<td>�û����</td>
      			<td>�û�����</td>
      			<td>�û��绰</td>
      			<td>��ע</td>
      			<td>&nbsp;</td>
			</tr>
			<c:choose>
				<c:when test="${rows!=null }">
					<c:forEach items="${rows }" var="ins" varStatus="vs">
						<tr>
							<td>&nbsp;</td>
							<td>${vs.count }</td>
							<td>
								<a href="#" onclick="onEdit('${ins.aa001 }','<%=path %>')">${ins.aa002 }</a>
							</td>
							<td>${ins.aa004 }</td>
							<td>${ins.aa005 }</td>
							<td>${ins.aa006 }</td>
							<td>${ins.aa007 }</td>
							<td>${ins.aa008 }</td>
							<td>${ins.aa009 }</td>
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
               				<td>&nbsp;</td>
               				<td>&nbsp;</td>
             			</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</form>	
</body>
</html>