package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties configProp;
    private static Properties affiliateProp;
    private static Properties registerInvalidProp;
    private static Properties forgetPasswordProp;

    public static Properties getProperties() {

        if (configProp == null) {

            try {

                FileInputStream fis =
                        new FileInputStream(
                                "src/test/resources/config.properties"
                        );

                configProp = new Properties();

                configProp.load(fis);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return configProp;
    }

    public static Properties getAffiliateProperties() {

        if (affiliateProp == null) {

            try {

                FileInputStream fis =
                        new FileInputStream(
                                "src/test/resources/AffilateTestData.properties"
                        );

                affiliateProp = new Properties();

                affiliateProp.load(fis);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return affiliateProp;
    }

    public static Properties getRegisterDataProperties() {

        if (registerInvalidProp == null) {

            try {

                FileInputStream fis =
                        new FileInputStream(
                                "src/test/resources/InvalidRegisterData.properties"
                        );

                registerInvalidProp = new Properties();

                registerInvalidProp.load(fis);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return registerInvalidProp;
    }

    public static Properties getForgetPasswordProperties() {

        if (forgetPasswordProp == null) {

            try {

                FileInputStream fis =
                        new FileInputStream(
                                "src/test/resources/ForgetPassworddata.properties"
                        );

                forgetPasswordProp = new Properties();
                forgetPasswordProp.load(fis);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return forgetPasswordProp;
    }
}