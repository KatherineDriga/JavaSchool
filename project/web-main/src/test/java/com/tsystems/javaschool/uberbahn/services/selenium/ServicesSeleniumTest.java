package com.tsystems.javaschool.uberbahn.services.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServicesSeleniumTest {

    String userLogin = "anna";
    String userPassword = "123";
    String stationTitle = "Omsk";
    String routeTitle = "Moscow - Omsk";

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

    private WebDriver loginEmployee() throws InterruptedException {
        WebDriver driver = getDriver("http://localhost:8080/");
        Thread.sleep(2000);
        driver.findElement(By.name("j_username")).sendKeys(userLogin);
        Thread.sleep(2000);
        driver.findElement(By.name("j_password")).sendKeys(userPassword);
        Thread.sleep(2000);
        driver.findElement(By.name("submit")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        return driver;
    }



    @Test
    public void testRegistration() throws InterruptedException {

        WebDriver driver = getDriver("http://localhost:8080/");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Sign up")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.id("login")).sendKeys(userLogin);
        Thread.sleep(2000);
        driver.findElement(By.id("email")).sendKeys("anna@example.com");
        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys(userPassword);
        Thread.sleep(2000);
        driver.findElement(By.id("confPassword")).sendKeys(userPassword);
        Thread.sleep(2000);
        driver.findElement(By.id("firstName")).sendKeys("Anna");
        Thread.sleep(2000);
        driver.findElement(By.id("lastName")).sendKeys("Brown");
        Thread.sleep(2000);
        driver.findElement(By.id("dateOfBirth")).sendKeys("02/02/2002");
        Thread.sleep(2000);
        driver.findElement(By.id("registerUser")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        String message = driver.findElement(By.id("successRegistration")).getText();
        Assert.assertEquals(message,"You've successfully registered on our website!");
        Thread.sleep(5000);
        driver.findElement(By.id("loginLink")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.findElement(By.name("j_username")).sendKeys(userLogin);
        Thread.sleep(2000);
        driver.findElement(By.name("j_password")).sendKeys(userPassword);
        Thread.sleep(2000);
        driver.findElement(By.name("submit")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.findElement(By.id("logout")).click();
        Thread.sleep(2000);
        closeDriver(driver);
    }

    @Test
    public void testBuyTicket() throws InterruptedException {
        WebDriver driver = loginEmployee();
        Select selectDeparture = new Select(driver.findElement(By.id("stationOfDeparture")));
        selectDeparture.selectByVisibleText("Saint-Petersburg");
        Thread.sleep(2000);
        Select selectArrival = new Select(driver.findElement(By.id("stationOfArrival")));
        selectArrival .selectByVisibleText("Moscow");
        Thread.sleep(2000);
        driver.findElement(By.id("searchTrains")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        List<WebElement> radioButton = driver.findElements(By.name("radioButton"));
        if (radioButton.size() > 0) {
            radioButton.get(0).click();
            Thread.sleep(2000);
            driver.findElement(By.id("chooseTrain")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
            driver.findElement(By.id("buyTicketButton")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        }
        driver.findElement(By.id("logout")).click();
        Thread.sleep(2000);
        closeDriver(driver);
    }

    @Test
    public void testStationTimetable() throws InterruptedException {
        WebDriver driver = getDriver("http://localhost:8080/");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Station Timetable")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        Select select = new Select(driver.findElement(By.name("station")));
        select.selectByVisibleText("Moscow");
        Thread.sleep(2000);
        driver.findElement(By.id("search")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(5000);
        closeDriver(driver);
    }

    @Test
    public void testPurchasedTickets () throws InterruptedException {
        WebDriver driver = getDriver("http://localhost:8080/");
        Thread.sleep(2000);
        driver.findElement(By.name("j_username")).sendKeys(userLogin);
        Thread.sleep(2000);
        driver.findElement(By.name("j_password")).sendKeys(userPassword);
        Thread.sleep(2000);
        driver.findElement(By.name("submit")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.findElement(By.linkText("My tickets")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("sinceDate")).sendKeys("01/03/2016");
        Thread.sleep(2000);
        driver.findElement(By.id("untilDate")).sendKeys("01/05/2016");
        Thread.sleep(2000);
        driver.findElement(By.id("searchTickets")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("logout")).click();
        Thread.sleep(2000);
        closeDriver(driver);
    }

    @Test
    public void testUserAuthentication() throws InterruptedException {
        WebDriver driver = getDriver("http://localhost:8080/");
        String login = "empl";
        Thread.sleep(2000);
        driver.findElement(By.name("j_username")).sendKeys(login);
        Thread.sleep(2000);
        driver.findElement(By.name("j_password")).sendKeys("123");
        Thread.sleep(2000);
        driver.findElement(By.name("submit")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        String welcomeMessage = driver.findElement(By.id("welcomeText")).getText();
        Assert.assertEquals(welcomeMessage, String.format("Welcome: %s | Logout", login));
        Thread.sleep(5000);
        driver.findElement(By.id("logout")).click();
        Thread.sleep(2000);
        closeDriver(driver);
    }

    @Test
    public void testEmployeeLinksAvailable() throws InterruptedException {
        WebDriver driver = loginEmployee();
        driver.findElement(By.linkText("Add Station")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.linkText("Add Route")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.linkText("Add Train")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.linkText("Find Train & Registered Passengers")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.id("logout")).click();
        Thread.sleep(2000);
        closeDriver(driver);
    }


    @Test
    public void testAddStation() throws InterruptedException {
        WebDriver driver = loginEmployee();
        driver.findElement(By.linkText("Add Station")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.id("stationTitle")).sendKeys(stationTitle);
        Thread.sleep(2000);
        driver.findElement(By.id("timezone")).sendKeys("6");
        Thread.sleep(2000);
        driver.findElement(By.id("addStationButton")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String message = driver.findElement(By.id("successMessage")).getText();
        Assert.assertEquals(message, String.format("Station %s is added!", stationTitle));
        Thread.sleep(3000);
        driver.findElement(By.id("logout")).click();
        Thread.sleep(2000);
        closeDriver(driver);
    }

    @Test
    public void testAddRoute() throws InterruptedException {
        WebDriver driver = loginEmployee();
        driver.findElement(By.linkText("Add Route")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.id("routeTitle")).sendKeys(routeTitle);
        Thread.sleep(2000);
        driver.findElement(By.id("numberOfStations")).sendKeys("3");
        Thread.sleep(2000);
        driver.findElement(By.id("timeOfDeparture")).sendKeys("16:00");
        Thread.sleep(2000);
        driver.findElement(By.id("pricePerMinute")).sendKeys("1.5");
        Thread.sleep(2000);
        driver.findElement(By.id("addStationsButton")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        Select select = new Select(driver.findElement(By.id("1")));
        select.selectByVisibleText("Moscow");
        Thread.sleep(2000);
        select = new Select(driver.findElement(By.id("2")));
        select.selectByVisibleText("Ekaterinburg");
        Thread.sleep(2000);
        select = new Select(driver.findElement(By.id("3")));
        select.selectByVisibleText("Omsk");
        Thread.sleep(2000);
        driver.findElement(By.id("2m")).sendKeys("2520");
        Thread.sleep(2000);
        driver.findElement(By.id("3m")).sendKeys("2880");
        Thread.sleep(2000);
        driver.findElement(By.id("addRouteButton")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String message = driver.findElement(By.id("routeInfo")).getText();
        Assert.assertEquals(message, String.format("Route Information"));
        Thread.sleep(5000);
        driver.findElement(By.id("logout")).click();
        Thread.sleep(2000);
        closeDriver(driver);
    }

    @Test
    public void addTrain() throws InterruptedException {
        WebDriver driver = loginEmployee();
        driver.findElement(By.linkText("Add Train")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        Select select = new Select(driver.findElement(By.id("route")));
        select.selectByVisibleText(routeTitle);
        Thread.sleep(2000);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String date = LocalDate.now().plusDays(2).format(formatter);
        driver.findElement(By.id("dateOfDeparture")).sendKeys(date);
        Thread.sleep(2000);
        driver.findElement(By.id("numberOfSeats")).sendKeys("100");
        Thread.sleep(2000);
        driver.findElement(By.id("priceCoefficient")).sendKeys("1.5");
        Thread.sleep(2000);
        driver.findElement(By.id("addTrainButton")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.findElement(By.id("logout")).click();
        Thread.sleep(2000);
        closeDriver(driver);
    }

    @Test
    public void findPassengers() throws InterruptedException {
        WebDriver driver = loginEmployee();
        driver.findElement(By.linkText("Find Train & Registered Passengers")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        Select select = new Select(driver.findElement(By.id("route")));
        select.selectByVisibleText("St.-Petersburg - Sochi");
        Thread.sleep(2000);
        List<WebElement> radioButton = driver.findElements(By.name("radioButton"));
        if (radioButton.size() > 0) {
            radioButton.get(0).click();
            Thread.sleep(2000);
            driver.findElement(By.id("chooseTrainButton")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        }
        driver.findElement(By.id("logout")).click();
        Thread.sleep(2000);
        closeDriver(driver);
    }
}


