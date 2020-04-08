import com.github.javafaker.Faker;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
static Main main=new Main();
public static Database db=Database.getInstance();

    public void GeneratorArtists(){




        Faker faker=new Faker();

        ArtistController ac= new ArtistController(db);

        for(int i=1;i<=10;i++)
        ac.create(faker.name().firstName()+" "+faker.space().galaxy(),faker.address().country());
        for(int i=1;i<=10;i++)
            ac.create(faker.name().lastName()+" "+faker.space().constellation(),faker.address().country());



    }

    public void GeneratorAlbums(){



        Faker faker=new Faker();
        Random rand=new Random();
        int id=0;
        String sql;

        sql="select max(id) from artists;";


        int nr=rand.nextInt(id)+1;

        AlbumController ac= new AlbumController(db);

        for(int i=1;i<=10;i++)
        {
       ac.create(faker.color().name()+" "+faker.space().moon(),nr,nr+1950);
            nr=rand.nextInt(id)+1;
        ac.create(faker.color().name()+" "+faker.space().moon(),nr,nr+1950);
            nr=rand.nextInt(id)+1;
        ac.create(faker.color().name()+" "+faker.space().moon(),nr,nr+1950);
            nr=rand.nextInt(id)+1;
        ac.create(faker.color().name()+" "+faker.superhero().power(),nr,nr+1950);
            nr=rand.nextInt(id)+1;
        ac.create(faker.gameOfThrones().city()+" "+faker.space().moon(),nr,nr+1950);
            nr=rand.nextInt(id)+1;
        ac.create(faker.beer().name()+" "+faker.lorem().word(),nr,nr+1950);
            nr=rand.nextInt(id)+1;
        ac.create(faker.beer().name()+" "+faker.superhero().name(),nr,nr+1950);
            nr=rand.nextInt(id)+1;
        ac.create(faker.music().instrument()+" "+faker.food().ingredient(),nr,nr+1950);
            nr=rand.nextInt(id)+1;
        }



    }

    public static void main(String args[])throws IOException{


       // main.GeneratorArtists();
       // main.GeneratorAlbums();

        int id=0;
        String sql="select max(id) from artists;";

        System.out.println(id);

        ArtistController ac= new ArtistController(db);
        System.out.println(ac.findByName("Alba Blackeye"));

        AlbumController alc= new AlbumController(db);


        System.out.println(alc.findByArtist(9));
        System.out.println(alc.findByArtist(23));
        System.out.println(alc.findByArtist(27));




        Chart chart=new Chart();
        Chart chart2=new Chart();
        Album album=new Album();
        Artist artist=new Artist();
        ChartController cc=new ChartController(db);

       album= alc.extractAlbumInfo(36);
        cc.InsertChart(chart,album);
        cc.InsertChart(chart2,album);


        //adaugare de albume in chart

     album=  alc.extractAlbumInfo(35);
        cc.InsertChart(chart,album);
     album=   alc.extractAlbumInfo(25);
        cc.InsertChart(chart,album);
     album=   alc.extractAlbumInfo(31);
        cc.InsertChart(chart,album);
     album=   alc.extractAlbumInfo(30);
        cc.InsertChart(chart,album);


        System.out.println(chart.getAlbums());
        ///poate suprascrii ultimul album de mai multe ori...?
        System.out.println(album.getName());

        for(var val:chart.getAlbums())
            System.out.println(val.getName()+" "+val.getArtistId());

        ac.extractArtistInfo(artist,31);
        System.out.println(artist.getName());
        System.out.println(artist.getArtistId());



        //rank-ul unui artist
        System.out.println(ac.getRank(artist,chart));



    }

}
