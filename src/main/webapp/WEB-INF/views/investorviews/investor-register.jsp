<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <title>Investor Registration</title>

    <style>

        * {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #064e3b, #16a34a);
            padding: 40px 20px;
        }

        .container {
            max-width: 850px;
            margin: auto;
            background: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.25);
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
            border-bottom: 2px solid #dcfce7;
        }

        .grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
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
            border: 1px solid #cbd5e1;
            border-radius: 8px;
            font-size: 14px;
        }

        .submit {
            width: 100%;
            padding: 15px;
            margin-top: 30px;
            background: #16a34a;
            color: white;
            border: none;
            border-radius: 9px;
            font-size: 17px;
            font-weight: bold;
            cursor: pointer;
        }

        .submit:hover {
            background: #15803d;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #475569;
            text-decoration: none;
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

        @media(max-width: 650px) {

            .grid {
                grid-template-columns: 1fr;
            }

        }

    </style>

</head>

<body>

<div class="container">

    <h1>Investor Registration</h1>

    <p class="subtitle">
        Create your Mutual Fund Investment account
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
          action="${pageContext.request.contextPath}/userlogin/investor/register">


        <!-- ================= INVESTOR DETAILS ================= -->

        <h2 class="section-title">
            Investor Details
        </h2>

        <div class="grid">

        <div class="field">

            <label>Name</label>

            <input type="text"
                   name="name"
                   value="${investor.name}"
                   placeholder="Enter full name"
                   required>

            <div class="field-error">
                ${errors.name}
            </div>

        </div>


         <div class="field">

             <label>Password</label>

             <input type="password"
                    name="password"
                    placeholder="Create password"
                    required>

             <div class="field-error">
                 ${errors.password}
             </div>

         </div>

<div class="field">

    <label>Email</label>

    <input type="email"
           name="email"
           value="${investor.email}"
           placeholder="name@example.com"
           required>

    <div class="field-error">
        ${errors.email}
    </div>

</div>


       <div class="field">

           <label>Phone Number</label>

           <input type="text"
                  name="phoneNumber"
                  value="${investor.phoneNumber}"
                  placeholder="10-digit phone number"
                  maxlength="10"
                  required>

           <div class="field-error">
               ${errors.phoneNumber}
           </div>

       </div>


         <div class="field">

             <label>PAN Number</label>

             <input type="text"
                    name="panNumber"
                    value="${investor.panNumber}"
                    placeholder="ABCDE1234F"
                    maxlength="10"
                    required>

             <div class="field-error">
                 ${errors.panNumber}
             </div>

         </div>

        </div>


        <!-- ================= NOMINEE DETAILS ================= -->

        <h2 class="section-title">
            Nominee Details
        </h2>

        <div class="grid">

       <div class="field">

           <label>Nominee Name</label>

           <input type="text"
                  name="nominee.name"
                  value="${investor.nominee.name}"
                  placeholder="Enter nominee name"
                  required>

           <div class="field-error">
               ${errors['nominee.name']}
           </div>

       </div>


      <div class="field">

          <label>Nominee Age</label>

          <input type="number"
                 name="nominee.age"
                 value="${investor.nominee.age}"
                 min="1"
                 placeholder="Enter age"
                 required>

          <div class="field-error">
              ${errors['nominee.age']}
          </div>

      </div>


        <div class="field">

            <label>Nominee Gender</label>

            <select name="nominee.gender"
                    required>

                <option value="">
                    Select Gender
                </option>

                <option value="MALE"
                        ${investor.nominee.gender == 'MALE' ? 'selected' : ''}>
                    Male
                </option>

                <option value="FEMALE"
                        ${investor.nominee.gender == 'FEMALE' ? 'selected' : ''}>
                    Female
                </option>

            </select>

            <div class="field-error">
                ${errors['nominee.gender']}
            </div>

        </div>


        <div class="field">

            <label>Relationship</label>

            <input type="text"
                   name="nominee.relationship"
                   value="${investor.nominee.relationship}"
                   placeholder="e.g. Father, Mother"
                   required>

            <div class="field-error">
                ${errors['nominee.relationship']}
            </div>

        </div>

        </div>


        <button type="submit"
                class="submit">

            Register Investor

        </button>

    </form>


    <a class="back"
       href="${pageContext.request.contextPath}/userlogin/investor">

        ← Back to Investor Login

    </a>

</div>

</body>

</html>