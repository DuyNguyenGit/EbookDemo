
package com.example.rct03.ebook_readerdm.models.ebooks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ebook {

    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("last_read")
    @Expose
    private List<LastRead> lastRead = null;
    @SerializedName("is_standalone")
    @Expose
    private boolean isStandalone;
    @SerializedName("claim")
    @Expose
    private String claim;
    @SerializedName("edition_text")
    @Expose
    private String editionText;
    @SerializedName("page_number")
    @Expose
    private int pageNumber;
    @SerializedName("highlights")
    @Expose
    private List<String> highlights = null;
    @SerializedName("edition_number")
    @Expose
    private int editionNumber;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("subscription_ids")
    @Expose
    private List<Object> subscriptionIds = null;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("keywords")
    @Expose
    private List<Object> keywords = null;
    @SerializedName("file_size")
    @Expose
    private int fileSize;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("topic_ids")
    @Expose
    private List<Integer> topicIds = null;
    @SerializedName("covers")
    @Expose
    private List<Cover> covers = null;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("copyright_year")
    @Expose
    private int copyrightYear;
    @SerializedName("usps")
    @Expose
    private List<String> usps = null;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("authors")
    @Expose
    private List<Author> authors = null;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LastRead> getLastRead() {
        return lastRead;
    }

    public void setLastRead(List<LastRead> lastRead) {
        this.lastRead = lastRead;
    }

    public boolean isIsStandalone() {
        return isStandalone;
    }

    public void setIsStandalone(boolean isStandalone) {
        this.isStandalone = isStandalone;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public String getEditionText() {
        return editionText;
    }

    public void setEditionText(String editionText) {
        this.editionText = editionText;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<String> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getSubscriptionIds() {
        return subscriptionIds;
    }

    public void setSubscriptionIds(List<Object> subscriptionIds) {
        this.subscriptionIds = subscriptionIds;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<Object> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Object> keywords) {
        this.keywords = keywords;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<Integer> topicIds) {
        this.topicIds = topicIds;
    }

    public List<Cover> getCovers() {
        return covers;
    }

    public void setCovers(List<Cover> covers) {
        this.covers = covers;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(int copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public List<String> getUsps() {
        return usps;
    }

    public void setUsps(List<String> usps) {
        this.usps = usps;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

}
