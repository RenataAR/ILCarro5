package manager;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[.=' Log in ']"));
    }

    public void fillLoginForm(User user) {
        type(By.id("email"),user.getEmail());
        type(By.id("password"),user.getPassword());
    }

    public void submitLoginForm() {
        wd.findElement(By.xpath("//button[@type=\"submit\"]")).submit();
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[.=' Logout ']"));
    }

    public void logOut() {
        click(By.xpath("//a[.=' Logout ']"));
    }

    public void clickOkButton() {
        if(isElementPresent(By.xpath("//button[.='Ok']"))){
            click(By.xpath("//button[.='Ok']"));
        }
    }

    public boolean isLoginSuccess() {
        WebDriverWait wait = new WebDriverWait(wd,10);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//h2[.='Logged in success']"))));
        return wd.findElement(By.xpath("//h2[.='Logged in success']")).getText().contains("success");
    }

    public boolean isLoginFailed() {
        WebDriverWait wait = new WebDriverWait(wd,10);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//div[.=\"It'snot look like email\"]"))));
        return wd.findElement(By.xpath("//div[.=\"It'snot look like email\"]")).getText().contains("like email");
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[.=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("nam"), user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void submitRegistrationForm() {
        wd.findElement(By.xpath("//button[@type='submit']")).submit();
    }

    public boolean isRegisteredSuccess() {
        WebDriverWait wait = new WebDriverWait(wd,10);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//h2[.='You are logged in success']"))));
        return wd.findElement(By.xpath("//h2[.='You are logged in success']")).getText().contains("success");
    }

    public void clickCheckBox() {
        JavascriptExecutor script = (JavascriptExecutor) wd;
        script.executeScript("document.querySelector('#terms-of-use').click();");
    }

    public boolean isRegisteredFailed() {
        WebDriverWait wait = new WebDriverWait(wd,10);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//div[.='Wrong email format']"))));
        return wd.findElement(By.xpath("//div[.='Wrong email format']")).getText().contains("email format");
    }
}
