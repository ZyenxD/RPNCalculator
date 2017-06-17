package com.example.neycasilla.calcs;

import java.util.ArrayList;
import java.util.List;



class operations {
    //RPN OPERATIONS

    /**se encarga de transformar el string escrito del usuario
     * en un conjunto de datos calculables, en este caso numeros tipo double
     * @param expresions expreseion escrita por los usuarios*/
    private List<Double> RpnNumbers(StringBuilder expresions){
        String data = "";
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

    /**se encarga de realizar los calculos y dar una respuesta al usuario
     * @param expresions expreseion escrita por los usuarios
     * @param  operand la operacion a realizar*/
    StringBuilder operate(StringBuilder expresions,String operand){
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
                    currenText.append( (valores.get(0)/valores.get(1)));
                    break;
            }
        }else if(valores.size()>2) {
            for (int i = 0;i<valores.size()-2;i++){
                double vals = valores.get(i);
                String apend = (int)vals+" ";
                currenText.append(apend);
            }
            switch (operand){
                case "+":
                    currenText.append((valores.get(valores.size()-2)+valores.get(valores.size()-1)));
                    break;
                case "-":
                    currenText.append((valores.get(valores.size()-2)-valores.get(valores.size()-1)));
                    break;
                case "x":
                    currenText.append((valores.get(valores.size()-2)*valores.get(valores.size()-1)));
                    break;
                case "/":
                    currenText.append((valores.get(valores.size()-2)/valores.get(valores.size()-1)));
                    break;
                }
        }else {
            currenText.append(expresions);
        }

        return currenText;
    }
}
