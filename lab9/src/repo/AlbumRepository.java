package repo;

import entity.Albums;
import entity.Artists;
import entityHibernate.AlbumsHib;
import util.PersistenceUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

///implementat dupa pattern-ul singleton



public class AlbumRepository implements AbstractRepository<Albums,Integer> {

    private static final AlbumRepository ar=new AlbumRepository();
    private AlbumRepository(){};
    public static AlbumRepository getInstance(){return ar;};

    PersistenceUtil pu= PersistenceUtil.getInstance();

    public Albums create(Albums entity) {
        EntityManagerFactory emf =pu.getEmf();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();


        em.persist(entity);
        em.getTransaction().commit();
        em.close();


        return entity;

    }

    public List<Albums> findByName(String name) {

           EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();


       Query query = em.createNamedQuery("findByName")  .setParameter("personName", name);// .getSingleResult();

   //     Query query = em.createQuery("SELECT a FROM  Albums a WHERE a.name LIKE :personName")//               .setParameter("personName", name);


        return query.getResultList();

    }



    @Override
    public Optional<Albums> findById(Integer integer) {


        EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();


        Query query = em.createQuery("SELECT a FROM  Albums a WHERE a.id = :personId",Albums.class)
                .setParameter("personId", integer);


        return Optional.ofNullable((Albums) query.getSingleResult());

    }

    public List<Albums> findByArtist(Artists artists) {

        //  if(factory == null) System.out.println("e null");
        // else System.out.println("nu e null");

        // try{
        EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();

        Integer artist_id=artists.getId();


        Query query = em.createQuery("SELECT a FROM  Artists a WHERE a.id = :personId")
                .setParameter("personId", artist_id);
        //.setMaxResults(10);

        return query.getResultList();

        //   }        catch(NullPointerException e){ e.getStackTrace();       }        return null;

    }


/////////////////////////

    @Override
    public <S extends Albums> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Albums> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }



    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Albums> findAll() {
        return null;
    }

    @Override
    public Iterable<Albums> findAllById(Iterable<Integer> integers) {
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
    public void delete(Albums entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Albums> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
