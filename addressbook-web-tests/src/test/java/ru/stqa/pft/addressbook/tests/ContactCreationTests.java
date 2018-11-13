package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation(){
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Nametest")
            .withLastname("Lastnametest")
            .withAddress("Test home 123")
            .withMobile("111111111")
            .withEmail("test@mail.ru")
            .withGroup("test1");
    app.contact().create(contact,true);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }
}
