package com.backend.studycenter.sccontrol.dto;

import com.backend.studycenter.common.dto.person.PersonDTO;
import com.backend.studycenter.common.model.person.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.backend.studycenter.scpay.constant.AppConstants.DATE_FORMAT;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebinarDTO {
    private Long id;
    private String title;
    private PersonDTO speaker;
    private List<PersonDTO> participants;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDateTime date;
    private String description;
    private String location;
}
