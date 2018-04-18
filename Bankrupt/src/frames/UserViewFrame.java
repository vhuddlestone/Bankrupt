package frames;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.bankroute.tools.CustomerAccountManagement;
import com.bankroute.user.Banker;
import com.bankroute.user.User;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author valentin
 */
public class UserViewFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int bankerRole =2;
	static final int customerRole = 1;
	static final int adminRole = 3;
	private User currentUser;
	private CustomerAccountManagement customerAccountManagement;
	/**
	 * Creates new form UserViewFrame
	 */
	public UserViewFrame(User user) {
		initComponents();
		this.currentUser = user;
		switch (user.getRole()){
			case bankerRole:
				
				break;
			case adminRole:
			
				break;
		}
		customerAccountManagement= new CustomerAccountManagement(user.getSQLInstance());
		Vector<User> vectUser = user.getCustomersInCharge();
		DefaultTableModel modelTable = parseUserToJTableModel(vectUser);
		UserTable.setModel(modelTable);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		
		jScrollPane1 = new javax.swing.JScrollPane();
		AddUserButton = new javax.swing.JButton();
		UserTable = new javax.swing.JTable();
		DeleteUserButton = new javax.swing.JButton();
		
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		UserTable.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
		UserTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "id", "First name", "Last name", "Address", "Mail", "role" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] types = new Class[] { java.lang.Integer.class, java.lang.String.class, java.lang.String.class,
					java.lang.String.class, java.lang.String.class, java.lang.Integer.class };
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		UserTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
		UserTable.setMinimumSize(new java.awt.Dimension(90, 15));
		UserTable.setRowHeight(20);
		UserTable.getTableHeader().setResizingAllowed(false);
		jScrollPane1.setViewportView(UserTable);
		UserTable.getColumnModel().getSelectionModel()
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		if (UserTable.getColumnModel().getColumnCount() > 0) {
			UserTable.getColumnModel().getColumn(0).setResizable(false);
			UserTable.getColumnModel().getColumn(1).setResizable(false);
			UserTable.getColumnModel().getColumn(2).setResizable(false);
			UserTable.getColumnModel().getColumn(3).setResizable(false);
			UserTable.getColumnModel().getColumn(4).setResizable(false);
			UserTable.getColumnModel().getColumn(5).setResizable(false);
		}

		AddUserButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		AddUserButton.setText("Add");
		AddUserButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AddUserButtonActionPerformed(evt);
			}
		});

		DeleteUserButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		DeleteUserButton.setText("Delete");
		DeleteUserButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DeleteUserButtonActionPerformed(evt);
			}
		});

		JButton EditButton = new JButton();
		EditButton.setText("Edit");
		EditButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		EditButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				EditUserButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(20)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
						.addComponent(AddUserButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(EditButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(DeleteUserButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 899, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(
						jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(DeleteUserButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(EditButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddUserButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addContainerGap()));
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>

	private void DeleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = UserTable.getSelectedRow();
		if(selectedRow!=-1) {
			int userId = (int) UserTable.getValueAt(UserTable.getSelectedRow(), 0);
			String error=customerAccountManagement.deleteUser(userId);
			if(error !="") {
				showMessageDialog(null, error, "Error", WARNING_MESSAGE);
			}
			updateTable();
		}else {
			showMessageDialog(null, "Select a user before click on DELETE", "Warning", WARNING_MESSAGE);
		}
	}

	private void AddUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
		UserDetailsFrame userCreateFrame = new UserDetailsFrame(currentUser.getSQLInstance(),null, currentUser.getId());
		System.out.println("currentUserId:="+currentUser.getId());
		userCreateFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent e) {
				updateTable();
			}
		});
		 userCreateFrame.setVisible(true);
	}

	private void EditUserButtonActionPerformed(ActionEvent evt) {
		System.out.println("selectedRow"+UserTable.getSelectedRow());
		if (UserTable.getSelectedRow() != -1) {
			int userId = (int) UserTable.getValueAt(UserTable.getSelectedRow(), 0);
			System.out.println(userId);
			Vector<User> vectCustomers = currentUser.getCustomersInCharge();
			User userToEdit = null;

			for (User customer : vectCustomers) {
				if (customer.getId() == userId) {
					userToEdit = customer;
					break;
				}
			}
			System.out.println("currentUserId:="+currentUser.getId());
			UserDetailsFrame editUserFrame = new UserDetailsFrame(currentUser.getSQLInstance(),userToEdit, currentUser.getId());
			editUserFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosed(java.awt.event.WindowEvent e) {
					updateTable();
					System.out.println("maj apres edit");
				}
			});
			editUserFrame.setVisible(true);
		}
	}

	private void updateTable() {
		switch(this.currentUser.getRole()) {
		case bankerRole:
			Vector<User> newCustomersInCharge;
			newCustomersInCharge=currentUser.getSQLInstance().getClientsFromBankerId(currentUser.getId());
			currentUser= new Banker(currentUser.getId(),currentUser.getAddress(), currentUser.getFirstName(), currentUser.getLastName(),currentUser.getMail(), currentUser.getPassword(), currentUser.getRole(), currentUser.getSQLInstance(),newCustomersInCharge);
			UserTable.setModel(parseUserToJTableModel(newCustomersInCharge));
			break;
		case adminRole:
			
			break;
		case customerRole:
			
			break;
		}
	}
	
	/**
	 * function who parse a vector of user on a table model who can be used to display data on JTable 
	 * @param vectUser 
	 * @return table Model
	 */
	private DefaultTableModel parseUserToJTableModel(Vector<User> vectUser) {
		DefaultTableModel modelTable = new DefaultTableModel();
		String header[] = { "id", "firstname", "lastname", "password", "mail", "address", "role", "" };

		modelTable.setColumnIdentifiers(header);

		for (User customer : vectUser) {
			Object[] model = new Object[8];
			model[0] = customer.getId();
			model[1] = customer.getFirstName();
			model[2] = customer.getLastName();
			model[3] = customer.getPassword();
			model[4] = customer.getMail();
			model[5] = customer.getAddress();
			model[6] = customer.getRole();

			modelTable.addRow(model);
		}
		
		return modelTable;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(UserViewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UserViewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UserViewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UserViewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

	}

	// Variables declaration - do not modify
	private javax.swing.JButton AddUserButton;
	private javax.swing.JButton DeleteUserButton;
	private javax.swing.JTable UserTable;
	private javax.swing.JScrollPane jScrollPane1;
}
