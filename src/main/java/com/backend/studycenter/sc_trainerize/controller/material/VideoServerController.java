package com.backend.studycenter.sc_trainerize.controller.material;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.service.material.VideoServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(path = "api/v1/video_server")
public class VideoServerController {
    @Autowired
    VideoServerService videoServerService;

    @PostMapping(value = "/upload", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Void> uploadVideo(@RequestParam("video") MultipartFile file) {
        try {
            videoServerService.upload(file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<byte[]> downloadVideo(@PathVariable(name = "id") Long id) {
        try {
            byte[] video = videoServerService.download(id);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(
                    videoServerService.findById(id).get().getType())).body(video);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable(name = "id") Long id) {
        try {
            videoServerService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
