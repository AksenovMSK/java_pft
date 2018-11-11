package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation(){
    List<ContactData> before = app.getContacrHelper().getContactList();
    ContactData contact = new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru", "test1");
    app.getContacrHelper().createContact(contact,true);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContacrHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before,after);
  }
}
