package resolucaojohn;

import java.util.ArrayList;
import java.util.Collections;

public class Estatistica20 {
    public static void main(String[] args) {

        int[] classes = { 16, 26, 36, 46, 56, 66, 76 };
        int[] frequencias = { 22, 10, 6, 2, 4, 5, 1 };

        double media = calcularMedia(classes, frequencias);
        double mediana = calcularMediana(classes, frequencias);
        ArrayList<Integer> modas = calcularModa(classes, frequencias);
        double desvioPadrao = calcularDesvioPadrao(classes, frequencias);

        System.out.println("Média: " + media);
        System.out.println("Mediana: " + mediana);
        System.out.println("Moda: " + modas);
        System.out.println("Desvio Padrão: " + desvioPadrao);

    }

    public static double calcularMedia(int[] classes, int[] frequencias) {
        double soma = 0;
        double totalObservacoes = 0;
        for (int i = 0; i < classes.length - 1; i++) {
            soma += ((classes[i] + classes[i + 1]) / 2.0) * frequencias[i];
            totalObservacoes += frequencias[i];
        }
        return soma / totalObservacoes;
    }

    public static double calcularMediana(int[] classes, int[] frequencias) {
        ArrayList<Integer> valores = new ArrayList<>();
        for (int i = 0; i < classes.length - 1; i++) {
            int valor = (classes[i] + classes[i + 1]) / 2;
            for (int j = 0; j < frequencias[i]; j++) {
                valores.add(valor);
            }
        }
        Collections.sort(valores);
        int tamanho = valores.size();
        if (tamanho % 2 == 0) {
            return (valores.get(tamanho / 2 - 1) + valores.get(tamanho / 2)) / 2.0;
        } else {
            return valores.get(tamanho / 2);
        }
    }

    public static ArrayList<Integer> calcularModa(int[] classes, int[] frequencias) {
        ArrayList<Integer> modas = new ArrayList<>();
        int maxFrequencia = 0;
        for (int i = 0; i < frequencias.length; i++) {
            if (frequencias[i] > maxFrequencia) {
                maxFrequencia = frequencias[i];
                modas.clear();
                modas.add((classes[i] + classes[i + 1]) / 2);
            } else if (frequencias[i] == maxFrequencia) {
                modas.add((classes[i] + classes[i + 1]) / 2);
            }
        }
        return modas;
    }

    public static double calcularDesvioPadrao(int[] classes, int[] frequencias) {
        double media = calcularMedia(classes, frequencias);
        double soma = 0;
        double totalObservacoes = 0;
        for (int i = 0; i < classes.length - 1; i++) {
            double xi = ((classes[i] + classes[i + 1]) / 2.0);
            soma += frequencias[i] * Math.pow(xi - media, 2);
            totalObservacoes += frequencias[i];
        }
        return Math.sqrt(soma / totalObservacoes);
    }

}
