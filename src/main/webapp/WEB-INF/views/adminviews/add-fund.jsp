<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <title>Add Mutual Fund</title>

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
            width: 90%;
            max-width: 850px;

            margin: 40px auto;
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

            border-radius: 16px 16px 0 0;
        }


        .header h1 {
            margin: 0;
        }


        .header p {
            margin-bottom: 0;
        }


        .form-container {
            background: white;

            padding: 30px;

            border-radius: 0 0 16px 16px;

            box-shadow:
                    0 8px 25px
                    rgba(0, 0, 0, 0.12);
        }


        .error {
            background: #ffebee;

            color: #c62828;

            padding: 14px;

            border-radius: 8px;

            margin-bottom: 20px;

            font-weight: bold;
        }


        .form-grid {
            display: grid;

            grid-template-columns:
                    1fr 1fr;

            gap: 20px;
        }


        .form-group {
            display: flex;

            flex-direction: column;
        }


        .form-group.full {
            grid-column:
                    1 / -1;
        }


        label {
            margin-bottom: 7px;

            font-weight: bold;

            color: #333;
        }


        input,
        select {
            padding: 12px;

            border: 1px solid #ccc;

            border-radius: 7px;

            font-size: 15px;

            outline: none;
        }


        input:focus,
        select:focus {
            border-color: #43a047;

            box-shadow:
                    0 0 0 2px
                    rgba(67, 160, 71, 0.15);
        }


        .buttons {
            margin-top: 30px;

            display: flex;

            gap: 12px;
        }


        button,
        .button {
            border: none;

            padding: 13px 22px;

            border-radius: 8px;

            font-size: 15px;

            font-weight: bold;

            text-decoration: none;

            cursor: pointer;

            color: white;
        }


        .submit {
            background: #2e7d32;
        }


        .back {
            background: #546e7a;
        }

    </style>

</head>


<body>

<div class="container">


    <!-- ===================================================== -->
    <!-- HEADER -->
    <!-- ===================================================== -->

    <div class="header">

        <h1>Add Mutual Fund</h1>

        <p>
            Enter the complete details of the new mutual fund.
        </p>

    </div>


    <!-- ===================================================== -->
    <!-- FORM -->
    <!-- ===================================================== -->

    <div class="form-container">


        <c:if test="${not empty error}">

            <div class="error">
                ${error}
            </div>

        </c:if>


        <form
                method="post"
                action="${pageContext.request.contextPath}/admin/funds/add">


            <div class="form-grid">


                <!-- FUND NAME -->

                <div class="form-group">

                    <label>
                        Fund Name
                    </label>

                    <input
                            type="text"
                            name="fundName"
                            placeholder="Example: SBI Bluechip Fund"
                            required>

                </div>


                <!-- FUND CODE -->

                <div class="form-group">

                    <label>
                        Fund Code
                    </label>

                    <input
                            type="text"
                            name="fundCode"
                            placeholder="Example: EQ001"
                            required>

                </div>


                <!-- CATEGORY -->

                <div class="form-group">

                    <label>
                        Fund Category
                    </label>

                    <select
                            name="fundCategory"
                            required>

                        <option value="">
                            -- Select Category --
                        </option>

                        <option value="Equity Fund">
                            Equity Fund
                        </option>

                        <option value="Debt Fund">
                            Debt Fund
                        </option>

                        <option value="Hybrid Fund">
                            Hybrid Fund
                        </option>

                    </select>

                </div>


                <!-- FUND HOUSE -->

                <div class="form-group">

                    <label>
                        Fund House
                    </label>

                    <input
                            type="text"
                            name="fundHouse"
                            placeholder="Example: SBI Mutual Fund"
                            required>

                </div>


                <!-- NAV -->

                <div class="form-group">

                    <label>
                        Current NAV (₹)
                    </label>

                    <input
                            type="number"
                            name="nav"
                            min="0.01"
                            step="0.01"
                            placeholder="Example: 500"
                            required>

                </div>


                <!-- MINIMUM INVESTMENT -->

                <div class="form-group">

                    <label>
                        Minimum Investment (₹)
                    </label>

                    <input
                            type="number"
                            name="minimumInvestment"
                            min="1"
                            step="1"
                            placeholder="Example: 5000"
                            required>

                </div>


                <!-- SIP GAIN -->

                <div class="form-group">

                    <label>
                        SIP Expected Gain / Year (%)
                    </label>

                    <input
                            type="number"
                            name="sipGainPerYear"
                            min="0"
                            step="0.01"
                            placeholder="Example: 14"
                            required>

                </div>


                <!-- LUMP SUM GAIN -->

                <div class="form-group">

                    <label>
                        Lump-Sum Expected Gain / Year (%)
                    </label>

                    <input
                            type="number"
                            name="lumpSumGainPerYear"
                            min="0"
                            step="0.01"
                            placeholder="Example: 16"
                            required>

                </div>


                <!-- RISK LEVEL -->

                <div class="form-group full">

                    <label>
                        Risk Level
                    </label>

                    <select
                            name="riskLevel"
                            required>

                        <option value="">
                            -- Select Risk Level --
                        </option>

                        <option value="LOW">
                            LOW
                        </option>

                        <option value="MEDIUM">
                            MEDIUM
                        </option>

                        <option value="HIGH">
                            HIGH
                        </option>

                    </select>

                </div>


            </div>


            <!-- BUTTONS -->

            <div class="buttons">

                <button
                        type="submit"
                        class="submit">

                    Add Mutual Fund

                </button>


                <a
                        href="${pageContext.request.contextPath}/admin/funds"
                        class="button back">

                    Cancel

                </a>

            </div>


        </form>

    </div>

</div>

</body>

</html>