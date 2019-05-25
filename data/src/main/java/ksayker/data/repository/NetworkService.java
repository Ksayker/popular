package ksayker.data.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Volchenko Yura
 * @since 25.05.19
 */
public class NetworkService {
    private static final String URL = "https://api.nytimes.com";
    private static NetworkService instance;

    private Retrofit retrofit;

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MostPopularApi getMostPopularApi() {
        return retrofit.create(MostPopularApi.class);
    }
}
