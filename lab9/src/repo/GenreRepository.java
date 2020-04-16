package repo;

import entity.Artists;
import entity.Charts;
import entity.Genres;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Optional;

///implementat dupa pattern-ul singleton

public class GenreRepository implements AbstractRepository<Genres,Integer> {

    private static final GenreRepository gr=new GenreRepository();
    private GenreRepository(){};
    public static GenreRepository getInstance(){return gr;};

    PersistenceUtil pu= PersistenceUtil.getInstance();


    public Genres create(Genres entity) {
        EntityManagerFactory emf =pu.getEmf();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(entity);
        em.getTransaction().commit();
        em.close();


        return entity;

    }


    @Override
    public Optional<Artists> findById(Integer integer) {

        EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();


        Query query = em.createQuery("SELECT a FROM  Genres a WHERE a.id = :personId",Genres.class)
                .setParameter("personId", integer);


        return Optional.ofNullable((Artists) query.getSingleResult());

    }



    @Override
    public <S extends Genres> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Genres> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }


    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Genres> findAll() {
        return null;
    }

    @Override
    public Iterable<Genres> findAllById(Iterable<Integer> integers) {
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
    public void delete(Genres entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Genres> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
