package com.oscarcpz.jce.services;

import com.oscarcpz.jce.exceptions.FileException;
import com.oscarcpz.jce.utils.CustomFilenameUtility;
import com.oscarcpz.jce.utils.LineUtility;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorServiceImpl implements ApplicationRunner, CalculatorService {

    @Value("${calculator.src}")
    private String src;
    @Value("${calculator.dst}")
    private String dst;
    @Value("${calculator.delimiter}")
    private String delimiter;
    @Value("${calculator.encoding}")
    private String encoding;
    @Value("${calculator.date_pattern}")
    private String datePattern;

    private final Logger log = LoggerFactory.getLogger(CalculatorServiceImpl.class);

    public CalculatorServiceImpl() {
        log.debug("Prepared");
    }

    /**
     * Method which contains the main functionality
     * @return true if output file could be wroten
     * @throws FileException
     */
    private boolean action() throws FileException {
        log.debug("src: {}", src);
        log.debug("dst: {}", dst);
        log.debug("delimiter: {}", delimiter);
        log.debug("encoding: {}", encoding);

        if(src == null) {
            throw new FileException("src file property is empty");
        }

        log.debug("1. Read file from [{}]", src);
        File inputFile = new File(src);
        log.debug("1.1 Check if file exists");
        if(!inputFile.exists()) {
            throw new FileException(String.format("File %s does not exist", src));
        }
        log.debug("1.2 Check if file is a file");
        if(!inputFile.isFile()) {
            throw new FileException(String.format("File %s is not a file", src));
        }
        log.debug("1.3 Check if file can be read");
        if(!inputFile.canRead()) {
            throw new FileException(String.format("File %s can not be read", src));
        }
        log.debug("1.4 File is correct");

        List<String> lines = null;
        try {
            lines = FileUtils.readLines(inputFile, encoding);
        } catch (IOException e) {
            throw new FileException(String.format("Unable to read file %s", src), e);
        }

        if((lines == null) || (lines.isEmpty())) {
            log.debug("File [{}] is empty", src);
            return false;
        }

        log.debug("2. [{}] lines have been read", lines.size());

        File outputFile = CustomFilenameUtility.buildOutputFile(dst, datePattern);
        log.debug("3. Prepare output file: {}", outputFile.getAbsolutePath());

        log.debug("3. For each line -> make operations");
        List<String> linesResult = new ArrayList<>();
        for(String line : lines) {
            String[] lineSplitted = line.split(delimiter);
            ArrayList<String> listResult = new ArrayList<>();
            int counter = 0;
            for(String element : lineSplitted) {
                if(counter == 0) {
                    //is the first element. The first element is the line number
                    listResult.add(element); //keep line number
                    counter++;
                    continue;
                }
                listResult.add(LineUtility.operation(element));
                counter++;
            }
            String lineResult = String.join(delimiter, listResult);
            log.debug("3.1 [{}] modified to [{}]", line, listResult);
            linesResult.add(lineResult);
        }

        try {
            FileUtils.writeLines(outputFile, linesResult);
            log.debug("4. [{}] lines have been wroten to output file", linesResult.size());
        } catch (IOException e) {
            throw new FileException(String.format("Unable to write lines to outputfile %s", outputFile.getAbsolutePath()), e);
        }

        return true;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(action()) {
            log.info("Success");
        }
        else {
            log.error("Error");
        }
    }
}
