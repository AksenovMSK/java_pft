package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        if(! app.getContacrHelper().isThereAContact()){
            app.getContacrHelper().createContact();
            app.getNavigationHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContacrHelper().getContactList();
        app.getContacrHelper().selectContacts(before.size() - 1);
        app.getContacrHelper().deletionSelectedContacts();
        app.getNavigationHelper().acceptAlert();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContacrHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before,after);
    }
}
