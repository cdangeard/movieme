package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.server.ExternalResource;



import java.util.ArrayList;


import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/* import for explicit declaration of callback */
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button.ClickEvent;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("prof.prodageo.org.MyAppWidgetset")
public class MyUI extends UI {

        private static final Logger log = LoggerFactory.getLogger(MyUIServlet.class);
        private ArrayList<String> recherches = new ArrayList<>();

    /* explicit declaration as attributes of graphical components for GenMyModel */
        final VerticalLayout layout = new VerticalLayout();
        final VerticalLayout layoutver2 = new VerticalLayout();
        final HorizontalLayout layoutHor = new HorizontalLayout();
        final HorizontalLayout layoutHor2 = new HorizontalLayout();
        final VerticalLayout layoutver = new VerticalLayout();
        Panel movieme = new Panel("MovieMe");
        final TextField film = new TextField();
        final TextField surname = new TextField();
        Label titre = new Label ("MovieMe");
        Button rechercher = new Button("Rechercher") ;
        Button profil = new Button("Mon Profil");
        Button preferences = new Button("Preferences");
        Button after = new Button("After Movie");
        Button recherchesBut = new Button("Mes recherches");
        Button reservationsBut = new Button("Mes reservations");



    /* explicit callback */
    /* https://vaadin.com/docs/-/part/framework/application/application-events.html */
    public class ClickMeClass implements Button.ClickListener
    {
        public void buttonClick(ClickEvent event)
        {
            if(event.getSource().equals(rechercher)){
              layout.addComponent(new Label("Voici les resultats de la recherche " + film.getValue() + " :    " +"titre du film :    "+"Réalisateur :    " + "Seances du film autour de vous : "));
              log.info("Recherche faites pour : " + film.getValue());
              recherches.add(film.getValue());
            }

            if(event.getSource().equals(after)){
              layout.addComponent(new Label("Voici quelques url interessant : " + "https://www.alloresto.fr/"));
              log.info("after Movie bouton");
            }

            if(event.getSource().equals(recherchesBut)){
              log.info("recherches bouton : ");
              layout.addComponent(new Label("Voici vos recherches recentes : "));
              for(String s : recherches){
                layout.addComponent(new Label(s));
                log.info(s);
              }
            }

            if(event.getSource().equals(reservationsBut)){
              log.info("reservations bouton : ");
              layout.addComponent(new Label("Voici vos reservations : film : Django:Unchained; Seances : VO_29.05.2012_12h50_stSever ; nb places : 3"));
            }


        }
    }



    @Override
    protected void init(VaadinRequest vaadinRequest) {

        movieme.setHeight(100.0f, Unit.PERCENTAGE);
        Image image = new Image(null,new ExternalResource("https://geoinfoindia.files.wordpress.com/2015/04/google-map.jpg"));


        // final VerticalLayout layout = new VerticalLayout();

        // final TextField name = new TextField();
        film.setCaption("Recherche du film");

        /*
        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));
            log.info("Button clicked with value : " + name.getValue());
        });
        */
        ClickMeClass callback = new ClickMeClass() ;
        rechercher.addClickListener( callback ) ;
        after.addClickListener( callback ) ;
        recherchesBut.addClickListener( callback ) ;
        reservationsBut.addClickListener( callback ) ;

        layoutver.addComponents(profil,preferences);
        layoutver.setMargin(true);
        layoutver.setSpacing(true);
        layoutHor.addComponents(titre,layoutver);
        layoutHor.setMargin(true);
        layoutHor.setSpacing(true);
        layoutver2.addComponents(recherchesBut,reservationsBut);
        layoutver2.setMargin(true);
        layoutver2.setSpacing(true);
        layoutHor2.addComponents(after,layoutver2);
        layoutHor2.setMargin(true);
        layoutHor2.setSpacing(true);
        layout.addComponents(layoutHor,film, rechercher,image,layoutHor2);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
