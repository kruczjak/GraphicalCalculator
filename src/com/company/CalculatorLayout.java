package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by kruczjak on 19.05.14.
 */
public class CalculatorLayout {
    private JPanel panel1;
    private JButton button7;
    private JButton button1;
    private JButton button4;
    private JButton button8;
    private JButton button5;
    private JButton button2;
    private JButton button9;
    private JButton button6;
    private JButton button3;
    private JButton button0;
    private JButton cButton;
    private JButton buttonDivide;
    private JButton buttonMul;
    private JButton buttonPlus;
    private JButton buttonMin;
    private JTextArea textArea1;
    private JButton buttonEq;

    private boolean delete;
    private boolean sign=true;
    private double last=0;

    private char [] numbers = {'0','1','2','3','4','5','6','7','8','9'};
    private char [] op = {'/','*','-','+'};
    public CalculatorLayout() {
        init();
    }

    /**
     * After number click
     * @param ch symbol
     */
    private void actionClickNumber(String ch) {
        if (delete) {
            textArea1.setText(ch);
            delete=false;
            sign=false;
            return;
        }
        textArea1.setText(textArea1.getText()+ch);
        sign=false;
    }

    /**
     * After operator click
     * @param ch symbol
     */
    private void actionClickOp(String ch)   {
        if (delete) {
            textArea1.setText(last+ch);
            delete=false;
            sign=true;
            return;
        }
        if (sign) return;

        textArea1.setText(textArea1.getText()+ch);
        sign=true;
    }

    /**
     * Clear screen (textArea1)
     */
    private void clear()    {
        textArea1.setText("");
        delete=false;
        sign=true;
    }

    /**
     * Evaluate expression from textArea1
     */
    private void eval() {
        if (sign) return;
        last = Calculator.calculate(textArea1.getText());
        textArea1.setText(textArea1.getText()+"="+last);
        delete = true;
        sign=false;
    }

    private void init() {
        /* number action listener */
        ActionListener clickNumbers = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                actionClickNumber(actionEvent.getActionCommand());
            }
        };

        button0.addActionListener(clickNumbers);
        button1.addActionListener(clickNumbers);
        button2.addActionListener(clickNumbers);
        button3.addActionListener(clickNumbers);
        button4.addActionListener(clickNumbers);
        button5.addActionListener(clickNumbers);
        button6.addActionListener(clickNumbers);
        button7.addActionListener(clickNumbers);
        button8.addActionListener(clickNumbers);
        button9.addActionListener(clickNumbers);

        /* operators */
        ActionListener clickOperators = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                actionClickOp(actionEvent.getActionCommand());
            }
        };
        buttonDivide.addActionListener(clickOperators);
        buttonMul.addActionListener(clickOperators);
        buttonPlus.addActionListener(clickOperators);
        buttonMin.addActionListener(clickOperators);

        /* other */
        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clear();
            }
        });
        buttonEq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eval();
            }
        });

        /* keylistener */

        textArea1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode()==KeyEvent.VK_DELETE) {
                    clear();
                    return;
                }

                if (keyEvent.getKeyCode()==KeyEvent.VK_ENTER) {
                    eval();
                    return;
                }

                char c = keyEvent.getKeyChar();

                for (char ch : numbers) {
                    if (ch==c) {
                        actionClickNumber(String.valueOf(ch));
                        return;
                    }
                }
                for (char ch : op)  {
                    if (ch==c) {
                        actionClickOp(String.valueOf(ch));
                        return;
                    }
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CalculatorLayout");
        CalculatorLayout calculatorLayout = new CalculatorLayout();
        frame.setResizable(false);
        frame.setContentPane(calculatorLayout.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
