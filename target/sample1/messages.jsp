<%@ taglib prefix="s" uri="/struts-tags"%>
<div>
	<s:if test="hasActionErrors()">
		<div>
			<s:actionerror />
		</div>
	</s:if>
	<s:if test="hasActionMessages()">
		<div>
			<s:actionmessage />
		</div>
	</s:if>
</div>
