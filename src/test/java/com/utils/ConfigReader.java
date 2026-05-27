package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties configProp;
    private static Properties affiliateProp;
    private static Properties registerInvalidProp;
    private static Properties forgetPasswordProp;

    // =========================
    // MAIN CONFIG FILE
    // =========================
    public static Properties getProperties() {

        if (configProp == null) {

            try {

                configProp = new Properties();

                String path =
                        System.getProperty("user.dir")
                        + "/src/test/resources/config.properties";

                FileInputStream fis =
                        new FileInputStream(path);

                configProp.load(fis);

            } catch (Exception e) {

                throw new RuntimeException(
                        "Failed to load config.properties file", e
                );
            }
        }

        return configProp;
    }

    // =========================
    // AFFILIATE TEST DATA
    // =========================
    public static Properties getAffiliateProperties() {

        if (affiliateProp == null) {

            try {

                affiliateProp = new Properties();

                String path =
                        System.getProperty("user.dir")
                        + "/src/test/resources/AffilateTestData.properties";

                FileInputStream fis =
                        new FileInputStream(path);

                affiliateProp.load(fis);

            } catch (Exception e) {

                throw new RuntimeException(
                        "Failed to load AffilateTestData.properties file", e
                );
            }
        }

        return affiliateProp;
    }

    // =========================
    // INVALID REGISTER DATA
    // =========================
    public static Properties getRegisterDataProperties() {

        if (registerInvalidProp == null) {

            try {

                registerInvalidProp = new Properties();

                String path =
                        System.getProperty("user.dir")
                        + "/src/test/resources/InvalidRegisterData.properties";

                FileInputStream fis =
                        new FileInputStream(path);

                registerInvalidProp.load(fis);

            } catch (Exception e) {

                throw new RuntimeException(
                        "Failed to load InvalidRegisterData.properties file", e
                );
            }
        }

        return registerInvalidProp;
    }

    // =========================
    // FORGET PASSWORD DATA
    // =========================
    public static Properties getForgetPasswordProperties() {

        if (forgetPasswordProp == null) {

            try {

                forgetPasswordProp = new Properties();

                String path =
                        System.getProperty("user.dir")
                        + "/src/test/resources/ForgetPassworddata.properties";

                FileInputStream fis =
                        new FileInputStream(path);

                forgetPasswordProp.load(fis);

            } catch (Exception e) {

                throw new RuntimeException(
                        "Failed to load ForgetPassworddata.properties file", e
                );
            }
        }

        return forgetPasswordProp;
    }
}