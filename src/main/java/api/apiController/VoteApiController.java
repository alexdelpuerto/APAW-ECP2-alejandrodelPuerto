package api.apiController;

import api.businessController.VoteBusinessController;
import api.dtos.VoteDto;
import api.exceptions.ArgumentNotValidException;

public class VoteApiController {

    public static final String VOTES = "/votes";

    private VoteBusinessController voteBusinessController = new VoteBusinessController();

    public String create(VoteDto voteDto) {
        this.validate(voteDto, "voteDto");
        this.validate(voteDto.getValue(), "VoteDto value");
        return this.voteBusinessController.create(voteDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
