package com.hooks;

import com.driver.DriverClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
public class Hooks {

    @Before
    public static void setUp() {

        DriverClass.initDriver();
    }

    @After
    public static void tearDown() {

        DriverClass.quitDriver();
    }
}