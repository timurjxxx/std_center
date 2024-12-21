package com.backend.studycenter.sc_trainerize.service.material;


import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.model.material.VideoServer;
import com.backend.studycenter.sc_trainerize.repository.material.VideoServerRepository;
import com.backend.studycenter.sc_trainerize.util.ImageUtil;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoServerService {
    @Autowired
    VideoServerRepository videoServerRepository;

    public Optional<VideoServer> findById(Long id) {
        return videoServerRepository.findById(id);
    }

    public VideoServer upload(MultipartFile file) throws IOException {
        VideoServer videoServer = new VideoServer();
        videoServer.setName(file.getOriginalFilename());
        videoServer.setType(file.getContentType());
        videoServer.setVideoData(ImageUtil.compressImage(file.getBytes()));
        return videoServerRepository.save(videoServer);
    }

    public byte[] download(Long id) throws EntityNotFoundException {
        VideoServer videoServer = videoServerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("VideoServer not found with id=" + id));
        return ImageUtil.decompressImage(videoServer.getVideoData());
    }

    public void deleteById(Long id) throws EntityNotFoundException {
        VideoServer videoServer = videoServerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("VideoServer not found with id=" + id));
        videoServerRepository.delete(videoServer);
    }
}

