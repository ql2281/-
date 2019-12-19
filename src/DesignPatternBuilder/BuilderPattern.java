package DesignPatternBuilder;

import java.util.Calendar;

public class BuilderPattern {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currentTimestamp = new java.sql.Date(calendar.getTime().getTime());

        User.UserBuilder builder = new User.UserBuilder("xiaowang", "1234");
        builder.userId().firstName("xiao").lastName("wang").phone("213-433-0982").email("xiaowang@gmail.com").timeJoined(currentTimestamp);
        User user = builder.build();

        System.out.println(user.toString());
    }
}
