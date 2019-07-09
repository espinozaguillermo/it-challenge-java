package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import app.Persona;
import net.miginfocom.swing.MigLayout;

public class PersonaUI extends JPanel {

//	private JTextField idField = new JTextField(10);
	private JTextField nombreField = new JTextField(40);
	private JTextField apellidoField = new JTextField(40);
	private JTextField tipodocField = new JTextField(30);
	private JTextField documentoField = new JTextField(30);
	private JTextField fechanacField = new JTextField(30);

	private JButton createButton = new JButton("Nuevo");
	private JButton updateButton = new JButton("Editar");
	private JButton deleteButton = new JButton("Eliminar");
	// ... updateButton, deleteButton, firstButton, prevButton, nextButton,
	// ...lastButton
	private PersonaBean bean = new PersonaBean();

	public PersonaUI() {
		setBorder(new TitledBorder(new EtchedBorder(), "Detalle Persona"));
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
		try {
			setFieldData(bean.getCurrent());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		panel.add(createButton);
		createButton.addActionListener((ActionListener) new ButtonHandler());
		panel.add(createButton);
		updateButton.addActionListener((ActionListener) new ButtonHandler());
		panel.add(deleteButton);
		deleteButton.addActionListener((ActionListener) new ButtonHandler());
		// ...
//	      panel.add(lastButton);
//	      lastButton.addActionListener(new ButtonHandler());
		return panel;
	}

	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
//	      panel.add(new JLabel("ID"), "align label");
//	      panel.add(idField, "wrap");
//	      idField.setEnabled(false);
		panel.add(new JLabel("Nombre"), "align label");
		panel.add(nombreField, "wrap");
		panel.add(new JLabel("Apellido"), "align label");
		panel.add(apellidoField, "wrap");
		panel.add(new JLabel("Tipo Documento"), "align label");
		panel.add(tipodocField, "wrap");
		panel.add(new JLabel("Documento"), "align label");
		panel.add(documentoField, "wrap");
		panel.add(new JLabel("Fecha Nacimiento"), "align label");
		panel.add(fechanacField, "wrap");

		return panel;
	}

	private Persona getFieldData() throws Exception {
		Persona p = new Persona();
		p.setNombre(nombreField.getText());
		p.setApellido(apellidoField.getText());
		TipoDocumento tipodoc = TipoDocumento.DNI;
		if (tipodocField.getText() != "DNI") {
			tipodoc = TipoDocumento.LIBRETACIVICA;
		}
		p.setTipoDocumento(tipodoc);
		if (documentoField.getText() != "") {
			p.setNroDocumento(Integer.parseInt(documentoField.getText()));
		}
		if (fechanacField.getText() != "") {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = simpleDateFormat.parse(fechanacField.getText());
			p.setFechaNacimiento(date);
		}

		return p;
	}

	private void setFieldData(Persona p) {
//	      idField.setText(String.valueOf(p.getPersonId()));
		nombreField.setText(p.getNombre());
		apellidoField.setText(p.getApellido());
		tipodocField.setText(p.getTipoDocumento().toString());
		documentoField.setText(String.valueOf(p.getNroDocumento()));
		try {
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = simpleDateFormat.parse(p.getFechaNacimiento().toString());

			SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			Date parsedDate = sdf.parse(p.getFechaNacimiento().toString());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			fechanacField.setText(simpleDateFormat.format(parsedDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isEmptyFieldData() {
		return (nombreField.getText().trim().isEmpty() && apellidoField.getText().trim().isEmpty()
				&& tipodocField.getText().trim().isEmpty() && documentoField.getText().trim().isEmpty()
				&& fechanacField.getText().trim().isEmpty());
	}

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Persona p;
			try {
				p = getFieldData();
				switch (e.getActionCommand()) {
				case "Guardar":
					if (isEmptyFieldData()) {
						JOptionPane.showMessageDialog(null, "No se puede guardar con valores vacios");
						return;
					}
					if (bean.create(p) != null)
						JOptionPane.showMessageDialog(null, "Nueva Persona creada!");
					createButton.setText("Nuevo");
					break;
				case "Nuevo":
//		            p.setPersonId(new Random()
//		            .nextInt(Integer.MAX_VALUE) + 1);
					p.setNombre("");
					p.setApellido("");
					p.setTipoDocumento(TipoDocumento.DNI);
					p.setNroDocumento(0);
					p.setFechaNacimiento(new Date());
					setFieldData(p);
					createButton.setText("Guardar");
					break;
				case "Editar":
					if (isEmptyFieldData()) {
						JOptionPane.showMessageDialog(null, "No se pudo actualizar");
						return;
					}
					if (bean.update(p) != null)
						JOptionPane.showMessageDialog(null,
								"Persona ID:" + String.valueOf(p.getIdentificador() + " actualizada correctamente"));
					break;
				case "Eliminar":
					if (isEmptyFieldData()) {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar");
						return;
					}
					p = bean.getCurrent();
					bean.delete();
					JOptionPane.showMessageDialog(null,
							"Persona ID:" + String.valueOf(p.getIdentificador() + " eliminada correctamente"));
					break;
//		         case "First":
//		            setFieldData(bean.moveFirst()); break;
//		         case "Previous":
//		            setFieldData(bean.movePrevious()); break;
//		         case "Next":
//		            setFieldData(bean.moveNext()); break;
//		         case "Last":
//		            setFieldData(bean.moveLast()); break;
				default:
					JOptionPane.showMessageDialog(null, "Comando invalido");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}