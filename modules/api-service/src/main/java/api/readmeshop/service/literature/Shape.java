package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.critic.CriticShape;
import api.readmeshop.domain.contents.literature.novel.NovelShape;
import api.readmeshop.domain.contents.literature.poetry.PoetryShape;
import lombok.Getter;

@Getter
public class Shape {
    private PoetryShape poetryShape;
    private NovelShape novelShape;
    private CriticShape criticShape;
}
