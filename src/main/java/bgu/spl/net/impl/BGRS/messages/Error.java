package bgu.spl.net.impl.BGRS.messages;

public class Error {
    private int opCode;

    public Error(){
        this.opCode = 0;
    }
    public Error(int opCode){
        this.opCode = opCode;
    }

    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    public String execute(){
        return opCode != 0 ? "ERROR " + opCode : "ERROR";
    }
}
