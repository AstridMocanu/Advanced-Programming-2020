package entity;

import javax.persistence.*;
import java.util.Objects;



@Entity
@Table(name="genres", schema = "MusicAlbums")
public class Genres {
    private Integer id;
    private String name;


    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genres genres = (Genres) o;
        return Objects.equals(id, genres.id) &&
                Objects.equals(name, genres.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
