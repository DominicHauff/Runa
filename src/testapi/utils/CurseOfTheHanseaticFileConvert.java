package testapi.utils;

import testapi.test.CodeTester;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurseOfTheHanseaticFileConvert extends FileConverter {

    public CurseOfTheHanseaticFileConvert(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public List< String > convert(List< String > fileContent) {
        List< String > convertedContent = new ArrayList<>();
        if (!fileContent.get(0).startsWith("> ") && !fileContent.get(0).startsWith("#") && !fileContent.get(0).startsWith("$$")) return fileContent;
        for (int i = 0; i < fileContent.size(); i++) {
            String next = fileContent.get(i);
            if (next.startsWith("> ")) {
                convertedContent.add(next.substring(2));
                if (next.equals("> EXIT")) {
                    convertedContent.add("#");
                }
                else if (next.equals("> quit")
                        || fileContent.get(i + 1).startsWith("> ")
                        || fileContent.get(i + 1).startsWith("#")) {
                    convertedContent.add("#");
                }
            } else if(next.equals("#")) convertedContent.add(next);
            else if (next.startsWith("##") || next.startsWith("#")) continue;
            else if (i + 1 >= fileContent.size()) convertedContent.add(next);
            else if (fileContent.get(i + 1).startsWith("> ")) {
                if (next.equals("<e")) convertedContent.add("Error,#");
                else convertedContent.add(next + "#");
            }else if (next.equals("EXIT")) convertedContent.add(next);
            else if (fileContent.get(i + 1).startsWith("#")) {
                convertedContent.add(next + "#");
            } else if (next.equals("<e")) convertedContent.add("Error,#");
            else convertedContent.add(next);
        }
        return convertedContent;
    }
}
