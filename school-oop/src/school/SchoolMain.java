package school;

public class SchoolMain {
    public static void main(String[] args) {

        String wonjun = "청소를 실시한다";
        String minsun = "청소를 실시한다";
        String taesun = "청소를 실시한다";
        String jihun = "청소를 실시한다";

        SchoolMain schoolMain = new SchoolMain();
        schoolMain.cleaning(minsun);
        schoolMain.cleaning(wonjun);
        schoolMain.cleaning(taesun);
        schoolMain.cleaning(jihun);
    }

    public void cleaning(String student) {
        String doSometing = student + "청소를 한다";
        System.out.println(doSometing);
    }

}
