import signup.CorporateImpl;
import signup.SignUp;

public class Main {
    public static void main(String[] args){
        SignUp corpUser = new SignUp(new CorporateImpl());
        corpUser.checkStart();
    }
}
