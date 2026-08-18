<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <title>Edit Admin Profile</title>

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

            max-width: 700px;

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


        /* ===================================================== */
        /* SUCCESS MESSAGE */
        /* ===================================================== */

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

            box-shadow:
                0 5px 15px
                rgba(0,0,0,0.08);
        }


        .success-icon {

            width: 42px;

            height: 42px;

            min-width: 42px;

            border-radius: 50%;

            background: #16a34a;

            color: white;

            display: flex;

            align-items: center;

            justify-content: center;

            font-size: 23px;

            font-weight: bold;
        }


        .success-content strong {

            font-size: 16px;
        }


        .success-content p {

            margin: 5px 0 0;

            color: #166534;

            font-size: 14px;
        }


        /* ===================================================== */
        /* BACK TO DASHBOARD */
        /* ===================================================== */

        .dashboard-container {

            text-align: center;

            margin-bottom: 25px;
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


        /* ===================================================== */
        /* GRID */
        /* ===================================================== */

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


        input {

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


        /* ===================================================== */
        /* BUTTONS */
        /* ===================================================== */

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


        .cancel:hover {

            background: #475569;
        }


        /* ===================================================== */
        /* ERROR */
        /* ===================================================== */

        .error {

            background: #fee2e2;

            color: #991b1b;

            padding: 12px;

            border-radius: 8px;

            margin-bottom: 20px;

            text-align: center;
        }


        /* ===================================================== */
        /* RESPONSIVE */
        /* ===================================================== */

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


    <h1>
        Edit Admin Profile
    </h1>


    <p class="subtitle">

        Update your administrator information

    </p>


    <!-- ===================================================== -->
    <!-- SUCCESS MESSAGE -->
    <!-- ===================================================== -->

    <c:if test="${not empty success}">

        <div class="success-message">

            <div class="success-icon">

                ✓

            </div>


            <div class="success-content">

                <strong>
                    Changes Saved Successfully!
                </strong>

                <p>
                    Your admin profile has been updated successfully.
                </p>

            </div>

        </div>


        <div class="dashboard-container">

            <a href="${pageContext.request.contextPath}/admin/dashboard"
               class="dashboard-button">

                ← Back to Dashboard

            </a>

        </div>

    </c:if>


    <!-- ===================================================== -->
    <!-- ERROR MESSAGE -->
    <!-- ===================================================== -->

    <c:if test="${not empty error}">

        <div class="error">

            ${error}

        </div>

    </c:if>


    <!-- ===================================================== -->
    <!-- EDIT FORM -->
    <!-- ===================================================== -->

    <form method="post"
          action="${pageContext.request.contextPath}/admin/profile/edit">


        <div class="grid">


            <!-- ADMIN ID -->

            <div class="field">

                <label>
                    Admin ID
                </label>

                <input type="text"
                       value="${admin.userId}"
                       disabled>

            </div>


            <!-- NAME -->

            <div class="field">

                <label>
                    Name
                </label>

                <input type="text"
                       name="name"
                       value="${admin.name}"
                       required>

            </div>


            <!-- EMAIL -->

            <div class="field">

                <label>
                    Email
                </label>

                <input type="email"
                       name="email"
                       value="${admin.email}"
                       required>

            </div>


            <!-- PHONE -->

            <div class="field">

                <label>
                    Phone Number
                </label>

             <input type="text"
                    name="phoneNumber"
                    value="${admin.phoneNumber}"
                    maxlength="10"
                    pattern="[6-9][0-9]{9}"
                    title="Phone number must be exactly 10 digits and start with 6, 7, 8 or 9"
                    required>

            </div>


            <!-- ADMIN CODE -->

            <div class="field">

                <label>
                    Admin Code
                </label>

                <input type="text"
                       name="adminCode"
                       value="${admin.adminCode}"
                       required>

            </div>


            <!-- CREATED DATE -->

            <div class="field">

                <label>
                    Created Date
                </label>

                <input type="text"
                       value="${admin.createdDate}"
                       disabled>

            </div>

        </div>


        <!-- ================================================= -->
        <!-- FORM BUTTONS -->
        <!-- ================================================= -->

        <div class="buttons">

            <button type="submit"
                    class="save">

                Save Changes

            </button>


            <a href="${pageContext.request.contextPath}/admin/dashboard"
               class="cancel">

                Cancel

            </a>

        </div>


    </form>


</div>

</body>

</html>