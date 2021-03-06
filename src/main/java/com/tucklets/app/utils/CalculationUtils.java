package com.tucklets.app.utils;

import com.tucklets.app.entities.enums.DonationDuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class CalculationUtils {
    private CalculationUtils(){}

    /**
     * Determines the age given a (birth) year.
     */
    public static int calculateAge(int year) {
        return LocalDate.now().getYear() - year;
    }

    /**
     * Computes the amount based on the selected donation duration.
     */
    public static BigDecimal calculateAmount(
            DonationDuration desiredDuration, DonationDuration prevDuration, BigDecimal amount)
    {
        // Going to monthly duration from a yearly one.
        if (desiredDuration == DonationDuration.MONTHLY
                && (prevDuration == DonationDuration.YEARLY || prevDuration == DonationDuration.YEARLY_RECURRING)) {
            return amount.divide(BigDecimal.valueOf(12), RoundingMode.CEILING);
        }
        // Going to a yearly duration from a monthly selection.
        else if (desiredDuration != DonationDuration.MONTHLY && prevDuration == DonationDuration.MONTHLY){
            return BigDecimal.valueOf(12).multiply(amount);
        }
        // All other cases do not require an amount adjustment.
        else {
            return amount;
        }
    }
}
