import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlbumController {
    Database db=Database.getInstance();

    private static final AlbumController ac=new AlbumController(Database.getInstance());

    public static AlbumController getInstance(){return ac;};

    public AlbumController(Database db) {
        this.db = db;
    }




    public Album extractAlbumInfo(int index){

        //extrage informatii din bd despre un album, dat prin index

        Album album=new Album();


        Integer[] vals=new Integer[100];
        String[] vals2=new String[100];


        String sql;


        album.setAlbumId(index);



        sql="select artist_id from albums where id="+index+";";
        vals=db.LansareSelect(sql);
        int id=vals[0];


        album.setArtistId(id);

        sql="select name from albums where id="+index+";";
        vals2=db.LansareSelectString(sql);
        String nume=vals2[0];

        album.setName(nume);



        return album;

    }

    public void create(String name, int artistId, int releaseYear) {

        ///inserare album in bd

        int id=0;
        String sql;
        Integer[] vals=new Integer[100];



        sql="select max(id) from albums;";

        vals=db.LansareSelect(sql);
        id=vals[0]+1;

        sql="INSERT INTO albums VALUES("+id+",'"+name+"',"+artistId+","+releaseYear+");";

        db.LansareInsert(sql);


    }

    public List<String> findByArtist(int artistId){

        ///gaseste albumurile unui anume artist

        Integer[] vals=new Integer[1000];
        List<String> vals2=new ArrayList<String>();



        String sql="select id from albums where artist_id="+artistId+";";

        vals=db.LansareSelect(sql);



        for(int i=0;vals[i]!=null;i++)
              {sql="select name from albums where id="+vals[i]+";";
            vals2.add(db.LansareSelectString(sql)[0]);
        };

        return vals2;

    };

  }
