package com.backend.studycenter.sc_trainerize.dto.topic;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {
    private Long id;
    private String title;
    private String content;

    private AuthorDTO authorDTO;

    private List<ContentDTO> contentDTOS;


}
