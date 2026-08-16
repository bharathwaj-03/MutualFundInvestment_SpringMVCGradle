<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>

    <title>Mutual Fund Investment</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #0f172a, #1e3a8a);
        }

        .container {
            width: 450px;
            background: white;
            padding: 45px;
            border-radius: 18px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.3);
            text-align: center;
        }

        h1 {
            color: #1e3a8a;
            margin-bottom: 10px;
        }

        .subtitle {
            color: #64748b;
            margin-bottom: 35px;
        }

        .role-btn {
            display: block;
            width: 100%;
            padding: 16px;
            margin: 15px 0;
            border-radius: 10px;
            text-decoration: none;
            font-size: 17px;
            font-weight: bold;
            transition: 0.2s;
        }

        .admin {
            background: #1e3a8a;
            color: white;
        }

        .investor {
            background: #16a34a;
            color: white;
        }

        .role-btn:hover {
            transform: translateY(-2px);
            opacity: 0.9;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>Mutual Fund Investment</h1>

    <p class="subtitle">
        Select your role to continue
    </p>

    <a href="${pageContext.request.contextPath}/userlogin/admin"
       class="role-btn admin">

        Admin

    </a>

    <a href="${pageContext.request.contextPath}/userlogin/investor"
       class="role-btn investor">

        Investor

    </a>

</div>

</body>
</html>