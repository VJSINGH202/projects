
package com.cms.entities;

/**
 *
 * @author DELL PC
 */
public class Report {
   private String id ;
   private String userId;
   private String blogId;
   private String reportStatus;
   private String ReportDate;

    public Report(String id, String userId, String blogId, String reportStatus, String ReportDate) {
        this.id = id;
        this.userId = userId;
        this.blogId = blogId;
        this.reportStatus = reportStatus;
        this.ReportDate = ReportDate;
    }
   

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getBlogId() {
        return blogId;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public String getReportDate() {
        return ReportDate;
    }
   
}
