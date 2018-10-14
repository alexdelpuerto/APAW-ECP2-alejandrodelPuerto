package api.daos.memory;

import api.daos.VoteDao;
import api.entities.Vote;

public class VoteDaoMemory extends GenericDaoMemory<Vote> implements VoteDao {

    @Override
    public String getId(Vote vote) {
        return vote.getId();
    }

    @Override
    public void setId(Vote vote, String id) {
        vote.setId(id);
    }
}
