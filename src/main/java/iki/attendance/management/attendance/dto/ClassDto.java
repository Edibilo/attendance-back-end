package iki.attendance.management.attendance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ClassDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String stream;

    public ClassDto(Long id, String name, String stream) {
        this.id = id;
        this.name = name;
        this.stream = stream;
    }

    private ClassDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.trim().toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStream() {
        return stream.trim().toUpperCase();
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
}
