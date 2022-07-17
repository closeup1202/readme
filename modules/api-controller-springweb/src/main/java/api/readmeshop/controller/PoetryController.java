package api.readmeshop.controller;

import api.readmeshop.request.literature.poetry.PostLiteratureRequest;
import api.readmeshop.service.literature.PostLiteratureRequired;
import api.readmeshop.service.literature.poetry.PoetryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PoetryController {

    private final PoetryService poetryService;

    @PostMapping("/poetry")
    public void poetry_post(@RequestBody PostLiteratureRequest request){
        PostLiteratureRequired required = request.getPostLiteratureRequired();
        poetryService.write(required);
    }

    @GetMapping("/poetry")
    public void poetry(){

    }
}
