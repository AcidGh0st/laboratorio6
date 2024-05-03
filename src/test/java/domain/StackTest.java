package domain;

import domain.stack.ArrayStack;
import domain.stack.LinkedStack;
import domain.stack.Stack;
import domain.stack.StackException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
class StackTest {

    @Test
    void testStack() throws StackException {

        //Crea las pilas
        LinkedStack st = new LinkedStack();
        ArrayStack aS = new ArrayStack(5);

        //Hace push (Apilar) con objetos tipo Person en ArrayStack
        aS.push(new Person(1, "Ana", 23));
        aS.push(new Person(2, "Pablo", 24));
        aS.push(new Person(3, "Ana", 25));
        aS.push(new Person(4, "Pablo", 19));
        aS.push(new Person(5, "Victoria", 18));

        //Hace push (Apilar) con objetos tipo Person en LinkedStack
        st.push(new Person(6, "Nicole", 26));
        st.push(new Person(7, "Mateo", 18));
        st.push(new Person(8, "Nicole", 23));
        st.push(new Person(9, "Victoria", 20));
        st.push(new Person(10, "Ana", 28));

        //Muestra el contenido inicial de cada una de las pilas
        System.out.println("Contenido inicial de ArrayStack:\n");
        printStackContent2(aS);

        System.out.println("\nContenido inicial de LinkedStack:\n");
        printStackContent(st);

        // ivide los objetos Person por casos
        System.out.println("\nDivision de los objetos Person por casos:\n");
        popPersons(aS, st, "", 20); // Caso 1: Edad igual o menor a 20
        popPersons(aS, st, "Nicole", 23); // Caso 2: Nombre Nicole y edad menor o igual a 23
        popPersons(aS, st, "Ana", 0); // Caso 3: Nombre Ana

        //Muestra el contenido actual de las pilas después de la división
        System.out.println("\nContenido actual de la lista:\n");
        System.out.println(st);
        System.out.println(aS);
    }


    private void printStackContent(LinkedStack stack) throws StackException {
        LinkedStack tempStack = new LinkedStack(); //se crea una pila enlazada temporal



        //Muestra contenido de la pila y guarda elementos en la pila temporal
        while (!stack.isEmpty()) { //Verifica si la pila enlazada no esta vacia

            //Desapila elementos de la pila Y los muestra en la consola, luego los apila temporalmente en tempStack
            Person person = (Person) stack.pop();

            System.out.println(person);
            tempStack.push(person);
        }

        //Restaura la pila original volviendo a apilar los elementos desde la pila tempStack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }


    private void printStackContent2(ArrayStack stack) throws StackException {
        ArrayStack tempStack = new ArrayStack(stack.size()); // Crear un ArrayStack temporal

        //Muestra contenido de la pila y guardar elementos en el ArrayStack temporal
        while (!stack.isEmpty()) {
            Person person = (Person) stack.pop();
            System.out.println(person);
            tempStack.push(person);
        }

        //Restaura la pila original desde el ArrayStack temporal
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }





    private void popPersons(ArrayStack arrayStack, LinkedStack linkedStack, String name, int age) throws StackException {
        LinkedStack tempStack = new LinkedStack(); // Pila temporal para restauración

        //Listas para almacenar objetos según cada caso
        List<Person> nicole = new ArrayList<>();
        List<Person> edadIgualMenor_20 = new ArrayList<>();
        List<Person> ana = new ArrayList<>();

        //Procesa ArrayStack
        while (!arrayStack.isEmpty()) {
            Person person = (Person) arrayStack.pop();

            if (Objects.equals(person.getName(), "Nicole") && person.getAge() <= 23) {
                nicole.add(person); //Caso 2: name=Nicole, age<=23
            } else if (person.getAge() <= age) {
                edadIgualMenor_20.add(person); //Caso 1: age<=20
            } else if (Objects.equals(person.getName(), "Ana")) {
                ana.add(person); //Caso 3: name=Ana
            } else {
                tempStack.push(person); //Conserva otros elementos en la pila temporal
            }
        }

        //Restaura los elementos en la pila original de ArrayStack desde la pila temporal
        while (!tempStack.isEmpty()) {
            arrayStack.push(tempStack.pop());
        }

        //Procesa LinkedStack
        while (!linkedStack.isEmpty()) {
            Person person = (Person) linkedStack.pop();

            if (Objects.equals(person.getName(), "Nicole") && person.getAge() <= 23) {
                nicole.add(person); //Caso 2: name=Nicole, age<=23
            } else if (person.getAge() <= age) {
                edadIgualMenor_20.add(person); //Caso 1: age<=20
            } else if (Objects.equals(person.getName(), "Ana")) {
                ana.add(person); //Caso 3: name=Ana
            } else {
                tempStack.push(person); //Conserva otros elementos en la pila temporal
            }
        }

        //Restaura los elementos en LinkedStack desde la pila temporal
        while (!tempStack.isEmpty()) {
            linkedStack.push(tempStack.pop());
        }

        //Impresión de los resultados solo si no se ha impreso antes
        boolean alreadyPrinted = false;

        //Construir la cadena de texto con los resultados
        StringBuilder result = new StringBuilder();

        //Caso 1
        if (!edadIgualMenor_20.isEmpty()) {
            result.append("Case 1 - Edad igual o menor a ").append(age).append(":\n");
            for (Person person : edadIgualMenor_20) {
                result.append("Case 1: ").append(person).append("\n");
            }
            alreadyPrinted = true;
        }

        //Caso 2
        if (!nicole.isEmpty()) {
            result.append("\nCase 2 - Nombre Nicole y edad menor o igual a 23:\n");
            for (Person person : nicole) {
                result.append("Case 2: ").append(person).append("\n");
            }
            alreadyPrinted = true;
        }

        //Caso 3
        if (!ana.isEmpty()) {
            result.append("\nCase 3 - Nombre Ana:\n");
            for (Person person : ana) {
                result.append("Case 3: ").append(person).append("\n");
            }
            alreadyPrinted = true;
        }

        //Imprimir los resultados si no se han impreso antes
        if (alreadyPrinted) {
            System.out.println(result.toString());
        }
    }


}
