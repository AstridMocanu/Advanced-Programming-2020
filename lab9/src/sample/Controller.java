    package sample;

    import app.AlbumManager;
    import entity.Albums;
    import entity.Artists;
    import entity.Charts;
    import entity.Genres;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.*;
    import javafx.scene.control.cell.MapValueFactory;
    import javafx.stage.Stage;
    import repo.ArtistRepository;
    import util.PersistenceUtil;

    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.Query;
    import javax.swing.text.html.ImageView;
    import java.io.IOException;
    import java.net.URL;
    import java.sql.SQLOutput;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.Optional;
    import java.util.ResourceBundle;
    import java.util.function.Consumer;


    public class Controller implements Initializable {
        public Button exitButton;
        public ComboBox<String> comboBox;
        public ComboBox<String> comboBox2;
        public String alegereBD=new String("0");
        public String alegereTabela=new String("0");
        public TableView tabela;
        private static final String Column1MapKey = "A";
        private static final String Column2MapKey = "B";
        public TextField textField1;
        public TextField textField2;
        public TextField textField3;
        public TextField textField4;
        public TextField textField5;
        public TextField fereastra;
        public Label scris1;
        public Label scris2;
        public ImageView iconExit;
        public Button okButton;
        public Boolean okBut=false;
        public Query querySpecial=null;
        public Optional<Artists> resultArtist=null;



//org.hibernate.jpa.HibernatePersistenceProvider


        public void exitIcon(){

            fereastra.setOpacity(0);
            scris1.setOpacity(0);
            scris2.setOpacity(0);
            iconExit.setSize(0,0);

        }


        public void functionare() throws IOException {

            if (alegereBD.equals("0")){
                System.out.println("COMPLETE THE DB APROACH METHOD!");

            }
            //exitIcon();
            /*if(alegereBD=="0"){


                sleep(12);
                exitIcon();
            }*/


            if(alegereBD.equals("JDBC"))
                metodaJDBC();
                else if(alegereBD.equals("JPA"))
                   metodaJPA();

        }

        //METODA DE LUCRU

        public void metodaJDBC(){
            System.out.println("jdbc-aici");
            //----urmeaza sa leg aici laboratorul trecut

        }


        public void metodaJPA() throws IOException {
            System.out.println("jpa-aici");
            AlbumManager am=AlbumManager.getInstance();
            int ok=0;

            while(ok==0){

                if(alegereTabela!=null)ok=1;
            }

            writeTable();

        }





        /////completam date preliminare despre tabel, apoi introducem datele cerute in query
        public void writeTable(){

            PersistenceUtil pu= PersistenceUtil.getInstance();
            EntityManagerFactory emf =pu.getEmf();
            EntityManager em = emf.createEntityManager();



            final Integer[] i = {0};

            tabela.getColumns().forEach((Consumer<TableColumn<Map, String>>) col -> {
                col.setCellValueFactory(new MapValueFactory<>("column" + (i[0]).toString()));


                if(alegereBD!="0")
                switch(alegereTabela){
                    case "Artists":

                        if(i[0]==0)
                        col.setText("id");
                        if(i[0]==1)
                            col.setText("Name");
                        if(i[0]==2)
                            col.setText("Country");
                        if(i[0]==3)
                            col.setText("");
                        if(i[0]==4)
                            col.setText("");
                        textField1.disableProperty().setValue(false);
                        textField2.disableProperty().setValue(false);

                        textField3.disableProperty().setValue(true);
                        textField4.disableProperty().setValue(true);
                        textField5.disableProperty().setValue(true);




                        break;
                    case "Albums":
                        if(i[0]==0)
                            col.setText("id");
                        if(i[0]==1)
                            col.setText("Name");
                        if(i[0]==2)
                            col.setText("Genre");
                        if(i[0]==3)
                            col.setText("Artist");
                        if(i[0]==4)
                            col.setText("Realease");

                        textField3.disableProperty().setValue(false);
                        textField4.disableProperty().setValue(false);
                        textField5.disableProperty().setValue(false);

                        textField1.disableProperty().setValue(true);
                        textField2.disableProperty().setValue(true);


                        break;
                    case "Genres":
                        if(i[0]==0)
                            col.setText("id");
                        if(i[0]==1)
                            col.setText("Name");
                        if(i[0]==2)
                            col.setText("");
                        if(i[0]==3)
                            col.setText("");
                        if(i[0]==4)
                            col.setText("");

                        textField1.disableProperty().setValue(true);
                        textField2.disableProperty().setValue(true);
                        textField3.disableProperty().setValue(true);
                        textField4.disableProperty().setValue(true);
                        textField5.disableProperty().setValue(true);

                        break;
                    case "Charts":
                        if(i[0]==0)
                            col.setText("id");
                        if(i[0]==1)
                            col.setText("Album");
                        if(i[0]==2)
                            col.setText("Artist");
                        if(i[0]==3)
                            col.setText("Position");
                        if(i[0]==4)
                            col.setText("");

                        textField1.disableProperty().setValue(true);
                        textField2.disableProperty().setValue(true);
                        textField3.disableProperty().setValue(true);
                        textField4.disableProperty().setValue(true);
                        textField5.disableProperty().setValue(true);

                        break;
                }


                System.out.println(i[0]);
                i[0]++;
            });



            tabela.setItems(generateDataInMap());
        }

        private ObservableList<Map> generateDataInMap() {


            PersistenceUtil pu= PersistenceUtil.getInstance();
            EntityManagerFactory emf =pu.getEmf();
            EntityManager em = emf.createEntityManager();
            Query query;
            ObservableList<Map> allData;
            Long max;

            switch(alegereTabela)
            {
                case "Artists":





                    query= em.createQuery("SELECT count(a.id) FROM  Artists a ");
                    max = (Long)query.getResultList().get(0);
                 //   max = (int)query.getMaxResults();
                    System.out.println(max);
                    //max = 20;

                    query= em.createQuery("SELECT a FROM  Artists a ");

                  //  max = 20;
                     allData = FXCollections.observableArrayList();
                    for (int i = 1; i < max; i++) {
                        Map<String, String> dataRow = new HashMap<>();
                        Artists artist=(Artists)query.getResultList().get(i);

                        if(resultArtist!=null)
                        {artist=(Artists)resultArtist.get();
                        max=(long)1;
                            System.out.println("si aicii");
                        }

                        String value1 =String.valueOf(artist.getId());
                        String value2 = String.valueOf(artist.getName());
                        String value3 = String.valueOf(artist.getCountry());

                        //String value6 =  query.getResultList().get(i).toString();

                        dataRow.put("column0", value1);
                        dataRow.put("column1", value2);
                        dataRow.put("column2", value3);





                        allData.add(dataRow);

                    }

                    return allData;
                case "Albums":

                    query= em.createQuery("SELECT count(a) FROM  Albums  a ");
                    max = (Long) query.getSingleResult();

                    query= em.createQuery("SELECT a FROM  Albums a ");

                  //  max = 20;
                    allData = FXCollections.observableArrayList();
                    for (int i = 1; i < max; i++) {
                        Map<String, String> dataRow = new HashMap<>();
                        Albums album=(Albums) query.getResultList().get(i);

                        String value1 =String.valueOf(album.getId());
                        String value2 = String.valueOf(album.getName());
                        String value3 = String.valueOf(album.getGenreId());
                        String value4 = String.valueOf(album.getArtistId());
                        String value5 = String.valueOf(album.getReleaseYear());

                        //String value6 =  query.getResultList().get(i).toString();

                        dataRow.put("column0", value1);
                        dataRow.put("column1", value2);
                        dataRow.put("column2", value3);
                        dataRow.put("column3", value4);
                        dataRow.put("column4", value5);

                        allData.add(dataRow);

                    }

                    return allData;
                case "Charts":




                    query= em.createQuery("SELECT count(a) FROM  Charts a ");
                    max = (Long) query.getSingleResult();

                            query= em.createQuery("SELECT a FROM  Charts a ");

                    allData = FXCollections.observableArrayList();
                    for (int i = 1; i < max; i++) {
                        Map<String, String> dataRow = new HashMap<>();
                        Charts chart=(Charts)query.getResultList().get(i);

                        String value1 =String.valueOf(chart.getId());
                        String value2 = String.valueOf(chart.getAlbumId());
                        String value4 = String.valueOf(chart.getPosition());
                        String value3 = String.valueOf(chart.getArtistId());

                        //String value6 =  query.getResultList().get(i).toString();

                        dataRow.put("column0", value1);
                        dataRow.put("column1", value2);
                        dataRow.put("column2", value3);
                        dataRow.put("column3", value4);

                        allData.add(dataRow);

                    }

                    return allData;
                case "Genres":

                    query= em.createQuery("SELECT count(a) FROM  Genres a ");
                    max = (Long) query.getSingleResult();

                    query= em.createQuery("SELECT a FROM  Genres a ");




                    allData = FXCollections.observableArrayList();
                    for (int i = 1; i < max; i++) {
                        Map<String, String> dataRow = new HashMap<>();
                        Genres genre=(Genres)query.getResultList().get(i);

                        String value1 =String.valueOf(genre.getId());
                        String value2 = String.valueOf(genre.getName());


                        dataRow.put("column0", value1);
                        dataRow.put("column1", value2);


                        allData.add(dataRow);

                    }
                    return allData;

            }


          return null;

        }





        ///COMBOBOX ALEGERE TABEL

        public void actiuneComboBox2(ActionEvent event) throws IOException {

            alegereTabela=new String(comboBox2.getValue());
            System.out.println(alegereTabela);
            functionare();
        }

        ///COMBOBOX ALEGERE METODA DE LUCRU
        public void actiuneComboBox(ActionEvent event) throws IOException {

            alegereBD=new String(comboBox.getValue());
            System.out.println(alegereBD);
            functionare();
        }




        public void initialize(URL url, ResourceBundle resourceBundle) {
            comboBox.getItems().addAll(
                    "JDBC",
                    "JPA"

            );
            comboBox2.getItems().addAll(
                    "Artists",
                    "Albums",
                    "Genres",
                    "Charts"

            );



        }

        @FXML

        private TableColumn<Artists , Integer > id_column;

        public void insertTabel(){

            PersistenceUtil pu= PersistenceUtil.getInstance();
            EntityManagerFactory emf =pu.getEmf();
            EntityManager em = emf.createEntityManager();
            Query query= em.createQuery("SELECT a.id FROM  Artists a ");




            TableColumn id_column = new TableColumn("id");
            for(int i=0;i<query.getResultList().size();i++)
            {id_column.setText(query.getResultList().get(i).toString());
                tabela.getColumns().addAll(id_column);}
        }

        //buttons
        ///butoane care gestioneaza toate textfield-urile si scoate rezultate pt query-urile speciale asignate lor
        public void okButtonAction(ActionEvent actionEvent){


            System.out.println("okkkkkk");
            querySpecial=null;

            PersistenceUtil pu= PersistenceUtil.getInstance();
            EntityManagerFactory emf =pu.getEmf();
            EntityManager em = emf.createEntityManager();

            ArtistRepository ar= ArtistRepository.getInstance();


          if(textField1.getText()!=null)
          {
              System.out.println(textField1.getText());
              resultArtist=ar.findById(Integer.parseInt(textField1.getText()));
              System.out.println(resultArtist.get().getName());
              writeTable();
          }


        }

        ///EXIT
        public void exitButton(ActionEvent actionEvent) {

            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();

            System.out.println("inchis!");
        }


    }
