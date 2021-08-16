package sg.edu.rp.c346.id20008460.bakinglist;

import java.io.Serializable;

public class Recipe implements Serializable {

    private int id;
    private String title;
    private String summary;
    private String ingredients;
    private String details;
    private String date;
    private String username;


    public Recipe(int id, String title, String summary, String ingredients, String details, String date, String username) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.ingredients = ingredients;
        this.details = details;
        this.date = date;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


}
