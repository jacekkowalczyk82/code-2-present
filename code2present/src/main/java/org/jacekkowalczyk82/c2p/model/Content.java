package org.jacekkowalczyk82.c2p.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Content {

    // private static Content instance;

    

    private ContentType contentType;
    private String text;
    private String image;
    private List<String> list;
//    private List<String> liList;

    public static Content withText(String text) {
        Content c =  new Content();
        c.text = text;
        c.contentType = ContentType.TEXT;
        return c;
    }

    public static Content withCodeText(String code) {
        Content c =  new Content();
        c.text = code;
        c.contentType = ContentType.CODE;
        return c;
    }

    // private static Content getInstance() {
    //     if (instance == null) {
    //         instance = new Content();
    //     }
    //     return instance;
    // }



    public static Content withUlList(String ... listArgs) {
        Content c =  new Content();
        
        List<String> list = Arrays.stream(listArgs).collect(Collectors.toList());
        c.list = list;
        c.contentType = ContentType.UL_LIST;
        return c;
    }

    public static Content withLiList(String ... listArgs) {
        Content c =  new Content();
        List<String> list = Arrays.stream(listArgs).collect(Collectors.toList());
        c.list = list;
        c.contentType = ContentType.LI_LIST;
        return c;
    }

    public static Content withImage(String image) {
        Content c =  new Content();
        c.image = image;
        c.contentType = ContentType.IMAGE;
        return c;
    }

    public String getImage() {
        return image;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public String getText() {
        return text;
    }

    public List<String> getList() {
        return list;
    }

//    public List<String> getLiList() {
//        return liList;
//    }


}
