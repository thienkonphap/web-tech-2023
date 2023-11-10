package com.example.tp2;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static RestTemplate restTemplate = new RestTemplate();
    public static String url(String team, String year, String teamNumber) {
        return String.format("https://jsonmock.hackerrank.com/api/football_matches?year=%s&team%s=%s", year, teamNumber, team);
    }

    public static int getTotalGoalsTeam(String team, String year, String asTeam) {
        String urlToCall = url(team, year, asTeam);
        ResponseEntity<InfoInPage> response =
                restTemplate.getForEntity(urlToCall,InfoInPage.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            InfoInPage info = response.getBody();
            int totalPages = info.getTotal_pages();
            int totalGoals = 0;
            for (int i = 1; i<= totalPages; i++) {
                String url = url(team, year, asTeam) + "&page=" + i;
                ResponseEntity<InfoInPage> response2 =
                        restTemplate.getForEntity(url,InfoInPage.class);
                   if (response2.getStatusCode() == HttpStatus.OK) {
                       MatchInfor[] data = response2.getBody().data;
                       int countGoals = 0;
                       for (MatchInfor matchInfor : data) {
                           if (asTeam.equals("1"))
                               countGoals += Integer.parseInt(matchInfor.getTeam1goals());
                           else if (asTeam.equals("2")) {
                                 countGoals += Integer.parseInt(matchInfor.getTeam2goals());
                           }
                       }
                       totalGoals += countGoals;
                   }

            }
            return totalGoals;
        }
        else {
            throw new RuntimeException("Something wrong when calling API");
        }
    }
    public static int getTotalGoalsByTeamAndYear(String team, String year) {
        return getTotalGoalsTeam(team, year, "1") + getTotalGoalsTeam(team, year, "2");
    }
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(getTotalGoalsByTeamAndYear("Barcelona", "2011"));
    }
}
