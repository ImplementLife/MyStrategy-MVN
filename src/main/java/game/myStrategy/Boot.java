package game.myStrategy;

import game.myStrategy.game.resource.ResourceService;
import game.myStrategy.ui.menu.FrameController;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Boot {
    private static ApplicationContext context;
    public static ApplicationContext getContext() {
        return context;
    }
    public static <T> T getBean(Class<T> aClass) throws BeansException {
        return context.getBean(aClass);
    }

    public static void main(String[] args) {
        context = new AnnotationConfigApplicationContext(Boot.class);
        getBean(ResourceService.class).load();
        getBean(FrameController.class).setMainMenu();
    }
}
