package com.example.tp2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoInPage {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public MatchInfor[] data;
}
