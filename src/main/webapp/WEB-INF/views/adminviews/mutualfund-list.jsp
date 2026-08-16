<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <title>Mutual Fund Management</title>

    <style>

        * {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            margin: 0;
            min-height: 100vh;
            background: linear-gradient(
                    135deg,
                    #0f172a,
                    #1e3a8a
            );
            padding: 40px;
        }

        .container {
            max-width: 1300px;
            margin: auto;
            background: white;
            padding: 35px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.3);
        }

        h1 {
            color: #1e3a8a;
        }

        .top-actions {
            margin: 25px 0;
            display: flex;
            gap: 12px;
        }

        .button {
            text-decoration: none;
            padding: 11px 17px;
            border-radius: 8px;
            color: white;
            background: #2563eb;
            font-weight: bold;
        }

        .history {
            background: #475569;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background: #1e3a8a;
            color: white;
            padding: 13px;
            text-align: left;
        }

        td {
            padding: 12px;
            border-bottom: 1px solid #e2e8f0;
        }

        tr:hover {
            background: #f8fafc;
        }

        .edit {
            color: #2563eb;
            font-weight: bold;
            text-decoration: none;
        }

        .nav {
            color: #15803d;
            font-weight: bold;
            text-decoration: none;
        }

        .delete {
            display: inline;
        }

        .delete button {
            border: none;
            background: #dc2626;
            color: white;
            padding: 7px 11px;
            border-radius: 6px;
            cursor: pointer;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>Mutual Fund Management</h1>


    <c:if test="${not empty error}">

        <div class="error">
            ${error}
        </div>

    </c:if>


    <div class="top-actions">

        <a class="button"
           href="${pageContext.request.contextPath}/mutualfund/admin/add">

            + Add Mutual Fund

        </a>


        <a class="button history"
           href="${pageContext.request.contextPath}/mutualfund/admin/nav-history">

            NAV History

        </a>

    </div>


    <table>

        <thead>

        <tr>

            <th>Code</th>
            <th>Name</th>
            <th>Category</th>
            <th>House</th>
            <th>Risk</th>
            <th>NAV</th>
            <th>Minimum</th>
            <th>Actions</th>

        </tr>

        </thead>


        <tbody>

        <c:forEach
                var="fund"
                items="${funds}">

            <tr>

                <td>
                    ${fund.fundCode}
                </td>

                <td>
                    ${fund.fundName}
                </td>

                <td>
                    ${fund.fundCategory}
                </td>

                <td>
                    ${fund.fundHouse}
                </td>

                <td>
                    ${fund.riskLevel}
                </td>

                <td>
                    ₹${fund.nav}
                </td>

                <td>
                    ₹${fund.minimumInvestment}
                </td>

                <td>

                    <a class="edit"
                       href="${pageContext.request.contextPath}/mutualfund/admin/edit/${fund.fundId}">

                        Edit

                    </a>

                    &nbsp;|&nbsp;

                    <a class="nav"
                       href="${pageContext.request.contextPath}/mutualfund/admin/nav/${fund.fundId}">

                        Update NAV

                    </a>

                    &nbsp;|&nbsp;

                    <form class="delete"
                          method="post"
                          action="${pageContext.request.contextPath}/mutualfund/admin/delete/${fund.fundId}">

                        <button type="submit">
                            Delete
                        </button>

                    </form>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

</body>

</html>