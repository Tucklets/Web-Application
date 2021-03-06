package com.tucklets.app;

import com.tucklets.app.entities.enums.DonationDuration;
import com.tucklets.app.utils.CalculationUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CalculationUtilsTest {

    @Test
    public void testMonthlyToYearly() {
        BigDecimal amount = new BigDecimal(60);
        Assertions.assertEquals(
                BigDecimal.valueOf(720),
                CalculationUtils.calculateAmount(
                        DonationDuration.YEARLY,
                        DonationDuration.MONTHLY,
                        amount));

        Assertions.assertEquals(
                BigDecimal.valueOf(720),
                CalculationUtils.calculateAmount(
                        DonationDuration.YEARLY_RECURRING,
                        DonationDuration.MONTHLY,
                        amount));

    }

    @Test
    public void testYearlyToMonthly() {
        BigDecimal amount = new BigDecimal(720);
        Assertions.assertEquals(
                BigDecimal.valueOf(60),
                CalculationUtils.calculateAmount(
                        DonationDuration.MONTHLY,
                        DonationDuration.YEARLY,
                        amount));

        Assertions.assertEquals(
                BigDecimal.valueOf(60),
                CalculationUtils.calculateAmount(
                        DonationDuration.MONTHLY,
                        DonationDuration.YEARLY_RECURRING,
                        amount));

    }

    @Test
    public void testYearlyToYearly() {
        BigDecimal amount = new BigDecimal(720);
        Assertions.assertEquals(
                BigDecimal.valueOf(720),
                CalculationUtils.calculateAmount(
                        DonationDuration.YEARLY_RECURRING,
                        DonationDuration.YEARLY,
                        amount));

        Assertions.assertEquals(
                BigDecimal.valueOf(720),
                CalculationUtils.calculateAmount(
                        DonationDuration.YEARLY_RECURRING,
                        DonationDuration.YEARLY,
                        amount));

    }

    @Test
    public void testMonthlyToMonthly() {
        BigDecimal amount = new BigDecimal(60);
        Assertions.assertEquals(
                BigDecimal.valueOf(60),
                CalculationUtils.calculateAmount(
                        DonationDuration.MONTHLY,
                        DonationDuration.MONTHLY,
                        amount));
    }

}
