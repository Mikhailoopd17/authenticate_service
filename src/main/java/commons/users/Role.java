package commons.users;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Role {
    private Integer id;
    private String name;
    private RoleType roleType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

//    public void setRoleType(String roleType) {
//        switch (roleType) {
//            case "admin":
//                this.roleType = RoleType.ADMIN;
//                break;
//            case "mentor":
//                this.roleType = RoleType.MENTOR;
//                break;
//            case "writer":
//                this.roleType = RoleType.WRITER;
//                break;
//            default:
//                this.roleType = RoleType.READER;
//        }
//    }
}
