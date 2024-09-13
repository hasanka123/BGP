package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = "test-output/screenshots/" + screenshotName + ".png";
        FileUtils.copyFile(srcFile, new File(dest));
        return dest;
    }
}