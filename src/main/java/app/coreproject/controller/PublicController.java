package app.coreproject.controller;

import app.coreproject.contract.ResponseContract;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ductbm
 */

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PublicController {

    @GetMapping("/health-check")
    public ResponseContract<?> demo() {
        return new ResponseContract<>(200, "OK", new Date());
    }
}
