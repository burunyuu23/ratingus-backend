package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.StudentLesson;

@Repository
public interface StudentLessonRepository  extends JpaRepository<StudentLesson, Integer> {
    StudentLesson findByLessonIdAndStudentId(int id, int id1);
}