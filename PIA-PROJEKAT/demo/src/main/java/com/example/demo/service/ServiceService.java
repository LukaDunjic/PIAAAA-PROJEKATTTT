package com.example.demo.service;

import com.example.demo.PIAResponse;
import com.example.demo.firm.model.FirmEntity;
import com.example.demo.firm.repository.FirmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {
    final ServiceRepository serviceRepository;
    final FirmRepository firmRepository;
    PIAResponse<List<ServiceDto>> getAllServiceForFirm(Long firmId) {
        List<ServiceEntity> serviceEntities = serviceRepository.getAllServiceForFirm(firmId);
        List<ServiceDto> serviceDtos = new ArrayList<>();
        serviceEntities.forEach(elem->{
            ServiceDto serviceDto = new ServiceDto();
            serviceDto.setDescription(elem.getDescription());
            serviceDto.setId(elem.getId());
            serviceDtos.add(serviceDto);
        });
        PIAResponse<List<ServiceDto>> piaRespone = new PIAResponse<>();
        piaRespone.setData(serviceDtos);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    PIAResponse<ServiceEntity> addService(ServiceEntity service){
        FirmEntity firm = firmRepository.getFirmById(service.getFirm().getFirmId());
        service.setFirm(firm);
        ServiceEntity serviceEntity = serviceRepository.save(service);

        PIAResponse<ServiceEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(serviceEntity);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }
}
