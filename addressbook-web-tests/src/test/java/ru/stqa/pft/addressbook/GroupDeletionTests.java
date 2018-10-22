package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage();
    deletionSelectedGroup();
    returnToGroupPage();
    logout();
  }

}
