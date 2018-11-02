package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class GroupHelper extends BaseHelper {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void selectGroup (){
    click(By.name("selected[]"));
  }

  public void deletionSelectedGroups() {
    click(By.name("delete"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModifivation() {
    click(By.name("update"));
  }

  public void createGroup(GroupData groupData) {
    initGroupCreation();
    fillGroupForm(groupData);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    return isElementPresent(By.name("selected[]"));
  }
}
