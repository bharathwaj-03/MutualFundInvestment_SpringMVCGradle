<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <title>SIP Created Successfully</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {

            margin: 0;

            font-family: Arial, sans-serif;

            min-height: 100vh;

          background: linear-gradient(135deg, #064e3b, #16a34a);

            display: flex;

            justify-content: center;

            align-items: center;

            padding: 30px;
        }


        .container {

            background: white;

            width: 100%;

            max-width: 650px;

            padding: 40px;

            border-radius: 18px;

            box-shadow:
                    0 15px 40px
                    rgba(0, 0, 0, 0.20);

            text-align: center;
        }


        .success-icon {

            width: 70px;

            height: 70px;

            margin:
                    0 auto 20px;

            border-radius: 50%;

            background: #dcfce7;

            color: #16a34a;

            display: flex;

            align-items: center;

            justify-content: center;

            font-size: 38px;

            font-weight: bold;
        }


        h1 {

            color: #14532d;

            margin-bottom: 10px;
        }


        .subtitle {

            color: #666;

            margin-bottom: 30px;
        }


        .details {

            text-align: left;

            background: #f0fdf4;

            border:
                    1px solid #bbf7d0;

            border-radius: 12px;

            padding: 20px;

            margin-bottom: 25px;
        }


        .row {

            display: flex;

            justify-content: space-between;

            padding: 10px 0;

            border-bottom:
                    1px solid #dcfce7;
        }


        .row:last-child {

            border-bottom: none;
        }


        .label {

            color: #666;

            font-weight: bold;
        }


        .value {

            color: #14532d;

            font-weight: bold;

            text-align: right;
        }


        .buttons {

            display: flex;

            gap: 12px;

            margin-top: 20px;
        }


        .button {

            flex: 1;

            padding: 13px;

            border-radius: 8px;

            text-decoration: none;

            font-weight: bold;

            color: white;

            background:
                    linear-gradient(
                            135deg,
                            #16a34a,
                            #22c55e
                    );
        }


        .button.secondary {

            background: #166534;
        }

    </style>

</head>


<body>

<div class="container">


    <div class="success-icon">
        ✓
    </div>


    <h1>
        SIP Created Successfully
    </h1>


    <p class="subtitle">
        Your SIP investment has been created successfully.
    </p>


    <div class="details">


        <div class="row">

            <span class="label">
                SIP ID
            </span>

            <span class="value">
                ${sip.sipId}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Fund
            </span>

            <span class="value">
                ${sip.mutualFund.fundName}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Fund Code
            </span>

            <span class="value">
                ${sip.mutualFund.fundCode}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Monthly Amount
            </span>

            <span class="value">
                ₹${sip.monthlyAmount}
            </span>

        </div>


        <div class="row">

            <span class="label">
                First Installment Units
            </span>

            <span class="value">
                ${sip.unitsPurchased}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Investment Period
            </span>

            <span class="value">
                ${sip.investmentYears} Years
            </span>

        </div>


        <div class="row">

            <span class="label">
                SIP Start Date
            </span>

            <span class="value">
                ${sip.startDate}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Next Installment
            </span>

            <span class="value">
                ${sip.nextInstallmentDate}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Expected Annual Gain
            </span>

            <span class="value">
                ₹${sip.assetGainPerYear}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Expected Total Gain
            </span>

            <span class="value">
                ₹${sip.assetGainTotalInvestedYears}
            </span>

        </div>


        <div class="row">

            <span class="label">
                Status
            </span>

            <span class="value">
                ${sip.sipStatus}
            </span>

        </div>


    </div>


    <div class="buttons">


        <a
                href="${pageContext.request.contextPath}/investor/portfolio"
                class="button">

            View Portfolio

        </a>


        <a
                href="${pageContext.request.contextPath}/investor/sip"
                class="button secondary">

            Start Another SIP

        </a>

    </div>


</div>

</body>

</html>