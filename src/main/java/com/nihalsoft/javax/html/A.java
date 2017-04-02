package com.nihalsoft.javax.html;

public class A extends BaseTag<A> {

    public A() {
        super("a");
    }

    public String href() {
        return this.attr("href");
    }

    public A href(String href) {
        this.attr("href", href);
        return this;
    }
}
