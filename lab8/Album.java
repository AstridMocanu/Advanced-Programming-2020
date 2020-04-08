/**
 *  create table albums(
 *  *     id integer not null generated always as identity (start with 1, increment by 1),
 *  *     name varchar(100) not null,
 *  *     artist_id integer not null references artists on delete restrict,
 *  *     release_year integer,
 *  *     primary key (id)
 *  * );
 */
public class Album {
    private String name;
    private Integer albumId;
    private Integer artistId=0;
    private Integer place=0;

    public Album() {
        this.name = " ";
        this.albumId = 0;
        this.artistId = 0;
        this.place = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }


    @Override
    public String toString() {
        return  name ;}
}
