package be.intecbrussel.cookies;

import java.util.ArrayList;

public class CookieJarAdd {

    private int count;
    private Object monitor = new Object();

    public CookieJarAdd() {
    }

    public void addCookie(Cookie cookie, ArrayList<Cookie> cookieJar){
        synchronized (monitor) {
            if(count<=1000) {
                cookieJar.add(cookie);
                count++;
            }else{
                System.out.println("cookiejar full number of cookies = "+count);
            }
        }
    }
}
