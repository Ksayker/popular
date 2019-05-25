package ksayker.data.repository;

import ksayker.domain.entities.SharedArticle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public interface MostPopularApi {
    @GET("/svc/mostpopular/v2/shared/{period}.json")
    Call<ArticlesResponse<SharedArticle>> getMostPopular(@Path("period") int period, @Query("api-key") String key);
}
