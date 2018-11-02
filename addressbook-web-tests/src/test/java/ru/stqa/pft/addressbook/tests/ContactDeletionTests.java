package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        if(! app.getContacrHelper().isThereAContact()){
            app.getContacrHelper().createContact();
            app.getNavigationHelper().returnToHomePage();
        }
        app.getContacrHelper().selectContacts();
        app.getContacrHelper().deletionSelectedContacts();
        app.getNavigationHelper().acceptAlert();
    }
}
