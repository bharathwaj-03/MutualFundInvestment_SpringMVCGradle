<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <title>Admin Login</title>

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
            background: linear-gradient(135deg, #0f172a, #1e3a8a);
        }

        .login-box {
            width: 400px;
            background: white;
            padding: 40px;
            border-radius: 18px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.3);
        }

        h2 {
            text-align: center;
            color: #1e3a8a;
            margin-bottom: 30px;
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
            background: #1e3a8a;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }

        button:hover {
            background: #172554;
        }

        .error {
            margin-top: 15px;
            text-align: center;
            color: #dc2626;
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

    <h2>Admin Login</h2>

    <form method="post"
          action="${pageContext.request.contextPath}/userlogin/admin">

        <label>Admin ID</label>

        <input type="text"
               name="userId"
               placeholder="Enter Admin ID"
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

    <a class="back"
       href="${pageContext.request.contextPath}/userlogin">

        ← Back to Role Selection

    </a>

</div>

</body>

</html>