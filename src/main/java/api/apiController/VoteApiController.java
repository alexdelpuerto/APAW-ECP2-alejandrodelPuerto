package api.apiController;

import api.businessController.VoteBusinessController;
import api.dtos.VoteDto;
import api.exceptions.ArgumentNotValidException;

public class VoteApiController {

    public static final String VOTES = "/votes";

    private VoteBusinessController voteBusinessController = new VoteBusinessController();

    public String create(VoteDto voteDto, String personId) {
        this.validate(voteDto, "voteDto");
        if (voteDto.getValue() < 0 || voteDto.getValue() > 10) {
            throw new ArgumentNotValidException("Vote value must be between 0 and 10");
        }
        return this.voteBusinessController.create(voteDto, personId);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
