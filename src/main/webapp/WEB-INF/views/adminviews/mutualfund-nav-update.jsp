<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <title>Update NAV</title>

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
                    #064e3b,
                    #166534
            );

            padding: 50px 20px;
        }

        .container {
            max-width: 600px;

            margin: auto;

            background: white;

            padding: 40px;

            border-radius: 20px;

            box-shadow:
                    0 15px 40px rgba(0,0,0,0.3);
        }

        h1 {
            text-align: center;
            color: #166534;
        }

        .fund-name {
            text-align: center;

            color: #64748b;

            margin-bottom: 30px;
        }

        .current {
            background: #f0fdf4;

            padding: 18px;

            border-radius: 10px;

            text-align: center;

            margin-bottom: 25px;
        }

        .current strong {
            display: block;

            font-size: 28px;

            color: #166534;

            margin-top: 5px;
        }

        .error {
            background: #fee2e2;

            color: #991b1b;

            padding: 12px;

            border-radius: 8px;

            margin-bottom: 20px;
        }

        label {
            display: block;

            font-weight: bold;

            margin-bottom: 8px;
        }

        input {
            width: 100%;

            padding: 13px;

            border:
                    1px solid #cbd5e1;

            border-radius: 8px;

            font-size: 15px;
        }

        .submit {
            width: 100%;

            padding: 14px;

            margin-top: 25px;

            background: #16a34a;

            color: white;

            border: none;

            border-radius: 8px;

            font-size: 16px;

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

    <h1>
        Update NAV
    </h1>


    <div class="fund-name">

        ${fund.fundName}

        <br>

        Fund Code: ${fund.fundCode}

    </div>


    <% if (request.getAttribute("error") != null) { %>

        <div class="error">
            ${error}
        </div>

    <% } %>


    <div class="current">

        Current NAV

        <strong>
            ₹${fund.nav}
        </strong>

    </div>


    <form method="post"
          action="${pageContext.request.contextPath}/mutualfund/admin/nav">


        <input type="hidden"
               name="fundId"
               value="${fund.fundId}">


        <label>
            New NAV
        </label>

        <input type="number"
               name="newNAV"
               step="0.01"
               min="0.01"
               placeholder="Enter new NAV"
               required>


        <label style="margin-top:20px;">
            Admin ID
        </label>

        <input type="text"
               name="adminId"
               placeholder="Enter Admin ID"
               required>


        <button type="submit"
                class="submit">

            Update NAV

        </button>

    </form>


    <a class="back"
       href="${pageContext.request.contextPath}/mutualfund/admin">

        ← Back to Mutual Fund Management

    </a>

</div>

</body>

</html>