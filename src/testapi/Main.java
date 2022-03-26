package testapi;

import testapi.test.Application;
import testapi.test.CodeTester;
import testapi.utils.CurseOfTheHanseaticFileConvert;
import testapi.utils.FileConverter;
import testapi.utils.RunaStriveFileConverter;

public class Main {
    public static void main(String[] args) {
        //start your main method like this:
        Application application = new Application(null);
        //the directory which holds the test-files.
        String testFileDirectoryPath = "src/testapi/resources/currenttests";
        FileConverter fileConverter = new RunaStriveFileConverter(testFileDirectoryPath);
        new CodeTester(application, testFileDirectoryPath, fileConverter).runTests();
    }
}
