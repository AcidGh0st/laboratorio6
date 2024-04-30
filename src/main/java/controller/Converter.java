package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Converter
{
    @javafx.fxml.FXML
    private Text result2Text1;
    @javafx.fxml.FXML
    private RadioButton prefixRadioButton;
    @javafx.fxml.FXML
    private RadioButton posfixRadioButton;
    @javafx.fxml.FXML
    private Text result1Text1;
    @javafx.fxml.FXML
    private TextField exp2TextField;
    @javafx.fxml.FXML
    private Text result1Text4;
    @javafx.fxml.FXML
    private TextField exp3TextField;
    @javafx.fxml.FXML
    private Text result1Text5;
    @javafx.fxml.FXML
    private TextField exp1TextField;
    @javafx.fxml.FXML
    private TextField exp4TextField;
    @javafx.fxml.FXML
    private TextField exp5TextField;
    @javafx.fxml.FXML
    private Text result2Text4;
    @javafx.fxml.FXML
    private Text result2Text5;
    @javafx.fxml.FXML
    private RadioButton infixRadioButton;
    @javafx.fxml.FXML
    private Text result2Text2;
    @javafx.fxml.FXML
    private Text result2Text3;
    @javafx.fxml.FXML
    private Text result1Text2;
    @javafx.fxml.FXML
    private Text result1Text3;
    Alert alert;
    @javafx.fxml.FXML
    private Label msg2Text;
    @javafx.fxml.FXML
    private Label msg1Text;
    @javafx.fxml.FXML
    private Label msg3Text;

    @javafx.fxml.FXML
    public void initialize() {
        this.alert = util.UtilityFX.alert("Converter", "Expression");
        cleanOnAction(new ActionEvent());//Cree uno nuevo, no es necesario

    }

    @javafx.fxml.FXML
    public void convertOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        this.exp1TextField.setText("");
        this.exp2TextField.setText("");
        this.exp3TextField.setText("");
        this.exp4TextField.setText("");
        this.exp5TextField.setText("");

        this.result1Text1.setText("");
        this.result1Text2.setText("");
        this.result1Text3.setText("");
        this.result1Text4.setText("");
        this.result1Text5.setText("");

        this.result2Text1.setText("");
        this.result2Text2.setText("");
        this.result2Text3.setText("");
        this.result2Text4.setText("");
        this.result2Text5.setText("");
    }

    @javafx.fxml.FXML
    public void infixOnAction(ActionEvent actionEvent) {
        cleanOnAction(new ActionEvent());

        //Cambiarle el label a los labels
        this.msg1Text.setText("Infix");
        this.msg2Text.setText("Prefix");
        this.msg3Text.setText("Postfix");

        this.prefixRadioButton.setSelected(false);
        this.infixRadioButton.setSelected(true);
        this.posfixRadioButton.setSelected(false);
    }

    @javafx.fxml.FXML
    public void postfixOnAction(ActionEvent actionEvent) {
        cleanOnAction(new ActionEvent());

        //Cambiarle el label a los labels
        this.msg1Text.setText("Postfix");
        this.msg2Text.setText("Prefix");
        this.msg3Text.setText("Infix");

        this.prefixRadioButton.setSelected(false);
        this.infixRadioButton.setSelected(false);
        this.posfixRadioButton.setSelected(true);

    }

    @javafx.fxml.FXML
    public void prefixOnAction(ActionEvent actionEvent) {
        cleanOnAction(new ActionEvent());

        //Cambiarle el label a los labels
        this.msg1Text.setText("Prefix");
        this.msg2Text.setText("Infix");
        this.msg3Text.setText("Postfix");

        this.prefixRadioButton.setSelected(true);
        this.infixRadioButton.setSelected(false);
        this.posfixRadioButton.setSelected(false);
    }
}