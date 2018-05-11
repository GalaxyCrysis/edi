import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.event.ChangeListener;

public class edi extends Application{
    //fx start method for starting the app
    //
    GridPane chat;
    ColumnConstraints c1;
    Label chatMessage;
    ScrollPane scroll;
    Scene scene;
    Integer counter;
    TextField messagebox;
    VBox layout;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Edi Assistant");
        counter = 0;
        chat = new GridPane();
        chat.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        c1 = new ColumnConstraints();
        c1.setPercentWidth(100);
        chat.getColumnConstraints().add(c1);

        chatMessage = new Label("Hi Dominik, How can I help you?");
        chatMessage.getStyleClass().add("chat-bubble");
        GridPane.setHalignment(chatMessage,HPos.LEFT);
        chat.addRow(counter, chatMessage);

        //SCROLL
        scroll = new ScrollPane(chat);
        scroll.setFitToWidth(true);
        scroll.setHmin(450);
        chat.heightProperty().addListener((observable, oldValue, newValue) ->
                scroll.setVvalue(newValue.doubleValue()));

        //MESSAGE BOX
        messagebox = new TextField();
        messagebox.setOnAction(e -> {
            // Listener Method after sending a message to the Assistant
            counter++;
            Label message = new Label(messagebox.getText());
            message.getStyleClass().add("chat-bubble");
            GridPane.setHalignment(message, HPos.RIGHT);
            chat.addRow(counter,message);
            messagebox.setText("");

        });
        //LAYOUT
        layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(15,12,15,12));
        layout.getChildren().addAll(scroll,messagebox);

        //SCENE
        scene = new Scene(layout, 500, 500);
        scene.getStylesheets().add("main.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //method: Assistant answers and the method updates the scrollpane
    private void answer(String message){
        counter++;
        Label answer = new Label(message);
        answer.getStyleClass().add("chat-bubble");
        GridPane.setHalignment(answer, HPos.LEFT);
        chat.addRow(counter,answer);

    }







    // main method: start program
    public static void main(String [] args){
        launch(args);
    }


}
