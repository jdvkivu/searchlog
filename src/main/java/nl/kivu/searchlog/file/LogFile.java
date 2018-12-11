package nl.kivu.searchlog.file;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Service
public class LogFile {

    public List<String> lines(String path, long start, long limit, String search) {
        List<String> loglines = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            Stream<String> s = lines;
            if (search != null && !search.trim().isEmpty()) {
                s = s.filter(l -> l.toLowerCase().contains(search.toLowerCase()));
            }
            s.skip(start).limit(limit).forEach(l ->  loglines.add(l));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loglines;
    }

    public long searchLineCount(String path, String search) {
        long count = 0;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            Stream<String> s = lines;
            if (search != null && !search.trim().isEmpty()) {
                count = s.filter(l -> l.toLowerCase().contains(search.toLowerCase())).count();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    public long lineCount(String path) {
        long count = 0;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            count = lines.count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}
