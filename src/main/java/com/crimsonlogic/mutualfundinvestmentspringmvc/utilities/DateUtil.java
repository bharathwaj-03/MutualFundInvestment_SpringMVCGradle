package com.crimsonlogic.mutualfundinvestmentspringmvc.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

    public DateUtil() {
    }

    public static LocalDate getCurrentDate() {


        return LocalDate.now();
    }

    public static LocalDateTime getCurrentDateTime() {



        return LocalDateTime.now();
    }

    public static LocalDate getNextMonthDate() {


        return LocalDate.now().plusMonths(1);
    }
}