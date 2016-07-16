package com.tsystems.javaschool.uberbahn.services.tests;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

public class SeleniumTest {

    private WebDriver getDriver(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        return driver;
    }

    private void closeDriver(WebDriver driver) {
        driver.close();
        driver.quit();
    }

    @Test
    public void testRegistration() {

        WebDriver driver = getDriver("http://localhost:8080/");
        driver.findElement(By.linkText("Sign up")).click();
        driver.findElement(By.id("login")).sendKeys("user1");
        driver.findElement(By.id("email")).sendKeys("user@example.com");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.id("confPassword")).sendKeys("123");
        driver.findElement(By.id("firstName")).sendKeys("Anna");
        driver.findElement(By.id("lastName")).sendKeys("Rossi");
        driver.findElement(By.id("dateOfBirth")).sendKeys(LocalDate.now().minusYears(20).toString());
        driver.findElement(By.id("registration")).click();
        closeDriver(driver);
    }

    @Test
    public void testStationTimetable() {
        WebDriver driver = getDriver("http://localhost:8080/");
        driver.findElement(By.linkText("Station Timetable")).click();
        Select select = new Select(driver.findElement(By.name("station")));
        select.selectByVisibleText("Moscow");
        driver.findElement(By.id("search")).click();
        System.out.println(driver.getCurrentUrl());
        closeDriver(driver);
    }

    @Test
    public void testEmployeeLinksAvailable() {
        WebDriver driver = getDriver("http://localhost:8080/");
        driver.findElement(By.name("j_username")).sendKeys("empl");
        driver.findElement(By.name("j_password")).sendKeys("123");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("Add Station")).click();
        driver.findElement(By.linkText("Add Route")).click();
        driver.findElement(By.linkText("Add Train")).click();
        driver.findElement(By.linkText("Find Train & Registered Passengers")).click();
        closeDriver(driver);
    }

    @Test
    public void testUserAuthorize() {
        WebDriver driver = getDriver("http://localhost:8080/");
        String login = "empl";
        driver.findElement(By.name("j_username")).sendKeys(login);
        driver.findElement(By.name("j_password")).sendKeys("123");
        driver.findElement(By.name("submit")).click();
        String welcomeMessage = driver.findElement(By.id("welcomeText")).getText();
        Assert.assertEquals(welcomeMessage, String.format("Welcome: %s | Logout", login));
        closeDriver(driver);
    }

    @Test
    public void testAddingStation() {
        WebDriver driver = getDriver("http://localhost:8080/");
        String stationTitle = "Samara";
        driver.findElement(By.name("j_username")).sendKeys("empl");
        driver.findElement(By.name("j_password")).sendKeys("123");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("Add Station")).click();
        driver.findElement(By.id("stationTitle")).sendKeys(stationTitle);
        driver.findElement(By.id("timezone")).sendKeys("10");
        driver.findElement(By.id("addStationButton")).click();
        String message = driver.findElement(By.id("successMessage")).getText();
        Assert.assertEquals(message, String.format("Station %s is added!", stationTitle));
        closeDriver(driver);
    }
}
