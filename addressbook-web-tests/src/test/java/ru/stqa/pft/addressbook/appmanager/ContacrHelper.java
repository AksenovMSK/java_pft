package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContacrHelper extends BaseHelper {

    public ContacrHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("//*[@id=\"content\"]/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("address"), contactData.getAddress());
      type(By.name("mobile"), contactData.getMobile());
      type(By.name("email"), contactData.getEmail());
    }

    public void initContact() {
      click(By.linkText("add new"));
    }

    public void selectContacts() {
        click(By.name("selected[]"));
    }

    public void deletionSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModifivation() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }
}
