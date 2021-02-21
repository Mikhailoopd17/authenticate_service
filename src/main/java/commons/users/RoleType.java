package commons.users;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RoleType {
    ADMIN(0),
    MENTOR(1),
    WRITER(2),
    READER(3);

    private Integer id;

    RoleType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @JsonValue
    public String getJson() {
        return this.toString().toLowerCase();
    }

    @JsonCreator
    public static RoleType fromJson(String id) {
        return Enum.valueOf(RoleType.class, id.toUpperCase());
    }

    public static RoleType fromId(Integer id) {
        for (RoleType type : RoleType.values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }
}
