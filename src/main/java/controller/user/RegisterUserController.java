package controller.user;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.UserList;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	/*if (request.getMethod().equals("GET")) {	
    		// GET request: ȸ������ ��� form ��û	
    		log.debug("RegisterForm Request");
		
			return "/user/registerForm.jsp";   //  registerForm���� ����     	
	    }	*/

    	// POST request (ȸ�������� parameter�� ���۵�)
       	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       	UserList user = new UserList(
       		Integer.parseInt(request.getParameter("userId")),
			request.getParameter("loginId"),
			request.getParameter("loginPwd"),
			request.getParameter("userNickname"),
			formatter.parse(request.getParameter("userBirth")),
			request.getParameter("phoneNumber"),
			request.getParameter("gender"),
			request.getParameter("address"),
			Integer.parseInt(request.getParameter("petList"))
			);
		
        log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
	        return "redirect:/user/list";	// ���� �� ����� ����Ʈ ȭ������ redirect
	        
		} catch (ExistingUserException e) {	// ���� �߻� �� ȸ������ form���� forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}

