package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContact();
    fillContactForm(new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru"));
    submitContactCreation();
  }
}
