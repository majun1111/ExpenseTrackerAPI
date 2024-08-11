package in.mallikarjun.expenseTrackerAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private String categoryId;
    private String name;
    private String description;
    private String categoryIcon;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private UserDTO user;

}
