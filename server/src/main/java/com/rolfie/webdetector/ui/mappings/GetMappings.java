package com.rolfie.webdetector.ui.mappings;

import com.rolfie.webdetector.ui.component.IndexForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GetMappings {

    @GetMapping("/")
    public String formIndex() {
        return IndexForm.getForm();
    }

}
