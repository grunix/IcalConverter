/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.icalconverter;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ladmin
 */
@Entity
public class EventItem implements Serializable{

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventItem(){};
    
    public EventItem(Date date, String summary, String description) {
        this.startDate = date;
        this.summary = summary;
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public String getDescription() {
        if(description.length()>255)
            return description.substring(0, 254);
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModificationionDate() {
        return modificationionDate;
    }

    @Override
    public String toString() {
        return "EventItem{" + "startDate=" + startDate + ", modificationionDate=" + modificationionDate + ", summary=" + summary + ", description=" + description + '}';
    }

    public void setModificationionDate(Date modificationionDate) {
        this.modificationionDate = modificationionDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    // the startDate time of that event
    @Temporal(TemporalType.DATE)
    private Date startDate=null;
    
    // the modification startDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationionDate = null;
    
    private String summary = null;
    
    @Column(length=512)
    private String description = null;
    
}
