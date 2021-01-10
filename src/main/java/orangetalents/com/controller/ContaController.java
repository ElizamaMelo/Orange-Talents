package orangetalents.com.controller;

import orangetalents.com.dto.response.request.ContaRequest;
import orangetalents.com.dto.response.response.ContaResponse;
import orangetalents.com.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/v1/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponse create(@Valid @RequestBody ContaRequest request) {
        return service.create(request);

    }

}
