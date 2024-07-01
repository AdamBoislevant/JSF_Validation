package com.coreservlets;

import java.io.Serializable;

// Project class used in ProjectBean
public class Project implements Serializable {
    private String title;
    private String keywords;
    private String companyName;
    private String companyPic;
    private String summary;
    private String description;
    private String descriptionPic;
    private String presentationPic;

    // Constructor
    public Project(String title, String keywords, String companyName, String companyPic,
                   String summary, String description, String descriptionPic, String presentationPic) {
        this.title = title;
        this.keywords = keywords;
        this.companyName = companyName;
        this.companyPic = companyPic;
        this.summary = summary;
        this.description = description;
        this.descriptionPic = descriptionPic;
        this.presentationPic = presentationPic;
    }

    // Default project structure
    public Project(){
        this.title = "not found";
        this.keywords = null;
        this.companyName = null;
        this.companyPic = null;
        this.summary = null;
        this.description = null;
        this.descriptionPic = null;
        this.presentationPic = null;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPic() {
        return companyPic;
    }

    public void setCompanyPic(String companyPic) {
        this.companyPic = companyPic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionPic() {
        return descriptionPic;
    }

    public void setDescriptionPic(String descriptionPic) {
        this.descriptionPic = descriptionPic;
    }

    public String getPresentationPic() {
        return presentationPic;
    }

    public void setPresentationPic(String presentationPic) {
        this.presentationPic = presentationPic;
    }
}
