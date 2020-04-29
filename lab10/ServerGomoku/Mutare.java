import java.util.Objects;

public class Mutare {
    private Integer linie;
    private Integer coloana;
    private Player player;

    public Mutare(Integer linie, Integer coloana, Player player) {
        this.linie = linie;
        this.coloana = coloana;
        this.player = player;
    }
    public Mutare(Integer linie, Integer coloana) {
        this.linie = linie;
        this.coloana = coloana;

    }

    public Integer getLinie() {
        return linie;
    }

    public void setLinie(Integer linie) {
        this.linie = linie;
    }

    public Integer getColoana() {
        return coloana;
    }

    public void setColoana(Integer coloana) {
        this.coloana = coloana;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mutare mutare = (Mutare) o;
        return linie.equals(mutare.linie) &&
                coloana.equals(mutare.coloana) &&
                Objects.equals(player, mutare.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linie, coloana, player);
    }
}
