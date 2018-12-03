package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void dbConnectionTest(){
        Connection conn = null;
        Contacts contacts = new Contacts();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT addressbook.id, addressbook.firstname, addressbook.lastname, addressbook.address, addressbook.mobile, addressbook.fax, addressbook.email, addressbook.homepage " +
                    "FROM addressbook INNER JOIN address_in_groups ON addressbook.id = address_in_groups.id");
            while (rs.next()){
                contacts.add(new ContactData()
                        .withId(rs.getInt("addressbook.id"))
                        .withFirstName(rs.getString("addressbook.firstname"))
                        .withLastName(rs.getString("addressbook.lastname"))
                        .withAddress(rs.getString("addressbook.address"))
                        .withMobilePhone(rs.getString("addressbook.mobile"))
                        .withFax(rs.getString("addressbook.fax"))
                        .withEmail(rs.getString("addressbook.email"))
                        .withHomepage(rs.getString("addressbook.homepage")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(contacts);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
