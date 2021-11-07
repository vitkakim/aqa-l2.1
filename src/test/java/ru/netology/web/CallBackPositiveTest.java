package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.tagName;

public class CallBackPositiveTest {
    private WebDriver driver;

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
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestV1() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Анисимова Виктория");
        textFields.get(1).sendKeys("+71234567890");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(className("paragraph")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldTestV2() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Анисимова Виктория Эдуардовна");
        textFields.get(1).sendKeys("+71234567890");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(className("paragraph")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldTestV3() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Анисимова Анна-Виктория Эдуардовна");
        textFields.get(1).sendKeys("+71234567890");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(className("paragraph")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


}
