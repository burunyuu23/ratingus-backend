package ru.dnlkk.ratingusbackend.service;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.api.dtos.claz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;
import ru.dnlkk.ratingusbackend.mapper.ClassMapper;
import ru.dnlkk.ratingusbackend.mapper.user_code.UserCodeMapper;
import ru.dnlkk.ratingusbackend.mapper.user_role.UserRoleMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminPanelService {
    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final UserCodeRepository userCodeRepository;
    private final TimetableRepository timetableRepository;
    private final SchoolRepository schoolRepository;

    public List<UserRoleDto> getAllUsersRolesForSchool(int schoolId) {
        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<UserRole> userRoles = school.get().getUserRoles();
            return UserRoleMapper.INSTANCE.toDtoList(userRoles); //todo;
        }
    }

    public List<UserCodeDto> getAllUsersCodesForSchool(int schoolId) {
        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<UserCode> userRoles = school.get().getUserCodes();
            return UserCodeMapper.INSTANCE.toUserCodeDtoList(userRoles); //todo;
        }
    }

    public UserCodeCreateDto createUserCode(UserCodeCreateDto userCodeCreateDto) {
        UserCode userCode = UserCodeMapper.INSTANCE.toUserCode(userCodeCreateDto);

        String userLogin = userCode.getUser().getLogin();

        List<User> userByLogin = userRepository.findByLogin(userLogin);
        if (userByLogin.size() > 1) {
            throw new RuntimeException("Обнаружены повторяющиеся логины!");
        }

        int id = userByLogin.getFirst().getId();
        userCode.getUser().setId(id);

        UUID uuid = Generators.nameBasedGenerator().generate(userLogin + id);

        System.out.println("Сгенерированный code: " + uuid.toString());

        userCode.setCode(uuid.toString());

        UserCode userCodeAfterSaving = userCodeRepository.saveAndFlush(userCode);
        UserCodeCreateDto userCodeCreateDtoAfterSaving =
                UserCodeMapper.INSTANCE.toUserCodeCreateDto(userCodeAfterSaving);

        return userCodeCreateDtoAfterSaving;
    }

    public List<ClassDto> getAllClassesForSchool(int schoolId) {
        List<Class> classesBySchoolId = classRepository.findClassesBySchoolId(schoolId);
        return ClassMapper.INSTANCE.toClassDtoList(classesBySchoolId);
    }

    public Class createClass(Class classEntity) {
        return classRepository.saveAndFlush(classEntity);
    }

    public List<Timetable> getTimetable() {
        return timetableRepository.findAll().stream().toList();
    }

    //todo: ApplicationDto - исправить
    public List<ApplicationDto> getAllApplications() {
        return null;
    }

    public ResponseEntity<ApplicationDto> createApplication(ApplicationDto applicationDto) {
        return null;
    }

    public ResponseEntity<Void> deleteApplication() {
        return null;
    }
}
