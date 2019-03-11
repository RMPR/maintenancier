package sma.main.controllers;

import com.github.cschen1205.ess.engine.EqualsClause;
import com.jfoenix.controls.JFXButton;
import static java.lang.Math.random;
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
import java.util.Vector;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import sma.engine.Engine;

public class MainController implements Initializable {

	public ImageView close, minimize;
	public TextField text;
	public Engine engine;

	private List<Label> messages = new ArrayList<>();
	@FXML
	public ScrollPane container;
	@FXML
	public AnchorPane chatBox;
	@FXML
	public JFXButton clearChat, yes, no;

	private double layouty2 = 10;

	public void close() {
		System.exit(0);
	}

	public void clear() {
		messages.clear();
		chatBox.getChildren().clear();
		chatBox.setPrefHeight(738);
		yes.setDisable(true);
		no.setDisable(true);
		layouty2 = 10;
	}

	public void yesAction() {
		Label label = new Label();
		label.getStyleClass().add("response");
		label.setLayoutX(248.0);

		label.setPrefWidth(170);
		label.setPrefHeight(30);
		label.setText("Oui");
		engine.getResponse("oui");
		
		messages.add(label);
		int size = chatBox.getChildren().size();
		if (layouty2 >= 700) {
			chatBox.setPrefHeight(chatBox.getPrefHeight() + 150);
			layouty2 += ((Label) (chatBox.getChildren().get(size - 1))).getHeight() + 5;
		} else {
			layouty2 += ((Label) (chatBox.getChildren().get(size - 1))).getHeight() + 5;
		}
		
		chatBox.getChildren().add(messages.get(messages.size() - 1));
		label.setLayoutY(layouty2);
		question(engine.sendResponse());
		
	}

	public void noAction() {
		Label label = new Label();
		label.getStyleClass().add("response");
		label.setLayoutX(248.0);

		label.setPrefWidth(170);
		label.setPrefHeight(30);
		label.setText("Non");
		messages.add(label);

		int size = chatBox.getChildren().size();
		if (layouty2 >= 700) {
			chatBox.setPrefHeight(chatBox.getPrefHeight() + 150);
			layouty2 += ((Label) (chatBox.getChildren().get(size - 1))).getHeight() + 5;
		} else {
			layouty2 += ((Label) (chatBox.getChildren().get(size - 1))).getHeight() + 5;
		}
		label.setLayoutY(layouty2);
		chatBox.getChildren().add(messages.get(messages.size() - 1));
	}

	public void question(String question) {
		Label label = new Label();
		label.getStyleClass().add("question");
		label.setText(question);
		label.setLayoutX(47.0);
		//
		int size = chatBox.getChildren().size();
		if (size == 0) {
			layouty2 = 10;
		} else {
			if (layouty2 >= 700) {
				chatBox.setPrefHeight(chatBox.getPrefHeight() + 150);
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
		close.setOnMouseClicked(e -> this.close());
		text.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
		
				question(text.getText());
			}
		});

		minimize.setOnMouseClicked(e -> {
			Stage stage = (Stage) minimize.getScene().getWindow();
			stage.setIconified(true);
		});
		engine = new Engine();
		question(engine.getQ_allume());
		/* Call of the Expert Engine */
	}
}
