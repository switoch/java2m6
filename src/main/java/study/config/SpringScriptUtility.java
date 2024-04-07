package study.config;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

public class SpringScriptUtility {
    public static void runScript(String path, Connection connection) {
        boolean continueOrError = false;
        boolean ignoreFailedDrops = false;
        String commentPrefix = "--";
        String separator = ";";
        String blockCommentStartDelimiter = "/*";
        String blockCommentEndDelimiter = "*/";

        ScriptUtils.executeSqlScript(
                connection,
                new EncodedResource(new PathResource(path)),
                continueOrError,
                ignoreFailedDrops,
                commentPrefix,
                separator,
                blockCommentStartDelimiter,
                blockCommentEndDelimiter
        );
    }

    public static String readScript(String path) throws IOException {
        String result = "";
        try (FileReader reader = new FileReader(path);
             // Wrap the FileReader in a BufferedReader for
             // efficient reading.
             BufferedReader bufferedReader
                     = new BufferedReader(reader);) {

            StringBuilder builder = new StringBuilder();

            String line;
            int lineNumber = 0;
            int count = 0;

            // Read lines from the SQL file until the end of the
            // file is reached.
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber += 1;
                line = line.trim();

                // Skip empty lines and single-line comments.
                if (line.isEmpty() || line.startsWith("--"))
                    continue;

                builder.append(line);
                // If the line ends with a semicolon, it
                // indicates the end of an SQL command.
                if (line.endsWith(";")) {
                    result = builder.toString();

                    ++count;
                    builder.setLength(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
