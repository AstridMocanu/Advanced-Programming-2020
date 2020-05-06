package lab11.demo;



public class MyException extends Exception {

    private String message;

    public MyException(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
