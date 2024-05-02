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

        //Se crean dos pilas, una con listas enlazadas y otra con arreglos
        LinkedStack st = new LinkedStack();
        ArrayStack aS = new ArrayStack(5);

        // Hacer push (Apilar) con objetos tipo Person y almacenarlos en un ArrayStack
        aS.push(new Person(1, "Ana", 23));
        aS.push(new Person(2, "Pablo", 24));
        aS.push(new Person(3, "Ana", 25));
        aS.push(new Person(4, "Pablo", 19));
        aS.push(new Person(5, "Victoria", 18));

        // Hacer push (Apilar) con objetos tipo Person y almacenarlos en una LinkedStack
        st.push(new Person(6, "Nicole", 26));
        st.push(new Person(7, "Mateo", 18));
        st.push(new Person(8, "Nicole", 23));
        st.push(new Person(9, "Victoria", 20));
        st.push(new Person(10, "Ana", 28));


        // Mostrar el contenido inicial de cada una de las pilas
        System.out.println(" ");
        System.out.println("Contenido inicial de ArrayStack:");
        printStackContent2(aS);

        System.out.println(" ");
        System.out.println("Contenido inicial de LinkedStack:");
        printStackContent(st);


        //Muestra la pila en donde se almacenan los objetos tipo Person que tienen una edad igual o menor a 20
        System.out.println(" ");

        System.out.println("Division de los objetos Person por casos: ");
        popPersons(aS, st, "", 20); // Caso 1: age <= 20


        System.out.println(" ");
        popPersons(aS, st, "Nicole", 23); // Caso 2: name = Nicole, age <= 23


        System.out.println(" ");
        popPersons(aS, st, "Ana", 0); // Caso 3: name = Ana


        System.out.println("Contenido actual de la lista: ");
        //Muestra las pilas originales despues de haber desapilado los objetos de cada caso
        System.out.println(st);
        System.out.println(aS);
    }

    private void printStackContent(LinkedStack stack) throws StackException {
        LinkedStack tempStack = new LinkedStack(); // se crea una pila enlazada temporal

        // Mostrar el contenido de una pila y luego restaurar la pila a su estado original después de mostrar el contenido.

        // Mostrar contenido de la pila y guardar elementos en la pila temporal
        while (!stack.isEmpty()) { //Verifica si la pila enlazada no esta vacia

            //Desapila elementos de la pila Y los muestra en la consola, luego los apila temporalmente en tempStack
            Person person = (Person) stack.pop();

            System.out.println(person);
            tempStack.push(person);
        }

        // Restaura la pila original volviendo a apilar los elementos desde la pila tempStack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }


    //Proceso similar al del lINKED, solo que este almacena y muestra pilas tipo arreglos
    private void printStackContent2(ArrayStack stack) throws StackException {
        LinkedStack tempStack = new LinkedStack();

        // Mostrar contenido de la pila y guardar elementos en la pila temporal
        while (!stack.isEmpty()) {
            Person person = (Person) stack.pop();
            System.out.println(person);
            tempStack.push(person);
        }

        // Restaurar la pila original
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }



    private void popPersons(ArrayStack arrayStack, LinkedStack linkedStack, String name, int age) throws StackException {
        LinkedStack tempStack = new LinkedStack(); // Pila temporal para restauración

        // Listas para almacenar objetos según cada caso
        List<Person> nicole = new ArrayList<>();
        List<Person> edadIgualMenor_20 = new ArrayList<>();
        List<Person> ana = new ArrayList<>();

        // Procesar ArrayStack
        while (!arrayStack.isEmpty()) {
            Person person = (Person) arrayStack.pop();

            if (Objects.equals(person.getName(), "Nicole") && person.getAge() <= 23) {
                nicole.add(person); // Caso 2: name=Nicole, age<=23
            } else if (person.getAge() <= age) {
                edadIgualMenor_20.add(person); // Caso 1: age<=20
            } else if (Objects.equals(person.getName(), "Ana")) {
                ana.add(person); // Caso 3: name=Ana
            } else {
                tempStack.push(person); // Conservar otros elementos en la pila temporal
            }
        }

        // Restaurar los elementos en la pila original de ArrayStack desde la pila temporal
        while (!tempStack.isEmpty()) {
            arrayStack.push(tempStack.pop());
        }

        // Procesar LinkedStack
        while (!linkedStack.isEmpty()) {
            Person person = (Person) linkedStack.pop();

            if (Objects.equals(person.getName(), "Nicole") && person.getAge() <= 23) {
                nicole.add(person); // Caso 2: name=Nicole, age<=23
            } else if (person.getAge() <= age) {
                edadIgualMenor_20.add(person); // Caso 1: age<=20
            } else if (Objects.equals(person.getName(), "Ana")) {
                ana.add(person); // Caso 3: name=Ana
            } else {
                tempStack.push(person); // Conservar otros elementos en la pila temporal
            }
        }

        // Restaurar los elementos en LinkedStack desde la pila temporal
        while (!tempStack.isEmpty()) {
            linkedStack.push(tempStack.pop());
        }

        // Mostrar resultados al final del procesamiento
        System.out.println("Case 1 - Edad igual o menor a " + age + ":");
        for (Person person : edadIgualMenor_20) {
            System.out.println("Case 1: " + person);
        }

        System.out.println("Case 2 - Nombre Nicole y edad menor o igual a 23:");
        for (Person person : nicole) {
            System.out.println("Case 2: " + person);
        }

        System.out.println("Case 3 - Nombre Ana:");
        for (Person person : ana) {
            System.out.println("Case 3: " + person);
        }
    }



}