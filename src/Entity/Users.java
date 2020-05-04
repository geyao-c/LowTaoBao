package Entity;

public class Users {
    private String UserId;
    private String UserName;
    private String Pass;

    public Users(String userId,String userName,String pass) {
        UserId = userId;
        UserName = userName;
        Pass = pass;
    }
    public Users(){

    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPass() {
        return Pass;
    }

//    @Override
    public String toString() {
        return "Users{" +
                "UserId='" + UserId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Pass='" + Pass + '\'' +
                '}';
    }
}
