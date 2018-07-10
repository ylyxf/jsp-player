<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Enumeration<String> paraNames=request.getParameterNames();
for(Enumeration e=paraNames;e.hasMoreElements();){
 
       String thisName=e.nextElement().toString();
       String thisValue=request.getParameter(thisName);
       System.out.println(thisName+"--------------"+thisValue);
}
out.println("{\"a\":\"b\"}");
%>