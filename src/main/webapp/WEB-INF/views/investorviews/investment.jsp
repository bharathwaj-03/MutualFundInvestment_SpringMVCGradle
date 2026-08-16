<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>

    <title>Start Investment</title>

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
            max-width: 800px;
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

        .subtitle {
            text-align: center;
            color: #64748b;
            margin-bottom: 30px;
        }

        .field {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 7px;
        }

        input,
        select {
            width: 100%;
            padding: 12px;
            border: 1px solid #cbd5e1;
            border-radius: 8px;
            font-size: 14px;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .payment-section {
            padding: 20px;
            background: #f0fdf4;
            border-radius: 10px;
            margin-top: 20px;
        }

        .submit {
            width: 100%;
            padding: 15px;
            margin-top: 20px;
            background: #16a34a;
            color: white;
            border: none;
            border-radius: 9px;
            font-size: 17px;
            font-weight: bold;
            cursor: pointer;
        }

        .submit:hover {
            background: #15803d;
        }

        .payment-fields {
            display: none;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #475569;
            text-decoration: none;
        }
        .field-error {
            color: #dc2626;
            font-size: 13px;
            margin-top: 6px;
        }

    </style>

</head>


<body>

<div class="container">

    <h1>Start Investment</h1>

    <p class="subtitle">
        Start your lump-sum mutual fund investment
    </p>


    <c:if test="${not empty error}">

        <div class="error">
            ${error}
        </div>

    </c:if>


    <form method="post"
          action="${pageContext.request.contextPath}/investor/investment">


        <!-- ================================================= -->
        <!-- MUTUAL FUND -->
        <!-- ================================================= -->

        <div class="field">

            <label>Mutual Fund</label>

            <select name="fundId" required>

                <option value="">
                    Select Mutual Fund
                </option>

                <c:forEach var="fund"
                           items="${funds}">

                    <option value="${fund.fundId}">

                        ${fund.fundName}
                        -
                        ₹${fund.nav}
                        -
                        Minimum ₹${fund.minimumInvestment}

                    </option>

                </c:forEach>

            </select>

        </div>


        <!-- ================================================= -->
        <!-- AMOUNT -->
        <!-- ================================================= -->

    <div class="field">

        <label>Investment Amount</label>

        <input type="number"
               name="amount"
               min="1"
               step="0.01"
               value="${amount}"
               placeholder="Enter investment amount"
               required>

        <c:if test="${not empty amountError}">
            <div class="field-error">
                ${amountError}
            </div>
        </c:if>

    </div>


        <!-- ================================================= -->
        <!-- YEARS -->
        <!-- ================================================= -->

        <div class="field">

            <label>Investment Period</label>

            <select name="investmentYears"
                    required>

                <option value="">
                    Select period
                </option>

                <option value="1">
                    1 Year
                </option>

                <option value="2">
                    2 Years
                </option>

                <option value="3">
                    3 Years
                </option>

                <option value="4">
                    4 Years
                </option>

                <option value="5">
                    5 Years
                </option>

            </select>

        </div>


        <!-- ================================================= -->
        <!-- PAYMENT -->
        <!-- ================================================= -->

        <div class="payment-section">

            <h3>Payment Details</h3>


            <div class="field">

                <label>Payment Method</label>

                <select name="paymentType"
                        id="paymentType"
                        onchange="showPaymentFields()"
                        required>

                    <option value="">
                        Select Payment Method
                    </option>

                    <option value="UPI">
                        UPI
                    </option>

                    <option value="CARD">
                        Card
                    </option>

                    <option value="BANK">
                        Bank Transfer
                    </option>

                </select>

            </div>


            <!-- UPI -->

            <div id="upiFields"
                 class="payment-fields">

                <div class="field">

                    <label>UPI ID</label>

                    <input type="text"
                           name="upiId"
                           placeholder="example@upi">

                </div>

            </div>


            <!-- CARD -->

            <div id="cardFields"
                 class="payment-fields">

                <div class="field">

                    <label>Card Holder Name</label>

                    <input type="text"
                           name="cardHolderName">

                </div>

                <div class="field">

                    <label>Card Number</label>

                    <input type="text"
                           name="cardNumber"
                           maxlength="16">

                </div>

            </div>


            <!-- BANK -->

            <div id="bankFields"
                 class="payment-fields">

                <div class="field">

                    <label>Bank Name</label>

                    <input type="text"
                           name="bankName">

                </div>

                <div class="field">

                    <label>Account Number</label>

                    <input type="text"
                           name="accountNumber">

                </div>

            </div>

        </div>


        <button type="submit"
                class="submit">

            Invest Now

        </button>

    </form>


    <a class="back"
       href="${pageContext.request.contextPath}/investor/portfolio">

        ← Back to Portfolio

    </a>

</div>


<script>

    function showPaymentFields() {

        document.getElementById(
            "upiFields"
        ).style.display = "none";

        document.getElementById(
            "cardFields"
        ).style.display = "none";

        document.getElementById(
            "bankFields"
        ).style.display = "none";


        const type =
            document.getElementById(
                "paymentType"
            ).value;


        if (type === "UPI") {

            document.getElementById(
                "upiFields"
            ).style.display = "block";

        }

        else if (type === "CARD") {

            document.getElementById(
                "cardFields"
            ).style.display = "block";

        }

        else if (type === "BANK") {

            document.getElementById(
                "bankFields"
            ).style.display = "block";
        }
    }

</script>

</body>

</html>