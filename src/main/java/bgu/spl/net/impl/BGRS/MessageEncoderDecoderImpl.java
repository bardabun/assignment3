package bgu.spl.net.impl.BGRS;

import bgu.spl.net.api.MessageEncoderDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MessageEncoderDecoderImpl<T> implements MessageEncoderDecoder<String> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    private int numOfZeros = 0;
    private short opCode=0 ;


    @Override
    public String decodeNextByte(byte nextByte) {
        if (nextByte == '\0')
            numOfZeros++;
        if (len == 2) {
            opCode = bytesToShort(bytes);
            if (opCode == 4 | opCode == 11)
                return popString();
        }
        //between 1 to 3
        if (opCode >= 1 & opCode <= 3 & numOfZeros == 3)
            return popString();
            //between 5 to 10 (not include 8)
        else if (len==4 && (opCode >= 5 & opCode <= 10 & opCode != 8 ))
            return popString();

        else if (opCode == 8 & numOfZeros == 2)
            return popString();

        pushByte(nextByte);
        return null;
    }


    private String popString() {
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        opCode=0;
        return result;
    }

    private short bytesToShort(byte[] byteArr) {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    @Override
    public byte[] encode(String message) {
        return new byte[0];
    }
}
