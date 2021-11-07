package ru.netology.web;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.tagName;

public class CallBackNegativeTest {
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
    void shouldBeNameNotRussianText() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Anisimova");
        textFields.get(1).sendKeys("+71234567890");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        List<WebElement> textResultFields = driver.findElements(className("input__sub"));
        String actualMessage = textResultFields.get(0).getText().strip();
        String expectedMessage = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeEmptyNameTest() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("");
        textFields.get(1).sendKeys("+71234567890");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        List<WebElement> textResultFields = driver.findElements(className("input__sub"));
        String actualMessage = textResultFields.get(0).getText().strip();
        String expectedMessage = "Поле обязательно для заполнения";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeNumberLessThen11NumbersTest() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Анисимова Виктория Эдуардовна");
        textFields.get(1).sendKeys("+7123456789");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        List<WebElement> textResultFields = driver.findElements(className("input__sub"));
        String actualMessage = textResultFields.get(1).getText().strip();
        String expectedMessage = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeNumberWithOutPlusTest() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Анисимова Виктория Эдуардовна");
        textFields.get(1).sendKeys("71234567890");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        List<WebElement> textResultFields = driver.findElements(className("input__sub"));
        String actualMessage = textResultFields.get(1).getText().strip();
        String expectedMessage = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeEmptyNumberTest() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Анисимова Виктория Эдуардовна");
        textFields.get(1).sendKeys("");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        List<WebElement> textResultFields = driver.findElements(className("input__sub"));
        String actualMessage = textResultFields.get(1).getText().strip();
        String expectedMessage = "Поле обязательно для заполнения";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeNotClickedCheckBoxTest() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Анисимова Виктория Эдуардовна");
        textFields.get(1).sendKeys("+71234567890");
        driver.findElement(tagName("button")).click();
        WebElement actualMessage = driver.findElement(className("checkbox__box"));
        Assertions.assertFalse(actualMessage.isSelected());
    }

    @Test
    @Disabled
    void shouldBeNegativeButItPositiveTest() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Анисимова");
        textFields.get(1).sendKeys("+71234567890");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        List<WebElement> textResultFields = driver.findElements(className("input__sub"));
        String actualMessage = textResultFields.get(0).getText().strip();
        String expectedMessage = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
