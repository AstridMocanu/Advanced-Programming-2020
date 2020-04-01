package sample;

public class Token {
    private String nr; ///String deoarece ii putem da si valoare BLANK
   // private static Integer maxVal;
    private boolean availableToken;


    public Token() {

    }

    public Token(String nr) {
        this.nr = nr;
        availableToken=true;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    /*
    public static Integer getMaxVal() {
        return maxVal;
    }

    public static void setMaxVal(Integer maxVal) {
        Token.maxVal = maxVal;
    }
*/
    public boolean isAvailableToken() {
        return availableToken;
    }

    public void setAvailableToken(boolean availableToken) {
        this.availableToken = availableToken;
    }


}
