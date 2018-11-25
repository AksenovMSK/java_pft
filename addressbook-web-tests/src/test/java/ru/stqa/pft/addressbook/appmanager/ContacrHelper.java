package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContacrHelper extends BaseHelper {

    public ContacrHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("//*[@id=\"content\"]/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      type(By.name("address"), contactData.getAddress());
      type(By.name("mobile"), contactData.getMobilePhone());
      type(By.name("email"), contactData.getEmail());
      attach(By.name("photo"), contactData.getPhoto());

      if(creation){
          String existingText = wd.findElement(By.name("new_group")).getAttribute("value");
          if(! existingText.equals("[none]")) {
              new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
          }
      } else {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }

    public void initContact() {
      click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        click(By.name("selected[]"),index);
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification(int id) {

        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();

//        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
//        WebElement row = checkbox.findElement(By.xpath("./../.."));
//        List<WebElement> cells = row.findElements(By.tagName("td"));
//        cells.get(7).findElement(By.tagName("a")).click();
//
//        wd.findElement(By.xpath(String.format("//input(@value='%s')//../../td(8)/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr(.//input(@value='%s'))/td(8)/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void create(ContactData contactData, boolean creation) {
        initContact();
        fillContactForm(contactData, true);
        submitContactCreation();
        contactCach = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCach = null;

    public Contacts all() {
        if(contactCach != null){
            return new Contacts(contactCach);
        }
        contactCach = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));

            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCach.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withAddress(address)
                    .withAllEmail(allEmails)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCach);
    }


    public void modify(ContactData contactData, boolean creation) {
        initContactModification(contactData.getId());
        fillContactForm(contactData, false);
        submitContactModification();
        contactCach = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCach = null;
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstname)
                .withLastName(lastname)
                .withAddress(address)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }
}
