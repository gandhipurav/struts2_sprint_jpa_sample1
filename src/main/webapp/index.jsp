<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="messages.jsp"/>
<table>
	<tr>
		<th></th>
		<th>Names</th>
		<th>Descriptions</th>		
		<th></th>
		<th></th>
	</tr>
	<s:if test="#session.items!=null">
		<s:iterator value="#session.items" var="item" status="count">
			<tr>
				<td>${count.index+1}</td>
				<td><s:property value="name" /></td>
				<td><s:property value="descriptions" /></td>				
				<td><s:a action="editItem?id=%{#item.id}">Edit</s:a></td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<td><s:url action="deleteItem" var="deleteActionVar">
							<s:param name="id" value="id"></s:param>
						</s:url> <s:a href="%{#deleteActionVar}">Delete</s:a>
					</td>
				</sec:authorize>
			</tr>
		</s:iterator>
	</s:if>
</table>
<s:a action="addItem">Add</s:a>
<s:a action="logout">Logout</s:a>
