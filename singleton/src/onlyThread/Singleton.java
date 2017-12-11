package onlyThread;

public class Singleton {

    private static Singleton singletonObject;

    private Singleton(){}

    // 단일 스레드 환경
    public static Singleton getInstance(){

        if(singletonObject == null){
            singletonObject = new Singleton();
        }
        return singletonObject;
    }
}
