package cn.yscme.blog.service;
import cn.yscme.blog.entity.User;

public interface UserService {
    /**name查找
     * @param username
     * @return
     */
    User findByUsername(String username);
    /**id查找
     * @param id
     * @return
     */
    User findById(int id);
    /**注册
     * @param username
     * @param password
     * @return
     * @throws Exception 
     */
    int insert(String username,String password);
    /**登录
     * @param name
     * @param password
     * @return
     */
    User login(String username,String password);
    /**获取登录用户信息
     * @param
     * @return
     */
    User getUser();
}
