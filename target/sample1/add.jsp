<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="messages.jsp"/>
<s:form action="saveItem" method="post">
	<s:hidden name="item.id" />
	<s:hidden name="item.color.id" />
	<s:textfield name="item.name" label="Name"/>
	<s:textfield name="item.color.color" label="Color" />
	<s:textfield name="item.descriptions" label="Description"/>
	<s:submit label="Save"/>	
</s:form>
