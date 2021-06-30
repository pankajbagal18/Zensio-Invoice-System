package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SeatingUsage {
    private int openSeatings;
    private int cabinSeatings;
    private int conferenceHours;
    private int meals;
}
