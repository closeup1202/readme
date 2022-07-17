package api.readmeshop.service.literature;

import lombok.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
