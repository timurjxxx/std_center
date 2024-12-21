package com.backend.studycenter.sc_trainerize.dto.material;


public class ImageDTO {
    private Long id;

    private String fileOriginalName;

    private long size;

    private String contentType;

    public ImageDTO() {
    }

    public ImageDTO(Long id, String fileOriginalName, long size, String contentType) {
        this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.size = size;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileOriginalName() {
        return fileOriginalName;
    }

    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
