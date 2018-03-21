package signup;

public class CorporateImpl extends SignUpCheckImpl {
    @Override
    public void checkUserId() {
        System.out.println("법인 ID를 확인합니다");
        checkCorpId();
    }

    public void checkCorpId(){
        System.out.println("사업자 ID를 확인합니다");
    }

    @Override
    public void checkUserPw() {
        System.out.println("법인 PW를 확인합니다");
    }

    @Override
    public void checkUserPhone() {
        System.out.println("법인 PHONE을 확인합니다");
    }

    @Override
    public void checkUserAgree() {
        System.out.println("법인 Agree를 확인합니다");
    }
}
