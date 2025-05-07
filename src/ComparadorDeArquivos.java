import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ComparadorDeArquivos {
    private static String arquivo1, arquivo2;

    // Construtor que irá pegar os dois arquivos que serão comparados
    public ComparadorDeArquivos(String arquivo1, String arquivo2)  {
        ComparadorDeArquivos.arquivo1 = arquivo1;
        ComparadorDeArquivos.arquivo2 = arquivo2;
        compararLinhas();
    }

    // Irá fazer a leitura dos arquivos para verificar se existem, caso sejam encontrados
    // irá ser pego as linhas e informações de ambos
    private static List<List<String>> lerArquivos()  {
        List<List<String>> listaDeLinhasDosArquivos = Arrays.asList();
        if (arquivo1.isEmpty() || arquivo2.isEmpty()) throw new NullPointerException("Arquivo faltando!");
        try {
            List<String> linhasDoArquivo1 = Files.readAllLines(Paths.get(arquivo1));
            List<String> linhasDoArquivo2 = Files.readAllLines(Paths.get(arquivo2));
            listaDeLinhasDosArquivos = List.of(linhasDoArquivo1, linhasDoArquivo2);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        System.out.println("Leitura realizada com sucesso!");
        System.out.println(listaDeLinhasDosArquivos);
        return listaDeLinhasDosArquivos;
    }

    // Vai verificar os dois arquivos para ver qual o maior número de linhas
    public static int arquivoComMaisLinhas(List<List<String>> linhasDosArquivos) {
        int tamanhoDoArquivo1 = linhasDosArquivos.get(0).size();
        int tamanhoDoArquivo2 = linhasDosArquivos.get(1).size();
        int maximoDeLinhas = Math.max(tamanhoDoArquivo1, tamanhoDoArquivo2);
        return maximoDeLinhas;
    }

    // Comparar linha por linha para fazer a verificação de mudanças
    private static void compararLinhas() {
        List<List<String>> arquivos = lerArquivos();
        int numeroDeLinhas = arquivoComMaisLinhas(lerArquivos());
        for(int i = 0; i < numeroDeLinhas; i++) {
            String linhaDoArquivo1 = i < arquivos.get(0).size() ? arquivos.get(0).get(i) : null;
            String linhaDoArquivo2 = i < arquivos.get(1).size() ? arquivos.get(1).get(i) : null;

            if (linhaDoArquivo1 == null && !(linhaDoArquivo2 == null)) {
                System.out.println("Linha " + (i + 1) + ": ➕ Adicionada: " + linhaDoArquivo2);
            } else if (linhaDoArquivo2 == null && !(linhaDoArquivo1 == null)) {
                System.out.println("Linha " + (i + 1) + ": ❌ Removida: " + linhaDoArquivo1);
            } else if (!linhaDoArquivo1.equals(linhaDoArquivo2)) {
                System.out.println("Linha " + (i + 1) + ": 🔁 Alterada");
                System.out.println("  Antes: " + linhaDoArquivo1);
                System.out.println("  Depois: " + linhaDoArquivo2);
            } else {
                System.out.println("Linha " + (i + 1) + ": ✅ Igual");
            }

        }
        System.out.println("Comparação finalizada com sucesso!");
    }


}
