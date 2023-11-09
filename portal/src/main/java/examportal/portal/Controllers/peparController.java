// package examportal.portal.Controllers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import examportal.portal.Entity.Pepar;
// import examportal.portal.Services.PeparSerivce;

// @RestController
// public class peparController {
//     @Autowired
//     public PeparSerivce peparSerivce;
//     @GetMapping("/pepar")
//     public ResponseEntity< List<Pepar>> getAllPepars(){
//         List<Pepar> list= this.peparSerivce.getAllPepars();
//         return new  ResponseEntity<List<Pepar>>(list,HttpStatus.CREATED);



//     }
// }
