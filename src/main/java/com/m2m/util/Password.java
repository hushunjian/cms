package com.m2m.util;

import java.util.*;

public class Password {
    private static Character[] _LOWERS = {
            'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x',
            'y', 'z'};
    private static Character[] _UPPERS = {
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'};
    private static Character[] _DIGITS = {
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9'
    };
    private static Character[] _SYMBOLS = {
    		'~','`','!','@','#','$','%','^','&','*','(',
    		')','_','+','-','=','{','}','|','[',']','\\',
    		':','"',';','\'','<','>','?',',','.','/'
    };

    private static List<Character> LOWERS = Arrays.asList(_LOWERS);
    private static List<Character> UPPERS = Arrays.asList(_UPPERS);
    private static List<Character> DIGITS = Arrays.asList(_DIGITS);
    private static List<Character> SYMBOLS = Arrays.asList(_SYMBOLS);
    private static List<Character> GLOSSARY = new ArrayList<>();

    static {
        GLOSSARY.addAll(LOWERS);
        GLOSSARY.addAll(UPPERS);
        GLOSSARY.addAll(DIGITS);
        GLOSSARY.addAll(SYMBOLS);
    }

    public static String random() {
        StringBuffer rtn = new StringBuffer();
        List<Character> password = new ArrayList<>();
        Random random = new Random();
        password.add(LOWERS.get(random.nextInt(LOWERS.size())));
        password.add(UPPERS.get(random.nextInt(UPPERS.size())));
        password.add(DIGITS.get(random.nextInt(DIGITS.size())));
        password.add(SYMBOLS.get(random.nextInt(SYMBOLS.size())));
        int max = 4 + random.nextInt(4);
        for (int i = 0; i < max; i++) {
            password.add(GLOSSARY.get(random.nextInt(GLOSSARY.size())));
        }
        Collections.shuffle(password);
        password.forEach(c -> rtn.append(c));
        return rtn.toString();
    }
}
