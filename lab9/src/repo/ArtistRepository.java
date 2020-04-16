package repo;

import entity.Artists;
import util.PersistenceUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



///implementat dupa pattern-ul singleton
public class ArtistRepository implements AbstractRepository<Artists,Integer>{

    private static final ArtistRepository ar=new ArtistRepository();
    private ArtistRepository(){};
    public static ArtistRepository getInstance(){return ar;};

    PersistenceUtil pu= PersistenceUtil.getInstance();



    public Artists create(Artists entity) {
        EntityManagerFactory emf =pu.getEmf();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(entity);
        em.getTransaction().commit();
        em.close();


        return entity;

    }

    public List<Artists> findByName(String name) {

      //  if(factory == null) System.out.println("e null");
       // else System.out.println("nu e null");

       // try{
            EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();




        Query query = em.createQuery("SELECT a FROM  Artists a WHERE a.name LIKE :personName")
                        .setParameter("personName", name);
                        //.setMaxResults(10);


        return query.getResultList();

     //   }        catch(NullPointerException e){ e.getStackTrace();       }        return null;

    }

    @Override
    public Optional<Artists> findById(Integer integer) {

        EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();


        Query query = em.createQuery("SELECT a FROM  Artists a WHERE a.id = :personId",Artists.class)
                .setParameter("personId", integer);


        return Optional.ofNullable((Artists) query.getSingleResult());

    }



    /////////////////////////-nenecesare in proiectul asta-revin daca imi trebuie
    @Override
    public <S extends Artists> S save(S entity) {
        return null;
    }


    @Override
    public <S extends Artists> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }



    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Artists> findAll() {
        return null;
    }

    @Override
    public Iterable<Artists> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Artists entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Artists> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
