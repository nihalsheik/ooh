package com.nihalsoft.javax.html;

public class Test1 {

    public static void main(String[] args) {
        Div div = new Div();
        Div t = new Div();

        t.setId("txtLogin").setName("txtLogin").attr("data-id", "123");

        t.append(new Div());

        div.append(t);

        Tag tag = new Tag("span");
        tag.attr("name", "test");
        tag.css("background", "white").css("border", "1px solid #ffff00");
        tag.append(t);
        tag.addClass("login login-new");
        div.append(tag);

        A a = new A();
        a.href("http://google.com");
        tag.append(a);
        
        Document doc = new Document();
        doc.body().append(div);

        System.out.println(doc);

    }

}
