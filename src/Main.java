import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application implements Runnable {
	
	public static int CIRCLES = 15;
	public static int RADIUS = 5;

    @Override
    public void start(final Stage stage) throws Exception {
   		Platform.setImplicitExit(false);
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {System.exit(0);}
        });
		
		AnchorPane pane = new AnchorPane();
		
		pane.setPrefHeight(300);
		pane.setPrefWidth(300);
		
		ArrayList<Circle> al = new ArrayList<Circle>();

		for (int i=0;i<CIRCLES;i++) {
			Circle c = new Circle(RADIUS);//, Color.color(Math.random(), Math.random(), Math.random()));
			c.setCenterX(150 + 50 * Math.cos((Math.PI * 2 * i)/CIRCLES));
			c.setCenterY(150 + 50 * Math.sin((Math.PI * 2 * i)/CIRCLES));
			
			al.add(c);
		}
		
		Group g = new Group();
		g.getChildren().addAll(al);
		
		DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
        
		g.setEffect(ds);
		
		al.get(CIRCLES /3).setVisible(false);
		al.get(CIRCLES*2 /3).setVisible(false);
		al.get(CIRCLES -1).setVisible(false);
		//al.get(CIRCLES -1).setVisible(false);
		
		new AnimationTimer() {
			
			int shift = -1;

			@Override
			public void handle(long now) {
				shift++;
				
				for (int i=0;i<CIRCLES;i++) {
					al.get(i).setCenterX(150 + 50 * Math.cos((Math.PI * 2 * (i + shift))/CIRCLES));
					al.get(i).setCenterY(150 + 50 * Math.sin((Math.PI * 2 * (i + shift))/CIRCLES));
					//al.get(i).setFill(Color.color(Math.random(), Math.random(), Math.random()));
				}
				
				try {Thread.sleep(100);} catch (InterruptedException e) {}
			}
			
		}.start();
		
        stage.setScene(new Scene(pane));
        System.out.println(pane.getChildren().addAll(g));
        stage.setTitle("Loading Circle");   
		stage.setResizable(false);
        
        stage.show();
    }
    
	@Override
	public void run() {
		launch( (String[]) null);
	}
	
	public static void main(String[] args) {
		launch( (String[]) null);
	}
	
}