package repo;

import entity.Albums;
import entity.Artists;
import entity.Charts;
import javafx.scene.chart.Chart;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

///implementat dupa pattern-ul singleton

public class ChartRepository implements AbstractRepository<Charts,Integer> {

    private static final ChartRepository cr=new ChartRepository();
    private ChartRepository(){};
    public static ChartRepository getInstance(){return cr;};

    PersistenceUtil pu= PersistenceUtil.getInstance();

    public Charts create(Charts entity) {
        EntityManagerFactory emf =pu.getEmf();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(entity);
        em.getTransaction().commit();
        em.close();


        return entity;

    }


    @Override
    public Optional<Charts> findById(Integer integer) {

        EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();


        Query query = em.createQuery("SELECT a FROM  Charts a WHERE a.id = :personId",Charts.class)
                .setParameter("personId", integer);


        return Optional.ofNullable((Charts) query.getSingleResult());

    }


    //////////////////////////////////
    @Override
    public <S extends Charts> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Charts> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }



    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Charts> findAll() {
        return null;
    }

    @Override
    public Iterable<Charts> findAllById(Iterable<Integer> integers) {
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
    public void delete(Charts entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Charts> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
