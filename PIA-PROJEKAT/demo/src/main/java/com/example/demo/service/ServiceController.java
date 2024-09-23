package com.example.demo.service;

import com.example.demo.PIAResponse;
import com.example.demo.comment.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ServiceController {
    final ServiceService serviceService;

    @GetMapping(value = "/getServicesForFirm/{firmId}")
    public PIAResponse<List<ServiceDto>> getAllCommentsByFirm(@PathVariable Long firmId){
        return serviceService.getAllServiceForFirm(firmId);
    }

    @PostMapping(value = "/addService")
    public PIAResponse<ServiceEntity> addService(@RequestBody ServiceEntity serviceEntity){
        return serviceService.addService(serviceEntity);
    }
}
