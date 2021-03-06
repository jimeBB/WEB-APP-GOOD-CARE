//Ver si se puede agregar un like a un integer para que solo muestre entrenadores
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import objetos.Colaborador;
import java.util.Vector; 

import javax.servlet.annotation.WebServlet;

@WebServlet("/showColaboradores")
public class ShowColaboradores
 extends HttpServlet{
 
	public void doPost(HttpServletRequest request, HttpServletResponse response){

		try{

			String base = getServletContext().getInitParameter("base");
			String usuario = getServletContext().getInitParameter("usuario");
			String password = getServletContext().getInitParameter("pass");


			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/"+base+"?useSSL=false&allowPublicKeyRetrieval=true";
		
			System.out.println(url);
			Connection con = DriverManager.getConnection(url,usuario,password);

			Statement stat = con.createStatement();
			
			String nombre = request.getParameter("nombre");
			int cuenta = Integer.parseInt(request.getParameter("cuenta"));
            
            int window = Integer.parseInt(request.getParameter("pestana"));
			
			String sql = "SELECT * FROM colaborador;";

			ResultSet res = stat.executeQuery(sql);

			Vector<Colaborador> colaboradores = new Vector<Colaborador>();

            while(res.next()){
                Colaborador aux = new Colaborador();
                aux.setId(res.getInt("ID"));
                aux.setCuenta(res.getInt("cuenta"));
                aux.setNombre(res.getString("nombre"));
                aux.setApellido(res.getString("apellido"));
                aux.setEdad(res.getString("edad"));
                aux.setGenero(res.getString("genero"));
                colaboradores.add(aux); 
            }

			stat.close();
            con.close();

			request.setAttribute("colaboradores", colaboradores);
			request.setAttribute("response", nombre);
            request.setAttribute("response2", cuenta);
            request.setAttribute("response3", window);

			RequestDispatcher disp = getServletContext().getRequestDispatcher("/showColaboradores.jsp");

			if(disp!=null){
				disp.forward(request, response);
			}
		}
		catch(Exception e){			
			try{
				e.printStackTrace();
			}
			catch(Exception e2){
				try{
				e.printStackTrace();
			}
			catch(Exception e3){
				e3.printStackTrace();
			}	
			}	
		}
	}

}