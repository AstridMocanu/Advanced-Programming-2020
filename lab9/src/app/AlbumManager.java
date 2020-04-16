package app;

import com.github.javafaker.Faker;
import entity.Albums;
import entity.Artists;
import entity.Genres;
import repo.AlbumRepository;
import repo.ArtistRepository;
import repo.ChartRepository;
import repo.GenreRepository;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.Random;

//singleton
public class AlbumManager {

    private static final AlbumManager gr=new AlbumManager();
    private AlbumManager(){};
    public static AlbumManager getInstance(){return gr;};




    public static Artists generatorArtist(){
        Artists artist=new Artists();
        Faker faker=new Faker();

        artist.setCountry(faker.address().country());
        artist.setName(faker.name().firstName()+" "+faker.space().galaxy());


        Integer id;

        EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();

        Query query = em.createQuery("SELECT count(a) FROM  Artists a");
        id=Integer.parseInt(query.getSingleResult().toString())+1;

        artist.setId(id);

    return artist;

    }


    public static Albums generatorAlbum(){
        Albums album=new Albums();
        Faker faker=new Faker();
        Random rand=new Random();
        Integer id;





        ///extragere id max din bd
        EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();

        Query query = em.createQuery("SELECT count(a) FROM  Albums a");
        id=Integer.parseInt(query.getSingleResult().toString())+1;


        album.setId(id);

        int nr;
        nr=rand.nextInt(id)+1;

        ///completare restul datelor random,fake
        album.setArtistId(nr);
        album.setName(faker.color().name()+" "+faker.superhero().power());
        album.setReleaseYear(nr+1950);


        return album;

    }
    public static void masiveGeneratorAlbum(int n){
        AlbumRepository alr= AlbumRepository.getInstance();

        for(int i=1;i<=n;i++)
        alr.create(generatorAlbum());

    }

    public static void generateGenres(){


        GenreRepository gr=GenreRepository.getInstance();
        String[] genuri={"rock","pop","folk","X-treme brutal folk beer heavy metal","theriffic black metal","baby metal","pink metal","rose flower armonia","beer"};

        /*Random rand=new Random();

        Integer id;

        ///extragere id max din bd
        EntityManager em = PersistenceUtil.getInstance().getEmf().createEntityManager();

        Query query = em.createQuery("SELECT count(a) FROM  Genres a");
        id=Integer.parseInt(query.getSingleResult().toString())+1;*/

        for(int id=1;id<=genuri.length;id++){

            Genres genre=new Genres();
        genre.setId(id);
        genre.setName(genuri[id-1]);

        gr.create(genre);

        }


    }


    public void addGenre(Albums album, Genres genre){
        album.setGenreId(genre.getId());
    }






    public static void main(/*String[] args*/)throws IOException{

        PersistenceUtil pu= PersistenceUtil.getInstance();
        pu.create();
        //generateGenres();


///////////////////////////////////////////////////////////////////////
        ///CHARTS TESTS

        ChartRepository cr= ChartRepository.getInstance();


        ///testare find by id
        //System.out.println("/////////////CHARTS////////////// "+cr.findById(3).get().getId());



////////////////////////////////////////////////////////////////////////
        ///ALBUMS TESTS

        AlbumRepository alr= AlbumRepository.getInstance();

        ///testare find by name
        //System.out.println(pu.getEmf());
        if(alr.findByName("Orion").size()>0)
        System.out.println("/////////////ALBUMS////////////// "+alr.findByName("Orion").get(0).getName());
        if(alr.findByName("ASTRID").size()>0)
        System.out.println("/////////////ALBUMS////////////// "+alr.findByName("ASTRID").get(0).getName());

        ///testare find by id
        if(alr.findById(3)!=null)
        System.out.println("/////////////ALBUMS////////////// "+alr.findById(3).get().getName());




        ////testare create

        System.out.println("/////////////ALBUMS////////////// "+alr.create(generatorAlbum()));



/////////////////////////////////////////////////////////////////////
        ///ARTISTS TESTS


        ArtistRepository ar= ArtistRepository.getInstance();

        ///testare find by name
        //System.out.println(pu.getEmf());
        if(ar.findByName("Tania Whirlpool").size()>0)
        System.out.println(ar.findByName("Tania Whirlpool").get(0).getName());


        ///testare find by id
        if(ar.findById(3)!=null)
            System.out.println(ar.findById(3).get().getName());




        ////testare create

        System.out.println(ar.create(generatorArtist()));


        ///testare functionare
        System.out.print("33\n");



        pu.getEmf().close();

    }



}
