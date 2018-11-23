import entity.ClientEntity;
import entity.ClientRequestEntity;
import entity.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

@RunWith(JUnit4.class)
public class ManagerTest {

    @Test
    public void checkRole() {
        //получилось что оно проверяет есть ли такой пользователь в системе. как????
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setRole("manager");
        List roleList = Arrays.asList("manager", "steward", "chef");
//        for (Object u : roleList) {
//            assert !usersEntity.getRole().equals(u) : "user registered";
//        }

        for (Object u : roleList) {
//            assertEquals("user not found", roleList.contains(u), usersEntity.getRole());
            assertTrue("user is not manager",usersEntity.getRole().equals(roleList.get(0)));
//            assertTrue("user is not steward",usersEntity.getRole().equals(roleList.get(1)));
        }
    }

    @Test
    public void checkClientExist(){
        final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ClientRequestEntity clientRequestEntity = new ClientRequestEntity();
        ClientEntity textField = clientRequestEntity.getClientByClientId();
        String clientNameEntered = textField.getName().concat(" " + textField.getSurname());
        String hql = "FROM ClientEntity" +
                " WHERE name LIKE '"+ clientNameEntered +"' AND surname LIKE '"+clientNameEntered+"'";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        ClientEntity result = (ClientEntity) query.list().get(0);
        session.close();

    }

}




