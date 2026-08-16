<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <title>Admin Dashboard</title>

    <style>

        body {
            margin: 0;
            font-family: Arial, sans-serif;

            background:
                linear-gradient(
                    135deg,
                    #dfffe8,
                    #b8f5c8,
                    #8ee8a5
                );

            min-height: 100vh;
        }

        .container {
            width: 90%;
            max-width: 1100px;
            margin: 50px auto;
        }

        .header {
            background: white;
            padding: 30px;
            border-radius: 15px;

            box-shadow:
                0 5px 20px
                rgba(0, 0, 0, 0.12);

            margin-bottom: 30px;
        }

        .header h1 {
            margin-top: 0;
            color: #14532d;
        }

        .header p {
            color: #555;
        }

        .features {
            display: grid;

            grid-template-columns:
                repeat(
                    auto-fit,
                    minmax(220px, 1fr)
                );

            gap: 20px;
        }

        .card {
            background: white;
            padding: 25px;

            border-radius: 15px;

            box-shadow:
                0 5px 15px
                rgba(0, 0, 0, 0.10);

            transition: 0.2s;
        }

        .card:hover {
            transform: translateY(-4px);
        }

        .card h2 {
            color: #166534;
            margin-top: 0;
        }

        .card p {
            color: #666;
            min-height: 45px;
        }

        .button {
            display: inline-block;

            padding: 11px 18px;

            background: #16a34a;

            color: white;

            text-decoration: none;

            border-radius: 8px;

            font-weight: bold;
        }

        .button:hover {
            background: #15803d;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="header">

        <h1>
            Welcome to Admin Dashboard
        </h1>

        <p>
            Admin ID:
            <strong>${adminId}</strong>
        </p>

        <p>
            Manage mutual funds, NAV values
            and other investment operations.
        </p>

    </div>


    <div class="features">

        <!-- VIEW FUNDS -->

        <div class="card">

            <h2>
                View All Funds
            </h2>

            <p>
                View all mutual funds available
                in the system.
            </p>

            <a
                    href="${pageContext.request.contextPath}/admin/funds"
                    class="button">

                View Funds

            </a>

        </div>


        <!-- ADD FUND -->

        <div class="card">

            <h2>
                Add Fund
            </h2>

            <p>
                Add a new mutual fund with
                complete fund details.
            </p>

            <a
                    href="${pageContext.request.contextPath}/admin/funds/add"
                    class="button">

                Add Fund

            </a>

        </div>


        <!-- UPDATE NAV -->

        <div class="card">

            <h2>
                Update NAV
            </h2>

            <p>
                Update the NAV of an existing
                mutual fund.
            </p>

         <a href="${pageContext.request.contextPath}/admin/funds/update-nav" class="button">
             Update NAV
         </a>

        </div>


    </div>

</div>

</body>

</html>