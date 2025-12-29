package edu.raijin.insight.fact.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
import edu.raijin.insight.fact.domain.model.FinancialReport;
import edu.raijin.insight.fact.domain.port.persistence.FindFinancialMovementReportPort;
import edu.raijin.insight.fact.domain.usecase.FetchFinancialMovementReportUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchFinancialMovementService implements FetchFinancialMovementReportUseCase {

    private final FindFinancialMovementReportPort find;

    @Override
    @Transactional
    public Paged<FinancialReport> fetchFinancialProjectReport(Filter<FinancialMovement> filter, Pageable pageable) {
        Paged<FinancialReport> page = find.findFinancialMovementReport(filter, pageable);
        List<FinancialReport> items = page.getItems();
        List<FinancialReport> result = new ArrayList<>();

        UUID lastProject = null;
        BigDecimal subtotalAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (FinancialReport item : items) {
            UUID currentClient = item.getProjectId();
            if (lastProject != null && !Objects.equals(currentClient, lastProject)) {
                result.add(FinancialReport.ofSubtotal(subtotalAmount));
                subtotalAmount = BigDecimal.ZERO;
            }
            result.add(item);
            lastProject = currentClient;
            subtotalAmount = subtotalAmount.add(item.getAmount());
            totalAmount = totalAmount.add(item.getAmount());
        }
        if (lastProject != null || !result.isEmpty()) {
            result.add(FinancialReport.ofSubtotal(subtotalAmount));
        }
        result.add(FinancialReport.ofTotal(totalAmount));
        page.setItems(result);
        return page;
    }
}
