package com.ait.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.awt.*;

public class MeinScreenHelper extends BaseHelper {
    public MeinScreenHelper(AppiumDriver driver) {
        super(driver);
    }

    public boolean isNoRemaindTextPresent() {
        return isElementPresent(By.xpath("//*[@resource-id='com.blanyal.remindly:id/no_reminder_text']"));
    }

    //new mainScr
    public void tapOnAddReminder() {
        tap(By.id("add_reminder"));
    }

    //new mainScrPage
    //po text or total as now
    public int getTotalReminders() {
//        int idcount = driver.findElements(By.id("thumbnail_image")).size();
//        System.out.println("Total reminders quantity is" + idcount);
//        return idcount;//return driver.findElements(By.id("thumbnail_image")).size(); potom bez sys out
        return driver.findElements(By.id("thumbnail_image")).size();
    }

    public String isTitlePresent(String title) {

        String text = driver.findElement(By.id("")).getText();
        System.out.println(text);
        return text;
    }
    public void removeReminder() {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int y = size.height /5 ;

        action.longPress(PointOption.point(x,y))
                .release().perform();
        tap(By.id("discard_reminder"));
    }
    //ctrl alt l
}
