package demo.vijay.surya.com.wikipediademo.interfaces;

import demo.vijay.surya.com.wikipediademo.models.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface HomeApi {

    @GET("w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&" +
            "redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=1080&pilimit=40&wbptterms=description")
    Observable<Response> getWikipediaSearchResponse(@Query("gpssearch") String searchedString, @Query("gpslimit") int limit);

}
