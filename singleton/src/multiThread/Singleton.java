package multiThread;

public class Singleton {

    // private 로 외부에서 new 연산자를 사용해 새로운 객체 생성하는 것을 방지
    private static Singleton singletonObject;

    private Singleton() {}

    // 멀티쓰레드 환경에서 사용하는 Lazy initialization
    public static Singleton getInstnace() {
        if (singletonObject == null) {
            // 인스턴스 객체가 두 개 이상 생성되는 것을 방지하기 위한 동기화
            synchronized (Singleton.class) {
                if (singletonObject == null) {
                    singletonObject = new Singleton();
                }
            }
        }
        return singletonObject;
    }
}
