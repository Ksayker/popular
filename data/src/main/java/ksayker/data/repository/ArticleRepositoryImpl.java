package ksayker.data.repository;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import ksayker.domain.entities.Article;
import ksayker.domain.entities.SharedArticle;
import ksayker.domain.repository.ArticleRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Volchenko Yura
 * @since 24.05.19
 */
public class ArticleRepositoryImpl implements ArticleRepository {
    private static final String API_KEY = "xGeAXtIZLm3iSmG4knqvAXYWOGzX5QVs";
    private static final int COUNT = 30;

    // TODO: 25.05.19 ???
    private Context appContext;

    public ArticleRepositoryImpl(Context context) {
        appContext = context.getApplicationContext();
    }

    @Override
    public Single<List<Article>> getMostEmailed() {
        return Single.create(emitter -> NetworkService.getInstance()
                .getMostPopularApi()
                .getMostPopular(COUNT, API_KEY)
                .enqueue(new Callback<ArticlesResponse<SharedArticle>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArticlesResponse<SharedArticle>> call, @NonNull Response<ArticlesResponse<SharedArticle>> response) {
                        ArticlesResponse articles = response.body();
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
}
