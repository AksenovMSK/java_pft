package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.contact().all().size() == 0){
            ContactData contact = new ContactData()
                    .withFirstname("Nametest")
                    .withLastname("Lastnametest")
                    .withAddress("Test home 123")
                    .withMobile("111111111")
                    .withEmail("test@mail.ru").withGroup("test1");
            app.contact().create(contact, true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification(){

        Set<ContactData> before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId())
                .withFirstname("Nametest")
                .withLastname("Lastnametest")
                .withAddress("Test home 123")
                .withMobile("111111111")
                .withEmail("test@mail.ru");
        app.contact().modify(contact,false);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifyContact);
        before.add(contact);
        Assert.assertEquals(before,after);
    }
}
