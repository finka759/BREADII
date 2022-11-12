package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private  SessionFactory sessionFactory = Util.getInstance().getSessionFactory();

    private Transaction transaction = null;
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("create table if not exists users\n" +
                    "(\n" + "    id       INT auto_increment not null,\n" +
                    "    name     TEXT not null,\n" +
                    "    lastName TEXT not null,\n" +
                    "    age      INT  null,\n" +
                    "    constraint users_pk\n" +
                    "        primary key (id)\n" + ");").executeUpdate();
            transaction.commit();
            System.out.println("Table created");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("Table deleted");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User saved");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("User deleted");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            result = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
            System.out.println("users truncated");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
