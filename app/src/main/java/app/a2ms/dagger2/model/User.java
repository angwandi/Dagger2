package app.a2ms.dagger2.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class User {

    public static JsonAdapter<User> jsonAdapter(Moshi moshi) {
        return new AutoValue_User.MoshiJsonAdapter(moshi);
    }

    public abstract long id();

    public abstract String login();
}
