package com.ait.tests;

import com.ait.model.Reminder;
import io.appium.java_client.TouchAction;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class AddReminderTests extends TestBase{
    @Test
    public void addReminderWithTitleTest(){
        int quantityBeforeAdd;
        int quantityAfterAdd;
        quantityBeforeAdd = app.getMainScreen()
                .getTotalReminders();
        //tap on add reminder
        app.getMainScreen().tapOnAddReminder();
        //enter reminder title
        app.getReminder().enterTitle("Test");
        //save reminder
        app.getReminder().saveReminder();
        //asser add new reminder
        quantityAfterAdd = app.getMainScreen()
                .getTotalReminders();

        Assert.assertEquals(quantityAfterAdd,quantityBeforeAdd +1);//nevidimka return  return driver.findElements(By.id("thumbnail_image")).size(); bez sys out
    }

    public void addReminderWithRandomSwipeTest(){

        //qantity tormozit test ctrl d tam delete
        int quantityBeforeAdd;
        int quantityAfterAdd;
        quantityBeforeAdd = app.getMainScreen()
                .getTotalReminders();
        //tap on add reminder
        app.getMainScreen().tapOnAddReminder();
        //enter reminder title
        app.getReminder().enterTitle("Test");
        app.getReminder().tapOnDate();
        //coose random month
        app.getReminder().selectMonth("future");
        //chose date
        app.getReminder().selectDate();
        //click on ok
        app.getReminder().tapOnOk();
        //save reminder
        app.getReminder().saveReminder();
        //asser add new reminder
        quantityAfterAdd = app.getMainScreen()
                .getTotalReminders();

        Assert.assertEquals(quantityAfterAdd,quantityBeforeAdd +1);//nevidimka return  return driver.findElements(By.id("thumbnail_image")).size(); bez sys out
    }

    public void addReminderWithAllDataTest(){

        //tap on add reminder
        app.getMainScreen().tapOnAddReminder();
        //enter reminder title
        app.getReminder().enterTitle("Feiertag");
        app.getReminder().tapOnDate();
        //choose random month
        //or past or future
        app.getReminder().selectSerialMonth("future", "JUL",3);
        //chose date
        app.getReminder().selectDate(29);

        //test start bez
        app.getReminder().tapOnYear();
        app.getReminder().selectYear("future","2026");
        app.getReminder().tapOnOk();
        app.getReminder().tapOnTimeField();
        app.getReminder().selectTime("pm",805,922,538,656);//posle dobavl methods add didits 00 chasy
        app.getReminder().tapOnOk();
        //select repetition interval
        app.getReminder().selectRepetition("3");//string interval
        app.getReminder().swipe(0.8,0.5);
        app.getReminder().selectTypeOfRepetition("Month");
        //click on ok
        //app.getReminder().saveDate();//rename tapOnOk
        //save reminder
        app.getReminder().saveReminder();
        //Assert by text
        app.getMainScreen().isTitlePresent("Feiertag");
        Assert.assertTrue(app.getMainScreen().isTitlePresent("Freirtag").contains("Feiertag"));
//по моделям  тут могу попробовать сама допісать

}

    public void addReminderShortTest(){
        app.getMainScreen().tapOnAddReminder();
        app.getReminder().enterAllData(Reminder.builder()
                .title("Freirtag").period("future").month("JUN").number(3).index(15).period2("future").year("2026")
                .timeOfDay("pm").xHour(805).yHour(922).xMin(538).yMin(656).repeat("4")
                .start(0.8).stop(0.5).typeRep("Month").build()
        );

        app.getReminder().saveReminder();
        Assert.assertTrue(app.getMainScreen().isNoRemaindTextPresent());
    }

@AfterMethod
    public void removeReminder(){
    app.getMainScreen().removeReminder();
}


    //ctrl alt l
}
