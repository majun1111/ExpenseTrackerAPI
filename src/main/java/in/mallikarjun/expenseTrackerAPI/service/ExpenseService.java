package in.mallikarjun.expenseTrackerAPI.service;

import in.mallikarjun.expenseTrackerAPI.dto.ExpenseDTO;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;


public interface ExpenseService {

   List<ExpenseDTO> getAllExpenses(Pageable page);

   ExpenseDTO getExpenseById(String expenseId);

   void deleteExpenseById(String expenseId);

   ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO);

   ExpenseDTO updateExpenseDetails(String expenseId, ExpenseDTO expenseDTO);

   List<ExpenseDTO> readByCategory(String category, Pageable page);

   List<ExpenseDTO> readByName(String keyword, Pageable page);

   List<ExpenseDTO> readByDate(Date startDate, Date endDate, Pageable page);
}