package testapi;

import testapi.test.Application;
import testapi.test.CodeTester;
import testapi.utils.FileConverter;
import testapi.utils.RunaStriveFileConverter;

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

        String testFileDirectoryPath = "src/testapi/resources/testfile_save";
        FileConverter fileConverter = new RunaStriveFileConverter(testFileDirectoryPath);
        new CodeTester(application, testFileDirectoryPath, fileConverter).runTests();
    }
}
