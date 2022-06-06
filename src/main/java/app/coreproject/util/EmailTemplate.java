/**
 *
 */
package app.coreproject.util;

/**
 * @author To Duc
 *
 *         Jan 17, 2021--2:23:00 PM
 */
public class EmailTemplate {
    public static final String email = "info@propatek.vn";
    public static final String password = "propatek123";
    public static final String register = "<p style=\"color:blue\">Please confirm your email address</p>\r\n"
            + "To finish setting up your account, we just need to make sure this email address is yours.\r\n"
            + "Please click  <a href=\"{{link_url}}\">Here</a>  to complete your  registration.   \r\n" + "\r\n"
            + "If you didn't request this registration, you can safely ignore this email. Someone else might have typed your email address by mistake.\r\n"
            + "";
    public static final String forgetPassword = "You recently requested a new password for your Propatek.vn account. If this is you, please click on the link below.\r\n"
            + "<br>" + "<a href=\"{{link_url}}\">Reset your password</a><br> \r\n"
            + "If you do not request to reset your password or have any questions, please Contact us. \r\n";
    public static final String userContact = "Dear Admin,<br>"
            + "Có một yêu cầu mua hàng vừa được gửi email: ${email}, SĐT : ${phone}.<br>"
            + "Nội dung: ${content}<br>"
            + "Yêu cầu dành cho supplier: ${supname}, email: ${sup_email}, Sđt: ${sup_phone}.";
    public static final String supplierContact = "Dear Admin,<br>"
            + "Có một yêu cầu bán hàng vừa được gửi email: ${email}, SĐT : ${phone}.<br>"
            + "Nội dung: ${content}<br>"
            + "Yêu cầu dành cho user: ${username}, email: ${user_email}, Sđt: ${user_phone}.";
}
