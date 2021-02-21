package commons.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {
    @JsonProperty("user_id")
    private Long userId;
    private String login;
    private Integer role;

    @JsonValue
    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
