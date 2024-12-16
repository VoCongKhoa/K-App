package di.fa.kagateway.feign.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookRequest {

    @JsonProperty("book_name")
    private String bookName;
    @JsonProperty("book_grade")
    private String bookGrade;
    @JsonProperty("book_type")
    private String bookType;
    @NotBlank
    @JsonProperty("page_number")
    private String pageNumber;
    @NotBlank
    @JsonProperty("page_size")
    private String pageSize;
    @JsonProperty("sort_by")
    private String sortBy;
    @JsonProperty("sort_direction")
    private String sortDirection;

}
