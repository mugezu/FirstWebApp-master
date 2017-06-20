package util.Spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by Роман on 15.06.2017.
 */
public class SpringContext {
    private static SpringContext ourInstance;
    private static AbstractApplicationContext context;


    public static synchronized SpringContext getInstance() {
        if (ourInstance == null) {
            ourInstance = new SpringContext();
        }

        return ourInstance;
    }

    private SpringContext() {
        //  context = new XmlWebApplicationContext();
        // context = new FileSystemXmlApplicationContext(new File("").getAbsoluteFile().getParent() + "\\webapps\\ROOT\\WEB-INF\\applicationContext.xml");
        context = new FileSystemXmlApplicationContext("C:\\Users\\Роман\\IdeaProjects\\FirstWebApp-master\\src\\main\\webapp\\WEB-INF\\applicationContext.xml");
    }

    public AbstractApplicationContext getContext() {
        return context;
    }
}
