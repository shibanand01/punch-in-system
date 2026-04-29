package com.punch;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class WorkSession {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a");

    //Formats duration into readable format
    public static String formatDuration(Duration duration)
    {
        long seconds = duration.getSeconds();
        long hours = seconds/3600;
        long minutes = (seconds%3600)/60;
        long secs = ((seconds%3600)%60);
        return String.format("%02dH %02dM %02dS",hours,minutes,secs);
    }

    //punch-in at the time of object creation
    public WorkSession(){
        this.startTime = LocalDateTime.now();
        System.out.println("Punched-In at: "+this.startTime.format(formatter));
    }
    //punch-out explicitly
    public void punchOut(){
        if(this.endTime == null){
            this.endTime = LocalDateTime.now();
            System.out.println("Punched-Out at: "+this.endTime.format(formatter));
        }
        else throw new IllegalStateException("Session Ended at : "+this.endTime.format(formatter));
    }
    //calculate Duration active
    public Duration getDuration(){
        if(this.endTime != null)
            return Duration.between(this.startTime, this.endTime);
        else
            return Duration.between(this.startTime, LocalDateTime.now());
    }
    //print Session info
    @Override
    public String toString(){
        return "Session Start: "+this.startTime.format(formatter)+" Session End: "+this.endTime.format(formatter)+" Session Duration: "+formatDuration(this.getDuration());
    }
    //gives out the start time of the session
    public LocalDateTime getStartTime(){
        return this.startTime;
    }
    //gives out the end time of the session
    public LocalDateTime getEndTime(){
        return this.endTime;
    }
    //gives out the Start date of the Worksession
    public LocalDate getStartDate(){
        return this.startTime.toLocalDate();
    }
    //gives out the End date of the worksession
    public LocalDate getEndDate() {
        if (this.endTime!=null)
            return this.endTime.toLocalDate();
        else
            return null;
    }

}