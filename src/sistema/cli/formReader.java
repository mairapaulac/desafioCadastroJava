package sistema.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class formReader {
    private static Path cadastroForm = Paths.get("src/sistema/storage/form.txt");

    public static void showForm() {
        try (BufferedReader reader = Files.newBufferedReader(cadastroForm)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException exception) {
            System.out.println("Erro ao ler arquivo: " + exception.getMessage());
        }
    }
}
