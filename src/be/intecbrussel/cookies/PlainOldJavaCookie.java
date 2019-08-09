package be.intecbrussel.cookies;

import java.io.Serializable;

public class PlainOldJavaCookie extends Cookie implements Serializable {
    private final String flavor = "Plain old Java";

    public PlainOldJavaCookie() {
    }
}
