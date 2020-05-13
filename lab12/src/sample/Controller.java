package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable{
    public Button exitButton;
    public ComboBox<String> comboBox;
    public TextField textField;

    public Button saveButton;
    public Button loadButton;
    public Button resetButton;
    public Pane pane;
    private List<Pair<Double,Double>> puncte=new ArrayList<>();
    private List<Object> elemPane=new ArrayList<>();


    private Object element;
    private Class clazz;
    private Map<Integer,Obiecte> mapObiecte=new HashMap<>();

    private Obiecte[] obiecte= {
            new Obiecte("Button","javafx.scene.control.Button","setText"),
            new Obiecte("TextField","javafx.scene.control.TextField","setPromptText"),
            new Obiecte("PasswordField","javafx.scene.control.PasswordField","setPromptText"),
            new Obiecte("TextArea","javafx.scene.control.TextArea","setText"),
            new Obiecte( "Checkbox","javafx.scene.control.CheckBox","setText"),
            new Obiecte( "RadioButton","javafx.scene.control.RadioButton","setText"),
            new Obiecte( "ComboBox","javafx.scene.control.ComboBox","setPromptText"),
            new Obiecte( "Label","javafx.scene.control.Label","setText")

            };

    /* new Obiecte("Button","java.awt.Button","setLabel"),
            new Obiecte("TextField","java.awt.TextField","setText"),
            new Obiecte("PasswordField","javax.swing.JPasswordField"),
            new Obiecte("TextArea","java.awt.TextArea"),
            new Obiecte( "Checkbox","java.awt.Checkbox","setLabel"),
            new Obiecte( "RadioButton","javax.swing.JRadioButton"),
            new Obiecte( "ComboBox","javax.swing.JComboBox<E>"),
            new Obiecte( "Label","java.awt.Label","setText"),
            new Obiecte( "Table","java.awt.Table"),
            new Obiecte( "List","java.awt.List")*/



public void initialize(URL url, ResourceBundle resourceBundle) {


        for(int i=0;i<obiecte.length;i++)
        {   mapObiecte.put(i,obiecte[i]);
            comboBox.getItems().add(mapObiecte.get(i).getNume());}
            //comboBox.getItems().add(obiecte[i]);


    }

    public void actiuneComboBox(ActionEvent event) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        if(comboBox.getValue()!=null)
        {


            Obiecte ales=new Obiecte();
            for(int i=0;i<obiecte.length;i++)
                if(mapObiecte.get(i).getNume().equals(comboBox.getValue()))
                    ales=mapObiecte.get(i);

            System.out.println(ales.getMethodName());

            clazz=Class.forName(ales.getPachet());
            element=clazz.getConstructor().newInstance();


            System.out.println("am element2");
            System.out.flush();


            ///casted ar trebui sa fie un obiect grafic acum
            //aparent si element retine acelasi lucru
            var casted = clazz.cast(element);


            System.out.println("blablabla");

            String text=textField.getText();



            try {
                clazz.getMethod(ales.getMethodName(),String.class).invoke(element,text);
                System.out.println(element.toString());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            pane.getChildren().add((Node) element);


        }

    }



    public void PaneOnMouseClick(javafx.scene.input.MouseEvent event){

        String text=textField.getText();

        Boolean ok=true;

        Double x,y;
        x=event.getX();
        y=event.getY();
        System.out.println(x+" "+y);
        System.out.println(element.getClass());


        //((Node) element).setLayoutX(x);
        try {
            clazz.getMethod("setLayoutX",double.class).invoke(element,x);
            System.out.println(element.toString());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

       // ((Node) element).setLayoutY(y);
        try {
            clazz.getMethod("setLayoutY",double.class).invoke(element,y);
            System.out.println(element.toString());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


       if(!puncte.contains(new Pair(x,y)))
       {
           // pane.getChildren().add((Node) element);


            // ((Node)element).setVisible(true);

            try {
                clazz.getMethod("setVisible", boolean.class).invoke(element, true);
                System.out.println(element.toString());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

           puncte.add(new Pair(x,y));        }

    }


    ///EXIT
    public void exitButton(ActionEvent actionEvent) {

        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

        System.out.println("inchis!");
    }

    public void SaveButton(javafx.event.ActionEvent actionEvent){

    ////am cautat pe net, obiectele create in javafx nu pot fi serializate cu beans
        ///asta ar fi fost corect daca foloseam java.awt
        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("out.xml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        encoder.writeObject(pane);
        encoder.close();

        }





}
