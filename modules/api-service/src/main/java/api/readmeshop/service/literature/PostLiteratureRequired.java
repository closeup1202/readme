package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.poetry.PoetryShape;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class PostLiteratureRequired {
    private String email;
    private String title;
    private String contents;
    private Enum type;

    @Builder
    public PostLiteratureRequired(String email, String title, String contents, String typeStr) {
        this.email = email;
        this.title = title;
        this.contents = contents;
        this.type = Arrays.stream(Shape.values())
                        .flatMap(Shape::getListStream)
                        .filter(type -> type.name().equalsIgnoreCase(typeStr))
                        .collect(Collectors.toList()).get(0);
    }
}
