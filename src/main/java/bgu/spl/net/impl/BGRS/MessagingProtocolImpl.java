package bgu.spl.net.impl.BGRS;
import bgu.spl.net.api.MessagingProtocol;

public class MessagingProtocolImpl<T> implements MessagingProtocol<String> {
    private boolean shouldTerminate = false;
    @Override
    public String process(String msg) {
       // shouldTerminate ;
        return null;
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
