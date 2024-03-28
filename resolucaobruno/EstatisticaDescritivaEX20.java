package resolucaobruno;

import javax.swing.JOptionPane;

public class EstatisticaDescritivaEX20 {
    public static void main(String[] args) {
        // Solicitação dos dados ao usuário
        String taxaInput = JOptionPane.showInputDialog("Digite as taxas de protrombina separadas por vírgula:");
        String[] taxaStr = taxaInput.split(",");
        int[] taxas = new int[taxaStr.length];

        for (int i = 0; i < taxaStr.length; i++) {
            taxas[i] = Integer.parseInt(taxaStr[i].trim());
        }

        String freqInput = JOptionPane.showInputDialog("Digite as frequências correspondentes separadas por vírgula:");
        String[] freqStr = freqInput.split(",");
        int[] frequencias = new int[freqStr.length];

        for (int i = 0; i < freqStr.length; i++) {
            frequencias[i] = Integer.parseInt(freqStr[i].trim());
        }

        // Verifica se o número de taxas corresponde ao dobro do número de frequências
        if (taxas.length != frequencias.length * 2) {
            JOptionPane.showMessageDialog(null, "O número de taxas deve ser igual ao dobro do número de frequências.");
            return;
        }

        // Gera os resultados
        String resultado = histograma(frequencias, "Histograma de frequência da taxa de protrombina:") + "\n";
        resultado += "Média: " + String.format("%.3f", media(taxas, frequencias)) + "\n" +
                "Mediana: " + String.format("%.3f", mediana(taxas, frequencias)) + "\n" +
                "Moda: " + String.format("%.2f", moda(taxas, frequencias)) + "\n" +
                "Desvio Padrão: " + String.format("%.3f", desvioPadrao(taxas, frequencias));

        // Exibe os resultados
        JOptionPane.showMessageDialog(null, resultado);
    }

    public static double media(int[] taxas, int[] frequencias) {
        double somaValores = 0.0;
        double somaFrequencias = 0.0;

        for (int i = 0; i < taxas.length; i++) {
            somaValores += taxas[i] * frequencias[i/2];
            somaFrequencias += frequencias[i/2];
        }

        return somaValores / somaFrequencias;
    }

    public static double mediana(int[] taxas, int[] frequencias) {
        int totalFrequencias = somaFrequencias(frequencias);

        // Calcula as frequências acumuladas
        int[] fa = new int[frequencias.length];
        fa[0] = frequencias[0];
        for (int i = 1; i < frequencias.length; i++) {
            fa[i] = fa[i - 1] + frequencias[i];
        }

        int n = totalFrequencias / 2;
        int classeMediana = -1;

        // Encontra a classe que contém a mediana
        for (int i = 0; i < fa.length; i++) {
            if (fa[i] >= n) {
                classeMediana = i;
                break;
            }
        }

        int li = taxas[classeMediana * 2];
        int fc = frequencias[classeMediana];
        int faAnterior = classeMediana == 0 ? 0 : fa[classeMediana - 1];
        int h = taxas[(classeMediana * 2) + 1] - li;

        return li + ((n - faAnterior) / (double) fc) * h;
    }

    public static double moda(int[] taxas, int[] frequencias) {
        double maxFreq = 0;
        double moda = 0;

        for (int i = 0; i < taxas.length; i += 2) {
            double li = taxas[i];
            double h = taxas[i + 1] - li;
            double d1 = i == 0 ? 0 : frequencias[i / 2 - 1];
            double d2 = i == taxas.length - 2 ? 0 : frequencias[i / 2 + 1];
            double currentModa = li + (d1 / (d1 + d2)) * h;

            if (frequencias[i / 2] > maxFreq) {
                moda = currentModa;
                maxFreq = frequencias[i / 2];
            }
        }

        return moda;
    }

    public static double desvioPadrao(int[] taxas, int[] frequencias) {
        double media = media(taxas, frequencias);
        double somaVariancia = 0.0;
        for (int i = 0; i < taxas.length; i++) {
            somaVariancia += frequencias[i/2] * Math.pow(taxas[i] - media, 2);
        }
        return Math.sqrt(somaVariancia / somaFrequencias(frequencias));
    }

    public static int somaFrequencias(int[] frequencias) {
        int soma = 0;
        for (int freq : frequencias) {
            soma += freq;
        }
        return soma;
    }

    public static String histograma(int[] frequencias, String titulo) {
        StringBuilder histogramaStr = new StringBuilder(titulo + "\n");
        for (int i = 0; i < frequencias.length; i++) {
            histogramaStr.append("Intervalo ").append(i + 1).append(": ");
            for (int j = 0; j < frequencias[i]; j++) {
                histogramaStr.append("█");
            }
            histogramaStr.append("\n");
        }
        return histogramaStr.toString();
    }
}




