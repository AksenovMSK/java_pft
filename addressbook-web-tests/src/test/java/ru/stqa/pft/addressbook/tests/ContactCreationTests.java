package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.initContact();
    app.fillContactForm(new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru"));
    app.submitContactCreation();
  }
}
