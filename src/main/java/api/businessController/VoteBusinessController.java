package api.businessController;

import api.daos.DaoFactory;
import api.dtos.VoteDto;
import api.entities.Vote;

public class VoteBusinessController {

    public String create(VoteDto voteDto) {
        Vote vote = new Vote(voteDto.getValue(), voteDto.getComment());
        DaoFactory.getDaoFactory().getVoteDao().save(vote);
        return vote.getId();
    }
}
