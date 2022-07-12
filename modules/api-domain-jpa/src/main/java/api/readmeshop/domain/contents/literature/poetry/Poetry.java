package api.readmeshop.domain.contents.literature.poetry;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.user.member.writer.Writer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("P")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Poetry extends Literature {

    @Enumerated(EnumType.STRING)
    private PoetryShape shape;

    @Builder
    public Poetry(String title, String contents, PoetryShape shape) {
        super(title, contents);
        this.shape = shape;
    }
}
