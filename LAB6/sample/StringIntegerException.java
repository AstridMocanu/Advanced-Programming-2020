package sample;

public class StringIntegerException
        extends Exception
{
    public StringIntegerException(Exception ex) {
        super("Input invalid.Ati introdus o valoare string,double,boleean,s.a intr-un camp ce permitea doar o valoare numerica intreaga", ex);
    }
}