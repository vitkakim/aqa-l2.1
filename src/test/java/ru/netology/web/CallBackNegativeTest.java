package ru.netology.web;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.By.cssSelector;

public class CallBackNegativeTest {
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
    void shouldBeNameNotRussianText() {
        fieldName.sendKeys("Anisimova");
        fieldPhone.sendKeys("+71234567890");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='name'] .input__sub")).getText().strip();
        String expectedMessage = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeEmptyNameTest() {
        fieldName.sendKeys("");
        fieldPhone.sendKeys("+71234567890");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='name'] .input__sub")).getText().strip();
        String expectedMessage = "Поле обязательно для заполнения";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeNumberLessThen11NumbersTest() {
        fieldName.sendKeys("Анисимова Виктория Эдуардовна");
        fieldPhone.sendKeys("+7123456789");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='phone'] .input__sub")).getText().strip();
        String expectedMessage = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeNumberMoreThen11NumbersTest() {
        fieldName.sendKeys("Анисимова Виктория Эдуардовна");
        fieldPhone.sendKeys("+712345678901");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='phone'] .input__sub")).getText().strip();
        String expectedMessage = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeNumberWithOutPlusTest() {
        fieldName.sendKeys("Анисимова Виктория Эдуардовна");
        fieldPhone.sendKeys("71234567890");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='phone'] .input__sub")).getText().strip();
        String expectedMessage = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeEmptyNumberTest() {
        fieldName.sendKeys("Анисимова Виктория Эдуардовна");
        fieldPhone.sendKeys("");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='phone'] .input__sub")).getText().strip();
        String expectedMessage = "Поле обязательно для заполнения";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeNotClickedCheckBoxTest() {
        fieldName.sendKeys("Анисимова Виктория Эдуардовна");
        fieldPhone.sendKeys("+71234567890");
        driver.findElement(tagName("button")).click();
        WebElement actualMessage = driver.findElement(className("input_invalid"));
        String color = actualMessage.getCssValue("color");
        Assertions.assertEquals("rgba(255, 92, 92, 1)", color);
    }

    @Test
    @Disabled
    void shouldBeNegativeButItPositiveTest() {
        fieldName.sendKeys("Анисимова");
        fieldPhone.sendKeys("+71234567890");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='name'] .input__sub")).getText().strip();
        String expectedMessage = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
