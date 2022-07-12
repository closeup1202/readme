package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.writer.Writer;

public interface Validation {

    Writer isWriter(String email);
}
