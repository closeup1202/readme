package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.writer.Writer;
import api.readmeshop.domain.user.member.writer.WriterRepository;
import api.readmeshop.service.exception.ReadmeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static api.readmeshop.service.exception.ErrorCases.NOTFOUND;

@RequiredArgsConstructor
@Component
public class Validations implements Validation{

    private final WriterRepository writerRepository;

    @Override
    public Writer isWriter(String email){
        return writerRepository.findByEmail(email)
                .orElseThrow(()-> new ReadmeException(NOTFOUND));
    }
}
