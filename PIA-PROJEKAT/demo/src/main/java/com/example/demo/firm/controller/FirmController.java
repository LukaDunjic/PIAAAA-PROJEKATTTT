package com.example.demo.firm.controller;

import com.example.demo.PIAResponse;
import com.example.demo.firm.model.FirmEntity;
import com.example.demo.firm.service.FirmService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/firm")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FirmController {
    final FirmService firmService;

    @GetMapping(value = "/allFirms")
    public PIAResponse<List<FirmEntity>> getAllFirms() {
        return firmService.getAllFirms();
    }

    @GetMapping(value = "/getFirmById/{firmId}")
    public PIAResponse<FirmEntity> getFirmById(@PathVariable Long firmId) {
        return firmService.getFirmById(firmId);
    }

    @GetMapping(value = "/getFirmByName/{name}")
    public PIAResponse<FirmEntity> getFirmByName(@PathVariable String name) {
        return firmService.getFirmByName(name);
    }

    @GetMapping(value = "/getFirmsByAddress/{address}")
    public PIAResponse<List<FirmEntity>> getFirmsByAddress(@PathVariable String address) {
        return firmService.getAllFirmByAddress(address);
    }

    @PostMapping(value = "/addFirm")
    public PIAResponse<FirmEntity> addFirm(@RequestBody FirmEntity firmEntity) {
        return firmService.addFirm(firmEntity);
    }

}
