package org.jacekkowalczyk82.c2p;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Content {

    private static Content instance;

    public String getText() {
        return text;
    }

    private String text;
    private String image;
    private List<String> ulList;
    private List<String> liList;

    public static Content withText(String text) {
        Content c =  getInstance();
        c.text = text;
        return c;
    }

    private static Content getInstance() {
        if (instance == null) {
            instance = new Content();
        }
        return instance;
    }

    public static Content withUlList(String ... listArgs) {
        Content c =  getInstance();
        List<String> list = Arrays.stream(listArgs).collect(Collectors.toList());
        c.ulList = list;
        return c;
    }

    public static Content withLiList(String ... listArgs) {
        Content c =  getInstance();
        List<String> list = Arrays.stream(listArgs).collect(Collectors.toList());
        c.liList = list;
        return c;
    }

    public static Content withImage(String image) {
        Content c =  getInstance();
        c.image = image;
        return c;
    }
}
