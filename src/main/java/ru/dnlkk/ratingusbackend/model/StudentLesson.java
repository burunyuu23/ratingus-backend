package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dnlkk.ratingusbackend.model.enums.Attendance;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_lesson")
public class StudentLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mark")
    private String mark;

    @Size(max = 1000)
    @Column(name = "note", length = 1000, columnDefinition = "varchar(1000)")
    private String note;

    @Column(name = "attendance")
    private Attendance attendance;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private UserRole student;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;
}