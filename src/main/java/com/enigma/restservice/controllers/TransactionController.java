package com.enigma.restservice.controllers;

import com.enigma.restservice.entities.Transaction;
import com.enigma.restservice.entities.TypeTransaction;
import com.enigma.restservice.models.*;
import com.enigma.restservice.services.EntityService;
import com.enigma.restservice.services.TransactionSummaryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    @Autowired
    private EntityService<Transaction, Integer> service;

    @Autowired
    private TransactionSummaryService transactionSummaryService;

    @PostMapping
    public ResponseMessage<TransactionModel> add(@RequestBody TransactionModel model) {
        Transaction entity = service.save(new Transaction(model.getAmount(), model.getDescription(), TypeTransaction.fromValue(model.getTypeTransaction())));

        ModelMapper modelMapper = new ModelMapper();
        TransactionModel data = modelMapper.map(entity, TransactionModel.class);
        return ResponseMessage.successAdd(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<TransactionModel> edit(@PathVariable Integer id, @RequestBody @Valid TransactionModel model) {
        ModelMapper modelMapper = new ModelMapper();

        model.setId(id);
        Transaction entity = service.findById(id);
        modelMapper.map(model, entity);

        entity = service.save(entity);

        TransactionModel data = modelMapper.map(entity, TransactionModel.class);
        return ResponseMessage.successEdit(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<TransactionModel> removeById(@PathVariable Integer id) {
        Transaction entity = service.removeById(id);

        ModelMapper modelMapper = new ModelMapper();
        TransactionModel data = modelMapper.map(entity, TransactionModel.class);

        return ResponseMessage.successDelete(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<TransactionModel> findById(@PathVariable Integer id) {
        Transaction entity = service.findById(id);

        ModelMapper modelMapper = new ModelMapper();
        TransactionModel data = modelMapper.map(entity, TransactionModel.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PageableList<TransactionModel>> findAll(
            @RequestParam(required = false) Integer amount,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) TypeTransaction typeTransaction,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }
        Transaction entity = new Transaction(amount, description, typeTransaction);
        Sort.Direction direction = Sort.Direction
                .fromOptionalString(sort.toUpperCase())
                .orElse(Sort.Direction.ASC);
        Page<Transaction> pageTransactions = service.findAll(entity, page, size, direction);
        List<Transaction> transactions = pageTransactions.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type type = new TypeToken<List<TransactionModel>>() {
        }.getType();
        List<TransactionModel> transactionModels = modelMapper.map(transactions, type);
        PageableList<TransactionModel> data = new PageableList(transactionModels, pageTransactions.getNumber(),
                pageTransactions.getSize(), pageTransactions.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/summary")
    public ResponseMessage<List<TransactionSummary>> summary(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer date
    ) {
        List<TransactionSummary> data = transactionSummaryService.transactionSummaries(
                        year != null ? Year.of(year) : Year.now(),
                        month != null ? Month.of(month) : null,
                        date != null ? date : null);

        System.out.println(data);
        return ResponseMessage.success(data);

    }

}
