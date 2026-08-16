<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <title>NAV History</title>

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
                    #334155
            );
            padding: 40px;
        }

        .container {
            max-width: 1100px;
            margin: auto;
            background: white;
            padding: 35px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.3);
        }

        h1 {
            color: #1e293b;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }

        th {
            background: #1e293b;
            color: white;
            padding: 13px;
            text-align: left;
        }

        td {
            padding: 13px;
            border-bottom: 1px solid #e2e8f0;
        }

        .up {
            color: #15803d;
            font-weight: bold;
        }

        .down {
            color: #dc2626;
            font-weight: bold;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
            padding: 12px;
            border-radius: 8px;
        }

        .back {
            display: inline-block;
            margin-top: 25px;
            text-decoration: none;
            color: #2563eb;
            font-weight: bold;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>NAV History</h1>


    <c:if test="${not empty error}">

        <div class="error">
            ${error}
        </div>

    </c:if>


    <c:choose>

        <c:when test="${empty history}">

            <p>
                No NAV history available.
            </p>

        </c:when>


        <c:otherwise>

            <table>

                <thead>

                <tr>

                    <th>Fund</th>
                    <th>Old NAV</th>
                    <th>New NAV</th>
                    <th>Change</th>
                    <th>Date</th>
                    <th>Changed By</th>

                </tr>

                </thead>


                <tbody>

                <c:forEach
                        var="record"
                        items="${history}">

                    <tr>

                        <td>

                            <strong>
                                ${record.mutualFund.fundName}
                            </strong>

                            <br>

                            ${record.mutualFund.fundCode}

                        </td>

                        <td>
                            ₹${record.oldNav}
                        </td>

                        <td>
                            ₹${record.newNav}
                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${record.newNav > record.oldNav}">

                                    <span class="up">
                                        ↑ Increased
                                    </span>

                                </c:when>

                                <c:otherwise>

                                    <span class="down">
                                        ↓ Decreased
                                    </span>

                                </c:otherwise>

                            </c:choose>

                        </td>

                        <td>
                            ${record.changeDate}
                        </td>

                        <td>
                            ${record.changedBy}
                        </td>

                    </tr>

                </c:forEach>

                </tbody>

            </table>

        </c:otherwise>

    </c:choose>


    <a class="back"
       href="${pageContext.request.contextPath}/mutualfund/admin">

        ← Back to Mutual Fund Management

    </a>

</div>

</body>

</html>