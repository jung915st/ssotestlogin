// Generated by Selenium IDE

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class TestSelenium {
    private final Logger logger = LoggerFactory.getLogger(TestSelenium.class);
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    public static class DriverInit {
        public WebDriver driver;
        private static DriverInit driverInit = null;

        public static DriverInit getInstance() {
            if (driverInit == null) {
                driverInit = new DriverInit();
            }
            return driverInit;
        }

        private DriverInit() {
            this.driver = new ChromeDriver();
            this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            this.driver.get("******");
        }

        public WebDriver getDriver() {
            return this.driver;
        }
    }

    @Before
    public void setUp() {
        //driver = new ChromeDriver();
        driver = DriverInit.getInstance().getDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testssologin() throws URISyntaxException {

        OpenidConnect connect = new OpenidConnect();
        //String url = connect.authorization_endpoint;
        String url = connect.getAuthorization_endpoint();
        String user = "jung915";
        String pass = "IamJung915";
        String captcha = "";
        // Test name: test-sso-login
        // Step # | name | target | value
        // 1 | open | /home?4 |
        logger.info(url);
        driver.get(url);
        // 2 | setWindowSize | 1854x1053 |
        driver.manage().window().setSize(new Dimension(800, 600));
        // 3 | click | css=.sfHover div |
        driver.findElement(By.cssSelector(".sfHover div")).click();
        // 4 | click | id=id57 |
        //driver.findElement(By.id("id57")).click();

        // 5 | type | id=id57 | jung915
        //driver.findElement(By.id("id57")).sendKeys("jung915");
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/form/div[2]/input"))
                .sendKeys(user);
        // 6 | type | id=id56 | IamJung915
        driver.findElement(By.xpath("")).sendKeys(pass);
        // 7 | type | name=captchatext | 414
        driver.findElement(By.xpath("captchatext")).sendKeys(captcha);
        // 8 | click | id=id4a |
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[3]/button"))
                .click();
    }

}
