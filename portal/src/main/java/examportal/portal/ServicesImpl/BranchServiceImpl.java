package examportal.portal.ServicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import examportal.portal.Entity.Branch;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Repo.BranchRepo;
import examportal.portal.Services.BranchService;

public class BranchServiceImpl implements BranchService{
    @Autowired
  public BranchRepo branchRepo;

  Logger log = LoggerFactory.getLogger("BranchServiceimpl.class");

  @Override
  public List<Branch> getAllBranch() {
    log.info("BranchServiceimpl,getBranch Method Start");
    List<Branch> branch = branchRepo.findAll();
    log.info("BranchServiceimpl,getBranch Method Ends");
    return branch;
  }

  @Override
  public Branch getBranchById(String getId) {
    log.info("BranchServiceimpl,getBranchById Method Start");
    Branch c = this.branchRepo.findById(getId).orElseThrow();
    log.info("BranchServiceimpl,getBranchById Method Ends");

    return c;
  }

  @Override
  public Branch addBranch(Branch branch) {
    log.info("BranchServiceimpl,addBranch Method Start");
    Branch c = this.branchRepo.save(branch);
    log.info("BranchServiceimpl,addBranch Method Ends");
    return c;
  }

  @Override
  public Branch updateBranch(Branch branch) {
    log.info("BranchServiceimpl, updateBranch Method Start");
    Branch c = this.branchRepo.findById(branch.getBranchId()).orElseThrow(() -> new ResourceNotFoundException("Branch", "BranchID", branch.getBranchId()));
    c.setBranchName(branch.getBranchName());
    log.info("BranchServiceimpl, updateBranch Method Ends");
    return branchRepo.save(c);
  }

  @Override
  public void deleteBranchById(String getId) {
    log.info("BranchServiceimpl, deleteBranch Method Start");
    branchRepo.deleteById(getId);
    log.info("BranchServiceimpl, deleteBranch Method Ends");
  }
}
