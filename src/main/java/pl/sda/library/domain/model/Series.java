package pl.sda.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Series {
    private Long idSeries;
    private String title;
    private String description;
}
