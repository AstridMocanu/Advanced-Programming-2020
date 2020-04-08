public class ChartController {

    Database db=Database.getInstance();
    private static final ChartController cc=new ChartController(Database.getInstance());
    public static ChartController getInstance(){return cc;};


    public ChartController(Database db) {
        this.db = db;
    }


    public void InsertChart (Chart chart, Album album) {

        ///insereaza in BD fiecare album din fiecare chart din care face parte

      String sql;


        if(chart.getId()==0) {

            int id = 0;
            Integer[] vals;

            sql = "select max(id) from charts;";


            vals = db.LansareSelect(sql);
            id = vals[0] + 1;

            chart.setId(id);
        }

      ///este aruncata eroare daca se incearca inserarea aceleiasi val in bd , deoarece cheia primara e form din: (id_album si id_chart)

        album.setPlace(chart.getAlbums().size()+1);
        chart.getAlbums().add(album);


        sql="INSERT INTO charts VALUES("+chart.getId()+","+album.getAlbumId()+","+album.getPlace()+","+album.getArtistId()+");";

        db.LansareInsert(sql);


    }



}
