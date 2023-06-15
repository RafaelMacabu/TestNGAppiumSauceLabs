package listeners;

import base.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static base.BasePage.getDriver;

public class TestListener implements ITestListener {

    public void onTestFailure(ITestResult result){
        if(result.getThrowable() != null){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            System.out.println(sw.toString());
        }

        File file = BasePage.
                getDriver().
                getScreenshotAs(OutputType.FILE);

        Map<String,String> params = new HashMap<>();
        params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String imagePath  = "Screenshot" + File.separator + params.get("platformName") + "_" + params.get("udid")
                + File.separator + BasePage.getDatetime() + File.separator + result.getTestClass().getRealClass().getSimpleName()
                + File.separator + result.getName() + ".png";

        String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;

        try {
            FileUtils.copyFile(file,new File(imagePath));
            Reporter.log("This is the sample screenshot ");
            Reporter.log("<a href='" + completeImagePath + "'> <img src='" + completeImagePath + "' height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
