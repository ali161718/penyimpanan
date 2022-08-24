package com.enigma.restservice.controllers;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.entities.Stock;
import com.enigma.restservice.entities.Unit;
import com.enigma.restservice.models.*;
import com.enigma.restservice.services.EntityService;
import com.enigma.restservice.services.StockSummaryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RequestMapping("/stocks")
@RestController
public class StockController {

    @Autowired
    private EntityService<Stock, Integer> serviceStock;

    @Autowired
    private EntityService<Item, Integer> serviceItem;

    @Autowired
    private EntityService<Unit, Integer> serviceUnit;

    @Autowired
    private StockSummaryService summaryService;

    @PostMapping
    public ResponseMessage<StockModel> add(@RequestBody StockRequestModel model) {
        ModelMapper modelMapper = new ModelMapper();

        Item item = serviceItem.findById(model.getItemId());
        Unit unit = serviceUnit.findById(model.getUnitId());

        Stock entity = serviceStock.save(new Stock(item, model.getQuantity(), unit));

        StockModel data = modelMapper.map(entity, StockModel.class);

        return ResponseMessage.successAdd(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<StockModel> edit(@PathVariable Integer id, @RequestBody @Valid StockRequestModel model) {
        ModelMapper modelMapper = new ModelMapper();

        Stock entity = serviceStock.findById(id);

        entity.setItem(serviceItem.findById(model.getItemId()));
        entity.setUnit(serviceUnit.findById(model.getUnitId()));
        entity.setQuantity(model.getQuantity());

        entity = serviceStock.save(entity);

        StockModel data = modelMapper.map(entity, StockModel.class);
        return ResponseMessage.successEdit(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<StockModel> removeById(@PathVariable Integer id) {
        Stock entity = serviceStock.removeById(id);

        ModelMapper modelMapper = new ModelMapper();
        StockModel data = modelMapper.map(entity, StockModel.class);

        return ResponseMessage.successDelete(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<StockModel> findById(@PathVariable Integer id) {
        Stock entity = serviceStock.findById(id);

        ModelMapper modelMapper = new ModelMapper();
        StockModel data = modelMapper.map(entity, StockModel.class);
        return ResponseMessage.success(data);

    }

    @GetMapping
    public ResponseMessage<PageableList<StockModel>> findAll(
            @RequestParam(required = false) Item item,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Unit unit,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }
        Stock entity = new Stock(item, quantity, unit);
        Sort.Direction direction = Sort.Direction
                .fromOptionalString(sort.toUpperCase())
                .orElse(Sort.Direction.ASC);
        Page<Stock> pageStocks = serviceStock.findAll(entity, page, size, direction);
        List<Stock> stocks = pageStocks.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type type = new TypeToken<List<StockModel>>() {
        }.getType();
        List<StockModel> stockModels = modelMapper.map(stocks, type);
        PageableList<StockModel> data = new PageableList(stockModels, pageStocks.getNumber(),
                pageStocks.getSize(), pageStocks.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/summary")
    public ResponseMessage<List<StockSummary>> stockSummary(){
        List<StockSummary> data = summaryService.stockSummaries();
        return ResponseMessage.success(data);
    }
}
