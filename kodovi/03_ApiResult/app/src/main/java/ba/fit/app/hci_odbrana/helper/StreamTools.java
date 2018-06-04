package ba.fit.app.hci_odbrana.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamTools {

    public static String readStream(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }
        if (builder.length() == 0) {
            return null;
        }

        return builder.toString();
    }
}
