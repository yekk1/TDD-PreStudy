package org.chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 4, 1)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 5)
        );
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        //LocalDate#plusMonths() 메서드가 알아서 한 달 추가 처리를 해줌
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 2, 28)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 30)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를떄_만원_납부() {

        //첫 납부일이 2019-01-31, 만료되는 2019-02-28에 1만 원을 납부하면 다음 만료일은 2019-03-31이다.
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019,3,31));

        //첫 납부일이 2019-01-30, 만료되는 2019-02-28에 1만 원을 납부하면 다음 만료일은 2019-03-30이다.
        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2019,3,30));

        //첫 납부일이 2019-05-31, 만료되는 2019-06-30에 1만 원을 납부하면 다음 만료일은 2019-07-31이다.
        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019,6,30))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData3, LocalDate.of(2019,7,31));
    }
    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
