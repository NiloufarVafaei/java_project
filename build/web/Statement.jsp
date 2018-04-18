<%-- 
    Document   : Statement
    Created on : Apr 8, 2018, 10:19:23 AM
    Author     : niloufar
--%>


<%@page import="Entity.TokenEntity"%>
<%@page import="Entity.TransactionsStatementEntity"%>
<%@page import="Entity.OperationCodeEntity"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.StatementEntity"%>
<%@page import="WebServlet.TokenServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        form {border: 3px solid white;}

        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
        }
        img.avatar {
            width:fit-content;
            border-radius: 50%;
        }
        .container {
            padding: 16px;
            font-size: medium;
        }
        span.psw {
            float: right;
            padding-top: 16px;
        }
        .main{
            padding-top: 60px;
            margin: auto;
            margin-left: 10px;
            margin-right: 30%;
            height: 300%;
            width: 50%; 
        }
        .col{
            margin-top: 12px;
            margin-bottom: 12px;
            padding: 10px;
            background-color:darkseagreen;
            font-size: large;
            color: #f1f1f1;
        }
        .customers  {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        .customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        .customers tr:nth-child(even){background-color: #f2f2f2;}
        //.customers tr:hover {background-color: #ddd;}
       .hoverShow tr:hover {background-color: #ddd;}
        .customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            padding-left: 10px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }
        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }
            .cancelbtn {
                width: 100%;
            }
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Statement</title>
</head>
<body>
    <table class="customers">
        <tr>
            <th><b>Name</b></th>
            <th><b>Family</b></th>
            <th><b>Shaba</b></th>
            <th><b>Branch Name</b></th>
        </tr>
        <%
            List<StatementEntity> data = (List<StatementEntity>) request.getAttribute("Info");
            for (int i = 0; i < data.size(); i++) {%>

        <tr>
            <td>
                <%=data.get(i).customerName%>
            </td>
            <td>
                <%=data.get(i).customerFamilyName%>    
            </td>
            <td>
                <%=data.get(i).shaba%>    
            </td>
            <td>
                <%=data.get(i).branchName%>    
            </td>
        </tr>
        <tr>
            <td>
                <table  class="customers">
                    <tr>
                        <td>Transaction no</td>
                        <td>Message</td>
                        <td>Balance</td>
                    </tr>
                    <%List<TransactionsStatementEntity> Trans = (List<TransactionsStatementEntity>) data.get(i).transactions;
                        for (int j = 0; j < Trans.size(); j++) {%>
                    <tr>
                        <td class="hoverShow">
                            <%=Trans.get(j).recordNumber%>
                        </td>
                        <td class="hoverShow">
                            <%=Trans.get(j).operationCode.message%>  
                        </td>
                        <td class="hoverShow">
                            <%=Trans.get(j).balance%> 
                        </td>
                    </tr>
                    <%  }%>
                </table>
            </td>
        </tr>
        <% }%>

    </table>
</body>
</html>
