package com.oscarcpz.jce.utils;

import com.oscarcpz.jce.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomFilenameUtility {

    private static final Logger log = LoggerFactory.getLogger(CustomFilenameUtility.class);

    /**
     * Method to build the correct path for the output file
     * @param output name of the output file
     * @param datePattern pattern to be applied to convert current date to string
     * @return File object with the path correctly built
     * @throws FileException
     */
    public static File buildOutputFile(final String output, final String datePattern) throws FileException {
        if(StringUtils.isEmpty(output)) {
            throw new FileException("[output] is mandatory");
        }

        if(StringUtils.isEmpty(datePattern)) {
            throw new FileException("[datePattern] is mandatory");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);

        Path dstPath = Paths.get(output);
        log.debug("dstPath: {}", dstPath);
        Path dstFileName = dstPath.getFileName();
        log.debug("dstFileName: {}", dstFileName);
        Path dstParent = dstPath.getParent();
        log.debug("dstParent: {}", dstParent);
        String extension = String.format(".%s", FilenameUtils.getExtension(dstFileName.toString()));
        log.debug("extension: {}", extension);
        String currentDateAsString = sdf.format(new Date(System.currentTimeMillis()));
        log.debug("currentDateAsString: {}", currentDateAsString);
        String outputFileName = String.format("%s-%s%s", StringUtils.replace(dstFileName.toString(), extension, ""), currentDateAsString, extension);
        log.debug("outputFileName: {}", outputFileName);
        File outputFile = new File(dstParent.toString(), outputFileName);
        log.debug("outputFile: {}", outputFile.getAbsolutePath());

        return outputFile;
    }
}
