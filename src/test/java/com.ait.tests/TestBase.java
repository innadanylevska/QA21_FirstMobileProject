package com.ait.tests;

import com.ait.fw.AppManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

//ctrl alt +o
public class TestBase {//extends appManager staem na Test base i alt enter rename to app


    protected static AppManager app = new AppManager();//rename to app protected static

    @BeforeMethod
    public void setUp() throws MalformedURLException {//na init alt enter except add
        app.init();

    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }
//ctrl alt l

}
