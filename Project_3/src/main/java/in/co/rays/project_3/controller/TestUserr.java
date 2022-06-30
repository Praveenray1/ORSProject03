package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.UserModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/TestUserr" })
public class TestUserr extends BaseCtl {
	public static final String OP_SIGN_UP = "SignUp";

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "first Name"));
			pass = false;
		} else if (DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "Name can contain only alphabets");

		}
		return pass;

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletUtility.forward(getView(), req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("operation");
		long id = DataUtility.getLong(request.getParameter("id"));
		UserModelInt userModel = ModelFactory.getInstance().getUserModel();
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			UserDTO dto = (UserDTO) populateDTO(request);
			try {
				userModel.add(dto);
				ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Successfully", request);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
