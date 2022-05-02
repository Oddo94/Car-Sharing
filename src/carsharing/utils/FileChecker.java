package carsharing.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

public class FileChecker {

    public static boolean isFilePresent(String path, String extension, int directoryLevel) {
        if(path == null || extension == null ||  directoryLevel <= 0) {
            return false;
        }

        List<String> fileNamesList = new ArrayList<>();
        try(Stream<Path> fileStream = Files.walk(Paths.get(path), directoryLevel)) {
            fileNamesList = fileStream
                    .filter(file -> !Files.isDirectory(file))
                    .map(file -> file.toString().toLowerCase())
                    .filter(fileName -> fileName.endsWith(extension))
                    .collect(Collectors.toList());

            if(fileNamesList.size() > 0) {
                return true;
            }

        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
