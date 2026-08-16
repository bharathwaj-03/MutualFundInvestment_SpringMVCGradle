<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <title>Edit Mutual Fund</title>

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

            grid-template-columns:
                    1fr 1fr;

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

            border:
                    1px solid #cbd5e1;

            border-radius: 8px;

            font-size: 14px;
        }

        input[readonly] {
            background: #f1f5f9;
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

    <h1>Edit Mutual Fund</h1>


    <% if (request.getAttribute("error") != null) { %>

        <div class="error">
            ${error}
        </div>

    <% } %>


    <form method="post"
          action="${pageContext.request.contextPath}/mutualfund/admin/edit">


        <input type="hidden"
               name="fundId"
               value="${fund.fundId}">


        <div class="grid">


            <div class="field">

                <label>
                    Fund ID
                </label>

                <input type="text"
                       value="${fund.fundId}"
                       readonly>

            </div>


            <div class="field">

                <label>
                    Fund Code
                </label>

                <input type="text"
                       name="fundCode"
                       value="${fund.fundCode}"
                       required>

            </div>


            <div class="field">

                <label>
                    Fund Name
                </label>

                <input type="text"
                       name="fundName"
                       value="${fund.fundName}"
                       required>

            </div>


            <div class="field">

                <label>
                    Category
                </label>

                <select name="fundCategory"
                        required>

                    <option value="Debt Fund"
                            ${fund.fundCategory == 'Debt Fund' ? 'selected' : ''}>
                        Debt Fund
                    </option>

                    <option value="Equity Fund"
                            ${fund.fundCategory == 'Equity Fund' ? 'selected' : ''}>
                        Equity Fund
                    </option>

                    <option value="Hybrid Fund"
                            ${fund.fundCategory == 'Hybrid Fund' ? 'selected' : ''}>
                        Hybrid Fund
                    </option>

                </select>

            </div>


            <div class="field">

                <label>
                    Fund House
                </label>

                <input type="text"
                       name="fundHouse"
                       value="${fund.fundHouse}"
                       required>

            </div>


            <div class="field">

                <label>
                    Risk Level
                </label>

                <select name="riskLevel"
                        required>

                    <option value="Low"
                            ${fund.riskLevel == 'Low' ? 'selected' : ''}>
                        Low
                    </option>

                    <option value="Medium"
                            ${fund.riskLevel == 'Medium' ? 'selected' : ''}>
                        Medium
                    </option>

                    <option value="High"
                            ${fund.riskLevel == 'High' ? 'selected' : ''}>
                        High
                    </option>

                </select>

            </div>


            <div class="field">

                <label>
                    NAV
                </label>

                <input type="number"
                       name="nav"
                       value="${fund.nav}"
                       step="0.01"
                       min="0.01"
                       required>

            </div>


            <div class="field">

                <label>
                    Minimum Investment
                </label>

                <input type="number"
                       name="minimumInvestment"
                       value="${fund.minimumInvestment}"
                       min="1"
                       required>

            </div>


            <div class="field">

                <label>
                    SIP Gain Per Year (%)
                </label>

                <input type="number"
                       name="sipGainPerYear"
                       value="${fund.sipGainPerYear}"
                       step="0.01"
                       min="0"
                       required>

            </div>


            <div class="field">

                <label>
                    Lump Sum Gain Per Year (%)
                </label>

                <input type="number"
                       name="lumpSumGainPerYear"
                       value="${fund.lumpSumGainPerYear}"
                       step="0.01"
                       min="0"
                       required>

            </div>


        </div>


        <button type="submit"
                class="submit">

            Update Mutual Fund

        </button>


    </form>


    <a class="back"
       href="${pageContext.request.contextPath}/mutualfund/admin">

        ← Back to Mutual Fund Management

    </a>

</div>

</body>

</html>