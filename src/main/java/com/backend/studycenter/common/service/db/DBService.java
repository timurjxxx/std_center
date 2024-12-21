package com.backend.studycenter.common.service.db;

import com.backend.studycenter.common.dto.course.CourseDTO;
import com.backend.studycenter.common.dto.course.LessonDTO;
import com.backend.studycenter.common.dto.course.TechnologyDTO;
import com.backend.studycenter.common.dto.person.PersonDTO;
import com.backend.studycenter.common.dto.person.StudentDTO;
import com.backend.studycenter.common.dto.person.TeacherDTO;
import com.backend.studycenter.common.dto.security.RoleDTO;
import com.backend.studycenter.common.dto.security.UserDTO;
import com.backend.studycenter.common.mapper.course.LessonMapper;
import com.backend.studycenter.common.mapper.course.TechnologyMapper;
import com.backend.studycenter.common.mapper.security.RoleMapper;
import com.backend.studycenter.common.model.course.Lesson;
import com.backend.studycenter.common.model.course.Technology;
import com.backend.studycenter.common.model.person.Person;
import com.backend.studycenter.common.service.course.CourseService;
import com.backend.studycenter.common.service.course.LessonService;
import com.backend.studycenter.common.service.course.TechnologyService;
import com.backend.studycenter.common.service.education.CalendarLessonService;
import com.backend.studycenter.common.service.education.GroupService;
import com.backend.studycenter.common.service.education.WeekDayAndTimeService;
import com.backend.studycenter.common.service.person.InterviewService;
import com.backend.studycenter.common.service.person.PersonService;
import com.backend.studycenter.common.service.person.StudentService;
import com.backend.studycenter.common.service.person.TeacherService;
import com.backend.studycenter.common.service.security.RoleService;
import com.backend.studycenter.common.service.security.UserService;
import com.backend.studycenter.configs.redis.Country;
import com.backend.studycenter.configs.redis.CountryService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private CalendarLessonService calendarLessonService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private WeekDayAndTimeService weekDayAndTimeService;
    @Autowired
    private InterviewService interviewService;
    @Autowired
    private PersonService personService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public void clearData() {
        calendarLessonService.clear();
        groupService.clear();
        weekDayAndTimeService.clear();
        technologyService.clear();
        lessonService.clear();
        courseService.clear();
        interviewService.clear();
        personService.clear();
        teacherService.clear();
        studentService.clear();
        userService.clear();
        roleService.clear();
    }

    public void initTestData() throws Exception {
        countryService.addCountry(new Country(1L, "Country1"));
        countryService.addCountry(new Country(2L, "Country2"));
        countryService.addCountry(new Country(3L, "Country3"));
        countryService.addCountry(new Country(4L, "Country4"));

        lessonService.addLesson(new LessonDTO(1L, "Lesson1", "Desc of Lesson1"));
        lessonService.addLesson(new LessonDTO(2L, "Lesson2", "Desc of Lesson2"));
        lessonService.addLesson(new LessonDTO(3L, "Lesson3", "Desc of Lesson3"));
        lessonService.addLesson(new LessonDTO(4L, "Lesson4", "Desc of Lesson4"));
        ArrayList<LessonDTO> lessonDTOs = new ArrayList<>();
        for (Lesson lesson : lessonService.getLessons()) {
            lessonDTOs.add(LessonMapper.toDTO(lesson));
        }
        technologyService.addTechnology(new TechnologyDTO(1L, "Technology1", "Desc of Technology1"));
        technologyService.addTechnology(new TechnologyDTO(2L, "Technology2", "Desc of Technology2"));
        technologyService.addTechnology(new TechnologyDTO(3L, "Technology3", "Desc of Technology3"));
        ArrayList<TechnologyDTO> technologyDTOs = new ArrayList<>();
        for (Technology technology : technologyService.getTechnologies()) {
            technologyDTOs.add(TechnologyMapper.toDTO(technology));
        }
        courseService.addCourse(new CourseDTO(1L, "Course1", "Description of Course1", 3, new BigDecimal(1000), lessonDTOs, technologyDTOs));
        teacherService.addTeacher(new TeacherDTO(1L, "Teacher1", "Desc of Teacher1", "Address of Teacher1", LocalDate.of(1990, 5, 21), LocalDate.of(2023, 5, 1), "+998931111111", "email1@gmail.com", "@TGUser1", "Math"));
        teacherService.addTeacher(new TeacherDTO(2L, "Teacher2", "Desc of Teacher2", "Address of Teacher2", LocalDate.of(1986, 9, 14), LocalDate.of(2023, 4, 21), "+998932222222", "email2@gmail.com", "@TGUser2", "IT"));
        studentService.addStudent(new StudentDTO(3L, "Student1", "Desc of Student1", "Address of Student1", LocalDate.of(2000, 9, 14), LocalDate.of(2023, 3, 1), "+998933333333", "email3@gmail.com", "@TGUser3", 2, "IT", "IELTS 7", "", null));
        studentService.addStudent(new StudentDTO(4L, "Student2", "Desc of Student2", "Address of Student2", LocalDate.of(1999, 7, 15), LocalDate.of(2023, 5, 11), "+998934444444", "emai42@gmail.com", "@TGUser4", 1, "IT", "IELTS 6", "", null));
        studentService.addStudent(new StudentDTO(5L, "Student3", "Desc of Student3", "Address of Student3", LocalDate.of(2002, 5, 4), LocalDate.of(2023, 4, 13), "+998935555555", "email5@gmail.com", "@TGUser5", 3, "IT", "IELTS 6.5", "", null));
        studentService.addStudent(new StudentDTO(6L, "Student4", "Desc of Student4", "Address of Student4", LocalDate.of(2003, 11, 22), LocalDate.of(2023, 3, 24), "+998936666666", "email6@gmail.com", "@TGUser6", 1, "IT", "IELTS 5.5", "", null));
        personService.addPerson(new PersonDTO(7L, "Person7", "Desc of Person1", "Address of Person1", LocalDate.of(1987, 9, 19), LocalDate.of(2023, 5, 11), "+998937777777", "email7@gmail.com", "@TGUser7"));
        personService.addPerson(new PersonDTO(8L, "Person8", "Desc of Person2", "Address of Person2", LocalDate.of(1987, 9, 20), LocalDate.of(2023, 5, 12), "+998938888888", "email8@gmail.com", "@TGUser8"));
        personService.addPerson(new PersonDTO(9L, "Person9", "Desc of Person3", "Address of Person3", LocalDate.of(1987, 9, 21), LocalDate.of(2023, 5, 13), "+998939999999", "email9@gmail.com", "@TGUser9"));
        roleService.addRole(new RoleDTO(1L, "VIEWER"));
        roleService.addRole(new RoleDTO(2L, "EDITOR"));
        roleService.addRole(new RoleDTO(3L, "ADMIN"));
        roleService.addRole(new RoleDTO(4L, "PAYMENT"));
        roleService.addRole(new RoleDTO(5L, "STUDENT"));
        roleService.addRole(new RoleDTO(6L, "TEACHER"));
        roleService.addRole(new RoleDTO(7L, "ASSISTANT"));
        Person person1 = personService.getPersonById(7L);
        Person person2 = personService.getPersonById(8L);
        Person person3 = personService.getPersonById(9L);
        ArrayList<RoleDTO> roleDTOs1 = new ArrayList<>(); //VIEWER + TEACHER
        roleDTOs1.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(1L)));
        roleDTOs1.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(6L)));
        ArrayList<RoleDTO> roleDTOs2 = new ArrayList<>(); //VIEWER + EDITOR
        roleDTOs2.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(1L)));
        roleDTOs2.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(2L)));
        ArrayList<RoleDTO> roleDTOs3 = new ArrayList<>(); //VIEWER + EDITOR + ADMIN + PAYMENT
        roleDTOs3.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(1L)));
        roleDTOs3.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(2L)));
        roleDTOs3.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(3L)));
        roleDTOs3.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(4L)));
        userService.addUser(new UserDTO(1L, "user", "user", true, roleDTOs1, LocalDateTime.of(2023, 6, 01, 00, 00), LocalDateTime.of(2023, 12, 1, 00, 00), person1));
        userService.addUser(new UserDTO(2L, "editor", "editor", true, roleDTOs2, LocalDateTime.of(2023, 6, 02, 00, 00), LocalDateTime.of(2023, 12, 2, 00, 00), person2));
        userService.addUser(new UserDTO(3L, "admin", "admin", true, roleDTOs3, LocalDateTime.of(2023, 6, 03, 00, 00), LocalDateTime.of(2023, 12, 3, 00, 00), person3));
    }
}
