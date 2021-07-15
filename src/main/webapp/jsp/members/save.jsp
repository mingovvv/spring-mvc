<%@ page import="com.mvc.devyu.domain.member.MemberRepository" %>
<%@ page import="com.mvc.devyu.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // request, response 는 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member savedMember = memberRepository.save(new Member(username, age));
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=savedMember.getId()%></li>
    <li>username=<%=savedMember.getUsername()%></li>
    <li>age=<%=savedMember.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
