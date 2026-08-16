<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <title>Add Mutual Fund</title>

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
                    #1e3a8a
            );

            padding: 40px 20px;
        }

        .container {
            max-width: 850px;
            margin: auto;
            background: white;
            padding: 40px;
            border-radius: 20px;

            box-shadow:
                    0 15px 40px rgba(0,0,0,0.3);
        }

        h1 {
            text-align: center;
            color: #1e3a8a;
            margin-bottom: 8px;
        }

        .subtitle {
            text-align: center;
            color: #64748b;
            margin-bottom: 30px;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        .field {
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: bold;
            margin-bottom: 7px;
        }

        input,
        select {
            padding: 12px;
            border: 1px solid #cbd5e1;
            border-radius: 8px;
            font-size: 14px;
        }

        .full {
            grid-column: 1 / -1;
        }

        .submit {
            width: 100%;
            padding: 15px;
            margin-top: 30px;

            background: #2563eb;
            color: white;

            border: none;
            border-radius: 9px;

            font-size: 17px;
            font-weight: bold;

            cursor: pointer;
        }

        .submit:hover {
            background: #1d4ed8;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 20px;

            color: #475569;
            text-decoration: none;
        }

    </style>

</head>


<body>

<div class="container">

    <h1>Add Mutual Fund</h1>

    <p class="subtitle">
        Create a new mutual fund
    </p>


    <% if (request.getAttribute("error") != null) { %>

        <div class="error">
            ${error}
        </div>

    <% } %>


    <form method="post"
          action="${pageContext.request.contextPath}/mutualfund/admin/add">


        <div class="grid">


            <!-- FUND CODE -->

            <div class="field">

                <label>
                    Fund Code
                </label>

                <input type="text"
                       name="fundCode"
                       placeholder="Example: EQ004"
                       required>

            </div>


            <!-- FUND NAME -->

            <div class="field">

                <label>
                    Fund Name
                </label>

                <input type="text"
                       name="fundName"
                       placeholder="Enter fund name"
                       required>

            </div>


            <!-- CATEGORY -->

            <div class="field">

                <label>
                    Fund Category
                </label>

                <select name="fundCategory"
                        required>

                    <option value="">
                        Select Category
                    </option>

                    <option value="Debt Fund">
                        Debt Fund
                    </option>

                    <option value="Equity Fund">
                        Equity Fund
                    </option>

                    <option value="Hybrid Fund">
                        Hybrid Fund
                    </option>

                </select>

            </div>


            <!-- FUND HOUSE -->

            <div class="field">

                <label>
                    Fund House
                </label>

                <input type="text"
                       name="fundHouse"
                       placeholder="Example: SBI Mutual Fund"
                       required>

            </div>


            <!-- RISK -->

            <div class="field">

                <label>
                    Risk Level
                </label>

                <select name="riskLevel"
                        required>

                    <option value="">
                        Select Risk
                    </option>

                    <option value="Low">
                        Low
                    </option>

                    <option value="Medium">
                        Medium
                    </option>

                    <option value="High">
                        High
                    </option>

                </select>

            </div>


            <!-- NAV -->

            <div class="field">

                <label>
                    Current NAV
                </label>

                <input type="number"
                       name="nav"
                       step="0.01"
                       min="0.01"
                       required>

            </div>


            <!-- MINIMUM INVESTMENT -->

            <div class="field">

                <label>
                    Minimum Investment
                </label>

                <input type="number"
                       name="minimumInvestment"
                       min="1"
                       required>

            </div>


            <!-- SIP GAIN -->

            <div class="field">

                <label>
                    SIP Gain Per Year (%)
                </label>

                <input type="number"
                       name="sipGainPerYear"
                       step="0.01"
                       min="0"
                       required>

            </div>


            <!-- LUMP SUM GAIN -->

            <div class="field">

                <label>
                    Lump Sum Gain Per Year (%)
                </label>

                <input type="number"
                       name="lumpSumGainPerYear"
                       step="0.01"
                       min="0"
                       required>

            </div>


        </div>


        <button type="submit"
                class="submit">

            Add Mutual Fund

        </button>


    </form>


    <a class="back"
       href="${pageContext.request.contextPath}/mutualfund/admin">

        ← Back to Mutual Fund Management

    </a>

</div>

</body>

</html>