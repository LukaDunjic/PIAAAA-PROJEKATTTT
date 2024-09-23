package com.example.demo.comment;

import com.example.demo.PIAResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {
    final CommentService commentService;

    @PostMapping(value = "/addComment")
    public PIAResponse<CommentDto> addComment(@RequestBody CommentEntity commentEntity){
        return commentService.addComment(commentEntity);
    }

    @GetMapping(value = "/getCommentsForFirm/{firmId}")
    public PIAResponse<List<CommentDto>> getAllCommentsByFirm(@PathVariable  Long firmId){
        return commentService.getAllCommentsByFirm(firmId);
    }
}
