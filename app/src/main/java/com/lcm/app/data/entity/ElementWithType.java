package com.lcm.app.data.entity;

import org.jsoup.nodes.Element;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/11 下午5:43
 * Desc:
 * *****************************************************************
 */

public class ElementWithType {
    private Element element;
    private String type;

    public ElementWithType(Element element, String type) {
        this.element = element;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ElementWithType{" +
                "element=" + element +
                ", type='" + type + '\'' +
                '}';
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
