<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>My Portfolio</title>


    <style>

        * {
            box-sizing: border-box;
        }


        body {

            margin: 0;

            padding: 0;

            font-family:
                    Arial,
                    Helvetica,
                    sans-serif;

            min-height: 100vh;

            background:
                    linear-gradient(
                            135deg,
                            #e8f5e9,
                            #c8e6c9,
                            #a5d6a7
                    );

            color: #1b3a2a;
        }


        .container {

            width: 92%;

            max-width: 1200px;

            margin: 40px auto;

        }


        /* ===================================================== */
        /* HEADER */
        /* ===================================================== */

        .header {

            background: rgba(255, 255, 255, 0.94);

            padding: 28px;

            border-radius: 16px;

            margin-bottom: 25px;

            box-shadow:
                    0 8px 25px
                    rgba(27, 94, 32, 0.12);

            border:
                    1px solid
                    rgba(46, 125, 50, 0.12);

        }


        .header h1 {

            margin-top: 0;

            margin-bottom: 18px;

            color: #1b5e20;

            font-size: 30px;

        }


        .header p {

            margin: 8px 0;

            color: #45624d;

        }


        .header strong {

            color: #1b5e20;

        }


        /* ===================================================== */
        /* ERROR */
        /* ===================================================== */

        .error {

            background: #ffebee;

            color: #b71c1c;

            padding: 15px 18px;

            border-radius: 10px;

            margin-bottom: 20px;

            border-left:
                    5px solid
                    #d32f2f;

        }


        /* ===================================================== */
        /* SUMMARY CARDS */
        /* ===================================================== */

        .summary {

            display: flex;

            gap: 20px;

            margin-bottom: 25px;

            flex-wrap: wrap;

        }


        .card {

            flex: 1;

            min-width: 220px;

            background:
                    rgba(255, 255, 255, 0.95);

            padding: 23px;

            border-radius: 15px;

            box-shadow:
                    0 6px 20px
                    rgba(27, 94, 32, 0.10);

            border:
                    1px solid
                    rgba(46, 125, 50, 0.10);

        }


        .card h3 {

            margin-top: 0;

            margin-bottom: 12px;

            color: #4f6656;

            font-size: 16px;

            font-weight: 600;

        }


        .amount {

            font-size: 25px;

            font-weight: bold;

            color: #1b5e20;

        }


        .gain {

            color: #2e7d32;

        }


        /* ===================================================== */
        /* EMPTY PORTFOLIO */
        /* ===================================================== */

        .empty {

            background:
                    rgba(255, 255, 255, 0.95);

            padding: 50px 30px;

            text-align: center;

            border-radius: 15px;

            box-shadow:
                    0 6px 20px
                    rgba(27, 94, 32, 0.10);

        }


        .empty h2 {

            color: #1b5e20;

            margin-bottom: 12px;

        }


        .empty p {

            color: #607568;

        }


        /* ===================================================== */
        /* HOLDINGS TABLE */
        /* ===================================================== */

        .table-container {

            width: 100%;

            overflow-x: auto;

            border-radius: 15px;

            box-shadow:
                    0 6px 20px
                    rgba(27, 94, 32, 0.10);

        }


        .portfolio-table {

            width: 100%;

            min-width: 1000px;

            border-collapse: collapse;

            background:
                    rgba(255, 255, 255, 0.96);

        }


        .portfolio-table th {

            padding: 15px;

            text-align: left;

            background: #2e7d32;

            color: white;

            font-size: 14px;

            white-space: nowrap;

        }


        .portfolio-table td {

            padding: 14px 15px;

            text-align: left;

            border-bottom:
                    1px solid
                    #e0ebe2;

            color: #405548;

            white-space: nowrap;

        }


        .portfolio-table tbody tr:hover {

            background: #f1f8f2;

        }


        .portfolio-table tbody tr:last-child td {

            border-bottom: none;

        }


        /* ===================================================== */
        /* BUTTONS */
        /* ===================================================== */

        .buttons {

            margin-top: 28px;

            display: flex;

            gap: 15px;

            flex-wrap: wrap;

        }


        .button {

            display: inline-block;

            padding: 13px 22px;

            text-decoration: none;

            border-radius: 8px;

            background:
                    linear-gradient(
                            135deg,
                            #388e3c,
                            #1b5e20
                    );

            color: white;

            font-weight: 600;

            box-shadow:
                    0 4px 12px
                    rgba(27, 94, 32, 0.20);

            transition:
                    transform 0.2s ease,
                    box-shadow 0.2s ease;

        }


        .button:hover {

            transform: translateY(-2px);

            box-shadow:
                    0 6px 16px
                    rgba(27, 94, 32, 0.25);

        }


        .button.secondary {

            background:
                    linear-gradient(
                            135deg,
                            #66bb6a,
                            #388e3c
                    );

        }


        /* ===================================================== */
        /* RESPONSIVE */
        /* ===================================================== */

        @media (max-width: 700px) {

            .container {

                width: 95%;

                margin: 20px auto;

            }


            .header {

                padding: 20px;

            }


            .header h1 {

                font-size: 25px;

            }


            .summary {

                flex-direction: column;

            }


            .card {

                min-width: 100%;

            }


            .buttons {

                flex-direction: column;

            }


            .button {

                text-align: center;

            }

        }

    </style>

</head>


<body>


<div class="container">


    <div class="header">

        <h1>My Portfolio</h1>

        <p>

            Welcome,

            <strong>
                ${investor.name}
            </strong>

        </p>


        <p>

            Investor ID:

            <strong>
                ${investor.userId}
            </strong>

        </p>


        <p>

            Portfolio ID:

            <strong>
                ${portfolio.portfolioId}
            </strong>

        </p>


        <p>

            Last Activity:

            <strong>
                ${portfolio.lastActivityDate}
            </strong>

        </p>

    </div>



    <c:if test="${not empty error}">

        <div class="error">

            ${error}

        </div>

    </c:if>



    <!-- ===================================================== -->
    <!-- PORTFOLIO SUMMARY -->
    <!-- ===================================================== -->

    <div class="summary">


        <div class="card">

            <h3>Total Invested</h3>

            <div class="amount">

                ₹${totalInvested}

            </div>

        </div>


        <div class="card">

            <h3>Current Portfolio Value</h3>

            <div class="amount">

                ₹${currentPortfolioValue}

            </div>

        </div>


        <div class="card">

            <h3>Total Gain / Loss</h3>

            <div class="amount gain">

                ₹${totalGain}

            </div>

        </div>


    </div>



    <!-- ===================================================== -->
    <!-- HOLDINGS -->
    <!-- ===================================================== -->

    <c:choose>


        <c:when test="${empty holdings}">


            <div class="empty">

                <h2>Your Portfolio is Empty</h2>

                <p>
                    You haven't made any investments yet.
                </p>

                <p>
                    Start your first mutual fund investment
                    to create your holdings.
                </p>

            </div>


        </c:when>


        <c:otherwise>


            <div class="table-container">

                <table class="portfolio-table">


                    <thead>

                    <tr>

                        <th>Holding ID</th>

                        <th>Fund</th>

                        <th>Fund Code</th>

                        <th>Category</th>

                        <th>Units Owned</th>

                        <th>Invested Amount</th>

                        <th>Average NAV</th>

                        <th>Current NAV</th>

                        <th>Current Value</th>

                    </tr>

                    </thead>


                    <tbody>


                    <c:forEach
                            var="holding"
                            items="${holdings}">


                        <tr>


                            <td>
                                ${holding.holdingId}
                            </td>


                            <td>
                                ${holding.mutualFund.fundName}
                            </td>


                            <td>
                                ${holding.mutualFund.fundCode}
                            </td>


                            <td>
                                ${holding.mutualFund.fundCategory}
                            </td>


                            <td>
                                ${holding.unitsOwned}
                            </td>


                            <td>
                                ₹${holding.investedAmount}
                            </td>


                            <td>
                                ₹${holding.averageNav}
                            </td>


                            <td>
                                ₹${holding.mutualFund.nav}
                            </td>


                            <td>
                                ₹${holding.unitsOwned * holding.mutualFund.nav}
                            </td>


                        </tr>


                    </c:forEach>


                    </tbody>


                </table>

            </div>


        </c:otherwise>


    </c:choose>



    <!-- ===================================================== -->
    <!-- BUTTONS -->
    <!-- ===================================================== -->

    <div class="buttons">


        <a
                href="${pageContext.request.contextPath}/investor/investment"
                class="button">

            Make Another Investment

        </a>


       <a
           href="${pageContext.request.contextPath}/userlogin/investor"
           class="button secondary">

           Back to Dashboard

       </a>


    </div>


</div>


</body>

</html>