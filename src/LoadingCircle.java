import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoadingCircle extends Application {
	
	public static int CIRCLES = 15;
	public static int RADIUS = 5;
	public static int shift = -1;

    @Override
    public void start(final Stage stage) throws Exception {
		AnchorPane pane = new AnchorPane();
		
		pane.setPrefHeight(300);
		pane.setPrefWidth(300);
		
		//---------------------------------------------------------------------//
		
		ArrayList<Circle> circles = new ArrayList<Circle>();

		for (int i=0;i<CIRCLES;i++) {
			Circle c = new Circle(
				150 + 50 * Math.cos((Math.PI * 2 * i)/CIRCLES),
				150 + 50 * Math.sin((Math.PI * 2 * i)/CIRCLES),
				RADIUS);
			circles.add(c);
		}
		
		circles.get((CIRCLES/3) -1).setVisible(false);
		circles.get((CIRCLES*2/3) -1).setVisible(false);
		circles.get(CIRCLES -1).setVisible(false);
		
		//---------------------------------------------------------------------//
		
		Group g = new Group();
		g.getChildren().addAll(circles);
		
		DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
        
		g.setEffect(ds);
		
		//---------------------------------------------------------------------//
		
		ProgressBar pb = new ProgressBar(0);
		pb.setPrefSize(100, 10);
		pb.setLayoutX(150 - pb.getPrefWidth()/2);
		pb.setLayoutY(250);
		
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				shift++;
				
				if (pb.getProgress() < 1)
					pb.setProgress(pb.getProgress() + .05);
				else {
					stop();
					pb.setProgress(0);
					start();
				}
				
				for (int i=0;i<CIRCLES;i++) {
					circles.get(i).setCenterX(150 + 50 * Math.cos((Math.PI * 2 * (i + shift))/CIRCLES));
					circles.get(i).setCenterY(150 + 50 * Math.sin((Math.PI * 2 * (i + shift))/CIRCLES));
				}
				
				try {Thread.sleep(200);} catch (InterruptedException e) {}
			}
			
		}.start();
		
		//---------------------------------------------------------------------//
		
        stage.setScene(new Scene(pane));
        pane.getChildren().addAll(g, pb);
        stage.setTitle("Loading Circle");   
		stage.setResizable(false);
        
        stage.show();
    }
    
	public static void main(String[] args) {
		launch( (String[]) null);
	}
	
}