package orangetalents.com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloController {

    @Value("${environment.msg}")
    private String environmentMsg;

    @GetMapping(value = {"", "/", "/hello"})
    public String hello() {
        return "Orange Talents APP Started!!!";
    }

    @GetMapping("/env")
    public ResponseEntity<String> env() {
        return new ResponseEntity<>(environmentMsg, HttpStatus.OK);
    }


    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("UP!!!", HttpStatus.OK);
    }
}
