package controller;

import domain.stack.StackException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void infixToPrefixConverter() throws StackException {
        Converter converter = new Converter();
        String infixExpression = "(A+B)*C-(D/E)";
        String expectedPrefix = "-*+ABC/DE";
        assertEquals(expectedPrefix, converter.infixToPrefixConverter(infixExpression));
    }

    @Test
    void prefixToInfixConverter() throws StackException {
        Converter converter = new Converter();
        String prefixExpression = "-*+ABC/DE";
        String expectedInfix = "(((A+B)*C)-(D/E))";
        assertEquals(expectedInfix, converter.prefixToInfixConverter(prefixExpression));
    }

    @Test
    void infixToPostfixConverter() throws StackException {
        Converter converter = new Converter();
        String infixExpression = "(A+B)*C-(D/E)";
        String expectedPostfix = "AB+C*DE/-";
        assertEquals(expectedPostfix, converter.infixToPostfixConverter(infixExpression));
    }

    @Test
    void postfixToInfixConverter() throws StackException {
        Converter converter = new Converter();
        String postfixExpression = "AB+C*DE/-";
        String expectedInfix = "(((A+B)*C)-(D/E))";
        assertEquals(expectedInfix, converter.postfixToInfixConverter(postfixExpression));
    }

    @Test
    void prefixToPostfixConverter() throws StackException {
        Converter converter = new Converter();
        String prefixExpression = "-*+ABC/DE";
        String expectedPostfix = "AB+C*DE/-";
        assertEquals(expectedPostfix, converter.prefixToPostfixConverter(prefixExpression));
    }

    @Test
    void postfixToPrefixConverter() throws StackException {
        Converter converter = new Converter();
        String postfixExpression = "AB+C*DE/-";
        String expectedPrefix = "-*+ABC/DE";
        assertEquals(expectedPrefix, converter.postfixToPrefixConverter(postfixExpression));
    }
}