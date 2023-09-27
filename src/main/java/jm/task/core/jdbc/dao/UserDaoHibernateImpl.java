package main.java.jm.task.core.jdbc.dao;

import main.java.jm.task.core.jdbc.model.User;
import main.java.jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    private Session session;

    private String sqlCommand;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        sqlCommand = "CREATE TABLE `newdatabase`.`user` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` TINYINT(3) UNSIGNED NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;";
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sqlCommand).addEntity(User.class).executeUpdate();
        } catch (Exception e) {
        }
        finally {
            session.getTransaction().commit();
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        sqlCommand = "DROP TABLE newdatabase.user;";
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sqlCommand).executeUpdate();
        }
        catch (Exception e) {}
        finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
