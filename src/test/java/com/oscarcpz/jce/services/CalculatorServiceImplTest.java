package com.oscarcpz.jce.services;

import com.oscarcpz.jce.utils.CustomFilenameUtility;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CalculatorServiceImplTest {

    @Value("${calculator.dst}")
    private String dst;
    @Value("${calculator.delimiter}")
    private String delimiter;
    @Value("${calculator.encoding}")
    private String encoding;
    @Value("${calculator.date_pattern}")
    private String datePattern;

    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void action_ok() {
        try {
            calculatorService.run(null);
            //check if a new file has been created
            File output = CustomFilenameUtility.buildOutputFile(dst, datePattern);
            Assertions.assertTrue(output.exists());
            Assertions.assertTrue(output.isFile());
            Assertions.assertTrue(output.canRead());
            List<String> lines = FileUtils.readLines(output, encoding);
            Assertions.assertNotNull(lines);
            Assertions.assertFalse(lines.isEmpty());
            Assertions.assertEquals(3, lines.size());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
