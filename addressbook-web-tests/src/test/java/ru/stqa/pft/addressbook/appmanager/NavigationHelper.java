package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    click(By.linkText("groups"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void homePage() {
    click(By.id("logo"));
  }
  public void addedGroupPage(){
    click(By.cssSelector("i > a"));
  }
}

