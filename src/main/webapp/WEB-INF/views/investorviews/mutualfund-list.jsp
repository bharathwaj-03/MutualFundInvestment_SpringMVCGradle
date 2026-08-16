<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <title>Mutual Funds</title>

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
                    #064e3b,
                    #16a34a
            );
            padding: 40px;
        }

        .container {
            max-width: 1200px;
            margin: auto;
            background: white;
            padding: 35px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.25);
        }

        h1 {
            color: #166534;
            margin-bottom: 5px;
        }

        .subtitle {
            color: #64748b;
            margin-bottom: 25px;
        }

        .filters {
            display: flex;
            gap: 10px;
            margin-bottom: 25px;
            flex-wrap: wrap;
        }

        .filters a {
            text-decoration: none;
            padding: 10px 16px;
            border-radius: 8px;
            background: #dcfce7;
            color: #166534;
            font-weight: bold;
        }

        .filters a:hover {
            background: #bbf7d0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background: #166534;
            color: white;
            padding: 13px;
            text-align: left;
        }

        td {
            padding: 13px;
            border-bottom: 1px solid #e2e8f0;
        }

        tr:hover {
            background: #f0fdf4;
        }

        .invest {
            text-decoration: none;
            background: #16a34a;
            color: white;
            padding: 8px 13px;
            border-radius: 7px;
            font-weight: bold;
        }

        .invest:hover {
            background: #15803d;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>Available Mutual Funds</h1>

    <p class="subtitle">
        Explore funds and choose where you want to invest.
    </p>


    <c:if test="${not empty error}">

        <div class="error">
            ${error}
        </div>

    </c:if>


    <div class="filters">

        <a href="${pageContext.request.contextPath}/mutualfund/investor">
            All Funds
        </a>

        <a href="${pageContext.request.contextPath}/mutualfund/investor/category?category=Debt%20Fund">
            Debt
        </a>

        <a href="${pageContext.request.contextPath}/mutualfund/investor/category?category=Equity%20Fund">
            Equity
        </a>

        <a href="${pageContext.request.contextPath}/mutualfund/investor/category?category=Hybrid%20Fund">
            Hybrid
        </a>

    </div>


    <table>

        <thead>

        <tr>

            <th>Fund Code</th>
            <th>Fund Name</th>
            <th>Category</th>
            <th>Fund House</th>
            <th>Risk</th>
            <th>NAV</th>
            <th>Minimum</th>
            <th>Action</th>

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
                    <strong>
                        ${fund.fundName}
                    </strong>
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

                    <a class="invest"
                       href="${pageContext.request.contextPath}/investor/investment?fundId=${fund.fundId}">

                        Start Investment

                    </a>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

</body>

</html>