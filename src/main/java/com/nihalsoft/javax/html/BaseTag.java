package com.nihalsoft.javax.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseTag<E> implements ITag {

    List<ITag> children = new ArrayList<ITag>();

    private Map<String, String> attrs = new LinkedHashMap<String, String>();

    private Map<String, String> css = new LinkedHashMap<String, String>();

    private List<String> className = new ArrayList<String>();

    private String tagName = "";

    private ITag parent = null;

    /**
     * 
     */
    private int index = 0;

    public int index() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BaseTag() {
    }

    public BaseTag(String tagName) {
        this.tagName = tagName;
    }

    public String getId() {
        return (String) attrs.get("id");
    }

    @SuppressWarnings("unchecked")
    public E setId(String id) {
        this.attrs.put("id", id);
        return (E) this;
    }

    public String getName() {
        return (String) attrs.get("name");
    }

    @SuppressWarnings("unchecked")
    public E setName(String name) {
        this.attrs.put("name", name);
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E attr(String key, String value) {
        attrs.put(key, value);
        return (E) this;
    }

    public String attr(String key) {
        return (String) attrs.get(key);
    }

    @SuppressWarnings("unchecked")
    public E css(String key, String value) {
        css.put(key, value);
        return (E) this;
    }

    public String css(String key) {
        return (String) css.get(key);
    }

    protected String getTagName() {
        return this.tagName;
    }

    @SuppressWarnings("unchecked")
    public E append(ITag tag) {
        tag.setIndex(this.children.size());
        this._setParent(tag);
        children.add(tag);
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E append(int index, ITag tag) {
        children.add(index, tag);
        this._setParent(tag);
        this._reindex();
        return (E) this;
    }

    private void _reindex() {
        int i = 0;
        for (ITag tag : this.children) {
            tag.setIndex(i++);
        }
    }

    public E removeChild(ITag tag) {
        this.children.remove(tag.index());
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E remove() {
        System.out.println("removing myself");
        this.parent.removeChild(this);
        return (E) this;
    }

    public ITag parent() {
        return parent;
    }

    private void _setParent(ITag tag) {
        ITag p = tag.parent();
        if (p != null) {
            tag.remove();
        }
        tag.setParent(this);
    }

    public E setParent(ITag tag) {
        this.parent = tag;
        return (E) this;
    }

    private String repeat(int len) {
        char[] chars = new char[len];
        Arrays.fill(chars, ' ');
        return String.valueOf(chars);
    }

    public String toString(int indent) {
        StringBuffer sb = new StringBuffer();
        sb.append(this.repeat(indent));
        sb.append("<" + getTagName());

        if (attrs.size() > 0) {
            sb.append(" " + this._toAttrString());
        }

        if (css.size() > 0) {
            sb.append(" style=\"" + _toCssString() + "\"");
        }

        if (this.className.size() > 0) {
            sb.append(" class=\"" + this._toClassString() + "\"");
        }

        sb.append(">");

        sb.append(System.lineSeparator());
        Iterator<ITag> it = children.iterator();
        while (it.hasNext()) {
            ITag i = it.next();
            sb.append(i.toString(indent + 2));
        }
        sb.append(System.lineSeparator());
        sb.append(this.repeat(indent));
        sb.append("</" + this.getTagName() + ">");
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    public String toString() {
        return this.toString(0);
    }

    private String _toCssString() {
        StringBuffer sb = new StringBuffer();

        Iterator<Entry<String, String>> it = this.css.entrySet().iterator();
        Entry<String, String> e;

        while (it.hasNext()) {
            e = it.next();
            if (e.getValue() != null && !e.getValue().equals("")) {
                sb.append(e.getKey().toString() + ":" + e.getValue().toString() + ";");
            }
        }
        return sb.toString().trim();
    }

    private String _toAttrString() {
        StringBuffer sb = new StringBuffer();

        String q = "\"";

        Iterator<Entry<String, String>> it = this.attrs.entrySet().iterator();
        Entry<String, String> e;

        while (it.hasNext()) {
            e = it.next();
            if (e.getValue() != null && !e.getValue().equals("")) {
                sb.append(e.getKey().toString() + "=" + q + e.getValue().toString() + q + " ");
            }
        }
        return sb.toString().trim();
    }

    private String _toClassString() {
        this.className.toArray();
        StringBuilder sb = new StringBuilder();
        for (String t : this.className) {
            sb.append(t + " ");
        }
        return sb.toString();
    }

    public E addClass(String className) {
        this.className.add(className);
        return (E) this;
    }

    public E removeClass(String className) {
        int i = this.className.indexOf(className);
        if (i != -1) {
            this.className.remove(i);
        }
        return (E) this;
    }

    public boolean hasClass(String className) {
        return this.className.contains(className);
    }

}
