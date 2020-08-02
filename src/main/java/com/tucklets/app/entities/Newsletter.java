package com.tucklets.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "Newsletter")
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "newsletter_id", updatable = false, nullable = false)
    private Long newsletterId;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "upload_date")
    @Temporal(TemporalType.DATE)
    private Date uploadDate;

    @Column(name = "archive_date")
    @Temporal(TemporalType.DATE)
    private Date archiveDate;

    private String newsletterLocation;

    public Newsletter() {};

    public Newsletter(String filename, Date uploadDate) {
        this.filename = filename;
        this.uploadDate = uploadDate;
    }

    public Long getNewsletterId() {
        return newsletterId;
    }

    public String getFilename() {
        return filename;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public Date getArchiveDate() {
        return archiveDate;
    }

    public String getNewsletterLocation() {
        return newsletterLocation;
    }

    public void setNewsletterLocation(String newsletterLocation) {
        this.newsletterLocation = newsletterLocation;
    }
}
