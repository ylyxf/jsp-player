<%@page import="org.siqisource.jspplayer.utils.DatabaseUtils"%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%
	out.println(DatabaseUtils.loginH2WebServer());
%>