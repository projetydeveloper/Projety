package com.projety.model;


import java.util.List;

public class Party {

    private String uid;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String creationDate;
    private String updateDate;
    private String image;
    private String imageThumb;
    private Long nbLike;
    private Long nbSeen;
    private String eventLink;
    private List<Price> price;
    private List<Dance> dance;
    private List<Organizer> organizer;
    private Location location;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public Long getNbLike() {
        return nbLike;
    }

    public void setNbLike(Long nbLike) {
        this.nbLike = nbLike;
    }

    public Long getNbSeen() {
        return nbSeen;
    }

    public void setNbSeen(Long nbSeen) {
        this.nbSeen = nbSeen;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
    }

    public List<Dance> getDance() {
        return dance;
    }

    public void setDance(List<Dance> dance) {
        this.dance = dance;
    }

    public List<Organizer> getOrganizer() {
        return organizer;
    }

    public void setOrganizer(List<Organizer> organizer) {
        this.organizer = organizer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
