<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%> --%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>
	<tiles:insertAttribute name="header" flush="true" />
	<tiles:insertAttribute name="body" flush="true" />
	<tiles:insertAttribute name="footer" flush="true" />
</body>
</html>