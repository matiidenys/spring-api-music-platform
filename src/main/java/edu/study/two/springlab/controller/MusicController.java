package edu.study.two.springlab.controller;


import edu.study.two.springlab.model.Music;
import edu.study.two.springlab.service.impls.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
public class MusicController {
    @Autowired
    private MusicService service;

    @GetMapping("/get")
    private List<Music> showAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    private Music showOne(@PathVariable String id) {
        return service.get(id);
    }

    @GetMapping("/search/{name}")
    private List<Music> findAllByName(@PathVariable String name) {
        return service.getAllByString(name);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping(value = "/create")
    private String createOne(@RequestParam("name") String name,
                             @RequestParam("data") MultipartFile data) throws IOException {
        return service.create(name, data);
    }

    @PutMapping("/update/{id}")
    private Music updateOne(@PathVariable String id, @RequestBody Music music) {
        music.setId(id);

        return service.update(music);
    }
}
