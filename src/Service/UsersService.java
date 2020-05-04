package Service;

import java.util.List;

import Dao.UsersDao;
import Entity.Users;

public class UsersService {
    UsersDao usersDao = new UsersDao();
    public UsersService() {

    }
    //根据用户ID查找该用户是否存在
    public boolean isExsit(String parauserid) {
        return usersDao.isExsit(parauserid);
    }
    //根据用户ID返回查找到的user
    public Users queryPersonByUserId(String parauserid) {
        return usersDao.queryUserByUserId(parauserid);
    }
    //增加新的用户
    public boolean addUser(Users user) {
        if(!usersDao.isExsit(user.getUserId())) {
            return usersDao.addUser(user);
        }else return false;
    }
}
