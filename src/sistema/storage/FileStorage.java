package sistema.storage;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileStorage {

    public static final Path cadastrosDir = Paths.get("src/sistema/petsCadastrados");

    public static void salvaArquivo(String content, String nomePet) throws IOException {
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HHmm");
        String dataFormatada = data.format(formatoData);
        String horaFormatada = data.format(formatoHora);
        String nomeArquivo = dataFormatada + "T" + horaFormatada + "-" + nomePet.toUpperCase() + ".txt";

        Path arquivoFinal = cadastrosDir.resolve(nomeArquivo);

        Files.writeString(
                arquivoFinal,
                content,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

}
