package services;

import junit.framework.TestCase;
import models.Invoice;
import models.SeatingUsage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceCalculatorTest {

    @InjectMocks
    InvoiceCalculator calculator;


    @Test
    public void open2Cabin3Conf35Meals5() {
        SeatingUsage usage = SeatingUsage.builder().openSeatings(2).cabinSeatings(3).conferenceHours(35).meals(5).build();
        Invoice invoice = calculator.calculateInvoice(usage);
        assertEquals(11800,invoice.getOpenSeatingExpense());
        assertEquals(35400,invoice.getCabinSeatingExpense());
        assertEquals(0,invoice.getConferenceExpense());
        assertEquals(560,invoice.getMealExpense());
        assertEquals(47760, invoice.getTotalExpense());
    }

    @Test
    public void open0Cabin1Conf50Meal10() {
        SeatingUsage usage = SeatingUsage.builder().openSeatings(0).cabinSeatings(1).conferenceHours(50).meals(10).build();
        Invoice invoice = calculator.calculateInvoice(usage);
        assertEquals(0,invoice.getOpenSeatingExpense());
        assertEquals(11800,invoice.getCabinSeatingExpense());
        assertEquals(9440,invoice.getConferenceExpense());
        assertEquals(1120,invoice.getMealExpense());
        assertEquals(22360, invoice.getTotalExpense());
    }

    @Test
    public void open2Meal30() {
        SeatingUsage usage = SeatingUsage.builder().openSeatings(2).cabinSeatings(0).conferenceHours(0).meals(30).build();
        Invoice invoice = calculator.calculateInvoice(usage);
        assertEquals(11800,invoice.getOpenSeatingExpense());
        assertEquals(0,invoice.getCabinSeatingExpense());
        assertEquals(0,invoice.getConferenceExpense());
        assertEquals(3360,invoice.getMealExpense());
        assertEquals(15160, invoice.getTotalExpense());
    }
}