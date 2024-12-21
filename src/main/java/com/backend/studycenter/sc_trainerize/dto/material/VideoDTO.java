package com.backend.studycenter.sc_trainerize.dto.material;

public class VideoDTO {
    private Long id;

    private String name;

    private String externalLink;

    public VideoDTO() {

    }

    public VideoDTO(Long id, String name, String externalLink) {
        this.id = id;
        this.name = name;
        this.externalLink = externalLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }
}
