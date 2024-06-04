package edu.study.two.springlab.service.impls;
import edu.study.two.springlab.exception.ApiRequestException;
import edu.study.two.springlab.model.Music;
import edu.study.two.springlab.repository.MusicRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {
    @Autowired
    private MusicRepository repository;

    public String create(String name, MultipartFile file) throws IOException {
        Music music = new Music(name);
        music.setData(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        music.setCreatedAt(LocalDateTime.now());
        music.setUpdatedAt(LocalDateTime.now());
        music = repository.insert(music);
        return music.getId();
    }


    public Music get(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiRequestException("No records found with " + id + " id"));
    }

    public List<Music> getAllByString(String str){
        return this.getAll().stream()
                .filter(music -> music.getName().toLowerCase().contains(str.toLowerCase()))
                .collect(Collectors.toList());
    }


    public Music update(Music music) {
        music.setUpdatedAt(LocalDateTime.now());
        return repository.save(music);
    }


    public void delete(String id) {
        repository.deleteById(id);
    }


    public List<Music> getAll() {
        return repository.findAll();
    }
}
