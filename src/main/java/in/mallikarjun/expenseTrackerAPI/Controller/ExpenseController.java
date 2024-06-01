package in.mallikarjun.expenseTrackerAPI.Controller;

import in.mallikarjun.expenseTrackerAPI.entity.Expense;
import in.mallikarjun.expenseTrackerAPI.service.ExpenseService;
import in.mallikarjun.expenseTrackerAPI.service.ExpenseServiceImpl;
import in.mallikarjun.expenseTrackerAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;




    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).toList();// if you add toList it removes the pageable information
    }

        @GetMapping("/expenses/{id}")
        public Expense getExpenseById(@PathVariable long id){
            return expenseService.getExpenseById(id);
        }

        @ResponseStatus(value = HttpStatus.NO_CONTENT)
        @DeleteMapping("/expenses")
        public void deleteByExpenseById(@RequestParam long id){
            expenseService.deleteExpenseById(id);
        }

        @ResponseStatus(value = HttpStatus.CREATED)
        @PostMapping("/expenses")
        public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
            return expenseService.saveExpenseDetails(expense);
        }

        @PutMapping("/expenses/{id}")
        public Expense updateExpenseDetails(@RequestBody Expense expense,@PathVariable Long id){
            return expenseService.updateExpenseDetails(expense,id);
        }

        @GetMapping("/expenses/category")
        public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable pageable){
        return expenseService.readByCategory(category,pageable);
        }

        @GetMapping("/expenses/name")
        public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page) {
            return expenseService.readByName(keyword, page);
        }

        @GetMapping("/expenses/date")
        public List<Expense> getExpenseByDate(@RequestParam(required = false) Date startDate,
                                              @RequestParam(required = false) Date endDate,
                                              Pageable pageable){
        return expenseService.readByDate(startDate,endDate,pageable);
        }

    }

