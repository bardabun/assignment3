package bgu.spl.net.impl.BGRS.messages;

public class Acknowledgement {
    private int opCode;
    private String message = "ACK ";


    public Acknowledgement(int opCode){
        this.opCode = opCode;
    }

    public Acknowledgement(int opCode, String message){

        this.opCode = opCode;
        this.message += message;
    }

    public String execute(){
        return  message + opCode;
    }
}
