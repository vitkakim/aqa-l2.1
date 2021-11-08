package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.tagName;

public class CallBackPositiveTest {
    private WebDriver driver;
    private WebElement fieldName;
    private WebElement fieldPhone;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
        fieldName = driver.findElement(cssSelector("[data-test-id='name'] input"));
        fieldPhone = driver.findElement(cssSelector("[data-test-id='phone'] input"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestV1() {
        fieldName.sendKeys("Анисимова Виктория");
        fieldPhone.sendKeys("+71234567890");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='order-success']")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldTestV2() {
        fieldName.sendKeys("Анисимова Виктория Эдуардовна");
        fieldPhone.sendKeys("+71234567890");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='order-success']")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldTestV3() {
        fieldName.sendKeys("Анисимова Анна-Виктория Эдуардовна");
        fieldPhone.sendKeys("+71234567890");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='order-success']")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


}
