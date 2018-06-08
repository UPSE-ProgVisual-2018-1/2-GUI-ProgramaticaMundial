package application;


import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	int equiposRestantes = 3;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Creo un nodo tipo label (etiqueta).
			Label titulo = new Label("Mundial");
			//Agrego el nodo como hijo del layout (en este caso un VBox).
			root.getChildren().add(titulo);
			
			TilePane tileEquipos = new TilePane();
			
			Button btnArgentina = new Button("Argentina");
			tileEquipos.getChildren().add(btnArgentina);
			Button btnEspana = new Button("Espana");
			tileEquipos.getChildren().add(btnEspana);
			Button btnAlemania = new Button("Alemania");
			tileEquipos.getChildren().add(btnAlemania);
			Button btnRusia = new Button("Rusia");
			tileEquipos.getChildren().add(btnRusia);
			Button btnPeru = new Button("Peru");
			tileEquipos.getChildren().add(btnPeru);
			Button btnBrasil = new Button("Brasil");
			tileEquipos.getChildren().add(btnBrasil);
			Button btnColombia = new Button("Colombia");
			tileEquipos.getChildren().add(btnColombia);
			Button btnMexico = new Button("Mexico");
			tileEquipos.getChildren().add(btnMexico);
	
			//Agregamos el tilePane al root
			root.getChildren().add(tileEquipos);
			
			//Creamos listView para mostrar equipos
			ListView<String> equiposApuesta = new ListView<String>();
			root.getChildren().add(equiposApuesta);
			
			List<String> listaPaisesSeleccionados = new ArrayList<String>();
			
			//Creo la lista Observable
			ObservableList<String> listaObservable = FXCollections.observableArrayList();
			
			//Por cada nodo (boton) que este dentro del tilePane
			for(Node n: tileEquipos.getChildren())
			{
				Button b = (Button) n;
				//Para cada boton agregamos un evento
				b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//Si aun quedan equipos por seleccionar
						if(equiposRestantes>0)
						{
							//Disminuyo el numero de equipos
							equiposRestantes--;
							System.out.println(b.getText());
							//Agrego el equipo a la lista
							listaPaisesSeleccionados.add(b.getText());
							System.out.println("Equipos por seleccionar :" + equiposRestantes);
							
							//Deshabilitamos el boton
							b.setDisable(true);
							listaObservable.setAll(listaPaisesSeleccionados);
							equiposApuesta.setItems(listaObservable);
						}else{
							System.out.println("No puede Seleccionar mas equipos");
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Alerta");
							alert.setHeaderText("Apuesta Finalizada");
							alert.setContentText("Su apuesta ha terminado. Gracias por participar en almacenes 567.");

							alert.showAndWait();
						}
						
					}
				});
			}
			

			//Amarro la lista observable al listView
			equiposApuesta.setItems(listaObservable);
			
			
			
			Button botonAceptar = new Button("Aceptar");
			root.getChildren().add(botonAceptar);

			botonAceptar.setOnAction(e -> System.out.println("Gracias por su apuesta"));
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
