package org.chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    // 길이가 8글자 이상
    // 0부터 9사이의 숫자를 포함
    // 대문자 포함
    // 모두 충족: 강함 / 2개: 보통 / 1개 이하: 약함


    private PasswordStrengthMeter meter = new PasswordStrengthMeter();
    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }
    @Test
    @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 강함이어야 함")
    void meetsAllCriteria_Then_Strong() {
        //To wirte code
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("패스워드 문자열의 길이가 8글자 미만이고 나머지 조건은 충족하는 암호")
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 암호")
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("얌호로 null 값을 받았을 때")
    void nullInput_Then_INVALID() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("암호로 빈 값을 받았을 때")
    void emptynput_Then_INVALID() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("대문자를 포함하지 않고 나머지 조건을 충족하는 암호")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족하는 암호")
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("abdefghi", PasswordStrength.WEEK);
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족하는 암호")
    void meetsOnlyNumCriteria_Then_Week() {
        assertStrength("12345", PasswordStrength.WEEK);
    }
    @Test
    @DisplayName("대문자 포함 조건만 충족하는 암호")
    void meetsOnlyUpperCriteria_Then_Week() {
        assertStrength("ABZEF", PasswordStrength.WEEK);
    }

    @Test
    @DisplayName("아무 조건도 충족하지 않는 암호")
    void meetsNoCriteria_Then_Week() {
        assertStrength("abc", PasswordStrength.WEEK);
    }
}
