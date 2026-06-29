package iki.attendance.management.attendance.dto;

import java.util.List;

public class TodayDashboardDto {

    private String date;

    private SchoolSummaryDto schoolSummary;

    private List<ClassTodayReportDto> classWise;

    private List<StreamTodayReportDto> streamWise;

    public TodayDashboardDto(String date, SchoolSummaryDto schoolSummary,
                             List<ClassTodayReportDto> classWise, List<StreamTodayReportDto> streamWise) {
        this.date = date;
        this.schoolSummary = schoolSummary;
        this.classWise = classWise;
        this.streamWise = streamWise;
    }

    public TodayDashboardDto(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SchoolSummaryDto getSchoolSummary() {
        return schoolSummary;
    }

    public void setSchoolSummary(SchoolSummaryDto schoolSummary) {
        this.schoolSummary = schoolSummary;
    }

    public List<ClassTodayReportDto> getClassWise() {
        return classWise;
    }

    public void setClassWise(List<ClassTodayReportDto> classWise) {
        this.classWise = classWise;
    }

    public List<StreamTodayReportDto> getStreamWise() {
        return streamWise;
    }

    public void setStreamWise(List<StreamTodayReportDto> streamWise) {
        this.streamWise = streamWise;
    }
}