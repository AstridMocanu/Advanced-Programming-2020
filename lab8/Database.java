

import java.sql.*;



public class Database {

   private static final Database db=new Database();
   private Database(){};
   public static Database getInstance(){return db;};
   private Connection con;


   private Integer[] valoriMultiple=new Integer[100];
   private String[] valoriMultipleString=new String[1000];
   private int contorVal=0;

    static final String DB_URL = "jdbc:mysql://localhost/MusicAlbums?useLegacyDatetimeCode=false&serverTimezone=America/New_York";

    //  Database credentials
    static final String USER = "dba";
    static final String PASS = "sql";



    public Integer[] LansareSelect(String sql){


        ///comanda utilizata pt lansare query-uri in BD- si returnare info integer


        ResultSet rs=null;
        int val=0;
        contorVal=0;
        try{

            con=DriverManager.getConnection(DB_URL,USER,PASS);


            Statement stmt=con.createStatement();


            rs=stmt.executeQuery(sql);

            while(rs.next()){

              val= rs.getInt(1);
              valoriMultiple[contorVal++]=val;

           }




            con.close();


        }catch(Exception r){ System.out.println(r);}


        return valoriMultiple;
    }


    public String[] LansareSelectString(String sql){

        ///comanda utilizata pt lansare query-uri in BD- si returnare inf string


        ResultSet rs=null;
        String val=new String();
        contorVal=0;
        try{

            con=DriverManager.getConnection(DB_URL,USER,PASS);


            Statement stmt=con.createStatement();


            rs=stmt.executeQuery(sql);

            while(rs.next()){

                val= rs.getString(1);
                valoriMultipleString[contorVal++]=val;

            }




            con.close();


        }catch(Exception r){ System.out.println(r);}


        return valoriMultipleString;
    }
    public void LansareInsert(String sql){

        ///companda utilizata pt operatia DML de inserare in BD


        int rs=0;
        int val=0;
        try{


            con=DriverManager.getConnection(DB_URL,USER,PASS);


            Statement stmt=con.createStatement();


            rs=stmt.executeUpdate(sql);



            con.close();


        }catch(Exception r){ System.out.println(r);}



    }


}


