package api.readmeshop.controller;

import api.readmeshop.request.literature.PostedLiterature;
import api.readmeshop.request.literature.PostingLiterature;
import api.readmeshop.service.literature.LiteratureService;
import api.readmeshop.service.literature.LiteratureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LiteratureController {

    private final LiteratureService literatureService;

//    @GetMapping("/poetry")
//    public List<LiteratureResponse> getList(@ModelAttribute PostedLiterature posted){
//        return literatureService.getPoetries(posted);
//    }

    @PostMapping("/poetry")
    public void posting(@RequestBody PostingLiterature posting){
        literatureService.write(posting);
    }
}
