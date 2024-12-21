package com.backend.studycenter;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.backend.studycenter.common.controller.course.CourseController;
import com.backend.studycenter.common.service.course.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest(classes = StudyCenterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CourseController2IT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CourseController courseController;
    @Autowired
    private CourseService courseService;

    @BeforeEach
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(courseController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

//    @Test
//    @Disabled
//    public void getCoursesTest() throws Exception {
//        mvc.perform(get("/api/v1/courses/{id}", 2L)
//                .contentType(APPLICATION_JSON))
//                .andExpect(content().contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(equalTo("Course2"))))
//                .andExpect(jsonPath("$.duration", is(equalTo(10))))
//                .andExpect(jsonPath("$.price", is(equalTo(200.00))))
//                .andReturn();
//    }

//    @Test
//    @Disabled
//    public void getNonExistingCoursesTest() throws Exception {
//        mvc.perform(get("/api/v1/courses/{id}", Long.MAX_VALUE))
//                .andExpect(status().isNotFound())
//                .andExpect(status().reason(containsString("Could not find entity with id!")))
//        ;
//    }


}
