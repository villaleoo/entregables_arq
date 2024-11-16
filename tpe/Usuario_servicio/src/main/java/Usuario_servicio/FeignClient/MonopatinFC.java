package Usuario_servicio.FeignClient;

import Monopatin_servicio.Entity.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "monopatin_servicio", url = "http://localhost:8080/monopatines")
public interface MonopatinFC {

    @PostMapping("/admin/nuevo")
    ResponseEntity<String> nuevo(@RequestBody Monopatin m);


}
