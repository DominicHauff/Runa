package testapi.utils;

import java.util.ArrayList;
import java.util.List;

public class RunaStriveFileConverter extends FileConverter {


    public RunaStriveFileConverter(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public List<String> convert(List<String> fileContent) {
        List< String > convertedContent = new ArrayList<>();
        if (fileContent.isEmpty() || fileContent.get(0).startsWith("|")) return fileContent;

        for (int i = 0; i < fileContent.size(); i++) {
            String next = fileContent.get(i);
            if (next.startsWith("# ")) continue;
            if (next.equals("Welcome to Runa's Strive")) {
                convertedContent.add("|" + next);
                continue;
            }
            if (next.startsWith("<e")) {
                convertedContent.add("Error,#");
                continue;
            }
            String endOutput = next.endsWith("#") ? next : next + "#";
            if (i == fileContent.size() - 1) {
                if (next.startsWith("> ")) {
                    convertedContent.add(next.substring(2));
                    convertedContent.add(next.endsWith("#") ? "" : "#");
                    continue;
                }
                convertedContent.add(endOutput);
                continue;
            }
            if (next.startsWith("> ")) {
                convertedContent.add(next.substring(2));
                continue;
            }
            if (fileContent.get(i + 1).startsWith("> ")) {
                convertedContent.add(endOutput);
                continue;
            }
            convertedContent.add(next);
        }
        return convertedContent;
    }
}
