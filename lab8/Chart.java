import java.util.ArrayList;
import java.util.List;


/**

 create table charts(
 id integer not null,
 album_id integer not null references albums on delete restrict,
 position integer not null,
 artist_id integer not null,
 primary key (id,album_id)         );
 */


public class Chart {

    private int id=0;
    private List<Album> albums=new ArrayList<>();

    public Chart() {
        this.id = 0;
            }


    public List<Album> getAlbums() {
        return albums;
    }

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
