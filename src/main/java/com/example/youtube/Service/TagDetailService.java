package com.example.youtube.Service;

import com.example.youtube.model.TagDetail;
import com.example.youtube.repository.TagDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagDetailService {
    @Autowired
    private TagDetailRepository tagDetailRepository;
    public List<TagDetail> findAll(){
        return tagDetailRepository.findAll();
    }
    public TagDetail findById(int id){
        return tagDetailRepository.findById(id).get();
    }
    public TagDetail create(TagDetail tagDetail){
        return tagDetailRepository.save(tagDetail);
    }
}
