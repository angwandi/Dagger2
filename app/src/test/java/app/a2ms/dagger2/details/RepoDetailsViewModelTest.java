package app.a2ms.dagger2.details;

import com.squareup.moshi.Types;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import app.a2ms.dagger2.R;
import app.a2ms.dagger2.model.Contributor;
import app.a2ms.dagger2.model.Repo;
import app.a2ms.dagger2.testUtils.TestUtils;

public class RepoDetailsViewModelTest {

    private RepoDetailsViewModel viewModel;

    private Repo repo = TestUtils.loadJson("mock/get_repo.json", Repo.class);
    private List<Contributor> contributors = TestUtils.loadJson("mock/get_contributors.json",
            Types.newParameterizedType(List.class, Contributor.class));


    @Before
    public void setUp() {
        viewModel = new RepoDetailsViewModel();
    }

    @Test
    public void details() throws Exception {

        viewModel.processRepo().accept(repo);

        viewModel.details().test().assertValue(
                RepoDetailState.builder()
                        .loading(false)
                        .name("RxJava")
                        .description("RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.")
                        .createdDate("10 06, 2017")
                        .updatedDate(null)
                        .build()
        );
    }

    @Test
    public void detaislError() throws Exception {
        viewModel.detaislError().accept(new IOException());

        viewModel.details().test().assertValue(
                RepoDetailState.builder()
                        .loading(false)
                        .errorRes(R.string.api_error_single_repo)
                        .build()
        );
    }


    @Test
    public void contributor() throws Exception {
        viewModel.processContributors().accept(contributors);

        viewModel.contributor().test().assertValue(
                ContributorState.builder()
                        .loading(false)
                        .contributors(contributors)
                        .build()
        );

    }

    @Test
    public void contributorsError() throws Exception {
        viewModel.contributorsError().accept(new IOException());

        viewModel.contributor().test().assertValue(
                ContributorState.builder()
                        .loading(false)
                        .errorRes(R.string.api_error_contributors)
                        .build()
        );
    }
}