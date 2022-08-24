package com.enigma.restservice.repositories.impl;

import com.enigma.restservice.entities.Stock;
import com.enigma.restservice.models.StockSummary;
import com.enigma.restservice.repositories.StockRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StockRepositoryCustomImpl implements StockRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<StockSummary> stockSummaries() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StockSummary> query = builder.createQuery(StockSummary.class);
        Root<Stock> root = query.from(Stock.class);

        query.multiselect(root.get("item").get("name"), builder.sum(root.get("quantity")), root.get("unit").get("description"))
                .groupBy(root.get("item"), root.get("unit"));
        return entityManager.createQuery(query).getResultList();
    }
}
