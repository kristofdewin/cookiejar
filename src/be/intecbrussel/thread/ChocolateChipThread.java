package be.intecbrussel.thread;

import be.intecbrussel.cookies.ChocolateChipCookie;
import be.intecbrussel.cookies.Cookie;
import be.intecbrussel.cookies.CookieJarAdd;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ChocolateChipThread extends Thread implements Runnable {

    private ArrayList<Cookie> cookiejar;
    private CookieJarAdd cookieJarAdd;
    private ObjectOutputStream outputStream;

    public ChocolateChipThread(ArrayList<Cookie> cookiejar, CookieJarAdd cookieJarAdd, ObjectOutputStream outputStream) {
        this.cookiejar = cookiejar;
        this.cookieJarAdd = cookieJarAdd;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (cookiejar.size() < 1000) {

                Cookie cookie = new ChocolateChipCookie();
                try {
                    outputStream.writeObject(cookie);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addToCookieJar(cookieJarAdd, cookie);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                this.interrupt();
            }

        }
    }

    public void addToCookieJar(CookieJarAdd add, Cookie cookie) {
        add.addCookie(cookie, cookiejar);
    }
}
