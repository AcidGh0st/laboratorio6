package domain;

import domain.queue.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class QueueTest {

    @Test
    void Queue() {
        Random randomPriority = new Random(); //Generar numeros aleatorios
        int totalNumb_Messages = 15; //Variable que contiene el numero total de mensajes que se van a procesar
        int maxMessagesQueue = 5; //Variable que define la cantidad maxima de objetos que cada cola debe contener

        // Crear colas
        Queue aQ = new ArrayQueue(maxMessagesQueue); //Se crea una array de colas, en el cual su tamaño sera la variable implementada anteriormente, la cual contiene la cantidad de objetos para cada cola correspondiente
        Queue lQ = new LinkedQueue(); //Crear una cola implementada con una lista enlazada
        Queue hQ = new HeaderLinkedQueue(); // implementar una cola con una lista enlazada con nodo cabecera


        // Ciclo en el que se recorre la cola y en cada posicion va encolando los objetos dependiendo de la cola que sea
        for (int i = 1; i <= totalNumb_Messages; i++) { // encolar los mensajes en las diferentes colas.

            int priorityNumber = randomPriority.nextInt(3) + 1; // Generar un numero aleatorio entre 1 a 3, los cuales representan la prioridad de los mensajes: 1=high, 2=medium, 3=low

            String Message = "Message No. " + i; //Contenido del mensaje

            Message messageObject = new Message(priorityNumber, Message); //Se instancia la clase Message y se le pasa como parametros el numero de prioridad  y el contenido del mensaje

            //Después de encolar los mensajes, se muestra el contenido de cada cola en la consola.

            //encolar el mensaje en la cola correspondiente en base su prioridad,
            try {

                //La primera cantidad de veces de maxMessagesQueue se van a agregar a la cola Array, las siguientes hasta que s ecumpla el numero declarado en la variable van agregados en el Linked y los ultimos van almacenados en la HeaderLINKED
                if (i <= maxMessagesQueue) { //si la variable que se incrementa es menor o igual a la variable que determina la cantidad maxima de cada cola

                    aQ.enQueue(messageObject, priorityNumber);

                } else if (i <= maxMessagesQueue * 2) {

                    lQ.enQueue(messageObject, priorityNumber);

                } else {

                    hQ.enQueue(messageObject, priorityNumber);

                }

            } catch (QueueException e) {
                System.out.println("Se detectó un error al encolar el mensaje: " + e.getMessage());
            }
        }

        //Se muestran los resultados de encolar los objetos por consola
        System.out.println("Contenido del ArrayQueue: " + aQ);
        System.out.println("Contenido del LinkedQueue: " + lQ);
        System.out.println("Contenido del HeaderLinkedQueu: " + hQ);


        // Se crean listas con el fin de almacenar mensajes por orden de prioridad
        List<Message> priority1_Message = new ArrayList<>();
        List<Message> priority2_Message = new ArrayList<>();
        List<Message> priority3_Message = new ArrayList<>();


        try {

            //desencola los mensajes de cada cola y los agrupa dependiendo de su prioridad en cada lista respectiva.

            MessagesByPriority(aQ, priority1_Message, priority2_Message, priority3_Message);
            MessagesByPriority(lQ, priority1_Message, priority2_Message, priority3_Message);
            MessagesByPriority(hQ, priority1_Message, priority2_Message, priority3_Message);

        } catch (QueueException e) {
            System.out.println("Se detectó un error al procesar la cola: " + e.getMessage());
        }

        // Mostrar mensajes agrupados por prioridad
        System.out.println("Caso 1- Mensajes de prioridad 1:");
        ShowMessages(priority1_Message);

        System.out.println("Caso 2- Mensajes de prioridad 2:");
        ShowMessages(priority2_Message);

        System.out.println("Caso 3- Mensajes de prioridad 3:");
        ShowMessages(priority3_Message);
    }

    private static void MessagesByPriority(Queue queue, List<Message> priority1, List<Message> priority2, List<Message> priority3) throws QueueException {

        //Desencola cada objeto de la cola

        while (!queue.isEmpty()) { //Verifica que la cola no este vacia

            Message message = (Message) queue.deQueue(); //Crea una instancia tipo Message

            switch (message.getPriority()) { //switch que analiza la prioridad del mensaje, y con base a eso se agrega a una de las tres lista
                case 1:
                    priority1.add(message);
                    break;
                case 2:
                    priority2.add(message);
                    break;
                case 3:
                    priority3.add(message);
                    break;
                default:
                    break;
            }
        }
    }

    private static void ShowMessages(List<Message> messages) {

        if (messages.isEmpty()) { //Verifica que la lista no este vacia
            System.out.println("No hay mensajes.");

        } else {

            for (Message message : messages) { //Imprime los mensajes por medio de un for-each
                System.out.println(message);

                //muestra los mensajes agrupados por prioridad
            }
        }
        
    }
}