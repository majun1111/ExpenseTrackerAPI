package in.mallikarjun.expenseTrackerAPI.service;

import in.mallikarjun.expenseTrackerAPI.entity.Expense;
import in.mallikarjun.expenseTrackerAPI.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface ExpenseService {
    Page<Expense> getAllExpenses(Pageable page);

   Expense getExpenseById(long id) throws ResourceNotFoundException;

   void deleteExpenseById(long id);

   Expense saveExpenseDetails(Expense expense);

   Expense updateExpenseDetails(Expense expense, long id);

   List<Expense> readByCategory(String catergory, Pageable pageable);

   List<Expense> readByName(String keyword, Pageable pageable);

   List<Expense> readByDate(Date startDate, Date endDate, Pageable pageable);
}
