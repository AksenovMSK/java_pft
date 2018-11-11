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
        if(! app.getContacrHelper().isThereAContact()){
            app.getContacrHelper().createContact(new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru", "test1"), true);
            app.getNavigationHelper().returnToHomePage();
        }
    }

    @Test
    public void testContactModification(){

        List<ContactData> before = app.getContacrHelper().getContactList();
        ContactData contact = new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru", null);
        int index = before.size() - 1;
        app.getContacrHelper().modifyContact(contact,index,false);
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContacrHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before,after);
    }
}
