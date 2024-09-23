package com.example.demo.firm.service;

import com.example.demo.PIAResponse;
import com.example.demo.firm.model.FirmEntity;
import com.example.demo.firm.repository.FirmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FirmService {
    final FirmRepository firmRepository;

    public PIAResponse<List<FirmEntity>> getAllFirms() {
        List<FirmEntity> firmEntityList = firmRepository.getAllFirms();

        PIAResponse<List<FirmEntity>> piaRespone = new PIAResponse<>();
        piaRespone.setData(firmEntityList);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<FirmEntity> getFirmById(Long firmId) {
        FirmEntity firmEntity = firmRepository.getFirmById(firmId);

        PIAResponse<FirmEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(firmEntity);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<FirmEntity> getFirmByName(String name) {
        FirmEntity firmEntity = firmRepository.getFirmByName(name);

        PIAResponse<FirmEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(firmEntity);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<List<FirmEntity>> getAllFirmByAddress(String address) {
        List<FirmEntity> firmEntityList = firmRepository.getFirmByAddress(address);

        PIAResponse<List<FirmEntity>> piaRespone = new PIAResponse<>();
        piaRespone.setData(firmEntityList);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<FirmEntity> addFirm(FirmEntity firmEntity) {
        FirmEntity firm = firmRepository.save(firmEntity);

        PIAResponse<FirmEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(firm);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }
}
