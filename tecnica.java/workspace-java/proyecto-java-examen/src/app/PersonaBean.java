package app;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.sql.rowset.JdbcRowSet;
import com.sun.rowset.JdbcRowSetImpl;
import app.Persona;

public class PersonaBean {

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	static final String DB_USER = "admin";
	static final String DB_PASS = "admin";
	private JdbcRowSet rowSet = null;

	public PersonaBean() {
		try {
			Class.forName(JDBC_DRIVER);
			rowSet = new JdbcRowSetImpl();
			rowSet.setUrl(DB_URL);
			rowSet.setUsername(DB_USER);
			rowSet.setPassword(DB_PASS);
			rowSet.setCommand("SELECT * FROM persona");
			rowSet.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public Persona create(Persona p) {
		try {
			rowSet.moveToInsertRow();
//		         rowSet.updateInt("identificador", p.getIdentificador());
			rowSet.updateString("tipodoc", p.getTipoDocumento().toString());
			rowSet.updateInt("documento", p.getNroDocumento());
			rowSet.updateString("nombre", p.getNombre());
			rowSet.updateString("apellido", p.getApellido());
			rowSet.updateDate("fechanac", new java.sql.Date(p.getFechaNacimiento().getTime()));
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
				p = null;
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return p;
	}

	public Persona update(Persona p) {
		try {
			rowSet.updateString("tipodoc", p.getTipoDocumento().toString());
			rowSet.updateInt("documento", p.getNroDocumento());
			rowSet.updateString("nombre", p.getNombre());
			rowSet.updateString("apellido", p.getApellido());
			rowSet.updateString("fechanac", p.getFechaNacimiento().toString());
			rowSet.updateRow();
			rowSet.moveToCurrentRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return p;
	}

	public void delete() {
		try {
			rowSet.moveToCurrentRow();
			rowSet.deleteRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
		}

	}

//		   public Persona moveFirst() {
//		      Persona p = new Persona();
//		      try {
//		         rowSet.first();
//		         p.setPersonId(rowSet.getInt("personId"));
//		         p.setFirstName(rowSet.getString("firstName"));
//		         p.setMiddleName(rowSet.getString("middleName"));
//		         p.setLastName(rowSet.getString("lastName"));
//		         p.setEmail(rowSet.getString("email"));
//		         p.setPhone(rowSet.getString("phone"));
//
//		      } catch (SQLException ex) {
//		         ex.printStackTrace();
//		      }
//		      return p;
//		   }
//
//		   public Persona moveLast() {
//		      Persona p = new Persona();
//		      try {
//		         rowSet.last();
//		         p.setPersonId(rowSet.getInt("personId"));
//		         p.setFirstName(rowSet.getString("firstName"));
//		         p.setMiddleName(rowSet.getString("middleName"));
//		         p.setLastName(rowSet.getString("lastName"));
//		         p.setEmail(rowSet.getString("email"));
//		         p.setPhone(rowSet.getString("phone"));
//
//		      } catch (SQLException ex) {
//		         ex.printStackTrace();
//		      }
//		      return p;
//		   }
//
//		   public Persona moveNext() {
//		      Persona p = new Persona();
//		      try {
//		         if (rowSet.next() == false)
//		            rowSet.previous();
//		         p.setPersonId(rowSet.getInt("personId"));
//		         p.setFirstName(rowSet.getString("firstName"));
//		         p.setMiddleName(rowSet.getString("middleName"));
//		         p.setLastName(rowSet.getString("lastName"));
//		         p.setEmail(rowSet.getString("email"));
//		         p.setPhone(rowSet.getString("phone"));
//
//		      } catch (SQLException ex) {
//		         ex.printStackTrace();
//		      }
//		      return p;
//		   }

//		   public Persona movePrevious() {
//		      Persona p = new Persona();
//		      try {
//		            rowSet.next();
//		            if (rowSet.previous() == false)
//		         p.setPersonId(rowSet.getInt("personId"));
//		         p.setFirstName(rowSet.getString("firstName"));
//		         p.setMiddleName(rowSet.getString("middleName"));
//		         p.setLastName(rowSet.getString("lastName"));
//		         p.setEmail(rowSet.getString("email"));
//		         p..(rowSet.getString("phone"));
//
//		      } catch (SQLException ex) {
//		         ex.printStackTrace();
//		      }
//		      return p;
//		   }

	public Persona getCurrent() throws ParseException {
		Persona p = new Persona();
		try {
			if (rowSet.next()) {
				rowSet.moveToCurrentRow();
				p.setIdentificador(rowSet.getInt("identificador"));
				p.setNombre(rowSet.getString("nombre"));
				p.setApellido(rowSet.getString("apellido"));
				TipoDocumento tipodoc = TipoDocumento.DNI;
				if (rowSet.getString("tipodoc") != "DNI") {
					tipodoc = TipoDocumento.LIBRETACIVICA;
				}
				p.setTipoDocumento(tipodoc);
				p.setNroDocumento(rowSet.getInt("documento"));

//				SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
//                        Locale.ENGLISH);
//				Date parsedDate = sdf.parse(rowSet.getString("fechanac"));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//				Date date = simpleDateFormat.parse(simpleDateFormat.format(parsedDate));
				Date date = simpleDateFormat.parse(rowSet.getString("fechanac"));
				p.setFechaNacimiento(date);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;
	}

}