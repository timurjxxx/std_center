package com.backend.studycenter.sc_trainerize.dto.topic;

import com.backend.studycenter.sc_trainerize.model.material.Material;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {
    private Long id;
    private String title;
    private String description;
    private Date creationDate;
    private Date lastModifiedDate;
    private Material material;
}
