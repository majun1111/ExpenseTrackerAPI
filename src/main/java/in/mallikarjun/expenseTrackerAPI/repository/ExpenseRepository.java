package in.mallikarjun.expenseTrackerAPI.repository;

import in.mallikarjun.expenseTrackerAPI.entity.Expense;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.sql.Date;

@Repository
public interface ExpenseRepository extends JpaRepository <Expense, Long>{

   //Select * From Tbl_expense where category=?
    Page<Expense> findByCategory(String Category, Pageable pageable);

  //Select * from tbl_expense where name like '%keyword%';
   Page<Expense> findByNameContaining(String keyword, Pageable pageable);

  //Select * from tbl_expense where date between 'startDate' and 'endDate';
   Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable pageable);


}
