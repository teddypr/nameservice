package com.example.mybatisdemo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class NameController {

    private NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    //リクエストパラム
    @GetMapping("/names") //URLのパスに影響
    public List<Name> findByNames(@RequestParam String startsWith) {
        List<Name>names = nameService.findNamesStartingWith(startsWith);
        return names;
    }

    @GetMapping("/names/{id}")
    public Name findName(@PathVariable("id") int id){
        return nameService.findName(id);
    }
}

public class ExceptionHandler {
    @ExceptionHandler(value = NameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNameNotFoundException(
            NameNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

