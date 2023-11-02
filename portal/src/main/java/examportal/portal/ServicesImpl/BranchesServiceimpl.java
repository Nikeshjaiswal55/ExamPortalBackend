package examportal.portal.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Branch;
import examportal.portal.Repo.BranchRepo;
import examportal.portal.Services.BranchService5;

@Service
public class BranchesServiceimpl implements BranchService5 {
    @Autowired
    public BranchRepo branchRepo;

    @Override
    public List<Branch> getBranch() {
        List<Branch> branchs = branchRepo.findAll();
        return branchs;
    }

    @Override
    public Branch geteBranchById(int getId) {
        return branchRepo.findById(getId).orElseThrow(); 
    }

    @Override
    public Branch postBranch(Branch user) {
        Branch b = branchRepo.save(user);
        return b;
    }

    @Override
    public Branch putBranch(Branch user) {
        // Branch b = branchRepo.findById(user.getId()).orElseThrow();
        // b.setBranchName(user.getBranchName());
        // b.setId(user.getId());
        // branchRepo.save(b);
      return  branchRepo.save(user);
       // return b;
    }

    @Override
    public void deleteBranchById(int getId) {
        // Branch b = branchRepo.findById(getId).orElseThrow();
        branchRepo.deleteById(getId);
    }
}