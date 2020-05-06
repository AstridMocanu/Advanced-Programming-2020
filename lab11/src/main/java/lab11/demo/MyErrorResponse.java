package lab11.demo;

import java.time.LocalDateTime;

public class MyErrorResponse {

    private String message;

    public MyErrorResponse(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

   // public void setTimestamp(LocalDateTime now) {    }
}