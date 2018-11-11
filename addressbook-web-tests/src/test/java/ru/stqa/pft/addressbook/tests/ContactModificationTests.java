package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.contact().list().size() == 0){
            app.contact().create(new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru", "test1"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification(){

        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru", null);
        int index = before.size() - 1;
        app.contact().modify(contact,index,false);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before,after);
    }
}
