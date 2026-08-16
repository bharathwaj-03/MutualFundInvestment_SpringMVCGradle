<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <title>Investor Dashboard</title>

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
                    #16a34a
            );

            padding: 40px 20px;
        }

        .container {
            max-width: 900px;
            margin: auto;
        }

        .welcome {
            background: white;
            border-radius: 20px;
            padding: 40px;
            text-align: center;
            box-shadow:
                    0 15px 40px
                    rgba(0,0,0,0.25);
        }

        h1 {
            color: #166534;
            margin-bottom: 10px;
        }

        .subtitle {
            color: #64748b;
            margin-bottom: 30px;
        }

        .portfolio-card {
            background: #f0fdf4;
            border: 1px solid #bbf7d0;
            border-radius: 15px;
            padding: 25px;
            margin-top: 25px;
        }

        .success {
            color: #15803d;
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 15px;
        }

        .portfolio-id {
            font-size: 17px;
            color: #334155;
        }

        .portfolio-id strong {
            color: #166534;
        }

        .buttons {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 30px;
            flex-wrap: wrap;
        }

        .button {
            padding: 14px 25px;
            border-radius: 9px;
            text-decoration: none;
            font-weight: bold;
        }

        .primary {
            background: #16a34a;
            color: white;
        }

        .secondary {
            background: #dcfce7;
            color: #166534;
        }

        .primary:hover {
            background: #15803d;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="welcome">

        <h1>
            Welcome, ${investor.name}!
        </h1>

        <p class="subtitle">
            Investor login successful
        </p>


        <div class="portfolio-card">

            <div class="success">
                ✓ Portfolio created successfully
            </div>

            <div class="portfolio-id">

                Your Portfolio ID:

                <strong>
                    ${portfolio.portfolioId}
                </strong>

            </div>

            <p>
                Your portfolio is currently empty.
                Start your first investment to add holdings.
            </p>

        </div>


        <div class="buttons">

            <a class="button primary"
               href="${pageContext.request.contextPath}/investor/investment">

                Start Investment

            </a>

            <a
                    href="${pageContext.request.contextPath}/investor/sip"
                    class="button">

                Start SIP

            </a>


            <a class="button secondary"
               href="${pageContext.request.contextPath}/investor/portfolio">

                View Portfolio

            </a>

        </div>

    </div>

</div>

</body>

</html>