package testapi;

import testapi.test.Application;
import testapi.test.CodeTester;
import testapi.utils.FileConverter;
import testapi.utils.RunaStriveFileConverter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //start your main method like this:
        Application application = new Application(null);
        //the directory which holds the test-files.

        //currenttests
        //exampleinteraction
        //fuzzyiotests
        //quitintests
        //testfiles
        System.out.print("folder: ");
        String folder = new Scanner(System.in).nextLine();
        String testFileDirectoryPath = "src/testapi/resources/" + folder;
        FileConverter fileConverter = new RunaStriveFileConverter(testFileDirectoryPath);
        new CodeTester(application, testFileDirectoryPath, fileConverter).runTests();
    }
}
