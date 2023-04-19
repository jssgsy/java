package com.univ.algorithom.base64;

import com.google.common.base.Splitter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Base64Util {
    public static final BiMap<Integer, Character> map = HashBiMap.create(64);

    /**
     * 64个可见字符的映射表，这里的key是6位二进制对应的值
     */
    static {
        map.put(0, 'A');map.put(1, 'B');map.put(2, 'C');map.put(3, 'D');
        map.put(4, 'E');map.put(5, 'F');map.put(6, 'G');map.put(7, 'H');
        map.put(8, 'I');map.put(9, 'J');map.put(10, 'K');map.put(11, 'L');
        map.put(12, 'M');map.put(13, 'N');map.put(14, 'O');map.put(15, 'P');
        map.put(16, 'Q');map.put(17, 'R');map.put(18, 'S');map.put(19, 'T');
        map.put(20, 'U');map.put(21, 'V');map.put(22, 'W');map.put(23, 'X');
        map.put(24, 'Y');map.put(25, 'Z');map.put(26, 'a');map.put(27, 'b');
        map.put(28, 'c');map.put(29, 'd');map.put(30, 'e');map.put(31, 'f');
        map.put(32, 'g');map.put(33, 'h');map.put(34, 'i');map.put(35, 'j');
        map.put(36, 'k');map.put(37, 'l');map.put(38, 'm');map.put(39, 'n');
        map.put(40, 'o');map.put(41, 'p');map.put(42, 'q');map.put(43, 'r');
        map.put(44, 's');map.put(45, 't');map.put(46, 'u');map.put(47, 'v');
        map.put(48, 'w');map.put(49, 'x');map.put(50, 'y');map.put(51, 'z');
        map.put(52, '0');map.put(53, '1');map.put(54, '2');map.put(55, '3');
        map.put(56, '4');map.put(57, '5');map.put(58, '6');map.put(59, '7');
        map.put(60, '8');map.put(61, '9');map.put(62, '+');map.put(63, '/');
    }

    @Test
    public void test1() {
        System.out.println(base64Encode("Man"));
    }

    /**
     * 对base64作base64编码操作
     * @param msg
     * @return
     */
    public String base64Encode(String msg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            sb.append(toEightBitBin(ch));
        }
        printBit(sb.toString(), 8);
        StringBuilder result = new StringBuilder();
        List<String> sixBitList = splitToSixBinary(sb.toString());
        printBit(String.join("", sixBitList), 6);
        sixBitList.forEach(sixBit -> {
            result.append(map.get(Integer.parseInt(sixBit, 2)));
        });
        // 补=，注：要取的是输入的字节时
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        if (bytes.length % 3 == 1) {
            result.append('=').append('=');
        } else if (bytes.length % 3 == 2) {
            result.append('=');
        }
        return result.toString();
    }

    void printBit(String str, int num) {
        Splitter.fixedLength(num).split(str).forEach(t -> System.out.print(t + " "));
        System.out.println();
    }

    /**
     * 将整数转成固定8位的二进制，不足八位在前面补0
     *
     * @param i
     * @return
     */
    public String toEightBitBin(int i) {
        String binaryString = Integer.toBinaryString(i);
        if (binaryString.length() < 8) {
            binaryString = prefixFixedZero(binaryString, 8 - binaryString.length());
        }
        return binaryString;
    }

    /**
     * 在字符串末尾添加固定位数的0
     * @param text
     * @param zeroLengthToAppend
     * @return
     */
    public String appendFixedZero(String text, int zeroLengthToAppend) {
        StringBuilder sb = new StringBuilder(text);
        for (int j = 0; j < zeroLengthToAppend; j++) {
            sb.append("0");
        }
        return sb.toString();
    }

    public String prefixFixedZero(String text, int zeroLengthToAppend) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < zeroLengthToAppend; j++) {
            sb.append("0");
        }
        sb.append(text);
        return sb.toString();
    }

    /**
     * 将字符串按固定6位进行分隔，不足6位的补0
     *
     * @param str
     * @return
     */
    public List<String> splitToSixBinary(String str) {
        Iterator<String> iterator = Splitter.fixedLength(6).split(str).iterator();
        List<String> resultList = new ArrayList<>();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.length() < 6) {
                resultList.add(appendFixedZero(next, 6 - next.length()));
            } else {
                resultList.add(next);
            }
        }
        return resultList;
    }


}
