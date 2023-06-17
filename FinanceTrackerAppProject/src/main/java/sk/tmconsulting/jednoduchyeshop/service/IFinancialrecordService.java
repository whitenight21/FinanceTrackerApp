package sk.tmconsulting.jednoduchyeshop.service;

import sk.tmconsulting.jednoduchyeshop.model.Financialrecord;

import java.util.Date;
import java.util.List;

public interface IFinancialrecordService {



// 22. in interface create the interface actions : here to save the shirts
// 22.a.  Below it add action to show the list of shirts as zobrazVsetkyZaznamy
    // Go to: service.TrickoService2

    Financialrecord saveRecord(Financialrecord financialrecordObjekt);

    void updateRecord(Financialrecord financialrecordObjekt);

    List<Financialrecord> showAllRecords();

    Financialrecord showRecordById(long id);

    void deleteRecordById(long id);    // long id is what it is to be deleted by


    List<Financialrecord> fetchDataPoints();


    double getInitialValue();


}
