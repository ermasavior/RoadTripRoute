package roadtriproute;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
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

@Override
public void start(Stage stage) throws Exception {

    //Create the JavaFX component and set this as a listener so we know when 
    //the map has been initialized, at which point we can then begin manipulating it.
    mapView = new GoogleMapView();
    mapView.addMapInializedListener(this);
    
    BorderPane bp = new BorderPane();
    
    // create components for fetch tab
    Button fetchButton = new Button("Fetch Data");
    Button displayButton = new Button("Show Intersections");
    TextField tf = new TextField();
    ComboBox cb = new ComboBox();
    
    //HBox fetchControls = getBottomBox(tf, fetchButton);
    VBox fetchBox = new VBox();//getFetchBox(displayButton, cb);
    
    // create components for fetch tab
    Button routeButton = new Button("Show Route");
    Button hideRouteButton = new Button("Hide Route");
    Button resetButton = new Button("Reset");
    Button visualizationButton = new Button("Start Visualization");
    
    Label startLabel = new Label("Empty.");
    Label stopLabel = new Label("Empty.");
    startLabel.setMinWidth(180);
    stopLabel.setMinWidth(180);
    Button startButton = new Button("Start");
    Button stopButton = new Button("Stop");
    
    Label routeDistLabel = new Label("No route distance yet.");
    
    Tab routeTab = new Tab("Routing");
    setupRouteTab(routeTab, fetchBox, routeDistLabel,
    			  startLabel, stopLabel, 
    			  routeButton, /*hideRouteButton,*/
        		  resetButton, routeButton, startButton, 
        		  stopButton);
    
    
    TabPane tp = new TabPane(routeTab);
    tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

    
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
    MarkerOptions markerOptions = new MarkerOptions();

    markerOptions.position( new LatLong(-6.891943, 107.610395) )
                .visible(Boolean.TRUE)
                .title("My Marker");

    Marker marker = new Marker( markerOptions );

    map.addMarker(marker);

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
		  	     Button showButton, /*Button hideButton, */
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
      startBox.getChildren().add(startLabel);
      startBox.getChildren().add(startButton);
      startBox.setSpacing(20);

      VBox stopBoxes = new VBox();
      HBox stopBox = new HBox();
      stopBox.getChildren().add(stopLabel);
      stopBoxes.getChildren().add(stopBox);

      VBox markerBox = new VBox();
      Label markerLabel = new Label("Selected Marker: ");
      markerBox.getChildren().add(markerLabel);
      //markerBox.getChildren().add(pointLabel);
      
      VBox routeInfoBox = new VBox();
      Label routeInfoLabel = new Label("Route Info: ");
      routeInfoBox.getChildren().add(routeInfoLabel);
      /*routeInfoBox.getChildren().add(routeDistLabel);
      routeInfoBox.getChildren().add(routeTimeLabel);
      
      VBox.setMargin(routeInfoLabel, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
      /*VBox.setMargin(routeDistLabel, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
      VBox.setMargin(routeTimeLabel, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
      VBox.setMargin(markerLabel, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
      VBox.setMargin(pointLabel, new Insets(0,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
      VBox.setMargin(fetchBox, new Insets(0,0,MARGIN_VAL*2,0));

      HBox showHideBox = new HBox();
      showHideBox.getChildren().add(showButton);
      showHideBox.getChildren().add(hideButton);
      showHideBox.setSpacing(2*MARGIN_VAL);*/

      //v.getChildren().add(fetchBox);
      v.getChildren().add(new Label("Start Position: "));
      v.getChildren().add(startBox);
      v.getChildren().add(new Label("Stops: "));
      v.getChildren().add(stopBoxes);
      //v.getChildren().add(showHideBox);
      
      v.getChildren().add(vButton);
      //VBox.setMargin(showHideBox, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
      //VBox.setMargin(vButton, new Insets(MARGIN_VAL,MARGIN_VAL,MARGIN_VAL,MARGIN_VAL));
      vButton.setDisable(true);
      v.getChildren().add(markerBox);
      v.getChildren().add(routeInfoBox);
      //v.getChildren().add(resetButton);
      
      routeTab.setContent(h);
  }

public static void main(String[] args) {
    launch(args);
}
}