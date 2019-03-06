package sma.main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainController implements Initializable {
   public ImageView close, minimize;
   public TextField text;

    private List<Label> messages = new ArrayList<>();
    @FXML
    public ScrollPane container;
    @FXML
    public AnchorPane chatBox;
    @FXML
    public JFXButton clearChat, yes, no;

    private double layouty2 = 10;

    public void close(){
        System.exit(0);
    }

    public void clear(){
        messages.clear();
        chatBox.getChildren().clear();
        chatBox.setPrefHeight(738);
        yes.setDisable(true);
        no.setDisable(true);
        layouty2 = 10;
    }

    public void yesAction(){
        Label label = new Label();
        label.getStyleClass().add("response");
        label.setLayoutX(248.0);

        label.setPrefWidth(170);
        label.setPrefHeight(30);
        label.setText("Oui");
        messages.add(label);
        int size = chatBox.getChildren().size();
        //Label previous = (Label) chatBox.getChildren().get(size-1);
        if(layouty2 >= 700) {
            chatBox.setPrefHeight(chatBox.getPrefHeight()+150);
            layouty2 += ((Label)(chatBox.getChildren().get(size-1))).getHeight() +5;
        } else{
            layouty2 += ((Label)(chatBox.getChildren().get(size-1))).getHeight() +5;
        }
        chatBox.getChildren().add(messages.get(messages.size()-1));
        label.setLayoutY(layouty2);
    }

    public void noAction(){
        Label label = new Label();
        label.getStyleClass().add("response");
        label.setLayoutX(248.0);

        label.setPrefWidth(170);
        label.setPrefHeight(30);
        label.setText("Non");
        messages.add(label);

        int size = chatBox.getChildren().size();
        if(layouty2 >= 700) {
            chatBox.setPrefHeight(chatBox.getPrefHeight()+150);
            layouty2 += ((Label)(chatBox.getChildren().get(size-1))).getHeight() +5;
        } else{
            layouty2 += ((Label)(chatBox.getChildren().get(size-1))).getHeight() +5;
        }
        label.setLayoutY(layouty2);
        chatBox.getChildren().add(messages.get(messages.size()-1));
    }

    public void question(String question){
        System.out.println(layouty2);
        Label label = new Label();
        label.getStyleClass().add("question");
        label.setText(question);
        label.setLayoutX(47.0);
        //
        int size = chatBox.getChildren().size();
        if(size == 0){
            layouty2 = 10;
        }else {
            if (layouty2 >= 700) {
                chatBox.setPrefHeight(chatBox.getPrefHeight() + 150);
                //TODO add code to scroll down
                layouty2 += ((Label) (chatBox.getChildren().get(size - 1))).getHeight() + 5;
            } else {
                layouty2 += ((Label) (chatBox.getChildren().get(size - 1))).getHeight() + 5;
            }
        }
        label.setMinHeight(60.0);
        label.setPrefWidth(200);
        label.setStyle("-fx-border-color: orange;");
        label.setLayoutY(layouty2);
        chatBox.getChildren().add(label);
        text.clear();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        container.setContent(chatBox);
        close.setOnMouseClicked(e->this.close());
        text.setOnKeyReleased(e-> {
            if(e.getCode() == KeyCode.ENTER) {
                yes.setDisable(false);
                no.setDisable(false);
                question(text.getText());
            }
        });

        if(chatBox.getChildren().size() == 0){
            yes.setDisable(true);
            no.setDisable(true);
        }

       /* text.setOnKeyReleased(e->{
            if(e.getCode() == KeyCode.ENTER){
                TextArea textArea = new TextArea();

                if(index%2==0) {
                    textArea.setLayoutX(49.0);
                    textArea.setLayoutY(layouty);
                    if(layouty >= 700) {
                        chatBox.setPrefHeight(chatBox.getPrefHeight()+150);
                        layouty += 140;
                    } else{
                        layouty += 140;
                    }

                    textArea.setPrefHeight(44.0);
                    textArea.setPrefWidth(218.0);
                    textArea.setStyle("-fx-background-color: orange;");
                }else {
                    textArea.setLayoutX(222.0);
                    textArea.setLayoutY(layouty2);
                    if(layouty2 >= 700) {
                        chatBox.setPrefHeight(chatBox.getPrefHeight()+150);
                        layouty2 += 140;
                    } else{
                        layouty2 += 140;
                    }
                    textArea.setPrefHeight(31.0);
                    textArea.setPrefWidth(218.0);
                    textArea.setStyle("-fx-background-color: #0678dd;");
                }
                textArea.setText(text.getText());
                //messages.add(new TextArea(text.getText()));
                messages.add(textArea);
                chatBox.getChildren().add(messages.get(index));
                System.out.println("The message is "+text.getText());
                text.clear();
                index++;
            }
        });*/
        minimize.setOnMouseClicked(e->{
            Stage stage = (Stage) minimize.getScene().getWindow();
            stage.setIconified(true);
        });
    }
}
