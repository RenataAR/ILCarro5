package tests;

import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    ApplicationManager app = new ApplicationManager(
            System.getProperty("browser", BrowserType.CHROME)
    );

    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        app.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void started(Method method){
        logger.info("******* Start test " + method);
    }

    @AfterMethod(alwaysRun = true)
    public void finished(){
        logger.info("*********Finish test");
    }
}
