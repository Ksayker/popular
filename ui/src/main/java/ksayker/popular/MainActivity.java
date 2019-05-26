package ksayker.popular;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ksayker.domain.entities.Article;
import ksayker.popular.adapter.ListAdapter;
import ksayker.popular.fragment.ArticleFragment;
import ksayker.popular.fragment.TabsFragment;

public class MainActivity extends AppCompatActivity implements ListAdapter.ArticleClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_mainActivity_mainFragmentHolder, TabsFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onArticleClick(Article article) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_right_in, R.anim.slide_right_out)
                .replace(R.id.fl_mainActivity_mainFragmentHolder, ArticleFragment.newInstance(article))
                .commit();
    }
}
