<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <title>Registration Successful</title>

    <style>

        body {
            margin: 0;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #064e3b, #16a34a);
        }

        .box {
            width: 450px;
            background: white;
            padding: 45px;
            border-radius: 18px;
            text-align: center;
            box-shadow: 0 15px 40px rgba(0,0,0,0.3);
        }

        h1 {
            color: #16a34a;
        }

        .id {
            margin: 25px 0;
            padding: 15px;
            background: #f0fdf4;
            border-radius: 8px;
            font-size: 20px;
            font-weight: bold;
            color: #166534;
        }

        a {
            display: block;
            padding: 13px;
            background: #16a34a;
            color: white;
            text-decoration: none;
            border-radius: 8px;
        }

    </style>

</head>

<body>

<div class="box">

    <h1>Registration Successful!</h1>

    <p>Your investor account has been created.</p>

    <div class="id">

        Investor ID: ${investorId}

    </div>

    <a href="${pageContext.request.contextPath}/userlogin/investor">

        Proceed to Investor Login

    </a>

</div>

</body>

</html>