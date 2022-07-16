package api.readmeshop.controller;

import api.readmeshop.request.literature.poetry.PostPoetryRequest;
import api.readmeshop.service.literature.poetry.PoetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PoetryController {

    private final PoetryService poetryService;

    @PostMapping("/poetry")
    public void poetry_post(@RequestBody PostPoetryRequest posting){
        poetryService.write(posting);
    }

    @GetMapping("/poetry")
    public void poetry(){

    }
}
