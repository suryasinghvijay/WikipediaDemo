package demo.vijay.surya.com.wikipediademo.models;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class JsonMemberContinue {

    @SerializedName("continue")
    private String jsonMemberContinue;

    @SerializedName("gpsoffset")
    private int gpsoffset;

    public void setJsonMemberContinue(String jsonMemberContinue) {
        this.jsonMemberContinue = jsonMemberContinue;
    }

    public String getJsonMemberContinue() {
        return jsonMemberContinue;
    }

    public void setGpsoffset(int gpsoffset) {
        this.gpsoffset = gpsoffset;
    }

    public int getGpsoffset() {
        return gpsoffset;
    }

    @Override
    public String toString() {
        return
                "JsonMemberContinue{" +
                        "continue = '" + jsonMemberContinue + '\'' +
                        ",gpsoffset = '" + gpsoffset + '\'' +
                        "}";
    }
}