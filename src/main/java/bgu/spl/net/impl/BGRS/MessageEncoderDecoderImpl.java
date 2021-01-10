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
        pushByte(nextByte);
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
        else if ((opCode >= 5 & opCode <= 10 & opCode != 8 & len == 4)) {
            System.out.println(Arrays.toString(bytes));
            return popString();
        }

        else if (opCode == 8 & numOfZeros == 2)
            return popString();

        return null;
    }


    private String popString() {
        String result;

        if (opCode >= 5 & opCode <= 10){
            byte[] bytesNum = new byte[2];
            bytesNum[0] = bytes[2];
            bytesNum[1] = bytes[3];
            short num = bytesToShort(bytesNum);
            result = String.valueOf(opCode) + " " + num;
        }
        else {
            String messageString = new String(bytes, 2, len, StandardCharsets.UTF_8);
            String[] splitMessage = messageString.split("\0");
            result = String.valueOf(opCode) + " ";
            for (String curr : splitMessage)
                result += curr + " ";
        }

        len = 0;
        opCode = 0;
        numOfZeros = 0;

        return result;
    }

    private short bytesToShort(byte[] byteArr) {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

    public byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    @Override
    public byte[] encode(String message) {

        String[] splitMessage = message.split(" ");
        short opCode = Short.parseShort(splitMessage[1]);
        byte[] ackOrError = shortToBytes(Short.parseShort(splitMessage[0]));
        byte[] opCodeMessage = shortToBytes(Short.parseShort(splitMessage[1]));
        byte[] optional = null;

        if(splitMessage.length > 2) {
            if(opCode == 6){
                String noParenthesis = splitMessage[2].substring(1, splitMessage.length);
                String[] kdamCourses = noParenthesis.split(",");

                byte[][] kdamCourseBytes = new byte[kdamCourses.length][];
                if(!kdamCourses[0].equals("")) {
                    int index = 0;
                    for (String courseNum : kdamCourses)
                        kdamCourseBytes[index++] = shortToBytes(Short.parseShort(courseNum));

                }
                optional= shortToBytes(Short.parseShort(splitMessage[2]));
                System.out.println("optional1 -->  " + Arrays.toString(optional));
            }
            else {
                optional = ("\0" + splitMessage[2] + "\0").getBytes();
                System.out.println("optional -->  " + Arrays.toString(optional));
            }
        }
        byte[] messageByte = new byte[ackOrError.length + opCodeMessage.length];
        System.arraycopy(ackOrError, 0, messageByte, 0, ackOrError.length);
        System.arraycopy(opCodeMessage, 0, messageByte, ackOrError.length, opCodeMessage.length);

        if(optional != null){
            byte[] result = new byte[messageByte.length + optional.length];
            System.arraycopy(messageByte, 0, result, 0, messageByte.length);
            System.arraycopy(optional, 0, result, messageByte.length, optional.length);

            System.out.println("result -->  " + Arrays.toString(result));

            return result;
        }
        return messageByte;
    }
}
