<%-- 
    Document   : login
    Created on : Sep 9, 2015, 3:18:47 PM
    Author     : malonen
--%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
      <% 
          
      response.setHeader("Refresh", "2;url=/");
    
      Object prov = request.getAttribute("Shib-Identity-Provider"); 
      Object displayName = request.getAttribute("displayName"); 
      Object group = request.getAttribute("group"); 
      Object mail = request.getAttribute("mail");
      Object sn = request.getAttribute("sn"); 
      Object uid = request.getAttribute("uid"); 

      
     if (prov!=null){
         
      session.setAttribute("displayName",displayName);
      session.setAttribute("group",group);
      session.setAttribute("mail",mail);
      session.setAttribute("uid",uid);
      
      %>
      <h1>Hei <%=displayName.toString()%>!</h1>
      <%} else {%>
      <h1>Kirjautuminen epäonnistui</h1>
      <%}%>
    </body>
</html>
