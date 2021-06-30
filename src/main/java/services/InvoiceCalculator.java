package services;

import models.Invoice;
import models.SeatingUsage;

import static constants.PricingConstants.*;

public class InvoiceCalculator {

    public Invoice calculateInvoice(SeatingUsage seatingUsage) {
        Invoice invoice = Invoice.builder()
                .openSeatingExpense(calculatePrice(seatingUsage.getOpenSeatings(), OPEN_SEATING, SEATING_GST_PERCENTAGE))
                .cabinSeatingExpense(calculatePrice(seatingUsage.getCabinSeatings(), CABIN_SEATING, SEATING_GST_PERCENTAGE))
                .conferenceExpense(calculatePrice(calculateChargeableConfHrs(seatingUsage), CONFERENCE_CHARGE_PER_HR, SEATING_GST_PERCENTAGE))
                .mealExpense(calculatePrice(seatingUsage.getMeals(), MEALS, MEAL_GST_PERCENTAGE))
                .build();
        invoice.setTotalExpense();
        return invoice;
    }

    private static int calculateChargeableConfHrs(SeatingUsage seatingUsage) {
        final int chargeable = seatingUsage.getConferenceHours() - seatingUsage.getOpenSeatings() * OPEN_SEATING_FREE_CONF_HRS - seatingUsage.getCabinSeatings() * CABIN_SEATING_FREE_CONF_HRS;
        return Math.max(chargeable, 0);
    }

    private static int calculatePrice(int noOfArticle, Integer pricePerArticle, Integer gst) {
        int originalPrice = noOfArticle * pricePerArticle;
        return  originalPrice + (originalPrice*gst)/100;
    }
}
