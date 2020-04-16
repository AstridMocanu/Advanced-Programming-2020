package util;

import entityHibernate.ArtistsHib;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static final PersistenceUtil pu=new PersistenceUtil();
    private PersistenceUtil(){};
    public static PersistenceUtil getInstance(){return pu;};
    private EntityManagerFactory emf;
   // private SessionFactory emf;



    public void create(){ emf = Persistence.createEntityManagerFactory("MusicAlbumsPU",null);
         //emf=new Configuration().configure("util/hibernate.cfg.xml").addAnnotatedClass(ArtistsHib.class).buildSessionFactory();

        //EntityManager em = emf.createEntityManager();
    }

   public EntityManagerFactory getEmf() {
        if (emf == null) create();
        return emf;
    }
   /* public SessionFactory getEmf() {
        if (emf == null) create();
        return emf;
    }*/
}
