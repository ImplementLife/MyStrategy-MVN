package game.myStrategy;

import game.myStrategy.game.resource.ResourceService;
import game.myStrategy.ui.FrameController;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;

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

//
//    static void initUi() {
//        JFrame frame = new JFrame( "JLayeredPaneExample");
//        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
//
//        // Панель с содержимымы, поверх которого нужно будет выставлять прогрессбар
//        JPanel background = new JPanel();
//        background.setBackground(Color.RED);
//
//        // прозрачная панель с прогрессбаром по центру
//        JPanel glasspane = new JPanel( new GridBagLayout() );
//        JProgressBar bar = new JProgressBar(0, 100);
//        glasspane.add( bar, new GridBagConstraints() );
//        glasspane.setBackground(new Color(17, 4, 134, 197));
//        glasspane.setOpaque( false );
//        glasspane.addMouseListener( new MouseAdapter() {} );
//
//        JLayeredPane layeredPane = new JLayeredPane();
//        layeredPane.add( background, JLayeredPane.DEFAULT_LAYER, 1 );
//        layeredPane.add( glasspane, JLayeredPane.MODAL_LAYER, 2 );
//        layeredPane.addComponentListener( new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                background.setSize( e.getComponent().getSize() );
//                glasspane.setSize( e.getComponent().getSize() );
//            }
//        });
//
//        JPanel contentPane = new JPanel( new BorderLayout() );
//        contentPane.add( layeredPane, BorderLayout.CENTER );
//
//
//        frame.setContentPane( contentPane );
//        frame.setSize( 800, 600 );
//        frame.setVisible( true );
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater( Boot::initUi );
//    }
}
