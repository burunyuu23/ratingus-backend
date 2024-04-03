package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dnlkk.ratingusbackend.api.model.LessonDto;
import ru.dnlkk.ratingusbackend.api.model.MagazineDto;

import java.util.List;

@Tag(name = "Контроллер журнала", description = "Просмотр и редактирование журнала")
@RequestMapping("/magazine")
public interface MagazineApi { //todo: сделать проставление оценки, посещаемости, темы
    //todo: понять, где будут создаваться уроки
    @GetMapping("/users")
    ResponseEntity<List<MagazineDto>> getMagazineWithUsers(
            @RequestParam(required = true) String stringClassName,
            @RequestParam(required = true) String stringLessonName
    );

    @GetMapping("/lessons")
    ResponseEntity<List<LessonDto>> getMagazineWithLessons(
            @RequestParam(required = true) String stringClassName,
            @RequestParam(required = true) String stringLessonName
    );
}
