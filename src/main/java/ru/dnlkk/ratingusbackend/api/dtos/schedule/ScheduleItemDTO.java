package ru.dnlkk.ratingusbackend.api.dtos.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.TeacherDto;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ScheduleItemDTO {
    private int timetableNumber;
    private int teacherSubjectId;
    private TeacherDto teacher;
    private String subject;
    private Timestamp startTime;
    private Timestamp endTime;
}
