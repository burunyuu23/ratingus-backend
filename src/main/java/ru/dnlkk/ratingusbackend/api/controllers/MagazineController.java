package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.MagazineApi;
import ru.dnlkk.ratingusbackend.api.dtos.LessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.GradeDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.LessonCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.MagazineDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.LessonUpdateDto;
import ru.dnlkk.ratingusbackend.model.Lesson;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.service.MagazineService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MagazineController implements MagazineApi {
    private final MagazineService magazineService;

    @Override
    public ResponseEntity<MagazineDto> getMagazineWithUsers(UserDetailsImpl user, Integer classId, Integer teacherSubjectId) {
        return ResponseEntity.ok(magazineService.getMagazineWithUsers(user, classId, teacherSubjectId));
    }

    @Override
    public ResponseEntity createUserGrade(UserDetailsImpl user, GradeDto gradeDto) {
        magazineService.createUserGrade(gradeDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<LessonDto>> getMagazineWithLessons(UserDetailsImpl user, Integer classId, Integer teacherSubjectId) {
        return ResponseEntity.ok(magazineService.getLessons(classId, teacherSubjectId));
    }

    @Override
    public ResponseEntity createLesson(UserDetailsImpl user, LessonCreateDto lessonCreateDto) {
        magazineService.createLesson(lessonCreateDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<LessonCreateDto>> updateLesson(UserDetailsImpl user, LessonUpdateDto lessonCreateDto) {
        magazineService.updateLesson(lessonCreateDto);
        return ResponseEntity.noContent().build();
    }
}