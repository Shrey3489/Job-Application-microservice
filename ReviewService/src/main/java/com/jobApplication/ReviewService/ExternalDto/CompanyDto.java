package com.jobApplication.ReviewService.ExternalDto;

public class CompanyDto
{
    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public CompanyDto() {
    }

    public CompanyDto(String description, String name, Long id) {
        this.description = description;
        this.name = name;
        this.id = id;
    }
}
