package testapi.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunaStriveFileConverterTest {

    @Test
    void testFileConversion() {
        FileConverter fileConverter = new RunaStriveFileConverter("src/testapi/resources/testfiles");
        fileConverter.convert();
    }
}