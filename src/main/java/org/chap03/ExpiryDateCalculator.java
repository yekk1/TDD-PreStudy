package org.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {

        int addedMonths =  calAddedMonths(payData.getPayAmount());
        if(payData.getfirstBillingDate() != null) {
           return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(
        PayData payData, int addedMonths
    ) {
        // 후보 만료일
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        if (!isSameDayOfMonth( payData.getfirstBillingDate(), candidateExp)) {
            //후보 만료일이 포함 된 달의 마지막 날 < 첫 납부일자면 => 후보만료일: 그 달의 마지막 일자
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            // 첫 남부일의 일자
            final int dayOfFirstBilling = payData.getfirstBillingDate().getDayOfMonth();
            if(dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth( dayOfFirstBilling );
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(
        LocalDate date1, LocalDate date2
    ) {
      return date1.getDayOfMonth() == date2.getDayOfMonth();
    }

    private int lastDayOfMonth(
        LocalDate date
    ) {
        return YearMonth.from(date).lengthOfMonth();
    }

    private int calAddedMonths(int payAmount) {
        return (payAmount / 100_000 * 12) + (payAmount % 100_000 / 10_000);
    }
}
