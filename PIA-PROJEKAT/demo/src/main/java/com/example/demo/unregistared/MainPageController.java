package com.example.demo.unregistared;

import com.example.demo.PIAResponse;
import com.example.demo.user.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mainPage")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MainPageController {
    final MainService mainService;

    @GetMapping(value = "/unregistered")
    public PIAResponse<MainPageDTO> getInfoUnregistered() {
        return mainService.getMainPageData();
    }

}
