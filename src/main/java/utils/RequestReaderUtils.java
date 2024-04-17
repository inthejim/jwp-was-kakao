package utils;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReaderUtils {
    public static String read(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();

        while (line != null && !line.isEmpty()) {
            builder.append(line);
            line = reader.readLine();
            builder.append("\n");
        }

        return builder.toString().trim();
    }
}
