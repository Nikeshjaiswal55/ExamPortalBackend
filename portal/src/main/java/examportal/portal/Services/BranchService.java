package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Branch;

public interface BranchService {
     List<Branch>getAllBranch();

    Branch getBranchById(String getId);

    Branch addBranch(Branch branch);

    Branch updateBranch(Branch branch);

    void deleteBranchById(String getId);

}
