/**
 *
 */
package app.coreproject.service;

import app.coreproject.contract.ResponseContract;
import app.coreproject.model.User;


/**
 * @author toduc
 *
 */
public interface IUserService {
    public String login(String userName, String pass);

    public ResponseContract<?> create(User user);

    public ResponseContract<?> update(User user);

    public ResponseContract<?> delete(int userId);

    ResponseContract<?> getUserInfo();
}
