package com.punch;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class PunchService {
    private List<WorkSession> sessions;//Stores the sessions that take place
    private WorkSession currentSession;//Starts a session
    private LocalDate currentSessionDate;

    //when Called creates a Arraylist that stores currentSession object
    public PunchService(){
        this.sessions = new ArrayList<>();
    }
    //when called it starts session if not started yet
    public void punchIn(){
        if (currentSession == null){
            currentSession = new WorkSession();
            currentSessionDate = currentSession.getStartDate();
        }
        else
            System.out.println("Already Punched-In!!");
    }
    //check if punched in or not
    public boolean isPunchedIn(){
        return currentSession!=null;
    }
    //when called it ends currentSessions if started and makes it ready for next session
    public void punchOut(){
        if(currentSession == null)
            System.out.println("Not Punched-In yet!!");
        else{
            currentSession.punchOut();
            sessions.add(currentSession);
            currentSession = null;
        }
    }
    //Formats duration into readable format
    public static String formatDuration(Duration duration)
    {
        long seconds = duration.getSeconds();
        long hours = seconds/3600;
        long minutes = (seconds%3600)/60;
        long secs = ((seconds%3600)%60);
        return String.format("%02dH %02dM %02dS",hours,minutes,secs);
    }
    //gives currentsession duration
    public String getSessionDuration(){
        String duration = null;
        if(currentSession==null){
            duration = "Not Punched-In yet!!";
        }
        else
            duration = formatDuration(currentSession.getDuration());
        return duration;
    }
    //when called it iterates over sessions to give out session info
    public void printAllSessions(){
        for(WorkSession session : sessions)
        {
            System.out.println(session);
        }
    }
    //when called filters outs Objects by session dates and calculates total work hour
    public String getTotalDurationForDate(LocalDate date){
        Duration totalDuration = Duration.ZERO;
        for(WorkSession session : sessions)
        {
            if (session.getStartDate().equals(date))
            {
                totalDuration = totalDuration.plus(session.getDuration());
            }
        }
        //if date is of the current session and its punched in but not out
        if (currentSession!=null && currentSessionDate.equals(date))
        {
            totalDuration = totalDuration.plus(currentSession.getDuration());
        }
        return formatDuration(totalDuration);
    }

    //calculate Total time for a week
    public String getTotalDurationForWeek(LocalDate date){
        Duration totalDuration = Duration.ZERO;
        LocalDate startWeekDate = date.with(DayOfWeek.MONDAY);
        LocalDate endWeekDate = startWeekDate.plusDays(6);
        for(WorkSession session : sessions){
            LocalDate sessionDate = session.getStartDate();
            if(!sessionDate.isBefore(startWeekDate) && !sessionDate.isAfter(endWeekDate))
            {
                totalDuration = totalDuration.plus(session.getDuration());
            }
        }
        //if date is of the current session and its punched in but not out
        if(currentSession!=null && (!currentSessionDate.isBefore(startWeekDate) && !currentSessionDate.isAfter(endWeekDate)))
        {
            totalDuration = totalDuration.plus(currentSession.getDuration());
        }

        return formatDuration(totalDuration);
    }

    //calculate Total time for a month
    public String getTotalDurationForMonth(YearMonth month){
        Duration totalDuration = Duration.ZERO;
        for(WorkSession session : sessions)
        {
            YearMonth sessionMonth = YearMonth.from(session.getStartDate());
            if(sessionMonth.equals(month))
            {
                totalDuration = totalDuration.plus(session.getDuration());
            }
        }
        //if date is of the current session and its punched in but not out
        if(currentSession!=null && YearMonth.from(currentSessionDate).equals(month))
            totalDuration = totalDuration.plus(currentSession.getDuration());
        return formatDuration(totalDuration);
    }

}