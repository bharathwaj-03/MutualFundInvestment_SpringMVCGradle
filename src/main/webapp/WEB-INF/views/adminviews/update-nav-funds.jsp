<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <title>Update NAV</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {

            margin: 0;

            font-family: Arial, sans-serif;

            min-height: 100vh;

            background:
                linear-gradient(
                    135deg,
                    #dff7e8,
                    #b7ebc6,
                    #8ed8a5
                );

            padding: 40px;
        }


        .container {

            width: 95%;

            max-width: 1200px;

            margin: auto;
        }


        .header {

            background: rgba(255,255,255,0.95);

            padding: 28px;

            border-radius: 16px;

            margin-bottom: 25px;

            box-shadow:
                0 8px 25px
                rgba(0,0,0,0.12);
        }


        .header h1 {

            margin: 0 0 10px 0;

            color: #14532d;
        }


        .header p {

            margin: 6px 0;

            color: #555;
        }


        .instruction {

            background: #ecfdf3;

            border-left: 5px solid #16a34a;

            padding: 16px 20px;

            border-radius: 8px;

            margin-top: 18px;

            color: #166534;

            font-weight: bold;
        }


        .error {

            background: #ffe5e5;

            color: #b91c1c;

            padding: 15px;

            border-radius: 8px;

            margin-bottom: 20px;
        }


        .success {

            background: #dcfce7;

            color: #166534;

            padding: 15px;

            border-radius: 8px;

            margin-bottom: 20px;
        }


        .table-container {

            background: rgba(255,255,255,0.96);

            padding: 20px;

            border-radius: 16px;

            box-shadow:
                0 8px 25px
                rgba(0,0,0,0.12);

            overflow-x: auto;
        }


        table {

            width: 100%;

            border-collapse: collapse;
        }


        th {

            background: #166534;

            color: white;

            padding: 15px;

            text-align: left;
        }


        td {

            padding: 15px;

            border-bottom:
                1px solid #e5e7eb;
        }


        tbody tr {

            cursor: pointer;

            transition:
                background 0.2s,
                transform 0.1s;
        }


        tbody tr:hover {

            background: #ecfdf3;

            transform: scale(1.005);
        }


        .nav-value {

            font-weight: bold;

            color: #166534;
        }


        .click-text {

            color: #15803d;

            font-weight: bold;
        }


        .back-button {

            display: inline-block;

            margin-top: 25px;

            padding: 12px 22px;

            text-decoration: none;

            color: white;

            background: #374151;

            border-radius: 8px;

            font-weight: bold;
        }


        .back-button:hover {

            background: #1f2937;
        }

    </style>

</head>


<body>

<div class="container">


    <!-- ===================================================== -->
    <!-- HEADER -->
    <!-- ===================================================== -->

    <div class="header">

        <h1>Update Mutual Fund NAV</h1>

        <p>
            Admin ID:
            <strong>${adminId}</strong>
        </p>

        <div class="instruction">

            Select a fund by clicking anywhere on its row
            to update its NAV.

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
    <!-- FUND TABLE -->
    <!-- ===================================================== -->

    <div class="table-container">

        <table>

            <thead>

            <tr>

                <th>Fund Code</th>

                <th>Fund Name</th>

                <th>Category</th>

                <th>Fund House</th>

                <th>Risk Level</th>

                <th>Current NAV</th>

                <th>Action</th>

            </tr>

            </thead>


            <tbody>

            <c:forEach
                    var="fund"
                    items="${funds}">

                <tr
                    onclick="window.location.href='${pageContext.request.contextPath}/admin/funds/update-nav/${fund.fundId}'">

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

                    <td class="nav-value">

                        ₹${fund.nav}

                    </td>

                    <td class="click-text">

                        Click to Update

                    </td>

                </tr>

            </c:forEach>

            </tbody>

        </table>

    </div>


    <!-- ===================================================== -->
    <!-- BACK -->
    <!-- ===================================================== -->

    <a
            href="${pageContext.request.contextPath}/admin/dashboard"
            class="back-button">

        ← Back to Dashboard

    </a>


</div>

</body>

</html>