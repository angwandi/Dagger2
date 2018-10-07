package app.a2ms.dagger2.details;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class RepoDetailState {

    static Builder builder() {
        return new AutoValue_RepoDetailState.Builder();
    }

    abstract boolean loading();

    @Nullable
    abstract String name();

    @Nullable
    abstract String description();

    @Nullable
    abstract String createdDate();

    @Nullable
    abstract String updatedDate();

    @Nullable
    abstract Integer errorRes();

    @AutoValue.Builder
    abstract static class Builder {

        abstract Builder loading(boolean loading);

        abstract Builder name(String name);

        abstract Builder description(String description);

        abstract Builder createdDate(String createdDate);

        abstract Builder updatedDate(String updatedDate);

        abstract Builder errorRes(Integer errorRes);

        abstract RepoDetailState build();
    }
}
