package pom;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private final By orderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[contains(@class, 'Button_Button__ra12g')]");
    private final By coockieConfirmation = By.xpath(".//button[contains(text(), 'да все привыкли')]");
    private final By inputName = By.xpath("//input[@placeholder='* Имя']");
    private final By inputLastName = By.xpath("//input[@placeholder='* Фамилия']");
    private final By inputAddress = By.xpath("//input[@placeholder=\"* Адрес: куда привезти заказ\"]");
    private final By inputSubwayStation = By.xpath("//input[@placeholder='* Станция метро']");
    private final By inputPhone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNextFromFirstForm = By.xpath("//button[contains(text(), 'Далее')]");
    private final By inputDateOfDelivery = By.xpath("//input[@placeholder=\"* Когда привезти самокат\"]");
    private final By clickRentPeriodFild = By.xpath(".//div[contains(text(), \"* Срок аренды\")]");
    private final By chooseRentPeriod = By.xpath(".//div[contains(text(), \"трое суток\")]");
    private final By chooseColour = By.xpath(".//label[contains(text(), \"чёрный жемчуг\")]");
    private final By commentForCorrier = By.xpath("//input[@placeholder=\"Комментарий для курьера\"]");
    private final By orderButtonConfirmation = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Заказать')]");
    private final By clickYesButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[contains(text(), 'Да')]");
    private final By getConfirmationMassage = By.xpath("//div[@class=\"Order_ModalHeader__3FDaJ\"]");
    private final By lastElementOnPage = By.id("accordion__heading-7");
    private WebDriver driver;
    private WebElement element;


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    //Нажать кнопку Заказ
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //подтверждаем куки
    public void clickCookieConfirmation() {
        driver.findElement(coockieConfirmation).click();
    }
    public void fillFirstForm(){
        driver.findElement(inputName).sendKeys("Иван");
        driver.findElement(inputLastName).sendKeys("Иванов");
        driver.findElement(inputAddress).sendKeys("Москва");
        driver.findElement(inputSubwayStation).sendKeys("Сокольники", Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(inputPhone).sendKeys("+79998884422");
        driver.findElement(buttonNextFromFirstForm).click();
    }
    public void fillSecondForm() {
        driver.findElement(inputDateOfDelivery).sendKeys("18.05.2023", Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(clickRentPeriodFild).click();
        driver.findElement(chooseRentPeriod).click();
        driver.findElement(chooseColour).click();
        driver.findElement(commentForCorrier).sendKeys("Привезти утром");
        driver.findElement(orderButtonConfirmation).click();
    }
    public void finalConfirmation() {
        driver.findElement(clickYesButton).click();
    }
    public String checkResult() {
        return driver.findElement(getConfirmationMassage).getText();
    }

    public void scrollPageDown() {
        element = driver.findElement(lastElementOnPage);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getTextWithAnswers(int stringNumber) throws InterruptedException {
        driver.findElement(By.xpath("//div[@class=\"accordion__item\"][" + stringNumber + "]")).click();
        Thread.sleep(500);
        return driver.findElement(By.xpath("//div[@id=\"accordion__panel-" + (stringNumber-1) + "\"]/p")).getText();
    }

}
