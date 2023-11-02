package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Branch;

public interface BranchService5 {

    List<Branch> getBranch();

    Branch geteBranchById(int getId);

    Branch postBranch(Branch user);

    Branch putBranch(Branch user);

    void deleteBranchById(int getId);
    
}
