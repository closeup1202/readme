package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.user.member.Member;
import lombok.Getter;

@Getter
public class LiteratureResponse {
    private Member member;
    private String title;
    private String contents;

    public LiteratureResponse(Literature literature){
        this.member = literature.getMember();
        this.title = literature.getTitle();
        this.contents = literature.getContents();
    }
}
