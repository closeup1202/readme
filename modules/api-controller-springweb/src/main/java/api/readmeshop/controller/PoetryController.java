package api.readmeshop.controller;

import api.readmeshop.request.literature.PostedLiterature;
import api.readmeshop.request.literature.poetry.PostLiteratureRequest;
import api.readmeshop.service.literature.LiteratureResponse;
import api.readmeshop.service.literature.PostLiteratureRequired;
import api.readmeshop.service.literature.poetry.PoetryService;
import api.readmeshop.service.policies.posting.PostedPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PoetryController {

    private final PoetryService poetryService;

    @PostMapping("/poetry")
    public void poetryPost(@RequestBody PostLiteratureRequest request){
        PostLiteratureRequired required = request.getPostLiteratureRequired();
        poetryService.write(required);
    }

    @GetMapping("/poetry")
    public List<LiteratureResponse> poetry(@ModelAttribute PostedLiterature literature){
        PostedPolicy policy = literature.postedPolicy();
        return poetryService.getList(policy);
    }
}
