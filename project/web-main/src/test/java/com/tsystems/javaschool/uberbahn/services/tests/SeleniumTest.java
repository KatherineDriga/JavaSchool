package com.tsystems.javaschool.uberbahn.services.tests;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

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
        driver.findElement(By.id("email")).sendKeys("user1@example.com");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.id("confPassword")).sendKeys("123");
        driver.findElement(By.id("firstName")).sendKeys("Anna");
        driver.findElement(By.id("lastName")).sendKeys("Rossi");
        driver.findElement(By.id("dateOfBirth")).sendKeys("02/02/2002");
        driver.findElement(By.id("registerUser")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String message = driver.findElement(By.id("successRegistration")).getText();
        Assert.assertEquals(message,"You've successfully registered on our website!");
        closeDriver(driver);
    }

    @Test
    public void testStationTimetable() {
        WebDriver driver = getDriver("http://localhost:8080/");
        driver.findElement(By.linkText("Station Timetable")).click();
        Select select = new Select(driver.findElement(By.name("station")));
        select.selectByVisibleText("Moscow");
        driver.findElement(By.id("search")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println(driver.getCurrentUrl());
        closeDriver(driver);
    }

    @Test
    public void testEmployeeLinksAvailable() {
        WebDriver driver = getDriver("http://localhost:8080/");
        driver.findElement(By.name("j_username")).sendKeys("empl");
        driver.findElement(By.name("j_password")).sendKeys("123");
        driver.findElement(By.name("submit")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Add Station")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Add Route")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Add Train")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Find Train & Registered Passengers")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("logout")).click();
        closeDriver(driver);
    }

    @Test
    public void testUserAuthorize() {
        WebDriver driver = getDriver("http://localhost:8080/");
        String login = "empl";
        driver.findElement(By.name("j_username")).sendKeys(login);
        driver.findElement(By.name("j_password")).sendKeys("123");
        driver.findElement(By.name("submit")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String welcomeMessage = driver.findElement(By.id("welcomeText")).getText();
        Assert.assertEquals(welcomeMessage, String.format("Welcome: %s | Logout", login));
        driver.findElement(By.id("logout")).click();
        closeDriver(driver);
    }

    @Test
    public void testAddingStation() {
        WebDriver driver = getDriver("http://localhost:8080/");
        String stationTitle = "Samara";
        driver.findElement(By.name("j_username")).sendKeys("empl");
        driver.findElement(By.name("j_password")).sendKeys("123");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("Add Station")).click();
        driver.findElement(By.id("stationTitle")).sendKeys(stationTitle);
        driver.findElement(By.id("timezone")).sendKeys("10");
        driver.findElement(By.id("addStationButton")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String message = driver.findElement(By.id("successMessage")).getText();
        Assert.assertEquals(message, String.format("Station %s is added!", stationTitle));
        driver.findElement(By.id("logout")).click();
        closeDriver(driver);
    }
}
