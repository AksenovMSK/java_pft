package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends BaseHelper  {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void users(){
        click(By.linkText("Manage Users"));
    }
}
