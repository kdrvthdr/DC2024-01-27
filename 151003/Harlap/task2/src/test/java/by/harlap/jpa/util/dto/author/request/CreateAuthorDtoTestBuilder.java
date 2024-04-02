package by.harlap.jpa.util.dto.author.request;

import by.harlap.jpa.dto.request.CreateAuthorDto;
import by.harlap.jpa.util.TestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor(staticName = "author")
@With
public class CreateAuthorDtoTestBuilder implements TestBuilder<CreateAuthorDto> {

    private String login = "login";
    private String password = "asdfghj241";
    private String firstname = "firstname1294";
    private String lastname = "lastname6724";

    @Override
    public CreateAuthorDto build() {
        return new CreateAuthorDto(login, password, firstname, lastname);
    }
}
