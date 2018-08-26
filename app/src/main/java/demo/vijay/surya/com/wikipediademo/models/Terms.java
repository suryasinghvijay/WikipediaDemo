package demo.vijay.surya.com.wikipediademo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Terms {

    @SerializedName("description")
    private List<String> description;

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return
                "Terms{" +
                        "description = '" + description + '\'' +
                        "}";
    }
}