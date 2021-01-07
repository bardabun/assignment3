package bgu.spl.net.impl.BGRS.messages;

public class Acknowledgement {
    private int opCode;

    public Acknowledgement(){
        this.opCode = 0;
    }
    public Acknowledgement(int opCode){
        this.opCode = opCode;
    }

    public int getOpCode() {
        return opCode;
    }

    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    public String execute(){
        return opCode != 0 ? "ACK " + opCode : "ACK";
    }
}
