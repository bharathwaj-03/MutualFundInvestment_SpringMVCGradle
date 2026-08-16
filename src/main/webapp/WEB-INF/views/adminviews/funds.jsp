<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <title>All Mutual Funds</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;

            background:
                    linear-gradient(
                            135deg,
                            #e8f5e9,
                            #f1f8e9,
                            #ffffff
                    );

            min-height: 100vh;
        }


        .container {
            width: 94%;
            max-width: 1400px;
            margin: 35px auto;
        }


        .header {
            background:
                    linear-gradient(
                            135deg,
                            #1b5e20,
                            #2e7d32,
                            #43a047
                    );

            color: white;

            padding: 28px;

            border-radius: 16px;

            box-shadow:
                    0 8px 25px
                    rgba(0, 0, 0, 0.15);

            margin-bottom: 25px;
        }


        .header h1 {
            margin: 0 0 8px 0;
        }


        .header p {
            margin: 5px 0;
            opacity: 0.95;
        }


        .top-buttons {
            margin-top: 20px;

            display: flex;
            gap: 12px;
        }


        .button {
            display: inline-block;

            padding: 11px 18px;

            text-decoration: none;

            border-radius: 8px;

            font-weight: bold;

            color: white;

            background: #1565c0;
        }


        .button.add {
            background: #2e7d32;
        }


        .button.dashboard {
            background: #455a64;
        }


        .table-container {
            background: white;

            border-radius: 16px;

            padding: 20px;

            box-shadow:
                    0 5px 20px
                    rgba(0, 0, 0, 0.10);

            overflow-x: auto;
        }

        .success {
            background: #dcfce7;
            color: #166534;

            padding: 14px 18px;

            border-radius: 8px;

            margin-bottom: 20px;

            font-weight: bold;
        }


        table {
            width: 100%;

            border-collapse: collapse;

            min-width: 1200px;
        }


        th {
            background:
                    linear-gradient(
                            135deg,
                            #2e7d32,
                            #43a047
                    );

            color: white;

            padding: 14px;

            text-align: left;

            white-space: nowrap;
        }


        td {
            padding: 13px;

            border-bottom:
                    1px solid #eeeeee;

            color: #333;

            white-space: nowrap;
        }


        tr:hover td {
            background: #f1f8e9;
        }


        .category {
            font-weight: bold;
        }


        .risk {
            font-weight: bold;
        }


        .empty {
            text-align: center;

            padding: 50px;

            background: white;

            border-radius: 16px;

            box-shadow:
                    0 5px 20px
                    rgba(0, 0, 0, 0.10);
        }


        .error {
            background: #ffebee;

            color: #c62828;

            padding: 15px;

            border-radius: 8px;

            margin-bottom: 20px;

            font-weight: bold;
        }

    </style>

</head>


<body>

<div class="container">


    <!-- ===================================================== -->
    <!-- HEADER -->
    <!-- ===================================================== -->

    <div class="header">

        <h1>Mutual Fund Management</h1>

        <p>
            View all mutual funds available in the system.
        </p>


        <div class="top-buttons">

            <a
                    href="${pageContext.request.contextPath}/admin/funds/add"
                    class="button add">

                + Add New Fund

            </a>


            <a
                    href="${pageContext.request.contextPath}/admin/dashboard"
                    class="button dashboard">

                Back to Dashboard

            </a>

        </div>

    </div>


    <!-- ===================================================== -->
    <!-- ERROR -->
    <!-- ===================================================== -->

    <c:if test="${not empty error}">

        <div class="error">

            ${error}

        </div>

    </c:if>


    <!-- ===================================================== -->
    <!-- FUNDS -->
    <!-- ===================================================== -->

    <c:choose>

        <c:when test="${empty funds}">

            <div class="empty">

                <h2>No Mutual Funds Found</h2>

                <p>
                    There are currently no mutual funds
                    available in the system.
                </p>

                <a
                        href="${pageContext.request.contextPath}/admin/funds/add"
                        class="button add">

                    Add First Fund

                </a>

            </div>

        </c:when>


        <c:otherwise>

            <div class="table-container">

                <table>

                    <thead>

                    <tr>

                        <th>Fund ID</th>

                        <th>Fund Code</th>

                        <th>Fund Name</th>

                        <th>Category</th>

                        <th>Fund House</th>

                        <th>NAV</th>

                        <th>Minimum Investment</th>

                        <th>SIP Gain / Year</th>

                        <th>Lump Sum Gain / Year</th>

                        <th>Risk Level</th>

                    </tr>

                    </thead>


                    <tbody>

                    <c:forEach
                            var="fund"
                            items="${funds}">

                        <tr>

                            <td>
                                ${fund.fundId}
                            </td>

                            <td>
                                ${fund.fundCode}
                            </td>

                            <td>
                                <strong>
                                    ${fund.fundName}
                                </strong>
                            </td>

                            <td class="category">
                                ${fund.fundCategory}
                            </td>

                            <td>
                                ${fund.fundHouse}
                            </td>

                            <td>
                                ₹${fund.nav}
                            </td>

                            <td>
                                ₹${fund.minimumInvestment}
                            </td>

                            <td>
                                ${fund.sipGainPerYear}%
                            </td>

                            <td>
                                ${fund.lumpSumGainPerYear}%
                            </td>

                            <td class="risk">
                                ${fund.riskLevel}
                            </td>

                        </tr>

                    </c:forEach>

                    </tbody>
                    <c:if test="${not empty success}">

                        <div class="success">

                            ${success}

                        </div>

                    </c:if>

                </table>

            </div>

        </c:otherwise>

    </c:choose>

</div>

</body>

</html>