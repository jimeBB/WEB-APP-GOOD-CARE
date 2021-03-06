import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import objetos.Cuenta;
import objetos.Paciente;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;

@WebServlet("/ShowPacientes")
public class ShowPacientes extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response){

		try{
			//PrintWriter debug = new PrintWriter("Debug1.txt","UTF-8");
			//PrintWriter writer = new PrintWriter("/Users/alanzavala/Desktop/DBCurso/proyecto1.txt", "UTF-8");
			String base = getServletContext().getInitParameter("base");
			String usuario = getServletContext().getInitParameter("usuario");
			String password = getServletContext().getInitParameter("pass");

			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/"+base+"?useSSL=false&allowPublicKeyRetrieval=true";
		
			System.out.println(url);
			Connection con = DriverManager.getConnection(url,usuario,password);

			Statement stat = con.createStatement();
			
			String nombre=request.getParameter("nombre");
			int cuenta = Integer.parseInt(request.getParameter("cuenta"));
            int window= Integer.parseInt(request.getParameter("pestana"));

            String sql = "SELECT ID FROM colaborador WHERE cuenta = "+cuenta+";";
			ResultSet res = stat.executeQuery(sql);

			int id_colab = 0;
			while(res.next()){
				id_colab = res.getInt("ID");
			}

			if(window == 1){
				sql = "SELECT * FROM paciente WHERE idMedico = "+ id_colab +";";
				res = stat.executeQuery(sql);
			}else if(window == 2){
				sql = "SELECT * FROM paciente WHERE idEntrenador = "+ id_colab +";";
				res = stat.executeQuery(sql);
			}

			Vector<Paciente> pacientes = new Vector<Paciente>();
			
			
            while(res.next()){
                Paciente aux = new Paciente();
                aux.setId(res.getInt("ID"));
                aux.setCuenta(res.getInt("cuenta"));
                aux.setNombre(res.getString("nombre"));
                aux.setApellido(res.getString("apellido"));  
                aux.setEdad(res.getString("edad"));
                aux.setTipoU(res.getString("tipo_u"));     
                aux.setGenero(res.getString("genero"));
                aux.setIdRutina(res.getInt("idRutina"));  
                aux.setContrasenia(res.getString("contrasenia")); 
                aux.setIdMedico(res.getInt("idMedico"));   
                aux.setIdEntrenador(res.getInt("idEntrenador"));       
                pacientes.add(aux);

            }

			stat.close();
            con.close();
            
			request.setAttribute("pacientes", pacientes);
			request.setAttribute("response", nombre);
            request.setAttribute("response2", cuenta);
            request.setAttribute("response3", window);


			RequestDispatcher disp = getServletContext().getRequestDispatcher("/showPacientes.jsp");

			if(disp!=null){
				//writer.print("Hola palan");
				//writer.close(); 
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