package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.critic.CriticShape;
import api.readmeshop.domain.contents.literature.novel.NovelShape;
import api.readmeshop.domain.contents.literature.poetry.PoetryShape;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Shape {

    POETRY(List.of(PoetryShape.values())),
    NOVEL(List.of(NovelShape.values())),
    CRITIC(List.of(CriticShape.values()));

    private List<Enum> list;

    public Stream<Enum> getListStream(){
        return getList().stream();
    }
}
