package com.dtt.constants;

import com.dtt.enums.ConfigProperties;
import com.dtt.utils.PropertyUtils;

public final class FrameworkConstants {

    /**
     * Private constructor to avoid external instantiation
     */
    private FrameworkConstants() {
    }

    private static final int EXPLICITWAIT = 10;
    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
    private static final String CONFIGFILEPATH = RESOURCESPATH + "/application.properties";
    private static final String JSONCONFIGFILEPATH = RESOURCESPATH + "/config/config.json";
    private static final String USEREXCELPATH = RESOURCESPATH + "/user.xlsx";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
    private static String extentReportFilePath = "";

    /**
     * @return Extent Report path where the index.html file will be generated.
     */
    public static String getExtentReportFilePath() {
        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    private static String createReportPath() {
        if (PropertyUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")) {
            return EXTENTREPORTFOLDERPATH + System.currentTimeMillis() + "/index.html";
        } else {
            return EXTENTREPORTFOLDERPATH + "/index.html";
        }
    }

    public static String getConfigFilePath() {
        return CONFIGFILEPATH;
    }

    public static String getUSEREXCELPath() {
        return USEREXCELPATH;
    }
}
