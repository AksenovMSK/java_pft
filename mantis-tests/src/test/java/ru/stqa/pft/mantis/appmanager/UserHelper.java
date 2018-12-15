package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends BaseHelper {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword() {
        click(By.xpath("//input[@value='Reset Password']"));
    }

    public void changeMail(String username, String email){
        type(By.name("email"), "new" + email);
        click(By.xpath("//input[@value='Update User']"));
        click(By.linkText("Manage Users"));
        click(By.xpath("//a[contains(text(),'" + username + "')]"));
    }
}
