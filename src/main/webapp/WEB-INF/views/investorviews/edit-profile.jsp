<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <title>Edit Investor Profile</title>

    <style>

        * {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {

            min-height: 100vh;

            margin: 0;

            padding: 40px 20px;

            background:
                linear-gradient(
                    135deg,
                    #064e3b,
                    #16a34a
                );
        }


        .container {

            max-width: 850px;

            margin: auto;

            background: white;

            padding: 40px;

            border-radius: 20px;

            box-shadow:
                0 15px 40px
                rgba(0,0,0,0.25);
        }


        h1 {

            text-align: center;

            color: #166534;

            margin-bottom: 10px;
        }


        .subtitle {

            text-align: center;

            color: #64748b;

            margin-bottom: 35px;
        }


        .section-title {

            color: #166534;

            margin: 30px 0 15px;

            padding-bottom: 8px;

            border-bottom:
                2px solid #dcfce7;
        }

        .success-message {

            display: flex;

            align-items: center;

            gap: 15px;

            background: #dcfce7;

            border: 1px solid #86efac;

            color: #166534;

            padding: 18px 20px;

            border-radius: 12px;

            margin-bottom: 25px;

            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
        }

        .success-icon {

            width: 40px;

            height: 40px;

            border-radius: 50%;

            background: #16a34a;

            color: white;

            display: flex;

            align-items: center;

            justify-content: center;

            font-size: 22px;

            font-weight: bold;
        }

        .success-message p {

            margin: 5px 0 0;

            color: #166534;

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


        input:disabled {

            background: #f1f5f9;

            cursor: not-allowed;
        }


        .field-error {

            color: #dc2626;

            font-size: 13px;

            margin-top: 6px;

            min-height: 18px;
        }


        .error {

            background: #fee2e2;

            color: #991b1b;

            padding: 12px;

            border-radius: 8px;

            margin-bottom: 20px;

            text-align: center;
        }


        .success {

            background: #dcfce7;

            color: #166534;

            padding: 12px;

            border-radius: 8px;

            margin-bottom: 20px;

            text-align: center;
        }


        .buttons {

            display: flex;

            gap: 15px;

            margin-top: 30px;
        }


        .save {

            flex: 1;

            padding: 15px;

            background: #16a34a;

            color: white;

            border: none;

            border-radius: 9px;

            font-size: 17px;

            font-weight: bold;

            cursor: pointer;
        }

        .dashboard-button {

            display: inline-block;

            padding: 12px 25px;

            background: #166534;

            color: white;

            text-decoration: none;

            border-radius: 8px;

            font-weight: bold;
        }

        .dashboard-button:hover {

            background: #14532d;
        }


        .save:hover {

            background: #15803d;
        }


        .cancel {

            flex: 1;

            padding: 15px;

            background: #64748b;

            color: white;

            border-radius: 9px;

            font-size: 17px;

            font-weight: bold;

            text-align: center;

            text-decoration: none;
        }


        @media(max-width:650px) {

            .grid {

                grid-template-columns: 1fr;
            }

            .buttons {

                flex-direction: column;
            }
        }

    </style>

</head>


<body>



<div class="container">

<c:if test="${not empty success}">

    <div class="success-message">

        <span class="success-icon">✓</span>

        <div>
            <strong>Changes Saved Successfully!</strong>

            <p>
                Your profile has been updated successfully.
            </p>
        </div>

    </div>

</c:if>



    <h1>Edit Investor Profile</h1>

    <p class="subtitle">

        Update your personal and nominee information

    </p>


    <%
        if (request.getAttribute("error") != null) {
    %>

    <div class="error">

        ${error}

    </div>

    <%
        }
    %>


    <form method="post"
          action="${pageContext.request.contextPath}/investor/profile/edit">


        <!-- ================================================= -->
        <!-- INVESTOR DETAILS -->
        <!-- ================================================= -->

        <h2 class="section-title">

            Investor Details

        </h2>


        <div class="grid">


            <div class="field">

                <label>User ID</label>

                <input type="text"
                       value="${investor.userId}"
                       disabled>

            </div>


            <div class="field">

                <label>Name</label>

                <input type="text"
                       name="name"
                       value="${investor.name}"
                       required>

            </div>


            <div class="field">

                <label>Email</label>

                <input type="email"
                       name="email"
                       value="${investor.email}"
                       required>

            </div>


            <div class="field">

                <label>Phone Number</label>

                <input type="text"
                       name="phoneNumber"
                       value="${investor.phoneNumber}"
                       maxlength="10"
                       required>

            </div>


            <div class="field">

                <label>PAN Number</label>

                <input type="text"
                       name="panNumber"
                       value="${investor.panNumber}"
                       maxlength="10"
                       required>

            </div>


            <div class="field">

                <label>Account Number</label>

                <input type="text"
                       name="accountNumber"
                       value="${investor.accountNumber}"
                       maxlength="18"
                       inputmode="numeric"
                       required>

            </div>


            <div class="field">

                <label>Risk Profile</label>

                <input type="text"
                       name="riskProfile"
                       value="${investor.riskProfile}">

            </div>


            <div class="field">

                <label>Registration Date</label>

                <input type="text"
                       value="${investor.registrationDate}"
                       disabled>

            </div>

        </div>


        <!-- ================================================= -->
        <!-- NOMINEE -->
        <!-- ================================================= -->

        <h2 class="section-title">

            Nominee Details

        </h2>

        <input type="hidden"
               name="nominee.nomineeId"
               value="${investor.nominee.nomineeId}">


        <div class="grid">


            <div class="field">

                <label>Nominee Name</label>

                <input type="text"
                       name="nominee.name"
                       value="${investor.nominee.name}"
                       required>

            </div>


            <div class="field">

                <label>Nominee Age</label>

                <input type="number"
                       name="nominee.age"
                       value="${investor.nominee.age}"
                       min="1"
                       required>

            </div>


            <div class="field">

                <label>Nominee Gender</label>

                <select name="nominee.gender"
                        required>

                    <option value="MALE"
                        ${investor.nominee.gender == 'MALE'
                            ? 'selected' : ''}>
                        Male
                    </option>

                    <option value="FEMALE"
                        ${investor.nominee.gender == 'FEMALE'
                            ? 'selected' : ''}>
                        Female
                    </option>

                </select>

            </div>


            <div class="field">

                <label>Relationship</label>

                <input type="text"
                       name="nominee.relationship"
                       value="${investor.nominee.relationship}"
                       required>

            </div>


            <div class="field">

                <label>Nominee Account Number</label>

                <input type="text"
                       name="nominee.accountNumber"
                       value="${investor.nominee.accountNumber}"
                       maxlength="18"
                       inputmode="numeric"
                       required>

            </div>

        </div>


        <!-- ================================================= -->
        <!-- BUTTONS -->
        <!-- ================================================= -->

        <div class="buttons">

            <button type="submit"
                    class="save">

                Save Changes

            </button>


            <a href="${pageContext.request.contextPath}/userlogin/investor"
               class="cancel">

                Cancel

            </a>

        </div>


    </form>

    <c:if test="${not empty success}">

        <div style="text-align:center; margin-bottom:25px;">

            <a href="${pageContext.request.contextPath}/userlogin/investor"
               class="dashboard-button">

                ← Back to Dashboard

            </a>

        </div>

    </c:if>

</div>

</body>

</html>