package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContact();
    fillContactForm(new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru"));
    submitContactCreation();
    logout();
  }
}
