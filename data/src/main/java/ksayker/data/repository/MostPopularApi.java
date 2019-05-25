package ksayker.data.repository;

import ksayker.data.repository.rest.ArticlesResponse;
import ksayker.domain.entities.EmailedArticle;
import ksayker.domain.entities.SharedArticle;
import ksayker.domain.entities.ViewedArticle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public interface MostPopularApi {
    @GET("/svc/mostpopular/v2/emailed/{period}.json")
    Call<ArticlesResponse<EmailedArticle>> getMostEmailedArticles(@Path("period") int period, @Query("api-key") String key);

    @GET("/svc/mostpopular/v2/shared/{period}.json")
    Call<ArticlesResponse<SharedArticle>> getMostSharedArticles(@Path("period") int period, @Query("api-key") String key);

    @GET("/svc/mostpopular/v2/viewed/{period}.json")
    Call<ArticlesResponse<ViewedArticle>> getMostViewedArticles(@Path("period") int period, @Query("api-key") String key);
}
