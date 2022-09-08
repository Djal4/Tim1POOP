package com.ldg.main.Controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppErrorController implements ErrorController {
    @GetMapping(path = "/error")
    public ResponseEntity<?> error() {
        Map<String, String> map = new HashMap<>();
        map.put("message", "API not exists");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
