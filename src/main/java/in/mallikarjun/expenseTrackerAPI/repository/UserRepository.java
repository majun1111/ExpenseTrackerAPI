package in.mallikarjun.expenseTrackerAPI.repository;

import in.mallikarjun.expenseTrackerAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

}
