package demo.vijay.surya.com.wikipediademo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Query {

    @SerializedName("pages")
    private List<PagesItem> pages;

    public void setPages(List<PagesItem> pages) {
        this.pages = pages;
    }

    public List<PagesItem> getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return
                "Query{" +
                        "pages = '" + pages + '\'' +
                        "}";
    }
}