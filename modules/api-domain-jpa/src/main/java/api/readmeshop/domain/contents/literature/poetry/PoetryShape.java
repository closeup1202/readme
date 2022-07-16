package api.readmeshop.domain.contents.literature.poetry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PoetryShape {
    PROSE("prose"),
    VERSE("verse"),
    FREE("free"),
    AVANT_GARDE("avant_garde");

    private String type;
}
