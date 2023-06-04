package com.ait.tests;

import com.ait.fw.BaseHelper;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainScreenTests extends TestBase {
    @Test
    public void appLaunchTest(){
        Assert.assertTrue(app.getMainScreen().isNoRemaindTextPresent());
    }


    //ctrl alt l

}
