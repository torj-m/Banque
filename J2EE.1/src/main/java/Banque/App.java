package Banque;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;


public class App {
    public static void main(String[] args){
        Client cl = new Client("youssef","mehrez", 27272727, "youssefmeh@gmail.com", "mehmeh12", "Sidi bou said", 000232314);
        Client cll = new Client( "karoui","mounir", 96400400, "mounirkaroui@gmail.com", "mounir22", "Tunis", 000256776);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(cl);
            session.save(cll);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            clients.forEach(s -> {
                System.out.println("Print client email id : " + s.getEmail());
            });
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
