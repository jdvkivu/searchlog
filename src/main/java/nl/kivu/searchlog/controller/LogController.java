package nl.kivu.searchlog.controller;

import nl.kivu.searchlog.file.LogFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Controller
public class LogController {

    @Autowired
    LogFile file;

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(required = false) String logfile,
                           @RequestParam(required = false) String search,
                           @RequestParam(required = false, defaultValue = "1") long page,
                           @RequestParam(required = false, defaultValue = "10000") long size) {
        String path = logfile; // == null ? "nth2.log" : logfile;

        if ( logfile == null || logfile.trim().isEmpty() ) {
            return "logpage";
        }

        long totalLineCount = file.lineCount(path);
        long fromLine = (page - 1) * size;
        List<String> lines = file.lines(path, fromLine, size, search);
        long lineCount = search != null && !search.trim().isEmpty() ? file.searchLineCount(path, search) : totalLineCount;
        long pageCount = pageCount(lineCount, size);

        if ( page > pageCount ) {
            page = 1;
        }

        if (pageCount > 1) {
            List<Long> pageNumbers = LongStream.rangeClosed(1, pageCount)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("pageCount", pageCount);
        model.addAttribute("size", size);
        model.addAttribute("logfile", path);
        model.addAttribute("totallinecount", totalLineCount);
        model.addAttribute("linecount", lineCount);
        model.addAttribute("loglines", lines);
        model.addAttribute("currentPage", page);
        model.addAttribute("search", search);

        return "logpage";
    }


    public long pageCount(long itemCount, long pageSize) {
        return itemCount / pageSize + ((itemCount % pageSize > 0) ? 1 : 0) ;
    }


}
