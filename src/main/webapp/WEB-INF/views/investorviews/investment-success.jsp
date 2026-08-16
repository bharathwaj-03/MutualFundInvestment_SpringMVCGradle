<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <title>Investment Successful</title>

    <style>

        * {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            background: linear-gradient(
                    135deg,
                    #064e3b,
                    #16a34a
            );
            padding: 40px 20px;
        }

        .container {
            max-width: 700px;
            margin: auto;
            background: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.25);
        }

        h1 {
            text-align: center;
            color: #166534;
        }

        .success {
            text-align: center;
            color: #15803d;
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 30px;
        }

        .details {
            background: #f0fdf4;
            padding: 25px;
            border-radius: 12px;
        }

        .row {
            display: flex;
            justify-content: space-between;
            padding: 12px 0;
            border-bottom: 1px solid #dcfce7;
        }

        .row:last-child {
            border-bottom: none;
        }

        .label {
            font-weight: bold;
        }

        .buttons {
            margin-top: 30px;
            text-align: center;
        }

        .button {
            display: inline-block;
            padding: 12px 20px;
            margin: 5px;
            background: #16a34a;
            color: white;
            text-decoration: none;
            border-radius: 8px;
        }

    </style>

</head>


<body>

<div class="container">

    <h1>✓ Investment Successful</h1>

    <div class="success">
        Your lump-sum investment has been created successfully.
    </div>


    <div class="details">

        <div class="row">

            <span class="label">
                Investment ID
            </span>

            <span>
                ${investment.investmentId}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Fund
            </span>

            <span>
                ${investment.mutualFund.fundName}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Fund Code
            </span>

            <span>
                ${investment.mutualFund.fundCode}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Amount
            </span>

            <span>
                ₹${investment.amount}
            </span>

        </div>


        <div class="row">

            <span class="label">
                NAV
            </span>

            <span>
                ₹${investment.mutualFund.nav}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Units Purchased
            </span>

            <span>
                ${investment.unitsPurchased}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Investment Period
            </span>

            <span>
                ${investment.investmentYears} Years
            </span>

        </div>


        <div class="row">

            <span class="label">
                Expected Annual Gain
            </span>

            <span>
                ₹${investment.assetGainPerYear}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Expected Total Gain
            </span>

            <span>
                ₹${investment.assetGainTotalInvestedYears}
            </span>

        </div>

    </div>


    <div class="buttons">

        <a class="button"
           href="${pageContext.request.contextPath}/investor/portfolio">

            View Portfolio

        </a>

        <a class="button"
           href="${pageContext.request.contextPath}/investor/investment">

            Make Another Investment

        </a>

    </div>

</div>

</body>

</html>