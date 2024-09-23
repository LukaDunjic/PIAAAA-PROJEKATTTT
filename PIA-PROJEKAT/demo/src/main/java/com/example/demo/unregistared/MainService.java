package com.example.demo.unregistared;

import com.example.demo.PIAResponse;
import com.example.demo.appointment.AppointmentRepository;
import com.example.demo.decoratedGarden.DecoratedGardenRepository;
import com.example.demo.decorator.DecoratorRepository;
import com.example.demo.firm.model.FirmEntity;
import com.example.demo.firm.repository.FirmRepository;
import com.example.demo.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {
    final DecoratedGardenRepository decoratedGardenRepository;
    final OwnerRepository ownerRepository;
    final DecoratorRepository decoratorRepository;
    final AppointmentRepository appointmentRepository;
    final FirmRepository firmRepository;

    public PIAResponse<MainPageDTO> getMainPageData() {
        MainPageDTO dto = new MainPageDTO();

        // Broj dekorisanih bašta
        dto.setTotalDecoratedGardens(decoratedGardenRepository.countDecoratedGardens());

        // Broj vlasnika
        dto.setTotalOwners(ownerRepository.countAllOwners());

        // Broj dekoratera
        dto.setTotalDecorators(decoratorRepository.numOfDecorators());

        // Broj zakazanih poslova u poslednjih 24h, 7 dana, i 30 dana
        dto.setAppointmentsLast24Hours(countAppointmentsInLastDays(1));
        dto.setAppointmentsLast7Days(countAppointmentsInLastDays(7));
        dto.setAppointmentsLast30Days(countAppointmentsInLastDays(30));

        // Lista firmi sa dekoraterima
        List<FirmEntity> firmEntityList = firmRepository.getAllFirms();
        List<FirmWithDecoratorsDTO> firmWithDecoratorsDTOList = new ArrayList<>();
        firmEntityList.forEach(elem->{
            FirmWithDecoratorsDTO firm = new FirmWithDecoratorsDTO();
            firm.setFirmEntity(elem);
            firm.setDecorators(decoratorRepository.getAllDecoratorForFirm(elem.getFirmId()));
            firmWithDecoratorsDTOList.add(firm);
        });
        dto.setFirms(firmWithDecoratorsDTOList);

//        // Galerija slika (pretpostavka da postoji metoda za dohvaćanje slika)
//        dto.setGallery(firmRepository.getRecentJobImages());

        PIAResponse<MainPageDTO> piaRespone = new PIAResponse<>();
        piaRespone.setData(dto);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    private Integer countAppointmentsInLastDays(int days) {
        LocalDateTime since = LocalDateTime.now().minusDays(days);
        return appointmentRepository.countByAppointmentDateAfter(since);
    }

}
