package com.oscarcpz.jce.utils;

import com.oscarcpz.jce.exceptions.FileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


class CustomFilenameUtilityTest {

    @Test
    public void buildOutputFile_ok() throws FileException {
        File output = CustomFilenameUtility.buildOutputFile("my/output/path/output-test.file", "yyyyMMdd");
        Assertions.assertNotNull(output);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        Assertions.assertEquals(String.format("%s/%s-%s.file", "my/output/path", "output-test", date), output.getPath());
    }

    @Test
    public void buildOutputFile_fail_empty_output() {
        Assertions.assertThrows(FileException.class, () -> {
            File output = CustomFilenameUtility.buildOutputFile(null, "yyyyMMdd");
        });
    }

    @Test
    public void buildOutputFile_fail_empty_datePattern() {
        Assertions.assertThrows(FileException.class, () -> {
            File output = CustomFilenameUtility.buildOutputFile("output", "");
        });
    }
}