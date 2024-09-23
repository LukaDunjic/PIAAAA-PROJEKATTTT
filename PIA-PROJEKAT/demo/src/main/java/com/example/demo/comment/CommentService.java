package com.example.demo.comment;

import com.example.demo.PIAResponse;
import com.example.demo.appointment.AppointmentEntity;
import com.example.demo.appointment.AppointmentRepository;
import com.example.demo.unregistared.MainPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    final CommentRepository commentRepository;
    final AppointmentRepository appointmentRepository;
    public PIAResponse<CommentDto> addComment(CommentEntity commentEntity) {
        AppointmentEntity appointment = appointmentRepository.getReferenceById(commentEntity.getAppointment().getId());
        commentEntity.setAppointment(appointment);

        CommentEntity savedComment = commentRepository.save(commentEntity);

        // Pripremi DTO za odgovor
        CommentDto dto = new CommentDto();
        dto.setId(Long.valueOf(savedComment.getId()));
        dto.setRating(savedComment.getRating());
        dto.setComment(savedComment.getComment());
        dto.setAppointmentId(savedComment.getAppointment().getId());

        PIAResponse<CommentDto> response = new PIAResponse<>();
        response.setData(dto);
        response.setStatus("ok");
        response.setMessage("Success");

        return response;
    }

    public PIAResponse<List<CommentDto>> getAllCommentsByFirm(Long firmId){
        List<CommentEntity> comments = commentRepository.getAllCommentsForFirm(firmId);
        List<CommentDto> commentDtos = comments.stream().map(comment -> {
            CommentDto dto = new CommentDto();
            dto.setId(Long.valueOf(comment.getId()));
            dto.setRating(comment.getRating());
            dto.setComment(comment.getComment());
            dto.setAppointmentName(String.valueOf(comment.getAppointment().getGarden().getType()));  // Primer
            return dto;
        }).toList();



        PIAResponse<List<CommentDto>> piaRespone = new PIAResponse<>();
        piaRespone.setData(commentDtos);
        piaRespone.setStatus("ok");
        piaRespone.setMessage("Success");

        return piaRespone;
    }
}
