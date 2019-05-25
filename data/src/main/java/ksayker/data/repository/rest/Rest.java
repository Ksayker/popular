package ksayker.data.repository.rest;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import ksayker.data.repository.NetworkService;
import ksayker.domain.entities.EmailedArticle;
import ksayker.domain.entities.SharedArticle;
import ksayker.domain.entities.ViewedArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class Rest {
    private static final String API_KEY = "xGeAXtIZLm3iSmG4knqvAXYWOGzX5QVs";
    private static final int COUNT = 30;

    public Single<List<EmailedArticle>> getMostEmailed() {
        return Single.create(emitter -> NetworkService.getInstance()
                .getMostPopularApi()
                .getMostEmailedArticles(COUNT, API_KEY)
                .enqueue(new Callback<ArticlesResponse<EmailedArticle>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArticlesResponse<EmailedArticle>> call, @NonNull Response<ArticlesResponse<EmailedArticle>> response) {
                        ArticlesResponse<EmailedArticle> articles = response.body();
                        if (!emitter.isDisposed() && articles != null) {
                            emitter.onSuccess(articles.getArticles());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArticlesResponse<EmailedArticle>> call, @NonNull Throwable t) {
                        if (!emitter.isDisposed()) {
                            emitter.onError(t);
                        }
                    }
                }));
    }

    public Single<List<SharedArticle>> getMostShared() {
        return Single.create(emitter -> NetworkService.getInstance()
                .getMostPopularApi()
                .getMostSharedArticles(COUNT, API_KEY)
                .enqueue(new Callback<ArticlesResponse<SharedArticle>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArticlesResponse<SharedArticle>> call, @NonNull Response<ArticlesResponse<SharedArticle>> response) {
                        ArticlesResponse<SharedArticle> articles = response.body();
                        if (!emitter.isDisposed() && articles != null) {
                            emitter.onSuccess(articles.getArticles());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArticlesResponse<SharedArticle>> call, @NonNull Throwable t) {
                        if (!emitter.isDisposed()) {
                            emitter.onError(t);
                        }
                    }
                }));
    }

    public Single<List<ViewedArticle>> getMostViewed() {
        return Single.create(emitter -> NetworkService.getInstance()
                .getMostPopularApi()
                .getMostViewedArticles(COUNT, API_KEY)
                .enqueue(new Callback<ArticlesResponse<ViewedArticle>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArticlesResponse<ViewedArticle>> call, @NonNull Response<ArticlesResponse<ViewedArticle>> response) {
                        ArticlesResponse<ViewedArticle> articles = response.body();
                        if (!emitter.isDisposed() && articles != null) {
                            emitter.onSuccess(articles.getArticles());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArticlesResponse<ViewedArticle>> call, @NonNull Throwable t) {
                        if (!emitter.isDisposed()) {
                            emitter.onError(t);
                        }
                    }
                }));
    }
}
