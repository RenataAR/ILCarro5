package tests;

import manager.NGListener;
import manager.ProviderData;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(NGListener.class)

public class LoginTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logOut();
        }
    }

    @Test(invocationCount = 3, groups = {"smoke"},dataProvider = "loginModelDto", dataProviderClass = ProviderData.class)
    public void loginPositiveTest(User user){
//        User user = User.builder()
//                .email("galina@gmail.com")
//                .password("Gg123456$")
//                .build();

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginForm();
        Assert.assertTrue(app.getUser().isLoginSuccess());
    }

    @Test(groups = {"smoke","regress"})
    public void loginNegativeTestWrongEmail(){
        User user = User.builder()
                .email("galinagmail.com")
                .password("Gg123456$")
                .build();

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginForm();
        Assert.assertTrue(app.getUser().isLoginFailed());
    }

    @Test(groups = {"regress"})
    public void loginNegativeTestWrongPassword(){
        User user = User.builder()
                .email("galina@gmail.com")
                .password("Gg123456")
                .build();

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginForm();
        Assert.assertTrue(app.getUser().isLoginFailed());
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition(){
        app.getUser().clickOkButton();
    }
}
