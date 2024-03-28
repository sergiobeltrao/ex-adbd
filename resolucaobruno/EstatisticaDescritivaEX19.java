package resolucaobruno;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class EstatisticaDescritivaEX19 {
    public static void main(String[] args) {

        String input = JOptionPane.showInputDialog("Digite os tempos separados por vírgula:");
        String[] temposStr = input.split(",");
        int[] tempos = new int[temposStr.length];

        for (int i = 0; i < temposStr.length; i++) {
            tempos[i] = Integer.parseInt(temposStr[i].trim());
        }

        JOptionPane.showMessageDialog(null, "Média: " + String.format("%.3f", media(tempos)) + "s\n" +
                "Mediana: " + String.format("%.3f", mediana(tempos)) + "s\n" +
                "Moda: " + moda(tempos) + "\n" +
                "Desvio padrão: " + String.format("%.3f", desvioPadrao(tempos)) + "s");

        // TODO: interprete os resultados
    }

    public static double media(int[] valores) {

        double total = 0.0;

        for (int tempo : valores) {
            total += tempo;
        }

        return total / valores.length;
    }

    public static double mediana(int[] valores) {

        Arrays.sort(valores);

        if (valores.length % 2 == 0) {
            int[] meioDoArray = new int[2];
            meioDoArray[0] = valores[(valores.length / 2) - 1];
            meioDoArray[1] = valores[valores.length / 2];
            return ((double) meioDoArray[0] + meioDoArray[1]) / 2;
        } else {
            int meioDoArray = ((valores.length - 1) / 2);
            return valores[meioDoArray];
        }
    }

    public static double desvioPadrao(int[] valores) {

        double[] variancia = new double[valores.length];

        double media = media(valores);

        for (int i = 0; variancia.length > i; i++) {
            variancia[i] = Math.abs(valores[i] - media);
        }

        double[] varianciaQuadratica = new double[variancia.length];

        for (int i = 0; varianciaQuadratica.length > i; i++) {
            varianciaQuadratica[i] = variancia[i] * variancia[i];
        }

        double totalVarianciaQuadratica = 0.0;

        for (double valorVariancia : varianciaQuadratica) {
            totalVarianciaQuadratica += valorVariancia;
        }

        return Math.sqrt(totalVarianciaQuadratica / (valores.length - 1));
    }

    public static String moda(int[] valores) {

        int[][] numeroIncidencia = new int[valores.length][2];

        for (int i = 0; valores.length > i; i++) {

            int contadorDeRepeticao = 0;

            for (int tempo : valores) {
                if (valores[i] == tempo) {
                    contadorDeRepeticao++;
                }
            }

            numeroIncidencia[i][0] = valores[i];
            numeroIncidencia[i][1] = contadorDeRepeticao;
        }

        int maiorNumero = 0;

        for (int[] ints : numeroIncidencia) {

            for (int j = 0; numeroIncidencia.length > j; j++) {
                if (ints[1] > maiorNumero) {
                    maiorNumero = ints[1];
                    break;
                }
            }
        }

        int[] moda = new int[valores.length];

        for (int i = 0; i < numeroIncidencia.length; i++) {
            if (numeroIncidencia[i][1] == maiorNumero) {
                moda[i] = numeroIncidencia[i][0];
            }
        }

        Set<Integer> numerosDiferentesDeZero = new HashSet<>();

        for (int valor : moda) {
            if (valor != 0) {
                numerosDiferentesDeZero.add(valor);
            }
        }

        StringBuilder saida = new StringBuilder();

        for (int numero : numerosDiferentesDeZero) {
            saida.append(numero).append("s ");
        }

        return saida.toString();
    }
}
