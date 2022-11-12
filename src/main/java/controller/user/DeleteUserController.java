package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.UserInfo;
import model.service.UserManager;

public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("loginId");
    	log.debug("Delete User : {}", deleteId);

		UserManager manager = UserManager.getInstance();		
		HttpSession session = request.getSession();	
	
												// �Ǵ� 
			if(UserSessionUtils.isLoginUser(deleteId, session)) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)			
				manager.remove(deleteId);				// ����� ���� ����
				return "redirect:/user/logout";		// logout ó��
		}
		
		/* ������ �Ұ����� ��� */
		UserInfo user = manager.findUser(deleteId);	// ����� ���� �˻�
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);											     
		return "/user/view.jsp";		// ����� ���� ȭ������ �̵� (forwarding)	
	}
}