package in.mallikarjun.expenseTrackerAPI.service;

import in.mallikarjun.expenseTrackerAPI.entity.Expense;
import in.mallikarjun.expenseTrackerAPI.exceptions.ResourceNotFoundException;
import in.mallikarjun.expenseTrackerAPI.repository.ExpenseRepository;
import jakarta.websocket.OnClose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{

@Autowired
private ExpenseRepository expenseRepo;

@Autowired
private UserService userService;
    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepo.findAll(page);
    }

    @Override
    public Expense getExpenseById(long id){
        Optional<Expense> expense= expenseRepo.findById(id);
        if(expense.isPresent()){
            return expense.get();
        }else {
            throw new ResourceNotFoundException("Expense is not found for the id "+id);
        }
    }

    @Override
    public void deleteExpenseById(long id) {

        Expense expense = getExpenseById(id);
        expenseRepo.delete(expense);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
       return expenseRepo.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Expense expense, long id) {
        Expense existingExpense=getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        return expenseRepo.save(existingExpense);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable pageable) {
       return expenseRepo.findByUserIdAndCategory(userService.getLoggedInUser().getUserId(),category, pageable).toList();
    }



    @Override
    public List<Expense> readByName(String keyword, Pageable page) {
        return expenseRepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), keyword, page).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable pageable) {
        if (startDate == null) {
            startDate =new Date(0);
        }
        if (endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }
        return expenseRepo.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(),startDate,endDate,pageable).toList();
    }
}
