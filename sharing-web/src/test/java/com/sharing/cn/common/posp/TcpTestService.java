package com.sharing.cn.common.posp;

import com.sharing.cn.utils.pos.CRC16;
import com.sharing.cn.utils.pos.HexCodec;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpTestService {


    @Test
    public void init() {
        String host = "localhost";
        int localPort = 40057;

        // 400D
//        String hexMessage = "0120250711100101209496376979886080011060980101003c6870754bFC32308740E559D098D36095479DF767652CE1881BF5904DAA67180D5777A8FE09AD905A5F958E4BB8FD9C3571CECEFCC495DEED3AA3693ADA8FE13B25449FCB";
        String hexMessage = "008948B700400D02230012340120AF951C202507181610289990010000006879F175000A0120250718100101212099893238304768015657090101000A6879F1751CA65B61C009101271E2F1938E969161CCF179AA44DD92982D64B74725DAE94D222931924A7FDC846C206B2982C6601FD184A4D75B02A7BBC77E4C94396C268B005DFFFFFFFFFFFF907D";
        String hexLength = String.format("%04X", hexMessage.getBytes().length);

        hexMessage = "400D012000166A" + hexMessage;
        String crc16Value = CRC16.CRC_16(HexCodec.hexDecode(hexMessage));



        sendTcp(host, localPort, hexMessage);
    }

    public static void main(String[] args) {
        // 008C
        // 9A37
        // 00
        // 3001
        // 02
        // 320003F0
        // 000121250000010000001F00000030020299999900052300000000001700080000000200000007000000000000000A000000FC00000000000004850000007800000001278500010000000000
        // 00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
        // DF0E
        String hexMessage = "0120250711100101209496376979886080011060980101003c6870754bFC32308740E559D098D36095479DF767652CE1881BF5904DAA67180D5777A8FE09AD905A5F958E4BB8FD9C3571CECEFCC495DEED3AA3693ADA8FE13B25449FCB";
        String crc16Value = CRC16.CRC_16(HexCodec.hexDecode(hexMessage));
        System.out.println("crc16Value:" + crc16Value);
        byte[] bytes = hexMessage.getBytes(StandardCharsets.UTF_8); // 获取字节数组
        int length = bytes.length; // 字节长度
        String hexLength = String.format("%04X", length);
        System.out.println("字符串字节长度: " + length);
        System.out.println("2HEX表示: " + hexLength);
    }

    public static void sendTcp(String host, int port, String hexMessage) {

        try (Socket socket = new Socket(host, port)) {
            OutputStream out = socket.getOutputStream();
            byte[] messageBytes = hexStringToByteArray(hexMessage);
            // 打印二进制数的base64
            System.out.println();
            System.out.println("发送报文：" + hexMessage);
            out.write(messageBytes);
            out.flush();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }


}
