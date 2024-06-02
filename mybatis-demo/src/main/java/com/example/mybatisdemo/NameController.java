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

