package repo;

import entityHibernate.AlbumsHib;

import java.util.Optional;

public class AlbumHibRepository implements AbstractRepository<AlbumsHib,Integer> {
    @Override
    public <S extends AlbumsHib> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AlbumsHib> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AlbumsHib> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<AlbumsHib> findAll() {
        return null;
    }

    @Override
    public Iterable<AlbumsHib> findAllById(Iterable<Integer> integers) {
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
    public void delete(AlbumsHib entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends AlbumsHib> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
