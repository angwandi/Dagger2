package app.a2ms.dagger2.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;

import javax.inject.Inject;

import app.a2ms.dagger2.R;
import app.a2ms.dagger2.base.BaseController;
import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RepoDetailsController extends BaseController {

    static final String REPO_NAME_KEY = "repo_name";
    static final String REPO_OWNER_KEY = "repo_owner";


    public static Controller newInstance(String repoName, String repoOwner) {

        Bundle bundle = new Bundle();
        bundle.putString(REPO_NAME_KEY, repoName);
        bundle.putString(REPO_OWNER_KEY, repoOwner);
        return new RepoDetailsController(bundle);
    }

    @Inject
    RepoDetailsViewModel viewModel;
    @Inject
    RepoDetailsPresenter presenter;

    @BindView(R.id.tv_repo_name)
    TextView repoNameText;
    @BindView(R.id.tv_repo_description)
    TextView repoDescriptionText;
    @BindView(R.id.tv_creation_date)
    TextView creationDateText;
    @BindView(R.id.tv_updated_date)
    TextView updatedDateText;
    @BindView(R.id.contributor_list)
    RecyclerView contributorList;
    @BindView(R.id.loading_indicator)
    View detailsLoadingView;
    @BindView(R.id.contributor_loading_indicator)
    View contributorLoadingView;
    @BindView(R.id.content)
    View contentContainer;
    @BindView(R.id.tv_error)
    TextView errorText;
    @BindView(R.id.tv_contributors_error)
    TextView contributorErrorText;


    public RepoDetailsController(Bundle bundle) {
        super(bundle);
    }

    @Override
    protected void onViewBound(View view) {
        contributorList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        contributorList.setAdapter(new ContributorAdapter());
    }

    @Override
    protected Disposable[] subscription() {
        return new Disposable[]{
                viewModel.details()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(details -> {
                    if (details.loading()) {
                        detailsLoadingView.setVisibility(View.VISIBLE);
                        contentContainer.setVisibility(View.GONE);
                        errorText.setVisibility(View.GONE);
                        errorText.setText(null);
                    } else {
                        if (details.isSuccess()) {
                            errorText.setText(null);
                        } else {
                            //noinspection ConstantConditions
                            errorText.setText(details.errorRes());
                        }
                        detailsLoadingView.setVisibility(View.GONE);
                        contentContainer.setVisibility(details.isSuccess() ? View.VISIBLE : View.GONE);
                        errorText.setVisibility(details.isSuccess() ? View.GONE : View.VISIBLE);
                        repoNameText.setText(details.name());
                        repoDescriptionText.setText(details.description());
                        creationDateText.setText(details.createdDate());
                        updatedDateText.setText(details.updatedDate());
                    }
                }),
                viewModel.contributors()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(contributorDetails -> {
                    if (contributorDetails.loading()) {
                        contributorLoadingView.setVisibility(View.VISIBLE);
                        contributorList.setVisibility(View.GONE);
                        contributorErrorText.setVisibility(View.GONE);
                        contributorErrorText.setText(null);
                    } else {
                        contributorLoadingView.setVisibility(View.VISIBLE);
                        contributorList.setVisibility(contributorDetails.isSuccess() ? View.VISIBLE : View.GONE);
                        contributorErrorText.setVisibility(contributorDetails.isSuccess() ? View.GONE : View.VISIBLE);
                        if (contributorDetails.isSuccess()) {
                            contributorErrorText.setText(null);
                            ((ContributorAdapter) contributorList.getAdapter()).setData(contributorDetails.contributors());
                        } else {
                            //noinspection ConstantConditions
                            contributorErrorText.setText(contributorDetails.errorRes());
                        }
                    }

                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_repo_details;
    }
}
