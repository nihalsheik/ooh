package com.nihalsoft.javax.html;

public class Test1 {

    public static void main(String[] args) {
        Div div = new Div();
        Div t = new Div();

        t.setId("100").setName("Sheik").attr("id2", "aaa");

        t.append(new Div());

        div.append(t);

        Tag tag = new Tag("xyz");
        tag.attr("aa", "aa1");
        tag.css("background", "white").css("S", "ssdf");
        tag.append(t);
        div.append(tag);

        Document doc = new Document();
        doc.body().append(div);

        System.out.println(doc);

    }

}
