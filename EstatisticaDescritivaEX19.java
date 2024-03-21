import java.util.Arrays;

public class EstatisticaDescritivaEX19 {
    public static void main(String[] args) {

        int[] tempos = {135, 90, 85, 121, 83, 69, 159, 177, 120, 133, 90, 80, 70, 93, 80, 110};

        System.out.printf("Média: %.3fs%n", mediaItensDoArray(tempos));
        System.out.printf("Mediana: %.3fs%n", medianaItensDoArray(tempos));
        System.out.printf("Moda: %.3fs%n", moda(tempos));
        System.out.printf("Desvio padrão: %.3fs%n", desvioPadrao(tempos));

        // TODO: interprete os resultados
    }

    public static double mediaItensDoArray(int[] valores) {

        double total = 0.0;

        for (int tempo : valores) {
            total += tempo;
        }

        return total / valores.length;
    }

    public static double medianaItensDoArray(int[] valores) {

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

        double media = mediaItensDoArray(valores);

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

    public static double moda(int[] valores) {
        // TODO: moda

        return 0.0;
    }
}
