package com.example.neycasilla.calcs;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by Ney Casilla on 6/10/2017.
 */

public class operations {
    private List<String> op = new ArrayList<>();

    public List<String> taductor(StringBuilder expresion){
        String val = "0";
        List<String> newExpresion = new ArrayList<>();
        if(expresion.length()==0){
            return newExpresion;
        }
        for(int i=0;i<expresion.length();i++){
            switch (expresion.charAt(i)){
                case '+':
                    newExpresion.add(val);
                    op.add("+");
                    val = "";
                    break;
                case '-':
                    newExpresion.add(val);
                    op.add("-");
                    val = "";
                    break;
                case '/':
                    newExpresion.add(val);
                    op.add("/");
                    val = "";
                    break;
                case 'x':
                    newExpresion.add(val);
                    op.add("x");
                    val = "";
                    break;
                default:
                    val += expresion.charAt(i);
                    break;
            }
        }
        newExpresion.add(val);
        return newExpresion;
    }

    public double operator(List<String> num){
        double resultados = 0;
        List<Double> values = numbers(num);
        if(op.contains("x")){
            for (int i=0;i<op.size();i++){
                if(op.get(i).equals("x")){
                    if(resultados == 0){
                        resultados = values.get(i);
                        resultados *= values.get(i+1);
                        values.remove(i+1);
                        values.set(i,resultados);
                    }else {
                        resultados = values.get(i) * resultados;
                        values.set(i,resultados);
                    }
                }
            }
        }
        if(op.contains("/")){
            for (int i=0;i<op.size();i++){
                if(op.get(i).equals("/")){
                    if(resultados == 0){
                        resultados = values.get(i);
                        resultados /= values.get(i+1);
                        values.remove(i+1);
                        values.set(i,resultados);

                    }else {
                        resultados = values.get(i) / resultados;
                        values.set(i,resultados);
                    }
                }
            }
        }
        if(op.contains("+")){
            for (int i=0;i<op.size();i++){
                if(op.get(i).equals("+")){
                    if(resultados == 0){
                        resultados = values.get(i);
                        resultados += values.get(i+1);
                        values.remove(i+1);
                        values.set(i,resultados);
                    }else {
                        resultados = values.get(i) + resultados;
                        values.set(i,resultados);
                    }
                }
            }
        }
        if(op.contains("-")){
            for (int i=0;i<op.size();i++){
                if(op.get(i).equals("-")){
                    if(resultados == 0){
                        resultados = values.get(i);
                        resultados -= values.get(i+1);
                        values.remove(i+1);
                        values.set(i,resultados);
                    }else {
                        resultados = values.get(i) - resultados;
                        values.set(i,resultados);
                    }
                }
            }
        }

        return resultados;
    }

    private List<Double> numbers(List<String> expresions){
        List<Double> vals = new ArrayList<>();
        for (int i=0;i<expresions.size();i++){
            vals.add(Double.parseDouble(expresions.get(i)));
        }
        return vals;
    }

    //RPN OPERATIONS
    public List<Double> RpnNumbers(StringBuilder expresions){
        String data = new String();
        List<Double> vals = new ArrayList<>();
        for (int i=0;i<expresions.length();i++){

            if(expresions.toString().charAt(i)!= ' '){
                data += expresions.toString().charAt(i);
            }else {
                vals.add(Double.parseDouble(data));
                data = "";
            }
        }
        vals.add(Double.parseDouble(data));
        return vals;
    }

    public StringBuilder operate(StringBuilder expresions,String operand){
        StringBuilder currenText = new StringBuilder();
        List<Double> valores = RpnNumbers(expresions);
        if(valores.size()==2){
            switch (operand){
                case "+":
                    currenText.append((int)(valores.get(0)+valores.get(1)));
                    break;
                case "-":
                    currenText.append((int)(valores.get(0)-valores.get(1)));
                    break;
                case "x":
                    currenText.append((int)(valores.get(0)*valores.get(1)));
                    break;
                case "/":
                    currenText.append((int) (valores.get(0)/valores.get(1)));
                    break;
            }
        }else if(valores.size()>2) {
            for (int i = 0;i<valores.size()-2;i++){
                double vals = valores.get(i);
                currenText.append((int)vals+" ");
            }
            switch (operand){
                case "+":
                    currenText.append((int)(valores.get(valores.size()-2)+valores.get(valores.size()-1)));
                    break;
                case "-":
                    currenText.append((int)(valores.get(valores.size()-2)-valores.get(valores.size()-1)));
                    break;
                case "x":
                    currenText.append((int)(valores.get(valores.size()-2)*valores.get(valores.size()-1)));
                    break;
                case "/":
                    currenText.append((int)(valores.get(valores.size()-2)/valores.get(valores.size()-1)));
                    break;
                }
        }else {
            currenText.append(expresions);
        }

        return currenText;
    }
}
