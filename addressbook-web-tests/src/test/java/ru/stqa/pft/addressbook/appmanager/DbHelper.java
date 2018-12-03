package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;
import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public void cleanRelationsBetweenContactsAndGroups(){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            String sql = "DELETE FROM address_in_groups";
            st.execute(sql);
            st.close();
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public ContactData addedContactToGroup(ContactData modifyContact, GroupData selectedGroup) {

        Connection conn = null;
        ContactData contact = new ContactData();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT addressbook.id, addressbook.firstname, addressbook.lastname, addressbook.address, addressbook.mobile, addressbook.fax, addressbook.email, addressbook.homepage " +
                    "FROM addressbook INNER JOIN address_in_groups ON addressbook.id = address_in_groups.id");
             contact.withId(rs.getInt("addressbook.id"))
                    .withFirstName(rs.getString("addressbook.firstname"))
                    .withLastName(rs.getString("addressbook.lastname"))
                    .withAddress(rs.getString("addressbook.address"))
                    .withMobilePhone(rs.getString("addressbook.mobile"))
                    .withFax(rs.getString("addressbook.fax"))
                    .withEmail(rs.getString("addressbook.email"))
                    .withHomepage(rs.getString("addressbook.homepage"));

            rs.close();
            st.close();
            conn.close();
            System.out.println(contact);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return contact;
    }
}
