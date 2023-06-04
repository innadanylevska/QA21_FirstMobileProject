package com.ait.fw;

import com.ait.model.Reminder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReminderHelper extends BaseHelper {
    public ReminderHelper(AppiumDriver driver) {
        super(driver);
    }
    //v appmanager obyavit init

    public void enterTitle(String title) {
        text(By.id("reminder_title"), title);
    }

    public void saveReminder() {
        tap(By.id("save_reminder"));//add aserts
    }

    public void selectMonth(String time) {
        pause(1000);
        if (time.equals("past")) {
            //down
            swipe(0.5, 0.8);
        }
        if (time.equals("future")) {
            //up
            swipe(0.7, 0.4);
        }

    }

    public void swipe(double start, double stop) {//rivate
        //razmer h i l
        //Actions for selenium
        //TouchAction for Appium here
        TouchAction action = new TouchAction<>(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;// altenter introduse local var //final
        int startY = (int) (size.height * start);//final double
        int stopY = (int) (size.height * stop);//final double

        action.longPress(PointOption.point(x, startY))
                .moveTo(PointOption.point(x, stopY))
                .release().perform();//.release().perform(); vsegda
    }

    public void tapOnDate() {
        tap(By.id("date"));
    }

    public void selectDate(int index) {
        List<WebElement> days = driver.findElements(By.className("-list-avds to see valid list"));
        days.get(index).click();
    }

    public void tapOnOk() {
        tap(By.id("ok"));
    }

    public void selectSerialMonth(String typeTime, String month, int number) {
        pause(1000);
        if (!selectedMonth().equals(month)) {
            for (int i = 0; i < number; i++) {
                if (typeTime.equals("future")) {
                    swipe(0.7, 0.4);
                } else if (typeTime.equals("past")) {
                    swipe(0.5, 0.8);
                }
            }

        }
    }
    /*public void selectCertainMonth(String typeTime, String month,int number) {
        pause(1000);
        if (!selectedMonth().equals(month)) {
            for (int i = 0; i < number; i++) {
                if (typeTime.equals("future")) {
                    swipe(0.7,0.4);
                } else if (typeTime.equals("past")) {
                    swipe(0.5,0.8);
                }
            }
        }
    }*/
    //2 swipe bez as may int num add  date_picker_month
}

    private String selectedMonth() {
        return driver.findElement(By.id("date_picker_month")).getText();
    }

    public void tapOnYear() {
        tap((By.id("date_picker_year"));
    }

    //11.05
    public void selectYear(String typeTm, String year) {
        pause(1000);
        if (!selectedYear().equals(year)) {
            if (typeTm.equals("future")) {
                swipeUpUntilNeededYear(year);
            } else if (typeTm.equals("past")) {
                swipeDownUntilNeededYear(year);
            }
        }
        tap(By.id("month_text_view"));//dopisali poslednee
    }

    private void swipeDownUntilNeededYear(String year) {
        while (!selectedYear().equals(year)) {
            moveUpInElement(By.className("android.widget.ListView", 0.5, 0.6));
            selectedYear();
        }

    }

    //ili class ili text getText
    private void swipeUpUntilNeededYear(String year) {
        while (!selectedYear().equals(year)) {
            moveUpInElement(By.className("android.widget.ListView", 0.6, 0.5));
            selectedYear();
        }
    }

    //zahodim vnutr el
    private void moveUpInElement(By locator, double down, double up) {//param was className
        TouchAction action = new TouchAction<>(driver);
        Dimension size = driver.manage().window().getSize();//
        int downPoint = (int) (size.height * 0.6);//alt enter local var
        //size.height * 0.6;
        int upPoint = (int) (size.height * 0.5);
        //get
        WebElement element = driver.findElement(locator);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int middleX = (leftX + rightX / 2);

        action.longPress(PointOption.point(middleX, downPoint))
                .moveTo(PointOption.point(middleX, upPoint))
                .release().perform();


    }

    private String selectedYear() {
        return driver.findElement(By.id("month_text_view")).getText();

    }

    public void tapOnTimeField() {
        tap(By.id("time"));
    }

    public void selectTime(String typeTime, int xHour, int yHour, int xMin, int yMin) { //am 12-00 pm time
        pause(1000);

        if (typeTime.equals("am")) {
            tapWithCoordinates(179, 1318);
        } else if (typeTime.equals("pm")) {
            tapWithCoordinates(789, 1318);
        }
        tapWithCoordinates(xHour, yHour);
        tapWithCoordinates(xMin, yMin);//v test dobavit 805, 922
    }

    private void tapWithCoordinates(int x, int y) {
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(x, y)).release().perform();//am 2791318 pm 78901318

    }

    public void selectRepetition(String repeat) {//ok xpath textom
        tap(By.id("repeat_no_text"));
        pause(1000);
        text(By.className("android.widget.EditText"), repeat);
        tap(By.xpath("//*[@text='OK']"));//ok xpath textom ne getText() ??++++++++++++
    }

    public void selectTypeOfRepetition(String typeRep) {
        tap(By.id("RepeatType"));
        tap(By.id("//*[@text='" + typeRep + "']"));//mnogo items i skleili

    }
// click na enterAll introduse peram obj ... before main java model
    public void enterAllData(Reminder reminder) {
        enterTitle(reminder.getTitle());
        tapOnDate();
        selectSerialMonth(reminder.getPeriod(), reminder.getMonth(), reminder.number());
        selectDate(reminder.getIndex());
        tapOnYear();
        selectYear(reminder.getPeriod2(), reminder.getYear());
        tapOnOk();
        tapOnTimeField();
        selectTime(reminder.getTimeOfDay(), reminder.getXHour(), reminder.getYHour(), reminder.getXMin(), reminder.getYMin());
        tapOnOk();

        selectRepetition(reminder.getRepeat());
        swipe(reminder.getStart(), reminder.getStop());
        selectTypeOfRepetition(reminder.getTypeRep());

    }
    /*public void enterAllData(String title, String period, String month, int number,
                             int index, String period2, String year,
                             String timeOfDay, int xHour, int yHour, int xMin, int yMin,
                             String repeat, double start, double stop, String typeRep) {
        enterTitle(title);
        tapOnDate();
        selectCertainMonth(period,month,number);
        selectDate(index);
        tapOnYear();
        selectYear(period2,year);
        tapOnOk();
        tapOnTimeField();
        selectTime(timeOfDay,xHour,yHour,xMin,yMin);
        tapOnOk();
        selectRepetition(repeat);
        swipe(start,stop);
        selectTypeOfRepetition(typeRep);
    }*/


    //vsego test

    //ctrl alt l
}
