package com.example.tp2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchInfor {
    String competition;
    String year;
    String team1;
    String team2;
    String team1goals;
    String team2goals;
}
