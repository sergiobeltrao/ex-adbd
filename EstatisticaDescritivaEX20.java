import java.util.Arrays;

public class EstatisticaDescritivaEX20 {
    public static void main(String[] args) {

        // Início, fim e frequência.
        int[][] taxaDeProtrombina = {
                {16, 25, 22},
                {26, 35, 10},
                {36, 45, 6},
                {46, 55, 2},
                {56, 65, 4},
                {66, 75, 5},
                {76, 85, 1}
        };

        histograma(taxaDeProtrombina);
        System.out.printf("Média: %.3f%n", media(taxaDeProtrombina));
        System.out.printf("Mediana: %.3f%n", mediana(taxaDeProtrombina));
        System.out.printf("Moda: %.3f%n", moda(taxaDeProtrombina));
        System.out.printf("Desvio Padrão: %.3f%n", desvioPadrao(taxaDeProtrombina));
    }

    public static double media(int[][] matriz) {

        double somaValores = 0.0;
        double somaFrequencias = 0.0;

        for (int[] valor : matriz) {
            double inicio = valor[0];
            double fim = valor[1];
            double frequencia = valor[2];

            somaValores += (((fim - inicio) / 2) + inicio) * frequencia;
            somaFrequencias += frequencia;
        }

        return somaValores / somaFrequencias;
    }

    public static double mediana(int[][] matriz) {
        int somaDasFrequencias = 0;

        for (int[] ints : matriz) {
            somaDasFrequencias += ints[2];
        }

        int meio;

        if (somaDasFrequencias % 2 == 0) {
            meio = somaDasFrequencias / 2;
        } else {
            meio = (somaDasFrequencias / 2) + 1;
        }

        int[] intervaloDaMediana = new int[0];
        int frequenciaDaMediana = 0;
        int posAtual = 0;
        int freqImediatamenteAnterior = 0;
        int intervalo = matriz[0][1] - matriz[0][0];

        for (int[] taxa : matriz) {
            posAtual += taxa[2];

            if (posAtual >= meio) {
                intervaloDaMediana = new int[]{
                        taxa[0],
                        taxa[1]
                };
                frequenciaDaMediana = taxa[2];
                break;
            } else {
                freqImediatamenteAnterior = taxa[2];
            }
        }

        return intervaloDaMediana[0] + ((((double) somaDasFrequencias / 2) - freqImediatamenteAnterior) / frequenciaDaMediana) * intervalo;
    }

    public static double moda(int[][] matriz) {

        int[] classeModal = new int[0];
        int maiorFrequencia = 0;
        int frequenciaImediatamenteSuperior = 0;
        int frequenciaImediatamenteInferior = 0;

        for (int[] ints : matriz) {

            if (maiorFrequencia < ints[2]) {
                maiorFrequencia = ints[2];
                classeModal = new int[]{ints[0], ints[1]};
            }
        }

        for (int[] frequencia : matriz) {

            if (frequencia[2] == maiorFrequencia) {
                break;
            } else {
                frequenciaImediatamenteSuperior = frequencia[2];
            }
        }

        for (int[] frequencia : matriz) {

            if (frequencia[2] == maiorFrequencia) {
                frequenciaImediatamenteInferior = -1;
            } else if (frequenciaImediatamenteInferior == -1) {
                frequenciaImediatamenteInferior = frequencia[2];
                break;
            }
        }

        int limiteInferiorDaClasseModal = classeModal[0];
        double deltaUm = maiorFrequencia - frequenciaImediatamenteSuperior;
        double deltaDois = maiorFrequencia - frequenciaImediatamenteInferior;
        double tamanhoDaClasse = classeModal[1] - classeModal[0];

        return limiteInferiorDaClasseModal + (deltaUm * tamanhoDaClasse) / (deltaUm + deltaDois);
    }

    public static double desvioPadrao(int[][] matriz) {

        double[] desvioQuadratico = new double[matriz.length];

        for (int i = 0; matriz.length > i; i++) {
            double inicio = matriz[i][0];
            double fim = matriz[i][1];

            double xi = ((fim - inicio) / 2) + inicio;
            double desvio = Math.abs(xi - media(matriz));
            desvioQuadratico[i] = Math.pow(desvio, 2);
        }

        double totalDesvioFrequencia = 0.0;

        for (int i = 0; desvioQuadratico.length > i; i++) {
            totalDesvioFrequencia += desvioQuadratico[i] * matriz[i][2];
        }

        int totalFrequencias = 0;

        for (int[] frequencia : matriz) {
            totalFrequencias += frequencia[2];
        }

        double variancia = totalDesvioFrequencia / (totalFrequencias - 1);
        return Math.sqrt(variancia);
    }

    public static void histograma(int[][] matriz) {
        System.out.println("Histograma");
        for (int[] ints : matriz) {
            System.out.print(ints[0] + ", " + ints[1] + " | ");
            int frequencia = ints[2];
            for (int j = 0; frequencia > j; j++) {
                System.out.print("█");
                frequencia--;
            }
            System.out.println();
        }
        System.out.println();
    }
}
