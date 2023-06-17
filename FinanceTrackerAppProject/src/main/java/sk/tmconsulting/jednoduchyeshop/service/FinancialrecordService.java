package sk.tmconsulting.jednoduchyeshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sk.tmconsulting.jednoduchyeshop.model.Financialrecord;
import sk.tmconsulting.jednoduchyeshop.repository.FinanceRepository;

import java.util.Date;
import java.util.List;

// 24. add @Service
@Service
public class FinancialrecordService implements IFinancialrecordService {

// 26. add @Autowired to private TrickoRepository
    // Go to: controller.JednoduchyEshopController.java
// 24. add the private object TrickoRepository :
    @Autowired
    private FinanceRepository financeRepository;

// 23. write implements ITrickoService and add the overrides needed :

    @Override
    public Financialrecord saveRecord(Financialrecord financialrecordObjekt) {

// 25. add trickoRepository . save , to save the object

        return financeRepository.save(financialrecordObjekt);
    }

    @Override
    public void updateRecord(Financialrecord financialrecordObjekt) {
        financeRepository.save(financialrecordObjekt);
    }

    @Override
    public List<Financialrecord> showAllRecords( ) {
        return financeRepository.findAll();
    }

    @Override
    public Financialrecord showRecordById(long id) {
        return financeRepository.findById(id).get();
    }

    @Override
    public void deleteRecordById(long id) {
        financeRepository.deleteById(id);
           }

    @Override
    public List<Financialrecord> fetchDataPoints() {
        return financeRepository.findAll();
    }

    @Override
    public double getInitialValue() {
        Financialrecord firstRecord = financeRepository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id"))).getContent().get(0);
        return firstRecord.getAmount();
    }



}
