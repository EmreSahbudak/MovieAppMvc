package com.bilgeadam.service;

import com.bilgeadam.entity.Comment;
import com.bilgeadam.repository.ICommentRepository;
import com.bilgeadam.utility.ICrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements ICrudService<Comment, Integer> {

    private final ICommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Iterable<Comment> saveAll(Iterable<Comment> t) {
        return commentRepository.saveAll(t);
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public Comment delete(Integer integer) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()){
            return optionalComment;
        }else {
            throw new NotFoundException("Böyle bir yorum bulunamadı");
        }
    }

    public List<Comment> findByMovieId(Integer id){
        return commentRepository.findByMovieId(id);
    }

    public List<Comment> findByMovieIdAndDateBetween(Integer movieId, String start, String end){
        LocalDate date1 = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate date2 = LocalDate.parse(end, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return commentRepository.findByMovieIdAndDateBetween(movieId, date1, date2);
    }

    public List<Comment> findByUserId(Integer uid) {
        return commentRepository.findByUserId(uid);
    }

    public List<Comment> findAllByUserIdAndDateBetween(Integer uid, String start, String end) {
        LocalDate date1 = LocalDate.parse(start);
        LocalDate date2 = LocalDate.parse(end);
        return commentRepository.findAllByUserIdAndDateBetween(uid, date1, date2);
    }


}
