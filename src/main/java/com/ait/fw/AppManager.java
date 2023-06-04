package com.ait.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AppManager {
    AppiumDriver driver;


    DesiredCapabilities capabilities;
    MeinScreenHelper mainScreen;
    ReminderHelper reminder;

    //1 pole iz 36         mainScreen = new MeinScreenHelper(driver);// i getter generate in field free
    public MeinScreenHelper getMainScreen() {
        return mainScreen;
    }

    //reminder = new ReminderHelper(driver);// i getter generate in field free
    public ReminderHelper getReminder() {//activno v test metod add AddRewminderTests
        return reminder;
    }

    public void init() throws MalformedURLException {//protected
        capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("deviceName", "mob");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "com.blanyal.remindly");
        capabilities.setCapability("appActivity", "com.blanyal.remindme.MainActivity");
        //moy path to C:\Users\innad\Downloads\com.blanyal.remindly_2_apps.evozi.com.apk C:/Users/Tel-Ran.de/Documents/apk/com.blanyal.remindly_2_apps.evozi.com.apk
        capabilities.setCapability("app", "C:/Users/innad/Downloads/com.blanyal.remindly_2_apps.evozi.com.apk ");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);//add except
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//ne 7.4.1 (Duration.ofSeconds(20));
        mainScreen = new MeinScreenHelper(driver);// i getter generate in field free
        reminder = new ReminderHelper(driver);// i getter generate in field free

    }

    public void stop() { //protected
        driver.quit();
    }
    //ctrl alt l
}
