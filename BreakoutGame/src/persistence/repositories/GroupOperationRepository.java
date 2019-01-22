


package persistence.repositories;

import domain.GroupOperation;


public class GroupOperationRepository extends GenericRepository<GroupOperation>
{
    public GroupOperationRepository()
    {
        super(GroupOperation.class);
    }

    private GroupOperationRepository(Class<GroupOperation> type)
    {
        super(type);
    }

}
