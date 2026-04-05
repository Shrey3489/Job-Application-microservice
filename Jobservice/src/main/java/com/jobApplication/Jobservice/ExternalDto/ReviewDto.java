package com.jobApplication.Jobservice.ExternalDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private String title;
    private String description;
    private double rating;
}
