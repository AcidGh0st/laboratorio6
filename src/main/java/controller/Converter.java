package controller;


import domain.stack.LinkedStack;
import domain.stack.StackException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Converter extends LinkedStack
{
    @javafx.fxml.FXML
    private Text result2Text1;
    @javafx.fxml.FXML
    private RadioButton prefixRadioButton;
    @javafx.fxml.FXML
    private RadioButton postfixRadioButton;
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

    private LinkedStack stack;

    public Converter() {
        this.stack = new LinkedStack();
    }

    @javafx.fxml.FXML
    public void initialize() {
        this.alert = util.UtilityFX.alert("Converter", "Expression");
        cleanOnAction(new ActionEvent());//Cree uno nuevo, no es necesario

    }

    @javafx.fxml.FXML
    public void convertOnAction(ActionEvent actionEvent) {
        try {
            //Convierte cada expresión y muestra los resultados dependiendo de la opción elegida
            convertExpression(exp1TextField, result1Text1, result2Text1);
            convertExpression(exp2TextField, result1Text2, result2Text2);
            convertExpression(exp3TextField, result1Text3, result2Text3);
            convertExpression(exp4TextField, result1Text4, result2Text4);
            convertExpression(exp5TextField, result1Text5, result2Text5);

        } catch (StackException e) {
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void convertExpression(TextField expressionField, Text result1Text, Text result2Text) throws StackException {
        String expression = expressionField.getText();
        if (!expression.isEmpty()) {
            String result1 = "";
            String result2 = "";
            if (infixRadioButton.isSelected()) {
                //Convierte de infija a prefija y postfija
                result1 = infixToPrefixConverter(expression);
                result2 = infixToPostfixConverter(expression);
            } else if (postfixRadioButton.isSelected()) {
                //Convierte de postfija a prefija e infija
                result1 = postfixToPrefixConverter(expression);
                result2 = postfixToInfixConverter(expression);
            } else {
                //Convierte de prefija a infija y postfija
                result1 = prefixToInfixConverter(expression);
                result2 = prefixToPostfixConverter(expression);
            }
            //Asigna los resultados a los campos de texto correspondientes
            result1Text.setText(result1);
            result2Text.setText(result2);
        }
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
        this.postfixRadioButton.setSelected(false);
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
        this.postfixRadioButton.setSelected(true);

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
        this.postfixRadioButton.setSelected(false);
    }


    public String infixToPrefixConverter(String infixExpression) throws StackException {
        LinkedStack operatorsStack = new LinkedStack();
        LinkedStack resultStack = new LinkedStack();

        infixExpression = infixExpression.replaceAll("\\s", ""); //Elimina los espacios en blanco

        for (int i = infixExpression.length() - 1; i >= 0; i--) {
            char c = infixExpression.charAt(i);

            //Si es un operando, lo agrega al resultado
            if (Character.isLetterOrDigit(c)) {
                resultStack.push(Character.toString(c));
            } else if (c == ')') {
                //Si es un paréntesis de cierre, lo apila
                operatorsStack.push(c);
            } else if (c == '(') {
                //Si es un paréntesis de apertura, desapila operadores hasta encontrar el paréntesis de cierre
                while (!operatorsStack.isEmpty() && (char)operatorsStack.top() != ')') {
                    resultStack.push(operatorsStack.pop());
                }
                operatorsStack.pop(); //Saca el paréntesis de cierre
            } else if (isOperator(c)) {
                //Si es un operador
                while (!operatorsStack.isEmpty() && getPriority((char)operatorsStack.top()) > getPriority(c)) {
                    resultStack.push(operatorsStack.pop());
                }
                operatorsStack.push(c);
            }
        }

        //Saca los operadores restantes de la pila y los agrega al resultado
        while (!operatorsStack.isEmpty())  {
            resultStack.push(operatorsStack.pop());
        }

        //Construye el resultado como un String
        String prefixExpression = "";
        while (!resultStack.isEmpty()) {
            prefixExpression += resultStack.pop();
        }

        //invierte el resultado
        return prefixExpression;
    }

    public String prefixToInfixConverter(String prefixExpression) throws StackException {
        LinkedStack stack = new LinkedStack();

        prefixExpression = prefixExpression.replaceAll("\\s", ""); //Elimina los espacios en blanco
        for (int i = prefixExpression.length() - 1; i >= 0; i--) {
            char c = prefixExpression.charAt(i);

            //Si es un operando, lo apila
            if (Character.isLetterOrDigit(c)) {
                stack.push(Character.toString(c));
            } else {
                //Si es un operador, desapila dos operandos y forma una expresión infix
                String operand1 = (String) stack.pop(); // Desapilamos primero el primer operando
                String operand2 = (String) stack.pop(); // Luego el segundo operando
                String infix = "(" + operand1 + c + operand2 + ")"; // Formamos la expresión infix
                stack.push(infix);
            }
        }

        //El resultado final es la expresión infix que quedó en la pila
        return (String) stack.pop();
    }



    public String infixToPostfixConverter(String infixExpression) throws StackException {
        LinkedStack operatorsStack = new LinkedStack();
        StringBuilder result = new StringBuilder();

        infixExpression = infixExpression.replaceAll("\\s", ""); //Elimina los espacios en blanco

        for (int i = 0; i < infixExpression.length(); i++) {
            char c = infixExpression.charAt(i);

            //Si es un operando, lo agrega directamente al resultado
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                //Si es un paréntesis de apertura, lo apila
                operatorsStack.push(c);
            } else if (c == ')') {
                //Si es un paréntesis de cierre, desapila operadores hasta encontrar el paréntesis de apertura
                while (!operatorsStack.isEmpty() && (char) operatorsStack.top() != '(') {
                    result.append(operatorsStack.pop());
                }
                operatorsStack.pop(); //Saca el paréntesis de apertura
            } else if (isOperator(c)) {
                //Si es un operador
                while (!operatorsStack.isEmpty() && getPriority((char) operatorsStack.top()) >= getPriority(c)) {
                    result.append(operatorsStack.pop());
                }
                operatorsStack.push(c);
            }
        }

        //Saca los operadores restantes de la pila y los agrega al resultado
        while (!operatorsStack.isEmpty()) {
            result.append(operatorsStack.pop());
        }

        return result.toString();
    }

    public String postfixToInfixConverter(String postfixExpression) throws StackException {
        LinkedStack stack = new LinkedStack();

        postfixExpression = postfixExpression.replaceAll("\\s", ""); //Elimina los espacios en blanco

        for (int i = 0; i < postfixExpression.length(); i++) {
            char c = postfixExpression.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                // Si es un operando, lo apila
                stack.push(Character.toString(c));
            } else if (isOperator(c)) {
                // Si es un operador, desapila dos operandos
                String operand2 = (String) stack.pop();
                String operand1 = (String) stack.pop();

                // Forma la expresión infix combinando operandos y operador
                String infix = "(" + operand1 + c + operand2 + ")";

                // Apila la expresión infix nuevamente en la pila
                stack.push(infix);
            }
        }

        // El resultado final es la expresión infix que queda en la pila
        return (String) stack.pop();
    }




    public String prefixToPostfixConverter(String prefixExpression) throws StackException {
        LinkedStack stack = new LinkedStack();
        LinkedStack resultStack = new LinkedStack();

        prefixExpression = prefixExpression.replaceAll("\\s", ""); //Elimina los espacios en blanco

        for (int i = prefixExpression.length() - 1; i >= 0; i--) {
            char c = prefixExpression.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                resultStack.push(Character.toString(c));
            } else if (isOperator(c)) {
                // Verificar si hay suficientes operandos en la pila
                if (resultStack.size() < 2) {
                    throw new StackException("Invalid prefix expression: insufficient operands");
                }
                // Desapilar los dos operandos
                String operand1 = (String) resultStack.pop();
                String operand2 = (String) resultStack.pop();
                // Formar la expresión postfix combinando operandos y operador
                String postfix = operand1 + operand2 + c;
                // Apilar la expresión postfix en resultStack
                resultStack.push(postfix);
            }
        }

        // Verificar que al final solo quede una expresión postfix en la pila
        if (resultStack.size() != 1) {
            throw new StackException("Invalid prefix expression: too many operands");
        }

        // El resultado final es la expresión postfix que queda en resultStack
        return (String) resultStack.pop();
    }



    public String postfixToPrefixConverter(String postfixExpression) throws StackException {
        LinkedStack stack = new LinkedStack();

        postfixExpression = postfixExpression.replaceAll("\\s", ""); //Elimina los espacios en blanco

        for (int i = 0; i < postfixExpression.length(); i++) {
            char c = postfixExpression.charAt(i);

            //Si es un operando, lo apila
            if (Character.isLetterOrDigit(c)) {
                stack.push(Character.toString(c));
            } else {
                //Si es un operador, desapila dos operandos y forma una expresión prefix
                String operand2 = (String) stack.pop();
                String operand1 = (String) stack.pop();
                String prefix = c + operand1 + operand2;
                stack.push(prefix);
            }
        }

        //resultado final es la expresión prefix que queda en la pila
        return (String) stack.pop();
    }


    //determina si un caracter es un operador
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }




    //determina la precedencia de un operador
    private int getPriority(char operator) {
        switch(operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3; // Asigna una prioridad más alta al operador de potencia
            default:
                return 0; // Si no es un operador, retorno 0
        }
    }

}