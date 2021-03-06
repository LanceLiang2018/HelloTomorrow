package hellotomorrow.liang.lance;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import hellotomorrow.liang.lance.jsons.Cards;
import hellotomorrow.liang.lance.jsons.Data;
import hellotomorrow.liang.lance.jsons.JsonRootBean;
import io.reactivex.annotations.NonNull;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private JsonRootBean bean;
    private int target_page = 0;
    private FloatingActionButton fab;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        if (bean != null)
            mViewPager.setAdapter(mSectionsPagerAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                getData();
                nextPage();
            }
        });

        nextPage();
    }


    public void nextPage() {
        target_page += 1;
        getData();
    }


    public void getData() {
        StringCallback callback = new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                bean = WeiboAPI.parseJson(response.body());
                mSectionsPagerAdapter.setData(bean);
//                if (bean == null)
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                mSectionsPagerAdapter.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, "Code:" + Integer.toString(bean.getOk()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Response<String> response) {
                Toast.makeText(MainActivity.this, "Error:" + response.body(), Toast.LENGTH_LONG).show();
                super.onError(response);
            }
        };
        WeiboAPI.getPage(target_page, callback);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int mCardId = -1;
        private Cards mCards;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, Cards cards, int cardId) {
//            Logger.getLogger("Hello").info("newInstance(sectionNumber=" + Integer.toString(sectionNumber) + ")");
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
//            fragment.page = sectionNumber;
            fragment.mCards = cards;
            fragment.mCardId = cardId;
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
//            Logger.getLogger("Hello").info("onCreateView(sectionNumber=" + page + ")(purpose)");
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(mCards.getScheme());
            TextView textView_content = (TextView) rootView.findViewById(R.id.textView_content);
            TextView textView_time = (TextView) rootView.findViewById(R.id.textView_time);
            Spanned html = Html.fromHtml(mCards.getMblog().getText());
            textView_content.setMovementMethod(ScrollingMovementMethod.getInstance());// 滚动
            textView_content.setText(html);
//            textView_content.setText(mCards.getMblog().getText());
            textView_time.setText(mCards.getMblog().getCreated_at());

//            if (mCards.getMblog().getPage_info() != null && mCards.getMblog().getPage_info().getPage_pic().getUrl() != null) {
//                final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
//                Glide.with(rootView).load(mCards.getMblog().getPage_info().getPage_pic().getUrl()).into(imageView);
//            }

//            else if(mCards.getMblog().getPic) {
//
//            }
            final ImageView imageView_head = (ImageView) rootView.findViewById(R.id.imageView_head);
            Glide.with(rootView).load(mCards.getMblog().getUser().getProfile_image_url()).into(imageView_head);

            TextView textView_user = (TextView) rootView.findViewById(R.id.textView_user);
            textView_user.setText(mCards.getMblog().getUser().getScreen_name());

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private List<Cards> mCards = new ArrayList<Cards>();
        private List<PlaceholderFragment> placeholderFragments = new ArrayList<PlaceholderFragment>();
        private PlaceholderFragment currentFragment = null;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);
//            if (position == mCards.size() - 1)
//                nextPage();
            return placeholderFragments.get(position);
        }

        public void setData(@NonNull JsonRootBean data) {
//            mData = data;
            mCards.clear();
            mCards.addAll(data.getData().getCards());
            /*
            placeholderFragments.clear();
            for (int i=0; i<mCards.size(); i++) {
                placeholderFragments.add(PlaceholderFragment.newInstance(i + 1,
                        mCards.get(i)));
            }*/
            for (int i=0; i<mCards.size(); i++) {
                if (i < placeholderFragments.size()) {
                    placeholderFragments.get(i).mCards = mCards.get(i);
                }
                else {
                    placeholderFragments.add(PlaceholderFragment.newInstance(i + 1, mCards.get(i), i));
                }
            }
        }

        @Override
        public int getCount() {
            return mCards.size();
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            currentFragment = (PlaceholderFragment) object;
            if (currentFragment.mCardId == mCards.size() - 1)
                nextPage();
            super.setPrimaryItem(container, position, object);
        }
    }
}
