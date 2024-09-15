package org.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = 1;
        if(payData.getfirstBillingDate() != null) {
            // 후보 만료일
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if (payData.getfirstBillingDate().getDayOfMonth() !=
                    candidateExp.getDayOfMonth()) {
                return candidateExp.withDayOfMonth( payData.getfirstBillingDate().getDayOfMonth() );
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
