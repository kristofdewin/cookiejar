package be.intecbrussel.thread;

import be.intecbrussel.cookies.CaramelFudgeCookie;
import be.intecbrussel.cookies.Cookie;
import be.intecbrussel.cookies.CookieJarAdd;
import be.intecbrussel.cookies.PlainOldJavaCookie;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PlainOldJavaThread extends Thread {
    private ArrayList<Cookie> cookiejar;
    private CookieJarAdd cookieJarAdd;
    private ObjectOutputStream outputStream;

    public PlainOldJavaThread(ArrayList<Cookie> cookiejar, CookieJarAdd cookieJarAdd, ObjectOutputStream outputStream) {
        this.cookiejar = cookiejar;
        this.cookieJarAdd = cookieJarAdd;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            if (cookiejar.size() < 1000) {
                try {
                    Cookie cookie = new PlainOldJavaCookie();
                    outputStream.writeObject(cookie);
                    addToCookieJar(cookieJarAdd, cookie);
                    Thread.sleep(137);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                this.interrupt();
            }
        }
    }
    public void addToCookieJar(CookieJarAdd add, Cookie cookie){
        add.addCookie(cookie, cookiejar);
    }

}
