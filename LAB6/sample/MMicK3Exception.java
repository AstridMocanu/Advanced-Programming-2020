package sample;

public class MMicK3Exception extends  Exception{

    public MMicK3Exception(Exception ex) {
        super("Input invalid.Ati introdus o valoare mai mica ca 3 si deci imposibil de desenat", ex);
    }
}
