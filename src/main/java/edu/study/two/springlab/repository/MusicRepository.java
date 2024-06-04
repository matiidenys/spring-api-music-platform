package edu.study.two.springlab.repository;

import edu.study.two.springlab.model.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends MongoRepository<Music, String> {

}