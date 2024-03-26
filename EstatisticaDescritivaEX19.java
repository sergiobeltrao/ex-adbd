import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EstatisticaDescritivaEX19 {
    public static void main(String[] args) {

        int[] tempos = {135, 90, 85, 121, 83, 69, 159, 177, 120, 133, 90, 80, 70, 93, 80, 110};

        System.out.printf("Média: %.3fs%n", media(tempos));
        System.out.printf("Mediana: %.3fs%n", mediana(tempos));
        System.out.println("Moda: " + moda(tempos));
        System.out.printf("Desvio padrão: %.3fs%n", desvioPadrao(tempos));

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

        // No exemplo dessa atividade a execução nunca chega no else, mas decidi fazer de forma
        // que o código funcione independentemente da quantidade de itens no array.

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

        // Armazena o número e a incidência em uma matriz.
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

        // Procura o maior número de incidências.
        int maiorNumero = 0;

        for (int[] ints : numeroIncidencia) {

            for (int j = 0; numeroIncidencia.length > j; j++) {
                if (ints[1] > maiorNumero) {
                    maiorNumero = ints[1];
                    break;
                }
            }
        }

        // Armazena somente os valores de maior incidência em um novo array.
        int[] moda = new int[valores.length];

        for (int i = 0; i < numeroIncidencia.length; i++) {
            if (numeroIncidencia[i][1] == maiorNumero) {
                moda[i] = numeroIncidencia[i][0];
            }
        }

        // Filtra pelos números não repetidos diferentes de zero.
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
