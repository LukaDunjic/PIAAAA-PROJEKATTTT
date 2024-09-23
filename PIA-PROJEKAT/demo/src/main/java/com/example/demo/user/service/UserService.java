package com.example.demo.user.service;

import com.example.demo.PIAResponse;
import com.example.demo.decorator.DecoratorEntity;
import com.example.demo.decorator.DecoratorRepository;
import com.example.demo.firm.model.FirmEntity;
import com.example.demo.firm.repository.FirmRepository;
import com.example.demo.owner.OwnerEntity;
import com.example.demo.owner.OwnerRepository;
import com.example.demo.owner.UserWithStatusDto;
import com.example.demo.user.model.RegistrationStatus;
import com.example.demo.user.model.RequestRegistrationEntity;
import com.example.demo.user.model.UserEntity;
import com.example.demo.user.repository.RequestRegistrationRepository;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public PIAResponse<UserEntity> getUserById(Integer userId){
        UserEntity userEntity = userRepository.getUserById(userId);
        PIAResponse<UserEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(userEntity);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<UserEntity> loginUser(String username, String password){
        RequestRegistrationEntity check = registrationRepository.checkUserStatus(username);
        UserEntity userEntity = null;
        if(check == null)userEntity=userRepository.loginUser(username, password);
        PIAResponse<UserEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(userEntity);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    final RequestRegistrationRepository registrationRepository;
    public PIAResponse<UserEntity> registerUser(UserEntity userEntity){
        RequestRegistrationEntity check = registrationRepository.checkIfExistUser(userEntity.getUsername(), userEntity.getEmail());
        UserEntity user = null;
        if(check == null) user= userRepository.save(userEntity);
        if(check == null) registrationRepository.save(new RequestRegistrationEntity(null, user, RegistrationStatus.PENDING));
        PIAResponse<UserEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(user);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<UserEntity> changePassword(String username, String oldPassword, String newPassword){
        UserEntity userEntity = userRepository.loginUser(username, oldPassword);
        userEntity.setPassword(newPassword);
        UserEntity user = userRepository.save(userEntity);

        PIAResponse<UserEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(user);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<UserEntity> updateUser(UserEntity userEntity){
        UserEntity user = userRepository.loginUser(userEntity.getUsername(), userEntity.getPassword());
        userEntity.setUserId(user.getUserId());
        if(user == null){
            return null;
        }
        if(userEntity.getProfilePicture() == null)userEntity.setProfilePicture(user.getProfilePicture());
        userRepository.save(userEntity);
        PIAResponse<UserEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(userEntity);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    final DecoratorRepository decoratorRepository;
    final FirmRepository firmRepository;
    public PIAResponse<DecoratorEntity> addDecorator(Integer userId, Long firmId) {
        FirmEntity firmEntity = firmRepository.getFirmById(firmId);
        UserEntity userEntity = userRepository.getUserById(userId);
        DecoratorEntity decoratorEntity = new DecoratorEntity();
        decoratorEntity.setUser(userEntity);
        decoratorEntity.setFirm(firmEntity);
        DecoratorEntity decorator = decoratorRepository.save(decoratorEntity);
        RequestRegistrationEntity registration = registrationRepository.getRequestByUserIdR(userId);
        registration.setStatus(RegistrationStatus.APPROVED);
        RequestRegistrationEntity reg1 = registrationRepository.save(registration);

        PIAResponse<DecoratorEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(decorator);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    final OwnerRepository ownerRepository;
    public PIAResponse<List<UserWithStatusDto>> getAllOwners() {
        List<UserEntity> ownerEntities = ownerRepository.getAllOwners();

        List<UserWithStatusDto> userWithStatusDtos = new ArrayList<>();
        ownerEntities.forEach(elem->{
            UserWithStatusDto dto = new UserWithStatusDto();
            dto.setUser(elem);
            dto.setStatus(registrationRepository.getRequestByUserIdR(elem.getUserId()).getStatus());
            userWithStatusDtos.add(dto);
        });
        PIAResponse<List<UserWithStatusDto>> piaRespone = new PIAResponse<>();
        piaRespone.setData(userWithStatusDtos);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<List<DecoratorEntity>> getAllDecorators() {
        List<DecoratorEntity> decoratorEntities = decoratorRepository.getAllDecorators();

        PIAResponse<List<DecoratorEntity>> piaRespone = new PIAResponse<>();
        piaRespone.setData(decoratorEntities);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<RequestRegistrationEntity> approveRegistration(Integer userId) {
        RequestRegistrationEntity registration = registrationRepository.getRequestByUserIdR(userId);
        registration.setStatus(RegistrationStatus.APPROVED);
        RequestRegistrationEntity reg1 = registrationRepository.save(registration);

        PIAResponse<RequestRegistrationEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(reg1);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }

    public PIAResponse<RequestRegistrationEntity> rejectRegistration(Integer userId) {
        RequestRegistrationEntity registration = registrationRepository.getRequestByUserIdR(userId);
        registration.setStatus(RegistrationStatus.REJECTED);
        RequestRegistrationEntity reg1 = registrationRepository.save(registration);

        PIAResponse<RequestRegistrationEntity> piaRespone = new PIAResponse<>();
        piaRespone.setData(reg1);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }
}
