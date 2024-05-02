package controller;

import domain.Message;
import domain.queue.PriorityLinkedQueue;
import domain.queue.QueueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MessengerServiceController {

    @FXML
    private TextArea txtArea;
    @FXML
    private TextArea txtField_EnQueue;
    @FXML
    private TextArea txtField_DeQueue;
    Alert alert;
    PriorityLinkedQueue priorityLinkedQueue = new PriorityLinkedQueue();
    private Message message;
    private Random random;
    private Timer timer;



    @FXML
    public void initialize() {;
        alert = new Alert(Alert.AlertType.NONE);
    }


    @FXML
    public void startOnAction(ActionEvent actionEvent) {

        int value = Integer.parseInt(txtArea.getText());// Obtener valor del textArea

        if (value > 99 && value < 501) { // Rango permitido 100 a 500
            random = new Random();
            timer = new Timer(); // Temporizador

            TimerTask tarea1 = new TimerTask() { // Tarea que se ejecuta

                int i = 1;
                @Override
                public void run() {
                    if(i<=value){
                        int priority = random.nextInt(3) + 1;
                        String content = "Message No." + (i++);
                        message = new Message(priority, content);

                        try {
                            priorityLinkedQueue.enQueue(message,priority); // Encolar
                            txtField_EnQueue.appendText(message.toString());
                        } catch (QueueException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            timer.schedule(tarea1, 0, 500);; //tarea del temporizador


            TimerTask tarea2 = new TimerTask() {
                @Override
                public void run() {

                    if (!priorityLinkedQueue.isEmpty()) {

                        try {
                            Message message = (Message) priorityLinkedQueue.deQueue();
                            txtField_DeQueue.appendText(message.toString());//apendText agrega al final, concatenando con el anterior
                        } catch (QueueException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            timer.schedule(tarea2, 1000, 1000);

        }else{
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Please, try again. Enter a number between 100 and 500");
            alert.showAndWait();
            txtArea.setText("");
            txtField_EnQueue.setText("");
            txtField_DeQueue.setText("");
        }

    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
        this.txtArea.setText("");
        this.txtField_DeQueue.setText("");
        this.txtField_EnQueue.setText("");
        timer.cancel();

    }
}