package com.backend.studycenter.sc_trainerize.dto.material;

import com.backend.studycenter.sc_trainerize.model.material.Image;
import com.backend.studycenter.sc_trainerize.model.material.Video;
import java.util.List;

public class MaterialDTO {
    private Long id;

    private String title;

    private String description;

    private List<Image> images;

    private List<Video> videoServers;

    public MaterialDTO() {
    }

    public MaterialDTO(Long id, String title, String description, List<Image> images, List<Video> videoServers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.images = images;
        this.videoServers = videoServers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Video> getVideoServers() {
        return videoServers;
    }

    public void setVideoServers(List<Video> videoServers) {
        this.videoServers = videoServers;
    }
}
