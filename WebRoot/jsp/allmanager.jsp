<%@ page language="java" pageEncoding="GBK"%>
<%@taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path=request.getContextPath();%>
<html>
<head>
	<script type="text/javascript" src="<%=path %>/js/allmanager.js"></script>
	<title>ͣ������̨����ϵͳ</title>
</head>
<body>
	<form action="<%=path %>/b1001.html" method="post">
	<!-- ���ڲ�ѯ���� -->
		<table border="1" width="95%" align="center">
			<caption>
			ͣ��������Ա
			<hr width="160">
			</caption>
			<tr>
				<td>ͣ����id</td>
				<td>
					<e:text name="qca002" autofocus="true"/>
				</td>
				<td>����Ա����</td>
				<td>
					<e:text name="qca003"/>
				</td>
			</tr>
		</table>
		<!-- ���ݵ��� -->
		<table border="1" width="95%" align="center">
			<tr>
				<td>&nbsp;</td>
      			<td>���</td>
      			<td>����Ա����</td>
      			<td>����ͣ����</td>
      			<td>&nbsp;</td>
			</tr>
			<c:choose>
				<c:when test="${rows!=null }">
					<c:forEach items="${rows }" var="ins" varStatus="vs">
						<tr>
							<td>
								<input type="checkbox" name="idlist"
										value="${ins.ba001 }" onclick="onSelect(this.checked)">
							</td>
							<td>${vs.count }</td>
							<td>${ins.ba002 }</td>
							<td>
								<a href="#" onclick = "onEdit('${ins.ca001 }','<%=path %>')" >${ins.ca002 }</a>
							</td>
							<td>
			    				<a href="#" onclick = "onDel('${ins.ba001 }','<%=path %>')" >[ɾ��]</a>
			  				</td>
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
             			</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<!-- ���ܰ�ť -->
		<table border="1" width="95%" align="center"> 
			 <tr>
      			<td align="center">
        			<input type="submit" name="next" value="��ѯ" > 
        			<input type="submit" name="next" value="���" formaction="<%=path%>/jsp/addpark.jsp">
       				 <input type="submit" name="next" value="ɾ��" disabled="disabled" formaction="<%=path%>/c1015.html">
     			</td>
   			</tr>
		</table>
	</form>	
</body>
</html>