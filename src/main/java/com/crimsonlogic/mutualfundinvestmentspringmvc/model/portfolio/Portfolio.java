package com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Portfolio {

    private String portfolioId;

    // Association
    private Investor investor;

    private List<Holding> holdings = new ArrayList<>();

    private LocalDate lastActivityDate;

    public Portfolio() {
    }

    public Portfolio(String portfolioId,
                     Investor investor,
                     LocalDate lastActivityDate) {

        this.portfolioId = portfolioId;
        this.investor = investor;
        this.lastActivityDate = lastActivityDate;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    public LocalDate getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(LocalDate lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "portfolioId='" + portfolioId + '\'' +
                ", investor=" +
                (investor != null ? investor.getUserId() : null) +
                ", holdings=" + holdings +
                ", lastActivityDate=" + lastActivityDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Portfolio portfolio = (Portfolio) o;

        return Objects.equals(portfolioId, portfolio.portfolioId)
                && Objects.equals(investor, portfolio.investor)
                && Objects.equals(holdings, portfolio.holdings)
                && Objects.equals(lastActivityDate,
                portfolio.lastActivityDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                portfolioId,
                investor,
                holdings,
                lastActivityDate);
    }
}