package testapi.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestFileReader {
    private final BufferedReader reader;
    public final File file;

    public TestFileReader(File file) {
        this.file = file;
        try {
            this.reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file could not be read :(");
        }
    }

    public InOutPair getNextPair() {
        try {
            StringBuilder sb = new StringBuilder();
            String inLine = reader.readLine();
            if (inLine == null) return null;
            while (inLine.startsWith("$$")) {
                inLine = reader.readLine();
            }
            if (inLine.startsWith("|")) {
                StringBuilder inSb = new StringBuilder(inLine.substring(1) + System.lineSeparator());
                inLine = reader.readLine();
                while (!inLine.endsWith("#")) {
                    inSb.append(UmlautsConverter.removeUmlauts(inLine)).append(System.lineSeparator());
                    inLine = reader.readLine();
                }
                String lastLine = inLine.substring(0, inLine.indexOf("#"));
                inSb.append(UmlautsConverter.removeUmlauts(lastLine));
                return new InOutPair(null, inSb.toString());
            }
            String nextOutputLine = reader.readLine();
            if (nextOutputLine == null) return new InOutPair(null, inLine);
            while (!nextOutputLine.endsWith("#")) {
                sb.append(UmlautsConverter.removeUmlauts(nextOutputLine)).append(System.lineSeparator());
                nextOutputLine = reader.readLine();
            }
            String lastLine = nextOutputLine.substring(0, nextOutputLine.indexOf('#'));
            sb.append(UmlautsConverter.removeUmlauts(lastLine));
            return new InOutPair(inLine, sb.toString());
        } catch (IOException ioException) {
            throw new RuntimeException("error while reading file");
        }
    }

    public String[] getArgs() {
        List<String> args = new ArrayList<>();
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                if (nextLine.startsWith("$$")) {
                    args.add(nextLine.substring(3));
                }
            }
            return args.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
