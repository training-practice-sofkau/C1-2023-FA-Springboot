package co.com.sofka.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String id;
    private String name;
    private String coach;
    private Integer level;
    private Instant lastUpdated;
    private List<StudentDTO> studentListDTO;
}
