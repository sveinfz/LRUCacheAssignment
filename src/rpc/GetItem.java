package rpc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CheckRecord
 */
@WebServlet("/api/v1/get/*")
public class GetItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetItem() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("GetServletInit");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String keyStr = request.getPathInfo().substring(1);
		Integer key = Integer.parseInt(keyStr);
		System.out.println(key);
		
		ServletContext context = request.getServletContext();
		LRUCache cache = (LRUCache) context.getAttribute("cache");
		int result = cache.get(key);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		if (result == -1) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			try {
				obj.put("Status", 404);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.setStatus(HttpServletResponse.SC_OK);
			try {
				obj.put("Status", 200);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject cur = new JSONObject();
			try {
				cur.put("key", key);
				cur.put("value", result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				obj.put("content", cur);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.print(obj.toString());
		System.out.println(obj.toString());
		out.flush();
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
