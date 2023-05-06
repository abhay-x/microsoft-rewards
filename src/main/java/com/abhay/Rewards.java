package com.abhay;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Rewards {
    public static void main(String[] args) throws InterruptedException {
//        String taskName = "msedge"; // Replace with the name of the process you want to terminate
//        try {
//            Process process = Runtime.getRuntime().exec("pkill " + taskName);
//            process.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

        String taskName = "msedge.exe"; // Replace with the name of the app you want to close
        try {
            Runtime.getRuntime().exec("taskkill /f /im " + taskName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        
//        String profiles[] = {"Default", "Profile 1", "Profile 2", " Profile 3", "Profile 4", "Profile 5"};
        String profiles[] = {"Default", "Profile 1", "Profile 2"};
        for (String profile : profiles) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--headless=new");
            edgeOptions.addArguments("user-data-dir=C:\\Users\\abhay\\Desktop\\User Data");
            edgeOptions.addArguments("profile-directory="+profile);
            WebDriver driver = new EdgeDriver(edgeOptions);
            driver.get("https://www.bing.com/search?q=rose&form=QBLH&sp=-1&ghc=1&lq=0&pq=rose&sc=10-4&qs=n&sk=&cvid=0CA72505D3E14DF68F0E204476A1343D&ghsh=0&ghacc=0&ghpl=");

            String keywords[] = {"Rose", "Daisy", "Sunflower", "Lily", "Tulip", "Orchid"};

            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            for (String keyword : keywords) {
                WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"sb_form_q\"]"));
                searchBox.click();
                searchBox.clear();
                searchBox.sendKeys(keyword);
                searchBox.sendKeys(Keys.ENTER);

                Thread.sleep(3000);

                String pcSearchPointsXpath = "/html/body/header/div";
                String points = driver.findElement(By.xpath(pcSearchPointsXpath)).getText();
                System.out.println(driver.getTitle());
                System.out.println("Points: " + points);
                driver.navigate().back();
            }
            driver.quit();
        }
        
    }
}