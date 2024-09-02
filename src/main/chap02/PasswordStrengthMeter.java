package chap02;

public class PasswordStrengthMeter {
    // 길이가 8글자 이상
    // 0부터 9사이의 숫자를 포함
    // 대문자 포함
    // 모두 충족: 강함 / 2개: 보통 / 1개 이하: 약함
    public PasswordStrength meter(String s){
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int metCounts = getMetCriteriaCounts(s);

        if(metCounts <= 1) return PasswordStrength.WEEK;
        if(metCounts == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if(Character.isUpperCase(ch)){
                return true;
            }
        }
        return false;
    }

    private int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        if(s.length() >= 8) metCounts++;
        if(meetsContainingNumberCriteria(s)) metCounts++;
        if(meetsContainingUppercaseCriteria(s)) metCounts++;
        return metCounts;
    }
}
