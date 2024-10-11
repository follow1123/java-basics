package cn.y.java.collection.collections;

import java.util.*;

public class RandomUtil {

    private static final Random random = new Random();

    public static int getRandomInteger(int bound){
        return random.nextInt(bound);
    }

    public static char getRandomChar(){
        int i = getRandomInteger(62);
        if (i < 10) return (char) (48 + i);
        if (i < 36) return (char) (65 + i - 10);
        if (i < 62) return (char) (97 + i - 10 - 26);
        throw new RuntimeException("error num " + i);
    }
    public static List getRandomIntList(int size, int bound){
        ArrayList list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            list.add(getRandomInteger(bound));
        }
        return list;
    }

    public static List getRandomStringList(int size, int maxLength){
        ArrayList list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            int length = getRandomInteger(maxLength) + 1;
            char[] chars = new char[length];
            for (int j = 0; j < length; j++) {
                chars[j] = getRandomChar();
            }

            list.add(new String(chars));
        }
        return list;
    }

    public static Set getRandomIntSet(int size, int bound){
        return new HashSet(getRandomIntList(size, bound));
    }

    public static Set getRandomStringSet(int size, int maxLength){
        return new HashSet(getRandomStringList(size, maxLength));
    }
    public static void main(String[] args) {

        System.out.println(getRandomIntList(10, 100));
        System.out.println(getRandomIntSet(5, 300));
        System.out.println(getRandomStringList(10, 10));
        System.out.println(getRandomStringSet(10, 10));

    }

}
