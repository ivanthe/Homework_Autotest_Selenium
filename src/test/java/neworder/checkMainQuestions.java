package neworder;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.startsWith;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.MainPage;
import java.time.Duration;

@RunWith(Parameterized.class)
public class checkMainQuestions {
    private WebDriver driver;
    private int stringNumber;
    private String text;

    public checkMainQuestions(int stringNumber, String text) {
        this.stringNumber = stringNumber;
        this.text = text;
    }

    @Parameterized.Parameters
    public static Object[][] getTestDates(){
        return new Object[][] {
            {1, "Сутки — 400 рублей"},
            {2, "Пока что у нас так"},
            {3, "Допустим, вы оформляете заказ на 8 мая"},
            {4, "Только начиная с завтрашнего дня"},
            {5, "Пока что нет!"},
            {6, "Самокат приезжает к вам с полной зарядкой."},
            {7, "Да, пока самокат не привезли."},
            {8, "Да, обязательно."},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
    }

    @Test
    public void checkMainQuestions() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickCookieConfirmation();
        mainPage.scrollPageDown();
        String actualText = mainPage.getTextWithAnswers(stringNumber);

        System.out.println(actualText);
        MatcherAssert.assertThat(actualText, startsWith(text));

    }
    @After
    public void tearDown(){
        driver.quit();
    }
}

