package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation(){
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withFirstname("Nametest")
            .withLastname("Lastnametest")
            .withAddress("Test home 123")
            .withMobile("111111111")
            .withEmail("test@mail.ru").withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before,after);
  }
}
