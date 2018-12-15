package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends BaseHelper {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword() {
        click(By.xpath("//input[@value='Reset Password']"));
    }
}
