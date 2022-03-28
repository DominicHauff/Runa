package testapi.utils;

import testapi.test.CodeTester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FileConverter {
    private final List<File> files;
    private final String directoryPath;

    public FileConverter(String directoryPath) {
        this.directoryPath = directoryPath;
        this.files = new ArrayList<>();
        String[] paths = new File(directoryPath).list();
        if (paths != null)
            Arrays.stream(paths).forEach(path -> files.add(new File(path)));
    }

    public void convert() {
        files.forEach(file -> {
            List< String > fileContent = read(file);
            List< String > convertedContent = convert(fileContent);
            write(file, convertedContent);
        });
    }

    public void write(File file, List< String > content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + "/" + file.getPath()));
            for (int i = 0; i < content.size() - 1; i++) {
                String s = content.get(i);
                writer.write(s + System.lineSeparator()); //test
            }
            writer.write(content.get(content.size() - 1));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(CodeTester.terminal);
        }
    }

    public List< String > read(File file) {
        try {
            List< String > fileContent = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(directoryPath + "/" + file.getPath()));
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                fileContent.add(nextLine);
            }
            reader.close();
            return fileContent;
        } catch (IOException o) {
            o.printStackTrace(CodeTester.terminal);
            return null;
        }
    }

    public abstract List< String > convert(List< String > fileContent);
}
