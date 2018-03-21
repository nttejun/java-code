package signup;

public class IndividualImpl extends SignUpCheckImpl {
    @Override
    public void checkUserId() {
        System.out.println("개인 ID를 확인합니다");
    }

    @Override
    public void checkUserPw() {
        System.out.println("개인 PW를 확인합니다");
    }

    @Override
    public void checkUserPhone() {
        System.out.println("개인 PHONE을 확인합니다");
    }

    @Override
    public void checkUserAgree() {
        System.out.println("개인 Agree를 확인합니다");
    }
}
