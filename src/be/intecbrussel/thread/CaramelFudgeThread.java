package be.intecbrussel.thread;

import be.intecbrussel.cookies.CaramelFudgeCookie;
import be.intecbrussel.cookies.Cookie;
import be.intecbrussel.cookies.CookieJarAdd;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CaramelFudgeThread extends Thread {

    private ArrayList<Cookie> cookiejar;
    private CookieJarAdd cookieJarAdd;
    private ObjectOutputStream outputStream;

    public CaramelFudgeThread(ArrayList<Cookie> cookiejar, CookieJarAdd cookieJarAdd, ObjectOutputStream outputStream) {
        this.cookiejar = cookiejar;
        this.cookieJarAdd = cookieJarAdd;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (cookiejar.size() < 1000) {
                try {
                    Cookie cookie = new CaramelFudgeCookie();
                    outputStream.writeObject(cookie);
                    addToCookieJar(cookieJarAdd, cookie);
                    Thread.sleep(330);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                this.interrupt();
            }
        }

    }

    public void addToCookieJar(CookieJarAdd add, Cookie cookie) {
        add.addCookie(cookie, cookiejar);
    }
}
