package signup;

public class SignUp {

    private SignUpCheckImpl checkImpl;

    public SignUp(SignUpCheckImpl checkImpl){
        this.checkImpl = checkImpl;
    }

    public void checkId(){checkImpl.checkUserId();}
    public void checkPw(){checkImpl.checkUserPw();}
    public void checkPhone(){checkImpl.checkUserPhone();}
    public void checkAgree(){checkImpl.checkUserAgree();}
    public void checkStart(){
        checkId();
        checkPw();
        checkPhone();
        checkAgree();
    }
}
