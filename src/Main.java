import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Digite '0' para encerrar.");

            System.out.println("Digite a Placa do carro: ");
            String placa = sc.nextLine().trim();
            if(placa.equals("0")) break;

            System.out.println("Digite a hora de entrada: ");
            String entrada = sc.nextLine().trim();
            if(entrada.equals("0")) break;

            System.out.println("Digite a hora de saída: ");
            String saida = sc.nextLine().trim();
            if(saida.equals("0")) break;

            String estado = identificarEstado(placa);
            int minutosEstacionados = conversaoHorasParaMinutos(entrada, saida);
            double preco = calcularPrecoEstacionamento(minutosEstacionados);
            System.out.println("\nEstado: " + estado + "\nValor à pagar: " + String.format("%.2f", preco) + "\n");
        }
    }

    public static String identificarEstado(String placa) {
        String prefixo = placa.substring(0, 3).toUpperCase();

        if (estaNoIntervalo(prefixo, new String[][]{
                {"MMN", "MOW"},
                {"OET", "OFH"},
                {"OFX", "OGG"},
                {"OFT", "OFW"},
                {"QFA", "QFZ"},
                {"QSA", "QSM"},
                {"RLQ", "RMC"}
        })) {
            return "Paraíba";
        }

        if (estaNoIntervalo(prefixo, new String[][]{
                {"MXH", "MZM"},
                {"NNJ", "NOH"},
                {"QGA", "QGZ"},
                {"RGE", "RGN"},
                {"RGN", "RGN"},
                {"OJR", "OKC"},
                {"OVZ", "OWG"}
        })) {
            return "Rio Grande do Norte";
        }

        if (estaNoIntervalo(prefixo, new String[][]{
                {"KFD", "KME"},
                {"PCA", "PED"},
                {"PEE", "PFQ"},
                {"PFR", "PGK"},
                {"PGL", "PGU"},
                {"PGV", "PGZ"},
                {"QYA", "QYZ"},
                {"RZE", "RZZ"},
                {"NXU", "NXW"},
                {"OYL", "OYZ"}
        })) {
            return "Pernambuco";
        }

        return "Estado não identificado";
    }

    private static boolean estaNoIntervalo(String prefixo, String[][] intervalos) {
        for (String[] intervalo : intervalos) {
            if (prefixo.compareTo(intervalo[0]) >= 0 && prefixo.compareTo(intervalo[1]) <= 0) {
                return true;
            }
        }
        return false;
    }

    private static int conversaoHorasParaMinutos(String entrada, String saida) {
        int minutosEntrada = parseInt(entrada.split(":")[0]) * 60 + parseInt(entrada.split(":")[1]);
        int minutosSaida = parseInt(saida.split(":")[0]) * 60 + parseInt(saida.split(":")[1]);
        return minutosSaida - minutosEntrada;
    }

    private static double calcularPrecoEstacionamento(int minutosEstacionado) {
        double taxaAdicional = (double) 213 / 100; // m == 0.13 == (0 + 5 + 8) / 100
        double precoTotal = 0.0;

        if (minutosEstacionado <= 15) {
            return precoTotal;
        }

        precoTotal = 10.0;

        if (minutosEstacionado <= 180) {
            return precoTotal;
        }

        int periodosExtras = (minutosEstacionado - 180) / 20;
        double valorExtra = (periodosExtras + 1) * (taxaAdicional);
        precoTotal += valorExtra;

        return precoTotal;
    }

}
