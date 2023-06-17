package sk.tmconsulting.jednoduchyeshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sk.tmconsulting.jednoduchyeshop.model.Financialrecord;
import sk.tmconsulting.jednoduchyeshop.repository.FinanceRepository;
import sk.tmconsulting.jednoduchyeshop.service.FinancialrecordService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


// 1. create the package called : Controller
// 2. in the controller package create a class ex. jednoduchyEshopController
// 2.a.  in application.properties, replace "your-username" and "your-password" with your actual MySQL
//        database username and password, and "mydatabase" with the name of your database
// 3. @Controller

@Controller
public class FinanceTrackerController {

// 26. Add @Autowired to create an object trickoService, so that we can use it later
    @Autowired   // @autowired is a dependency injection
    private FinancialrecordService financeService;

    @Autowired
    private FinanceRepository financeRepository;


// 4. @Getmapping is the main index, url address, ex. localhost:8080  AND it needs a method :  public ... and return
    @GetMapping("/")
    public String indexPage () {
        return "index";
    }

    @GetMapping("/pridaj-novy-zaznam")
    public String addNewRecord(Model model) {
        Financialrecord financialrecordObjekt = new Financialrecord();   // have to create a new T-shirt
        model.addAttribute("financialrecordObjekt", financialrecordObjekt);   // added to the object
        return "pridaj-novy-zaznam";
    }

    @GetMapping("/zobraz-vsetky-zaznamy")
    public String showAllRecords (Model model) {
        model.addAttribute("showAllRecords", financeService.showAllRecords());
        return "zobraz-vsetky-zaznamy";
    }

// 5. create the "index" html file in "Templates" package
// 6. in Index write in the body with HTML coding the text and edits and so on...
    // GO TO : model.Tricko.java

// 13. Add after lang="en" this: xmlns:th="http://www.thymeleaf.org"
// 14. add all the lines in pridaj novy zaznam.html
// 15. in GetMapping priday novy zaznam:  import the Tricko class, import the Model (swing,maven), add the Tricko class,
// to connect to thymeleaf model.addAttribute and the name has to be exact as to what in html, then the name.

// 16. PostMapping is used to map the URL with the info hidden from the URL, within the PostMapping connect with ModelAttribute
// the trickoObjekt
    @PostMapping("/uloz-zaznam")
    public String saveRecord(@ModelAttribute("financialrecordObjekt") Financialrecord financialrecordObjektNaplneny) {   // Tricko is class mapping, and the object is trickoObjektNaplneny
// 17. Drop the trickoObjektNaplneny into the database:
// 18. Create a repository package and Interface : TrickoRepository
        // Go to : repository.TrickoRepository

// 27. write where it is to be saved and what to return.
        financeService.saveRecord(financialrecordObjektNaplneny);
        return "index";
    }

    @GetMapping("/uprav-zaznam/{id}")
    public String updateRecord(@PathVariable(value="id") long id, Model model) {
    Financialrecord financialrecordFromDatabase = financeService.showRecordById(id);
    model.addAttribute("financialrecordFromDatabase", financialrecordFromDatabase);
    return "uprav-zaznam";
    }

    @PostMapping("/uloz-upraveny-zaznam")
    public String saveUpdatedRecord(@ModelAttribute("financialrecordFromDatabase") Financialrecord financialrecordObjektNaplneny) {
        financeService.saveRecord(financialrecordObjektNaplneny);
        return "index";
    }

    @GetMapping("/odstran-zaznam/{id}")
    public String deleteRecord(@PathVariable(value="id") long id, Model model) {
        financeService.deleteRecordById(id);
        return "index";
    }

    @GetMapping("/bar-chart")
    public String showAllRecordsChart(Model model) {
        List<String> financeList = financeService.showAllRecords().stream().map(x->x.getName()).collect(Collectors.toList());
        List<Double> amountList = financeService.showAllRecords().stream().map(x->x.getAmount()).collect(Collectors.toList());
        model.addAttribute("finance", financeList);
        model.addAttribute("amount", amountList);
        return "bar-chart";
    }


    @GetMapping("/dynamic-chart")
    public String showFinanceChart(Model model) {
        // Fetch finance data points from the database
        List<Financialrecord> dataPoints = financeService.fetchDataPoints();

        // Prepare the data for Highcharts
        List<String> dates = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        double initialValue = financeService.getInitialValue();
        double currentValue = initialValue;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Financialrecord dataPoint : dataPoints) {
            LocalDate date = dataPoint.getDate();
            String formattedDate = date.format(formatter);
            dates.add(formattedDate);
            currentValue += dataPoint.getAmount();
            values.add(currentValue);
        }

        model.addAttribute("dates", dates);
        model.addAttribute("values", values);
        model.addAttribute("initialValue", initialValue);

        return "dynamic-chart";
    }


}

