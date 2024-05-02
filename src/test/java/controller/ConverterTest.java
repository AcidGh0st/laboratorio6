package controller;

import domain.stack.StackException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void infixToPrefixConverter() throws StackException {
        Converter converter = new Converter();
        String infixExpression = "a+b*(c^d-e) ";
        String expectedPrefix = "+a*b-^cde";
        assertEquals(expectedPrefix, converter.infixToPrefixConverter(infixExpression));
    }

    @Test
    void prefixToInfixConverter() throws StackException {
        Converter converter = new Converter();
        String prefixExpression = "*2+1+*4+213";
        String expectedInfix = "(2*(1+((4*(2+1))+3)))";
        assertEquals(expectedInfix, converter.prefixToInfixConverter(prefixExpression));
    }

    @Test
    void infixToPostfixConverter() throws StackException {
        Converter converter = new Converter();
        String infixExpression = "a+b*(c^d-e) ";
        String expectedPostfix = "abcd^e-*+";
        assertEquals(expectedPostfix, converter.infixToPostfixConverter(infixExpression));
    }

    @Test
    void postfixToInfixConverter() throws StackException {
        Converter converter = new Converter();
        String postfixExpression = "ab-ac+*";
        String expectedInfix = "((a-b)*(a+c))";
        assertEquals(expectedInfix, converter.postfixToInfixConverter(postfixExpression));
    }

    @Test
    void prefixToPostfixConverter() throws StackException {
        Converter converter = new Converter();
        String prefixExpression = "*2+1+*4+213";
        String expectedPostfix = "21421+*3++*";
        assertEquals(expectedPostfix, converter.prefixToPostfixConverter(prefixExpression));
    }

    @Test
    void postfixToPrefixConverter() throws StackException {
        Converter converter = new Converter();
        String postfixExpression = "ab-ac+*";
        String expectedPrefix = "*-ab+ac";
        assertEquals(expectedPrefix, converter.postfixToPrefixConverter(postfixExpression));
    }
}