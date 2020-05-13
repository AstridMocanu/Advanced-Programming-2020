package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Obiecte  {
    private String nume;
    private String pachet;
    private String methodName;
    private Integer id;
    private static Integer idMax;

    public Obiecte(String nume, String pachet) {
        this.nume = nume;
        this.pachet = pachet;
    }

    public Obiecte(String nume, String pachet, String methodName) {
        if(nume.equals("Button"))idMax=-1;
        this.id=idMax+1;
        this.nume = nume;
        this.pachet = pachet;
        this.methodName = methodName;
    }

    public Obiecte() {
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPachet() {
        return pachet;
    }

    public void setPachet(String pachet) {
        this.pachet = pachet;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

