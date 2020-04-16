package entityHibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "charts", schema = "MusicAlbums", catalog = "")
@IdClass(ChartsHibPK.class)
public class ChartsHib {
    private int id;
    private int albumId;
    private int position;
    private int artistId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "album_id", nullable = false)
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "position", nullable = false)
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Basic
    @Column(name = "artist_id", nullable = false)
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartsHib chartsHib = (ChartsHib) o;
        return id == chartsHib.id &&
                albumId == chartsHib.albumId &&
                position == chartsHib.position &&
                artistId == chartsHib.artistId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumId, position, artistId);
    }
}
