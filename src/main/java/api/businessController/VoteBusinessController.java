package api.businessController;

import api.daos.DaoFactory;
import api.dtos.VoteDto;
import api.entities.Person;
import api.entities.Vote;

public class VoteBusinessController {

    private PersonBusinessController personBusinessController = new PersonBusinessController();

    public String create(VoteDto voteDto, String personId) {
        Person person = personBusinessController.getPerson(personId);

        Vote vote = new Vote(voteDto.getValue(), voteDto.getComment());
        DaoFactory.getDaoFactory().getVoteDao().save(vote);
        person.addVotes(vote.getId());
        DaoFactory.getDaoFactory().getPersonDao().save(person);
        return vote.getId();
    }
}
