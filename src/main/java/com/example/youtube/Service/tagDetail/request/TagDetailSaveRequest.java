package com.example.youtube.Service.tagDetail.request;

import com.example.youtube.model.Tag;
import com.example.youtube.model.Video;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDetailSaveRequest {
    private String tag;

}
