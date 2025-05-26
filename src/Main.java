import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(identificarEstado("KFG1234"));
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
}
