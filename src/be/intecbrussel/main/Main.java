package be.intecbrussel.main;


import be.intecbrussel.cookies.Cookie;
import be.intecbrussel.cookies.CookieJarAdd;
import be.intecbrussel.thread.CaramelFudgeThread;
import be.intecbrussel.thread.ChocolateChipThread;
import be.intecbrussel.thread.PlainOldJavaThread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        File chocolateChipFile = new File("cc.txt");
        File caramelFudgeFile = new File("cf.txt");
        File plainOldJavaFile = new File("poj.txt");

        ArrayList<Cookie> cookieJar = new ArrayList<>();
        CookieJarAdd cookieJarAdd = new CookieJarAdd();

        try {
            ObjectOutputStream ccOutputStream = new ObjectOutputStream(new FileOutputStream(chocolateChipFile));
            ObjectOutputStream cfOutputStream = new ObjectOutputStream(new FileOutputStream(caramelFudgeFile));
            ObjectOutputStream pojfOutputStream = new ObjectOutputStream(new FileOutputStream(plainOldJavaFile));


            Thread thread1 = new ChocolateChipThread(cookieJar,cookieJarAdd, ccOutputStream );
            Thread thread2 = new CaramelFudgeThread(cookieJar,cookieJarAdd,cfOutputStream);
            Thread thread3 = new PlainOldJavaThread(cookieJar,cookieJarAdd,pojfOutputStream);


            thread1.start();
            thread2.start();
            thread3.start();

            thread1.join();
            thread2.join();
            thread3.join();

            System.out.println("cookies in cookiejar:"+cookieJar.size());
            for (Cookie cookie : cookieJar) {
                System.out.println(cookie);

            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
