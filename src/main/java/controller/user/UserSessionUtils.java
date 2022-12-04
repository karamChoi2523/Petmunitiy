package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "userId";
    /* 현재 로그인한 사용자의 ID를 구함 */
    public static String getLoginId(HttpSession session) {
        String loginId = (String)session.getAttribute("loginId");
        return loginId;
    }
    
    public static String getLoginUserId(HttpSession session) {
        String userId = (String)session.getAttribute(USER_SESSION_KEY);
        return userId;
    }

    /* 로그인한 상태인지를 검사 */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginId(session) != null) {
            return true;
        }
        return false;
    }

    /* 현재 로그인한 사용자의 ID가 userId인지 검사 */
    public static boolean isLoginUser(String userId, HttpSession session) {
        String loginUser = getLoginId(session);
        if (loginUser == null) {
            return false;
        }
        return loginUser.equals(userId);
    }
}
