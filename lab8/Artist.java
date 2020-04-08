/**
 * create table artists(
 *     id integer not null generated always as identity (start with 1, increment by 1),
 *     name varchar(100) not null,
 *     country varchar(100),
 *     primary key (id)
 * );
 *
 */
public class Artist {
    private String name;
    private Integer artistId;
    private String country;
    private Integer rank=0;

    public Artist() {
        this.name = " ";
        this.artistId = 0;
        this.rank = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
