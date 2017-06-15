package util.Spring;

import java.io.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by Роман on 15.06.2017.
 */
public class SpringContext {
    private static SpringContext ourInstance = new SpringContext();
    private static AbstractApplicationContext context;


    public static synchronized SpringContext getInstance() {
        if (ourInstance == null) {
            ourInstance = new SpringContext();
        }

        return ourInstance;
    }

    private SpringContext() {
        context = new FileSystemXmlApplicationContext("C:\\Users\\Роман\\IdeaProjects\\FirstWebApp-master\\src\\config.xml");
    }

    public  AbstractApplicationContext getContext() {
        return context;
    }
}
