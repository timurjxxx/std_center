package com.backend.studycenter.common.service.course;

import com.backend.studycenter.common.dto.course.TechnologyDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.course.TechnologyMapper;
import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.course.Technology;
import com.backend.studycenter.common.repository.course.TechnologyRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnologyService {
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private CourseService courseService;

    public ArrayList<Technology> getTechnologies() {
        return (ArrayList<Technology>) technologyRepository.findAll();
    }

    public ArrayList<Technology> getTechnologiesByCourseId(Long courseId) throws EntityNotFoundException {
        ArrayList<Technology> technologies = new ArrayList<>();
        Course course = courseService.getCourseById(courseId);
        for (Technology technology : course.getTechnologies()) {
            technologies.add(technology);
        }
        return technologies;
    }

    public Technology getTechnologyById(Long technologyId) throws EntityNotFoundException {
        Technology technology = technologyRepository.findById(technologyId)
                .orElseThrow(() -> new EntityNotFoundException("Not found technology with id = " + technologyId));
        return technology;
    }

    public Technology addTechnology(TechnologyDTO technologyDTO) {
        return technologyRepository.save(TechnologyMapper.toModel(technologyDTO));
    }

    public void deleteTechnology(Long technologyId) throws EntityNotFoundException {
        Technology technology = technologyRepository.findById(technologyId)
                .orElseThrow(() -> new EntityNotFoundException("Not found technology with id = " + technologyId));
        technologyRepository.delete(technology);
    }

    public Technology updateTechnology(TechnologyDTO technologyDTO, Long technologyId) throws EntityNotFoundException {
        Optional<Technology> technologyOptional = technologyRepository.findById(technologyId);
        if (technologyOptional.isEmpty()) {
            throw new EntityNotFoundException("Not found technology with id = " + technologyId);
        } else {
            Technology technology = technologyOptional.get();
            return technologyRepository.save(TechnologyMapper.toModel(technologyDTO, technology));
        }
    }

    public void clear() {
        technologyRepository.deleteAll();
    }
}

