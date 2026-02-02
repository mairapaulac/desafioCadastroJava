package sistema.util;

import java.text.Normalizer;

public class StringNormalizer {

    public static String normaliza(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }

}
