package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Invoice {
    private int openSeatingExpense;
    private int cabinSeatingExpense;
    private int conferenceExpense;
    private int mealExpense;
    private int totalExpense;

    public void setTotalExpense() {
        this.totalExpense = openSeatingExpense + cabinSeatingExpense + conferenceExpense + mealExpense;
    }
}
