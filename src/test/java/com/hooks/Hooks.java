package com.hooks;

import com.driver.DriverClass;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
public class Hooks {

    @BeforeAll
    public static void setUp() {
        DriverClass.initDriver();
    }

    @AfterAll
    public static void tearDown() {
        DriverClass.quitDriver();
    }
}