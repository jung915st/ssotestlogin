import java.net.URISyntaxException;

public class logintest {
    public static void main(String[] args) throws NoSuchFieldException, URISyntaxException {
        //test sso login using selenium
        TestSelenium selenium = new TestSelenium();
        selenium.testssologin();
    }
}
