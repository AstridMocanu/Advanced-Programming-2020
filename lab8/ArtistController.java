public class ArtistController {


    public Database db=Database.getInstance();
    private static final ArtistController ac=new ArtistController(Database.getInstance());

    public static ArtistController getInstance(){return ac;};

    public ArtistController(Database db) {
        this.db = db;
    }


    public Artist extractArtistInfo(Artist artist,int index){

        //extrage info despre artist din BD

        Integer[] vals=new Integer[100];
        String[] vals2=new String[100];



        String sql;


        artist.setArtistId(index);



        sql="select name from artists where id="+index+";";
        vals2=db.LansareSelectString(sql);
        String nume=vals2[0];

        artist.setName(nume);

        sql="select country from artists where id="+index+";";
        vals2=db.LansareSelectString(sql);
        String country=vals2[0];

        artist.setCountry(country);


        return artist;

    }


    public int getRank(Artist artist,Chart chart){
            Integer min=10000;

            ///ofera rankul unui artist dintr-un anumit chart

        int id=0;
        String sql;
        Integer[] vals;

        sql="select position from charts where artist_id="+artist.getArtistId()+" and id="+chart.getId()+";";


        vals=db.LansareSelect(sql);


        for(int i=0;i<vals.length;i++)
            if(vals[i]!=null)
                if(vals[i]<min)
                min=vals[i];


         if(min==10000) System.out.println("Artist inexistent in chart");

     return min;

    }


    public void create(String name, String country){

       // introduce in bd un artist

        int id=0;
        String sql;
        Integer[] vals;

        sql="select max(id) from artists;";


        vals=db.LansareSelect(sql);
        id=vals[0]+1;

        sql="INSERT INTO artists values("+id+",'"+name+"','"+country+"');";

        db.LansareInsert(sql);



    };
    public int findByName(String name){

        ///furnizeaza id-ul unui artist cautat in bd dupa nume

        String artist="0";
        int id=0;


        String sql="select id from artists where name='"+name+"';";

        Integer[] vals;
        vals=db.LansareSelect(sql);
        id=vals[0];

        return id;


    };
}
