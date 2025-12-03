package org.first_lab.secureapi.repository;

import org.first_lab.secureapi.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByUserLogin(String userLogin);
    Optional<UserAccount> findByUserLogin(String userLogin);
}
