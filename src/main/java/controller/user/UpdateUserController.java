package controller.user;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;
import model.UserList;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	if (request.getMethod().equals("GET")) {	
    		// GET request: ȸ������ ���� form ��û	
    		// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
    		String updateId = request.getParameter("loginId");

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		UserManager manager = UserManager.getInstance();
			UserList user = manager.findUser(updateId);	// �����Ϸ��� ����� ���� �˻�
			request.setAttribute("user", user);			

			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(updateId, session)) {
				// ���� �α����� ����ڰ� ���� ��� ������̰ų� �������� ��� -> ���� ����
								
				return "/user/updateForm.jsp";   // �˻��� ����� ���� �� Ŀ�´�Ƽ ����Ʈ�� updateForm���� ����     
			}    
			
			// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));            
			return "/user/view.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
	    }	
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	// POST request (ȸ�������� parameter�� ���۵�)
    	UserList updateUser = new UserList(
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
    	log.debug("Update User : {}", updateUser);

		UserManager manager = UserManager.getInstance();
		manager.update(updateUser);			
        return "redirect:/user/list";			
    }
}