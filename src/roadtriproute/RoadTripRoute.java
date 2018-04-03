package roadtriproute;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.geometry.Insets;
import java.io.File;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class RoadTripRoute extends Application implements MapComponentInitializedListener {

    GoogleMapView mapView;
    GoogleMap map;
    GraphMap mapGraph;
    
    private static final int MARGIN_VAL = 10;

    @Override
    public void start(Stage stage) throws Exception {
        //Create the JavaFX component and set this as a listener so we know when 
        //the map has been initialized, at which point we can then begin manipulating it.
        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);
    
        BorderPane bp = new BorderPane();
        // create components for fetch tab
        Button displayButton = new Button("Show Map");
        TextField tf = new TextField();
        ComboBox<File> cb = new ComboBox<File>();
        VBox fetchBox = getFetchBox(displayButton, cb);
    
        Button resetButton = new Button("Reset");
        Button visualizeButton = new Button("Visualize Route");
    
        Label startLabel = new Label("   No Chosen Point");
        Label stopLabel = new Label("   No Chosen Point");
        startLabel.setMinWidth(180);
        stopLabel.setMinWidth(180);
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
    
        Label routeDistLabel = new Label("No route distance yet.");
    
        Tab routeTab = new Tab("Routing");
        setupRouteTab(routeTab, fetchBox, routeDistLabel,
                      startLabel, stopLabel, 
                      resetButton, visualizeButton, startButton,
                      stopButton);
    
        TabPane tp = new TabPane(routeTab);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        /*mapView.addMapReadyListener(() -> {
            GeneralService gs = new GeneralService(mapView, manager, markerManager);
            RouteService rs = new RouteService(mapView, manager, markerManager);
            //System.out.println("in map ready : " + this.getClass());
            // initialize controllers
            new RouteController(rs, routeButton,
		resetButton, startButton, stopButtons, 
		group, searchOptions, visualizationButton,
		startLabel, stopLabel,
		manager, markerManager);
        });*/
        
        bp.setLeft(tp);
        bp.setCenter(mapView);
    
        Scene scene = new Scene(bp);
        stage.setTitle("Road Trip Route");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(-6.891943, 107.610395))
                .overviewMapControl(false)
                .panControl(true)
                .rotateControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(15);
        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        /*MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(-6.891943, 107.610395) )
                    .visible(Boolean.TRUE)
                    .title("My Marker");
        
        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);*/

    }

    /**
    * Setup layout of route tab and controls
    *
    * @param routeTab
    * @param box
    */
    private void setupRouteTab(Tab routeTab, VBox fetchBox,
                             Label routeDistLabel,
                             Label startLabel, 
                             Label stopLabel, 
		  	     Button resetButton, Button vButton, 
		  	     Button startButton, Button stopButton) {

        //set up tab layout
        HBox h = new HBox();
      
        // v is inner container
        VBox v = new VBox();
        h.getChildren().add(v);

        VBox selectLeft = new VBox();

        selectLeft.getChildren().add(startLabel);
        
        HBox startBox = new HBox();
        startBox.getChildren().add(startButton);
        startBox.getChildren().add(startLabel);
        startBox.setSpacing(2);

        HBox stopBox = new HBox();
        stopBox.getChildren().add(stopButton);
        stopBox.getChildren().add(stopLabel);
        stopBox.setSpacing(2);
      
        VBox routeInfoBox = new VBox();
        Label routeInfoLabel = new Label("Route Info: ");
        routeInfoBox.getChildren().add(routeInfoLabel);
        routeInfoBox.getChildren().add(routeDistLabel);
      
        VBox.setMargin(routeInfoLabel, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
        VBox.setMargin(routeDistLabel, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
        VBox.setMargin(fetchBox, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL*2,MARGIN_VAL*2));
        VBox.setMargin(startBox, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
        VBox.setMargin(stopBox, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));

        v.getChildren().add(fetchBox);
        v.getChildren().add(new Label("   Start Position: "));
        v.getChildren().add(startBox);
        v.getChildren().add(new Label("   Destination: "));
        v.getChildren().add(stopBox);
      
        v.getChildren().add(vButton);
        VBox.setMargin(vButton, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL*8));
        VBox.setMargin(resetButton, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
        vButton.setDisable(true);
        v.getChildren().add(routeInfoBox);
        v.getChildren().add(resetButton);
      
        routeTab.setContent(h);
    }
    
    private VBox getFetchBox(Button displayButton, ComboBox<File> cb) {
        // add button to tab, rethink design and add V/HBox for content
  	VBox v = new VBox();
        HBox h = new HBox();

        HBox intersectionControls = new HBox();
        cb.setPrefWidth(100);
        intersectionControls.getChildren().add(cb);
        displayButton.setPrefWidth(100);
        intersectionControls.getChildren().add(displayButton);

        h.getChildren().add(v);
        v.getChildren().add(new Label("  Choose map file: "));
        v.getChildren().add(intersectionControls);
        return v;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}