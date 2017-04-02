package com.nihalsoft.javax.html;

public class Document extends BaseTag<Document> {

    private Body body = null;
    private Head head = null;

    public Document() {
        super("html");
        head = new Head();
        append(head);
        body = new Body();
        append(body);
    }

    public ITag body() {
        return body;
    }

    public ITag head() {
        return head;
    }

}
