package team.arcticfox.frms.dataset;

import team.arcticfox.frms.program.environment.Constant;
import team.arcticfox.frms.security.Base64;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountInfo {
    private final int id;                       // Id.                  Type: Integer           Encode Type: None
    private final String username;              // Username.            Type: String            Encode Type: Base64
    private final String email;                 // Email.               Type: String            Encode Type: Base64
    private final String password;              // Password.            Type: String            Encode Type: Base64 -> MD5
    private final AccountPermission permission; // Account Permission   Type: AccountPermission Encode Type: None
    private final DateTime registrationTime;    // Registration Time.   Type: DateTime          Encode Type: None
    private final DateTime destructionTime;     // Destruction Time.    Type: DateTime          Encode Type: None
    private final DateTime lastLoginTime;       // Last Login Time.     Type: DateTime          Encode Type: None

    public AccountInfo(int id, String username, String email, String password, AccountPermission permission,
                       DateTime registrationTime, DateTime destructionTime, DateTime lastLoginTime
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.permission = permission;
        this.registrationTime = registrationTime;
        this.destructionTime = destructionTime;
        this.lastLoginTime = lastLoginTime;
    }

    public static AccountInfo Parse(ResultSet rs) {
        int id = 0;
        String username = "";
        String email = "";
        String password = "";
        AccountPermission permission = AccountPermission.NULL;
        DateTime registrationTime = null;
        DateTime destructionTime = null;
        DateTime lastLoginTime = null;
        try {
            id = rs.getInt(Constant.COLUMNLABEL_ID);
            username = Base64.decode(rs.getString(Constant.COLUMNLABEL_USERNAME));
            email = Base64.decode(rs.getString(Constant.COLUMNLABEL_EMAIL));
            password = Base64.decode(rs.getString(Constant.COLUMNLABEL_PASSWORD));
            permission = AccountPermission.parse(rs.getString(Constant.COLUMNLABEL_PERMISSION));
            registrationTime = DateTime.parse(rs.getString(Constant.COLUMNLABEL_REGISTRATIONTIME));
            destructionTime = DateTime.parse(rs.getString(Constant.COLUMNLABEL_DESTRUCTIONTIME));
            lastLoginTime = DateTime.parse(rs.getString(Constant.COLUMNLABEL_LASTLOGINTIME));

        } catch (SQLException e) {
            // Do nothing.
        }
        return new AccountInfo(id, username, email, password, permission, registrationTime, destructionTime, lastLoginTime);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AccountPermission getPermission() {
        return permission;
    }

    public DateTime getRegistrationTime() {
        return registrationTime;
    }

    public DateTime getDestructionTime() {
        return destructionTime;
    }

    public DateTime getLastLoginTime() {
        return lastLoginTime;
    }
}
