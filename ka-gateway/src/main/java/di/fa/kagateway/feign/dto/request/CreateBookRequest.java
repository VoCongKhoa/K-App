package di.fa.kagateway.feign.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @JsonProperty("book_description")
    private String bookDescription;
    @NotBlank
    @JsonProperty("book_grade")
    private String bookGrade;
    @NotBlank
    @JsonProperty("book_name")
    private String bookName;
    @NotBlank
    @JsonProperty("book_type")
    private String bookType;
    @NotBlank
    @JsonProperty("book_volume")
    private String bookVolume;

    public static void main(String[] args) {
        int[] numbersTest = {2, 4, 3, 5, 7, 8, 9};
        int[] numbersWithDuplicates = {2, 4, 3, 5, 6, -2, 4, 7, 8, 9};
        printPairsUsingSet(numbersTest, 7);
        printPairsUsingSet(numbersWithDuplicates, 7);

    }

    public static void printPairsUsingSet(int[] numbers, int n) {
        if (numbers.length < 2) {
            return;
        }
        Set set = new HashSet(numbers.length);
        for (int value : numbers) {
            int target = n - value; // if target number is not in set then add
            if (!set.contains(target)) {
                set.add(value);
            } else {
                System.out.printf("(%d, %d) %n", value, target);
            }
        }
    }

}
