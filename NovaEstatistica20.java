import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class NovaEstatistica20 {

    public static void main(String[] args) {
        // Dados fornecidos
        int[] faixas = { 16, 26, 36, 46, 56, 66, 76 };
        int[] frequencias = { 22, 10, 6, 2, 4, 5, 1 };

        // Calculando média
        double media = calcularMedia(faixas, frequencias);

        // Calculando mediana
        double mediana = calcularMediana(faixas, frequencias);

        // Calculando moda
        String moda = calcularModa(faixas, frequencias);

        // Calculando desvio padrão
        double desvioPadrao = calcularDesvioPadrao(faixas, frequencias, media);

        // Construindo histograma
        String histograma = construirHistograma(faixas, frequencias);

        // Exibindo os resultados usando JOptionPane
        DecimalFormat df = new DecimalFormat("#0.00");
        JOptionPane.showMessageDialog(null,
                "Média: " + media + "\n" +
                        "Mediana: " + df.format(mediana) + "\n" +
                        "Moda: " + moda + "\n" +
                        "Desvio Padrão: " + desvioPadrao + "\n\n" +
                        "Histograma:\n" + histograma,
                "Análise de Haras",
                JOptionPane.PLAIN_MESSAGE);
    }

    // Método para calcular a média
    public static double calcularMedia(int[] faixas, int[] frequencias) {
        double soma = 0;
        int totalElementos = Arrays.stream(frequencias).sum();

        for (int i = 0; i < faixas.length; i++) {
            double pontoMedio = (faixas[i] + faixas[i] + 9) / 2.0;
            soma += pontoMedio * frequencias[i];
        }

        return soma / totalElementos;
    }

    // Método para calcular a mediana
    public static double calcularMediana(int[] faixas, int[] frequencias) {
        int totalFrequencias = Arrays.stream(frequencias).sum();
        double elementoMediano = totalFrequencias / 2.0;
        double somaFrequencias = 0;
        double mediana = 0;

        for (int i = 0; i < faixas.length; i++) {
            somaFrequencias += frequencias[i];
            if (somaFrequencias >= elementoMediano) {
                int freqAnterior = i > 0 ? frequencias[i - 1] : 0;
                double limiteInferior = faixas[i];
                double intervalo = faixas[i + 1] - faixas[i];
                mediana = limiteInferior
                        + (((elementoMediano - (somaFrequencias - frequencias[i])) / (double) frequencias[i])
                                * intervalo);
                break;
            }
        }

        return mediana;
    }

    // Método para calcular a moda
    public static String calcularModa(int[] faixas, int[] frequencias) {
        int modaIndex = 0;
        int maxFrequencia = frequencias[0];
    
        // Encontre a classe modal (classe com a maior frequência)
        for (int i = 1; i < frequencias.length; i++) {
            if (frequencias[i] > maxFrequencia) {
                maxFrequencia = frequencias[i];
                modaIndex = i;
            }
        }
    
        // Determine o ponto médio da classe modal
        double limiteInferior = faixas[modaIndex];
        double larguraClasse = faixas[1] - faixas[0]; // Assumindo que as faixas são uniformemente espaçadas
        int freqAnterior = modaIndex > 0 ? frequencias[modaIndex - 1] : 0;
        int freqProxima = modaIndex < frequencias.length - 1 ? frequencias[modaIndex + 1] : 0;
        double diferencaAnterior = maxFrequencia - freqAnterior;
        double diferencaProxima = maxFrequencia - freqProxima;
    
        // Calcular a moda usando a fórmula fornecida
        double pontoMedioModa = limiteInferior + ((diferencaAnterior) / (diferencaAnterior + diferencaProxima)) * larguraClasse;
    
        // Retorne o ponto médio da classe modal como uma string
        return String.valueOf(pontoMedioModa);
    }

    // Método para calcular o desvio padrão
    public static double calcularDesvioPadrao(int[] faixas, int[] frequencias, double media) {
        double soma = 0;
        int totalElementos = Arrays.stream(frequencias).sum();

        for (int i = 0; i < faixas.length; i++) {
            soma += Math.pow(faixas[i] - media, 2) * frequencias[i];
        }

        return Math.sqrt(soma / totalElementos);
    }

    // Método para construir o histograma
    public static String construirHistograma(int[] faixas, int[] frequencias) {
        StringBuilder histograma = new StringBuilder();
        for (int i = 0; i < faixas.length; i++) {
            histograma.append(faixas[i]).append("-").append(faixas[i] + 9).append(" | ");
            for (int j = 0; j < frequencias[i]; j++) {
                histograma.append("*");
            }
            histograma.append("\n");
        }
        return histograma.toString();
    }
}