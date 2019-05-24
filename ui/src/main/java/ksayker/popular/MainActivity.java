package ksayker.popular;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ksayker.popular.fragment.TabsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_mainActivity_mainFragmentHolder, TabsFragment.newInstance())
                .commit();
    }
}
