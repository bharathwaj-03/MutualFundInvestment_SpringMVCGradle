<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <title>Investor Login</title>

    <style>

        * {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #064e3b, #16a34a);
        }

        .login-box {
            width: 430px;
            background: white;
            padding: 40px;
            border-radius: 18px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.3);
        }

        h2 {
            text-align: center;
            color: #166534;
            margin-bottom: 10px;
        }

        .subtitle {
            text-align: center;
            color: #64748b;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 7px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 13px;
            border: 1px solid #cbd5e1;
            border-radius: 8px;
        }

        button {
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

        .register {
            display: block;
            text-align: center;
            margin-top: 25px;
            padding: 13px;
            background: #f0fdf4;
            border-radius: 8px;
            color: #166534;
            text-decoration: none;
            font-weight: bold;
        }

        .error {
            color: #dc2626;
            text-align: center;
            margin-top: 15px;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #475569;
        }

    </style>

</head>

<body>

<div class="login-box">

    <h2>Investor Login</h2>

    <p class="subtitle">
        Access your investment account
    </p>

    <form method="post"
          action="${pageContext.request.contextPath}/userlogin/investor">

        <label>Investor ID</label>

        <input type="text"
               name="userId"
               placeholder="Enter Investor ID"
               required>


        <label>Password</label>

        <input type="password"
               name="password"
               placeholder="Enter Password"
               required>


        <button type="submit">
            Login
        </button>

    </form>

    <%
        if (request.getAttribute("error") != null) {
    %>

        <div class="error">
            ${error}
        </div>

    <%
        }
    %>

    <a class="register"
       href="${pageContext.request.contextPath}/userlogin/investor/register">

        New Investor? Register Here

    </a>

    <a class="back"
       href="${pageContext.request.contextPath}/userlogin">

        ← Back to Role Selection

    </a>

</div>

</body>

</html>