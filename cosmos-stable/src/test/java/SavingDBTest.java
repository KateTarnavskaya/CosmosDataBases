import entity.ClientEntity;
import javafx.beans.property.SimpleStringProperty;
import models.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import util.HibernateUtil;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SavingDBTest {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Test
    public void checkClientName(){
        ClientEntity clientEntity = new ClientEntity(new SimpleStringProperty("1"),new SimpleStringProperty("name1"),new SimpleStringProperty("surname1"),new SimpleStringProperty( "phone1"),new SimpleStringProperty("phone2"),new SimpleStringProperty("address1"),new SimpleStringProperty("email1"));
        Client client = new Client(clientEntity);
        client.setName("changedName");
        Session session = sessionFactory.openSession();
        Query query1 = session.createQuery(" FROM ClientEntity WHERE id=1");
        ClientEntity clientEntity1 = (ClientEntity) query1.list().get(0);
        assertEquals("error", client.getName(), clientEntity1.getName());
        session.close();
    }

    @Test
    public void checkClientID(){
        //request
        //getClientName
//String hql = "FROM ClientEntity" +
//                " WHERE name LIKE '"+ name +"' AND surname LIKE '"+surname+"'";
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery(hql);
//        ClientEntity result = (ClientEntity) query.list().get(0);//check this shit
//        session.close();
    }
}
