<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <title>Start SIP Investment</title>

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

            padding: 40px 20px;
        }


        .container {

            width: 100%;

            max-width: 650px;

            background: white;

            border-radius: 18px;

            padding: 35px;

            box-shadow:
                    0 15px 40px
                    rgba(0, 0, 0, 0.20);
        }


        h1 {

            margin-top: 0;

            color: #14532d;

            text-align: center;
        }


        .subtitle {

            text-align: center;

            color: #666;

            margin-bottom: 30px;
        }


        .error {

            background: #ffe5e5;

            color: #c62828;

            border-left:
                    5px solid #c62828;

            padding: 12px 15px;

            border-radius: 7px;

            margin-bottom: 20px;
        }


        .success-message {

            background: #e8f5e9;

            color: #2e7d32;

            padding: 12px;

            border-radius: 7px;

            margin-bottom: 20px;
        }


        .form-group {

            margin-bottom: 20px;
        }


        label {

            display: block;

            font-weight: bold;

            margin-bottom: 8px;

            color: #333;
        }


        select,
        input {

            width: 100%;

            padding: 12px;

            border:
                    1px solid #ccc;

            border-radius: 8px;

            font-size: 15px;

            outline: none;
        }


        select:focus,
        input:focus {

            border-color: #22c55e;

            box-shadow:
                    0 0 0 3px
                    rgba(34, 197, 94, 0.15);
        }


        .field-error {

            color: #d32f2f;

            font-size: 13px;

            margin-top: 6px;
        }


        .fund-info {

            background: #f0fdf4;

            border:
                    1px solid #bbf7d0;

            padding: 15px;

            border-radius: 10px;

            margin-top: 10px;

            color: #166534;

            font-size: 14px;
        }


        .button {

            width: 100%;

            border: none;

            padding: 14px;

            border-radius: 8px;

            background:
                    linear-gradient(
                            135deg,
                            #16a34a,
                            #22c55e
                    );

            color: white;

            font-size: 16px;

            font-weight: bold;

            cursor: pointer;

            margin-top: 10px;
        }


        .button:hover {

            opacity: 0.92;
        }


        .back {

            display: block;

            text-align: center;

            margin-top: 20px;

            color: #166534;

            text-decoration: none;

            font-weight: bold;
        }


        .back:hover {

            text-decoration: underline;
        }


        .info {

            background: #f8fafc;

            border-radius: 10px;

            padding: 15px;

            margin-top: 25px;

            font-size: 13px;

            color: #555;

            line-height: 1.6;
        }

    </style>

</head>


<body>

<div class="container">

    <h1>Start SIP Investment</h1>

    <p class="subtitle">
        Start your monthly mutual fund investment
    </p>


    <!-- ===================================================== -->
    <!-- GENERAL ERROR -->
    <!-- ===================================================== -->

    <c:if test="${not empty error}">

        <div class="error">
            ${error}
        </div>

    </c:if>


    <!-- ===================================================== -->
    <!-- SIP FORM -->
    <!-- ===================================================== -->

    <form
            method="post"
            action="${pageContext.request.contextPath}/investor/sip">


        <!-- FUND -->

        <div class="form-group">

            <label>
                Select Mutual Fund
            </label>

            <select
                    name="fundId"
                    required>

                <option value="">
                    -- Select Mutual Fund --
                </option>


                <c:forEach
                        var="fund"
                        items="${funds}">

                    <option
                            value="${fund.fundId}"
                            ${selectedFund == fund.fundId
                                    ? 'selected'
                                    : ''}>

                        ${fund.fundName}
                        -
                        ${fund.fundCode}

                    </option>

                </c:forEach>

            </select>


            <c:if test="${not empty errors.fundId}">

                <div class="field-error">
                    ${errors.fundId}
                </div>

            </c:if>

        </div>


        <!-- MONTHLY AMOUNT -->

        <div class="form-group">

            <label>
                Monthly SIP Amount (₹)
            </label>

            <input
                    type="number"
                    name="monthlyAmount"
                    value="${monthlyAmount}"
                    min="1"
                    step="0.01"
                    placeholder="Enter monthly amount"
                    required
            />


            <c:if test="${not empty errors.monthlyAmount}">

                <div class="field-error">
                    ${errors.monthlyAmount}
                </div>

            </c:if>

        </div>


        <!-- INVESTMENT YEARS -->

        <div class="form-group">

            <label>
                Investment Period
            </label>

            <select
                    name="investmentYears"
                    required>

                <option value="">
                    -- Select Period --
                </option>

                <option
                        value="1"
                        ${investmentYears == 1
                                ? 'selected'
                                : ''}>
                    1 Year
                </option>

                <option
                        value="2"
                        ${investmentYears == 2
                                ? 'selected'
                                : ''}>
                    2 Years
                </option>

                <option
                        value="3"
                        ${investmentYears == 3
                                ? 'selected'
                                : ''}>
                    3 Years
                </option>

                <option
                        value="5"
                        ${investmentYears == 5
                                ? 'selected'
                                : ''}>
                    5 Years
                </option>

                <option
                        value="10"
                        ${investmentYears == 10
                                ? 'selected'
                                : ''}>
                    10 Years
                </option>

                <option
                        value="15"
                        ${investmentYears == 15
                                ? 'selected'
                                : ''}>
                    15 Years
                </option>

                <option
                        value="20"
                        ${investmentYears == 20
                                ? 'selected'
                                : ''}>
                    20 Years
                </option>

            </select>


            <c:if test="${not empty errors.investmentYears}">

                <div class="field-error">
                    ${errors.investmentYears}
                </div>

            </c:if>

        </div>


        <!-- START DATE -->

        <div class="form-group">

            <label>
                SIP Start Date
            </label>

            <input
                    type="date"
                    name="startDate"
                    value="${startDate}"
                    required
            />


            <c:if test="${not empty errors.startDate}">

                <div class="field-error">
                    ${errors.startDate}
                </div>

            </c:if>

        </div>

       <!-- ===================================================== -->
       <!-- PAYMENT METHOD -->
       <!-- ===================================================== -->

       <div class="form-group">

           <label>Payment Method</label>

           <select
                   name="paymentType"
                   id="paymentType"
                   required>

               <option value="">
                   -- Select Payment Method --
               </option>

               <option value="UPI"
                       ${paymentType == 'UPI' ? 'selected' : ''}>
                   UPI
               </option>

               <option value="CARD"
                       ${paymentType == 'CARD' ? 'selected' : ''}>
                   Card
               </option>

               <option value="BANK"
                       ${paymentType == 'BANK' ? 'selected' : ''}>
                   Bank Transfer
               </option>

           </select>

           <c:if test="${not empty errors.paymentType}">
               <div class="field-error">
                   ${errors.paymentType}
               </div>
           </c:if>

       </div>


       <!-- ===================================================== -->
       <!-- UPI DETAILS -->
       <!-- ===================================================== -->

       <div
               id="upiFields"
               class="payment-fields"
               style="display:none;">

           <div class="form-group">

               <label>UPI ID</label>

               <input
                       type="text"
                       name="upiId"
                       id="upiId"
                       value="${upiId}"
                       placeholder="example@upi"
               />

               <c:if test="${not empty errors.upiId}">
                   <div class="field-error">
                       ${errors.upiId}
                   </div>
               </c:if>

           </div>

       </div>


       <!-- ===================================================== -->
       <!-- CARD DETAILS -->
       <!-- ===================================================== -->

       <div
               id="cardFields"
               class="payment-fields"
               style="display:none;">

           <div class="form-group">

               <label>Card Number</label>

               <input
                       type="text"
                       name="cardNumber"
                       id="cardNumber"
                       value="${cardNumber}"
                       placeholder="Enter card number"
               />

               <c:if test="${not empty errors.cardNumber}">
                   <div class="field-error">
                       ${errors.cardNumber}
                   </div>
               </c:if>

           </div>


           <div class="form-group">

               <label>Card Holder Name</label>

               <input
                       type="text"
                       name="cardHolderName"
                       id="cardHolderName"
                       value="${cardHolderName}"
                       placeholder="Enter card holder name"
               />

               <c:if test="${not empty errors.cardHolderName}">
                   <div class="field-error">
                       ${errors.cardHolderName}
                   </div>
               </c:if>

           </div>

       </div>


       <!-- ===================================================== -->
       <!-- BANK DETAILS -->
       <!-- ===================================================== -->

       <div
               id="bankFields"
               class="payment-fields"
               style="display:none;">

           <div class="form-group">

               <label>Bank Name</label>

               <input
                       type="text"
                       name="bankName"
                       id="bankName"
                       value="${bankName}"
                       placeholder="Enter bank name"
               />

               <c:if test="${not empty errors.bankName}">
                   <div class="field-error">
                       ${errors.bankName}
                   </div>
               </c:if>

           </div>


           <div class="form-group">

               <label>Account Number</label>

               <input
                       type="text"
                       name="accountNumber"
                       id="accountNumber"
                       value="${accountNumber}"
                       placeholder="Enter account number"
               />

               <c:if test="${not empty errors.accountNumber}">
                   <div class="field-error">
                       ${errors.accountNumber}
                   </div>
               </c:if>

           </div>

       </div>


        <!-- SUBMIT -->

        <button
                type="submit"
                class="button">

            Start SIP

        </button>

    </form>


    <a
            href="${pageContext.request.contextPath}/investor/investor-success"
            class="back">

        ← Back to Dashboard

    </a>


    <div class="info">

        <strong>How SIP works:</strong>

        <br>

        A fixed amount is invested every month.

        The first installment will be recorded
        when the SIP is created.

        Future installments can be processed
        according to the SIP schedule.

    </div>

</div>

<script>

    function updatePaymentFields() {

        const paymentType =
                document.getElementById("paymentType").value;

        const upiFields =
                document.getElementById("upiFields");

        const cardFields =
                document.getElementById("cardFields");

        const bankFields =
                document.getElementById("bankFields");


        const upiId =
                document.getElementById("upiId");

        const cardNumber =
                document.getElementById("cardNumber");

        const cardHolderName =
                document.getElementById("cardHolderName");

        const bankName =
                document.getElementById("bankName");

        const accountNumber =
                document.getElementById("accountNumber");


        // Hide everything first

        upiFields.style.display = "none";
        cardFields.style.display = "none";
        bankFields.style.display = "none";


        // Remove required from everything

        upiId.required = false;

        cardNumber.required = false;
        cardHolderName.required = false;

        bankName.required = false;
        accountNumber.required = false;


        // =================================================
        // UPI
        // =================================================

        if (paymentType === "UPI") {

            upiFields.style.display = "block";

            upiId.required = true;
        }


        // =================================================
        // CARD
        // =================================================

        else if (paymentType === "CARD") {

            cardFields.style.display = "block";

            cardNumber.required = true;
            cardHolderName.required = true;
        }


        // =================================================
        // BANK
        // =================================================

        else if (paymentType === "BANK") {

            bankFields.style.display = "block";

            bankName.required = true;
            accountNumber.required = true;
        }

    }


    // Run whenever payment method changes

    document
            .getElementById("paymentType")
            .addEventListener(
                    "change",
                    updatePaymentFields
            );


    // Run once when page loads
    // Important when validation fails and
    // paymentType is already selected.

    document.addEventListener(
            "DOMContentLoaded",
            updatePaymentFields
    );

</script>

</body>

</html>