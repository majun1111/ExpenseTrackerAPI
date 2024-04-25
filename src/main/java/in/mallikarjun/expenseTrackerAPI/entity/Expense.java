package in.mallikarjun.expenseTrackerAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tbl_expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "expense_name")
    @NotBlank(message = "Expense must not be null")
    @Size(min = 3,message = "Expense name must be at least 3 character")
    private String name;

    private String description;

   @Column(name = "expense_amount")
   @NotNull(message = "Expense amount must not be null")
   private BigDecimal amount;

    @NotBlank(message = "Catergory must not be null")
    private String category;

    @NotNull(message = "Date must not be null")
    private Date date;

    @Column(name="created_at",nullable=false,updatable= false)
    @CreationTimestamp
    private Timestamp createAt;
    @Column(name = "updated_at")
    @CreationTimestamp
    private Timestamp updatedAt;

}
