import java.io.*;
import java.util.*;

public class AnalisadorLL {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java LL1Parser <caminho-completo-tabela.csv>");
            return;
        }

        String csvFile = args[0];

        try {
            Map<String, Map<String, String>> ll1Table = readLL1Table(csvFile);
            List<String> inputTokens = readInput();

            String startSymbol = ll1Table.keySet().iterator().next(); // Assumindo que o primeiro não-terminal é o símbolo inicial

            String result = predictiveParse(ll1Table, inputTokens, startSymbol);
            System.out.println(result);

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static Map<String, Map<String, String>> readLL1Table(String filename) throws IOException {
        Map<String, Map<String, String>> ll1Table = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        String[] headers = br.readLine().split(",");

        while ((line = br.readLine()) != null) {
            String[] row = line.split(",");
            String nonTerminal = row[0];
            ll1Table.put(nonTerminal, new HashMap<>());

            for (int i = 1; i < row.length; i++) {
                if (!row[i].isEmpty()) {
                    ll1Table.get(nonTerminal).put(headers[i], row[i]);
                }
            }
        }

        br.close();
        return ll1Table;
    }

    public static List<String> readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a entrada para análise sintática (tokens separados por espaços):");
        String input = scanner.nextLine();
        scanner.close();

        List<String> inputTokens = new ArrayList<>(Arrays.asList(input.split(" ")));
        inputTokens.add("$"); // Marcador de fim de entrada
        return inputTokens;
    }

    public static String predictiveParse(Map<String, Map<String, String>> ll1Table, List<String> inputTokens, String startSymbol) {
        Stack<String> stack = new Stack<>();
        stack.push(startSymbol);

        int index = 0;

        while (!stack.isEmpty()) {
            String top = stack.pop();
            String currentToken = inputTokens.get(index);

            if (top.equals(currentToken)) {
                index++;
            } else if (ll1Table.containsKey(top)) { // Non-terminal
                if (ll1Table.get(top).containsKey(currentToken)) {
                    String production = ll1Table.get(top).get(currentToken);
                    if (!production.equals("ε")) {
                        String[] symbols = production.split(" ");
                        for (int i = symbols.length - 1; i >= 0; i--) {
                            stack.push(symbols[i]);
                        }
                    }
                } else {
                    return "Erro: token inesperado '" + currentToken + "' na posição " + index + ".";
                }
            } else {
                return "Erro: símbolo inesperado na pilha '" + top + "'.";
            }
        }

        if (index == inputTokens.size() - 1) {
            return "Análise sintática bem-sucedida!";
        } else {
            return "Erro: análise incompleta. Token restante '" + inputTokens.get(index) + "'.";
        }
    }
}
