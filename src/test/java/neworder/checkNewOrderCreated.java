package neworder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.startsWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pom.MainPage;
import java.time.Duration;

public class checkNewOrderCreated {
    private WebDriver driver;

    @Before
    public void setUp(){
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void checkOrderStatus() {
        MainPage mainPage = new MainPage(driver);

        //Ищем кнопку "Заказ"
        mainPage.open();
        mainPage.clickOrderButton();
        mainPage.clickCookieConfirmation();
        mainPage.fillFirstForm();
        mainPage.fillSecondForm();
        mainPage.finalConfirmation();
        MatcherAssert.assertThat(mainPage.checkResult(), startsWith("Заказ оформлен"));

    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
