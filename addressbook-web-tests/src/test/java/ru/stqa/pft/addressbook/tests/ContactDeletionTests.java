package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.contact().all().size() == 0){
            ContactData contact = new ContactData()
                    .withFirstname("Nametest")
                    .withLastname("Lastnametest")
                    .withAddress("Test home 123")
                    .withMobile("111111111")
                    .withEmail("test@mail.ru")
                    .withGroup("test1");
            app.contact().create(contact, true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion(){
        Set<ContactData> before = app.contact().all();
        ContactData deletionContact = before.iterator().next();
        app.contact().delete(deletionContact);
        app.goTo().acceptAlert();
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletionContact);
        Assert.assertEquals(before,after);
    }
}
