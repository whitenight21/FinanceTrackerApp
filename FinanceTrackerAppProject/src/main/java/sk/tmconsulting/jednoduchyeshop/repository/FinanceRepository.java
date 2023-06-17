package sk.tmconsulting.jednoduchyeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tmconsulting.jednoduchyeshop.model.Financialrecord;

@Repository
public interface FinanceRepository extends JpaRepository<Financialrecord, Long> {
}
// 19. Add: Extentds JpaRepository  and then the components that are to go to the database
// 20. Add package "service" / which is the list of methods of the app logic.
// 21. Create interface ITrickoService
    // Go to : service.ITrickoService
