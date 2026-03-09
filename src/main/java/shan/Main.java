package shan;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kaitai.struct.ByteBufferKaitaiStream;
import shan.protocol.SensorData;
import shan.protocol.SensorData.CategoryType;
import shan.protocol.SensorData.FlagBits;
import shan.protocol.TcpNewMessage;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        TcpNewMessage tcpNewMessage = createTcpMessage();
        tcpNewMessage._check();
        tcpNewMessage._write();
        byte[] payload = tcpNewMessage._io().toByteArray();
        System.out.println(Arrays.toString(payload));
        TcpNewMessage newMessage = new TcpNewMessage(new ByteBufferKaitaiStream(payload));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newMessage);
        System.out.println(json);
    }

    private static TcpNewMessage createTcpMessage() {
        TcpNewMessage tcpNewMessage = new TcpNewMessage();
        tcpNewMessage.setId("ad");
        tcpNewMessage.setMessageType(1);
        tcpNewMessage.setPayloadLength(2);

        return tcpNewMessage;
    }


    private static SensorData createSensorData() {
        SensorData sensorData = new SensorData();
        FlagBits flags = new FlagBits();
        flags.setCategory(CategoryType.HUMIDITY);
        flags.setReserved(1);
        flags.setSensorValue(2);
        flags.setIsActiveBool(true);
        sensorData.setFlags(flags);
        return sensorData;
    }
}
