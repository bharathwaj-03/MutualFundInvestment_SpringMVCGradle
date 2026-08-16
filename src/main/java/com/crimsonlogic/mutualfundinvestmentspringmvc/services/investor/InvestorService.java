package com.crimsonlogic.mutualfundinvestmentspringmvc.services.investor;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.InvestorMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.NomineeMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.UserMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Nominee;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.I_PortfolioService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.DateUtil;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.IdGeneratorUtil;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.security.PasswordUtil;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.UserDataValidation;
import com.crimsonlogic.mutualfundinvestmentspringmvc.exception.UserDataValidationException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class InvestorService implements I_InvestorService {

    private InvestorMapper investorMapper;
    private UserMapper userMapper;
    private NomineeMapper nomineeMapper;
    private I_PortfolioService portfolioService;

    public void setPortfolioService(
            I_PortfolioService portfolioService) {

        this.portfolioService = portfolioService;
    }

    // =========================================================
    // SETTER INJECTION
    // =========================================================

    public void setInvestorMapper(
            InvestorMapper investorMapper) {

        this.investorMapper = investorMapper;
    }

    public void setUserMapper(
            UserMapper userMapper) {

        this.userMapper = userMapper;
    }

    public void setNomineeMapper(
            NomineeMapper nomineeMapper) {

        this.nomineeMapper = nomineeMapper;
    }


    // =========================================================
    // VALIDATION FUNCTIONAL INTERFACES
    // =========================================================

    public UserDataValidation nameValidate =
            (String str) -> {

                if (str == null ||
                        str.trim().isEmpty()) {

                    throw new UserDataValidationException(
                            "Please enter a valid name."
                    );
                }

                if (!str.matches(
                        "^[a-zA-Z]+(?: [a-zA-Z]+)*$")) {

                    throw new UserDataValidationException(
                            "Name should contain only alphabets and spaces."
                    );
                }

                return str.toUpperCase();
            };


    public UserDataValidation emailValid =
            (String str) -> {

                if (str == null ||
                        str.trim().isEmpty()) {

                    throw new UserDataValidationException(
                            "Please enter a valid email address. Ex: name@company.com"
                    );
                }

                boolean isValid =
                        str.matches(
                                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
                        );

                if (!isValid) {

                    throw new UserDataValidationException(
                            "Please enter a valid email address. Ex: name@company.com"
                    );
                }

                return str;
            };


    public UserDataValidation phoneNum =
            (String str) -> {

                if (str == null ||
                        str.trim().isEmpty()) {

                    throw new UserDataValidationException(
                            "Please enter a valid 10-digit phone number."
                    );
                }

                boolean isValid =
                        str.matches(
                                "^[6-9]\\d{9}$"
                        );

                if (!isValid) {

                    throw new UserDataValidationException(
                            "Please enter a valid 10-digit phone number."
                    );
                }

                return str;
            };


    public UserDataValidation panValidate =
            (String str) -> {

                if (str == null ||
                        str.trim().isEmpty()) {

                    throw new UserDataValidationException(
                            "Please enter a valid PAN."
                    );
                }

                boolean isValid =
                        str.matches(
                                "^[A-Z]{5}[0-9]{4}[A-Z]{1}$"
                        );

                if (!isValid) {

                    throw new UserDataValidationException(
                            "Please enter a valid PAN (5 letters in CAPS followed by 4 digits and 1 letter)."
                    );
                }

                return str.toUpperCase();
            };


    public UserDataValidation passwordValidate =
            (String str) -> {

                if (str == null ||
                        str.trim().isEmpty()) {

                    throw new UserDataValidationException(
                            "Password cannot be empty."
                    );
                }

                boolean isValid =
                        str.matches(
                                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{6,}$"
                        );

                if (!isValid) {

                    throw new UserDataValidationException(
                            "Password must contain at least 6 characters, one uppercase letter, one lowercase letter, one digit and one special character."
                    );
                }

                return str;
            };


    // =========================================================
    // NOMINEE VALIDATIONS
    // =========================================================

    public UserDataValidation nomineeNameValidate =
            (String str) -> {

                if (str == null ||
                        str.trim().isEmpty()) {

                    throw new UserDataValidationException(
                            "Please enter a valid nominee name."
                    );
                }

                if (!str.matches(
                        "^[a-zA-Z]+(?: [a-zA-Z]+)*$")) {

                    throw new UserDataValidationException(
                            "Nominee name should contain only alphabets and spaces."
                    );
                }

                return str.toUpperCase();
            };


    public UserDataValidation genderValidate =
            (String str) -> {

                if (str == null ||
                        str.trim().isEmpty()) {

                    throw new UserDataValidationException(
                            "Please select nominee gender."
                    );
                }

                if (!str.matches(
                        "(?i)^(male|female)$")) {

                    throw new UserDataValidationException(
                            "Gender must be Male or Female."
                    );
                }

                return str.toUpperCase();
            };


    public UserDataValidation relationshipValidate =
            (String str) -> {

                if (str == null ||
                        str.trim().isEmpty()) {

                    throw new UserDataValidationException(
                            "Please enter relationship with nominee."
                    );
                }

                return str;
            };


    // =========================================================
    // VALIDATE ALL INVESTOR DETAILS
    // =========================================================

    @Override
    public Map<String, String> validateInvestor(
            Investor investor) {

        Map<String, String> errors =
                new LinkedHashMap<>();


        // -----------------------------------------------------
        // Investor Name
        // -----------------------------------------------------

        try {

            investor.setName(
                    nameValidate.validate(
                            investor.getName()
                    )
            );

        } catch (UserDataValidationException e) {

            errors.put(
                    "name",
                    e.getMessage()
            );
        }


        // -----------------------------------------------------
        // Password
        // -----------------------------------------------------

        try {

            passwordValidate.validate(
                    investor.getPassword()
            );

        } catch (UserDataValidationException e) {

            errors.put(
                    "password",
                    e.getMessage()
            );
        }


        // -----------------------------------------------------
        // Email
        // -----------------------------------------------------

        try {

            investor.setEmail(
                    emailValid.validate(
                            investor.getEmail()
                    )
            );

        } catch (UserDataValidationException e) {

            errors.put(
                    "email",
                    e.getMessage()
            );
        }


        // -----------------------------------------------------
        // Phone
        // -----------------------------------------------------

        try {

            investor.setPhoneNumber(
                    phoneNum.validate(
                            investor.getPhoneNumber()
                    )
            );

        } catch (UserDataValidationException e) {

            errors.put(
                    "phoneNumber",
                    e.getMessage()
            );
        }


        // -----------------------------------------------------
        // PAN
        // -----------------------------------------------------

        try {

            investor.setPanNumber(
                    panValidate.validate(
                            investor.getPanNumber()
                    )
            );

        } catch (UserDataValidationException e) {

            errors.put(
                    "panNumber",
                    e.getMessage()
            );
        }


        // =====================================================
        // NOMINEE
        // =====================================================

        Nominee nominee =
                investor.getNominee();

        if (nominee == null) {

            errors.put(
                    "nominee",
                    "Nominee details are required."
            );

            return errors;
        }


        // -----------------------------------------------------
        // Nominee Name
        // -----------------------------------------------------

        try {

            nominee.setName(
                    nomineeNameValidate.validate(
                            nominee.getName()
                    )
            );

        } catch (UserDataValidationException e) {

            errors.put(
                    "nominee.name",
                    e.getMessage()
            );
        }


        // -----------------------------------------------------
        // Nominee Age
        // -----------------------------------------------------

        try {

            Integer age =
                    nominee.getAge();

            if (age == null) {

                throw new UserDataValidationException(
                        "Please enter nominee age."
                );
            }

            if (age <= 0) {

                throw new UserDataValidationException(
                        "Nominee age must be greater than 0."
                );
            }

        } catch (UserDataValidationException e) {

            errors.put(
                    "nominee.age",
                    e.getMessage()
            );
        }


        // -----------------------------------------------------
        // Nominee Gender
        // -----------------------------------------------------

        try {

            nominee.setGender(
                    genderValidate.validate(
                            nominee.getGender()
                    )
            );

        } catch (UserDataValidationException e) {

            errors.put(
                    "nominee.gender",
                    e.getMessage()
            );
        }


        // -----------------------------------------------------
        // Relationship
        // -----------------------------------------------------

        try {

            nominee.setRelationship(
                    relationshipValidate.validate(
                            nominee.getRelationship()
                    )
            );

        } catch (UserDataValidationException e) {

            errors.put(
                    "nominee.relationship",
                    e.getMessage()
            );
        }


        return errors;
    }


    // =========================================================
    // REGISTER INVESTOR
    // =========================================================


    @Override
    public boolean registerInvestor(
            Investor investor) {

        try {

            // -------------------------------------------------
            // Safety validation
            // -------------------------------------------------

            Map<String, String> errors =
                    validateInvestor(investor);

            if (!errors.isEmpty()) {

                return false;
            }


            // -------------------------------------------------
            // Generate Investor ID
            // -------------------------------------------------

            if (investor.getUserId() == null ||
                    investor.getUserId().trim().isEmpty()) {

                investor.setUserId(
                        IdGeneratorUtil.generateInvestorId()
                );
            }


            // -------------------------------------------------
            // Generate Nominee ID
            // -------------------------------------------------

            Nominee nominee =
                    investor.getNominee();

            if (nominee.getNomineeId() == null ||
                    nominee.getNomineeId().trim().isEmpty()) {

                nominee.setNomineeId(
                        IdGeneratorUtil.generateNomineeId()
                );
            }


            // -------------------------------------------------
            // Default values
            // -------------------------------------------------

            investor.setUserRole("INVESTOR");

            investor.setActive(true);

            investor.setRegistrationDate(DateUtil.getCurrentDate());


            // -------------------------------------------------
            // Hash password
            // -------------------------------------------------

            investor.setPassword(
                    PasswordUtil.hashPassword(
                            investor.getPassword()
                    )
            );


            // -------------------------------------------------
            // Insert order
            //
            // 1. Nominee
            // 2. User
            // 3. Investor
            // -------------------------------------------------

            nomineeMapper.insertNominee(
                    nominee
            );

            userMapper.insertUser(
                    investor
            );

            investorMapper.insertInvestor(
                    investor
            );
            Portfolio portfolio =
                    portfolioService.createPortfolio(
                            investor.getUserId()
                    );


            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // INVESTOR AUTHENTICATION
    // =========================================================

    @Override
    public Investor authenticateInvestor(
            String userId,
            String password) {

        Investor investor =
                investorMapper.getInvestorByUserId(
                        userId
                );

        if (investor == null) {

            return null;
        }

        if (!PasswordUtil.verifyPassword(
                password,
                investor.getPassword())) {

            return null;
        }

        return investor;
    }

    @Override
    public Investor getInvestorByUserId(String userId) {

        return investorMapper.getInvestorByUserId(userId);
    }
}