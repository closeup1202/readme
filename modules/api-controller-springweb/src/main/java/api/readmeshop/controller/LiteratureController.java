package api.readmeshop.controller;

import api.readmeshop.request.literature.PostingPoetry;
import api.readmeshop.service.literature.LiteratureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LiteratureController {

    private final LiteratureService literatureService;

    @GetMapping("/poetry")
    public void poetry(){

    }

    @PostMapping("/poetry")
    public void poetry_post(@RequestBody PostingPoetry posting){
        literatureService.write(posting);
    }
}
