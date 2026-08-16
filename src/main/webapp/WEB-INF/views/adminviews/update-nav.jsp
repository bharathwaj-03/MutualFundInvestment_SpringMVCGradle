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

            padding: 50px 20px;
        }


        .container {

            max-width: 650px;

            margin: auto;
        }


        .card {

            background:
                rgba(255,255,255,0.97);

            padding: 35px;

            border-radius: 18px;

            box-shadow:
                0 10px 30px
                rgba(0,0,0,0.15);
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


        .fund-info {

            background: #ecfdf3;

            border-radius: 12px;

            padding: 20px;

            margin-bottom: 25px;
        }


        .info-row {

            display: flex;

            justify-content:
                space-between;

            padding: 9px 0;

            border-bottom:
                1px solid #d1fae5;
        }


        .info-row:last-child {

            border-bottom: none;
        }


        .label {

            color: #555;

            font-weight: bold;
        }


        .value {

            color: #14532d;

            font-weight: bold;
        }


        .current-nav {

            font-size: 22px;

            color: #15803d;
        }


        label {

            display: block;

            margin-bottom: 8px;

            font-weight: bold;

            color: #374151;
        }


        input[type="number"] {

            width: 100%;

            padding: 14px;

            border: 1px solid #cbd5e1;

            border-radius: 8px;

            font-size: 17px;

            outline: none;
        }


        input[type="number"]:focus {

            border-color: #16a34a;

            box-shadow:
                0 0 0 3px
                rgba(22,163,74,0.15);
        }


        .error {

            background: #ffe5e5;

            color: #b91c1c;

            padding: 14px;

            border-radius: 8px;

            margin-bottom: 20px;

            font-weight: bold;
        }


        .buttons {

            display: flex;

            gap: 12px;

            margin-top: 25px;
        }


        button,
        .cancel {

            flex: 1;

            padding: 13px;

            border: none;

            border-radius: 8px;

            font-size: 16px;

            font-weight: bold;

            text-align: center;

            text-decoration: none;

            cursor: pointer;
        }


        button {

            background: #16a34a;

            color: white;
        }


        button:hover {

            background: #15803d;
        }


        .cancel {

            background: #6b7280;

            color: white;
        }


        .cancel:hover {

            background: #4b5563;
        }


        .warning {

            margin-top: 20px;

            padding: 13px;

            background: #fff7ed;

            color: #9a3412;

            border-radius: 8px;

            font-size: 14px;
        }

    </style>

</head>


<body>

<div class="container">


    <div class="card">


        <h1>Update NAV</h1>


        <p class="subtitle">

            Update the current NAV for this mutual fund.

        </p>


        <!-- ================================================= -->
        <!-- ERROR -->
        <!-- ================================================= -->

        <c:if test="${not empty error}">

            <div class="error">

                ${error}

            </div>

        </c:if>


        <!-- ================================================= -->
        <!-- FUND INFORMATION -->
        <!-- ================================================= -->

        <div class="fund-info">


            <div class="info-row">

                <span class="label">
                    Fund Name
                </span>

                <span class="value">
                    ${fund.fundName}
                </span>

            </div>


            <div class="info-row">

                <span class="label">
                    Fund Code
                </span>

                <span class="value">
                    ${fund.fundCode}
                </span>

            </div>


            <div class="info-row">

                <span class="label">
                    Category
                </span>

                <span class="value">
                    ${fund.fundCategory}
                </span>

            </div>


            <div class="info-row">

                <span class="label">
                    Fund House
                </span>

                <span class="value">
                    ${fund.fundHouse}
                </span>

            </div>


            <div class="info-row">

                <span class="label">
                    Current NAV
                </span>

                <span class="value current-nav">

                    ₹${fund.nav}

                </span>

            </div>


        </div>


        <!-- ================================================= -->
        <!-- UPDATE FORM -->
        <!-- ================================================= -->

        <form
                method="post"
                action="${pageContext.request.contextPath}/admin/funds/update-nav">


            <input
                    type="hidden"
                    name="fundId"
                    value="${fund.fundId}"
            />


            <label for="newNAV">

                Enter New NAV

            </label>


            <input
                    type="number"
                    id="newNAV"
                    name="newNAV"
                    step="0.01"
                    min="0.01"
                    required
                    placeholder="Enter new NAV"
            />


            <div class="warning">

                The current NAV is
                <strong>₹${fund.nav}</strong>.
                The new NAV must be greater than zero
                and different from the current NAV.

            </div>


            <div class="buttons">


                <button type="submit">

                    Update NAV

                </button>


                <a
                        href="${pageContext.request.contextPath}/admin/funds/update-nav"
                        class="cancel">

                    Cancel

                </a>


            </div>


        </form>


    </div>

</div>

</body>

</html>