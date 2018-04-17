package frames;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import com.bankroute.user.User;
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

	/**
	 * Creates new form UserViewFrame
	 */
	public UserViewFrame(User user) {
		initComponents();
		Vector<User> customersInCharge = user.getCustomersInCharge();
		int i = 0;
		Object datas[][] = new Object[customersInCharge.size()][7];

		DefaultTableModel model = new DefaultTableModel();
		for (User customerIncharge : customersInCharge) {
			datas[i][0] = customerIncharge.getId();
			datas[i][1] = customerIncharge.getRole();
			datas[i][2] = customerIncharge.getFirstName();
			datas[i][3] = customerIncharge.getLastName();
			datas[i][4] = customerIncharge.getMail();
			datas[i][5] = customerIncharge.getAddress();
			datas[i][6] = customerIncharge.getPassword();
			model.addRow(datas[i]);
			i++;
		}
		UserTable.setModel(model);
		UserTable.repaint();
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
		UserTable = new javax.swing.JTable();
		AddUserButton = new javax.swing.JButton();
		DeleteUserButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		UserTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
		UserTable.setColumnSelectionAllowed(true);
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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(443)
					.addComponent(AddUserButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DeleteUserButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)
					.addGap(38))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addGap(40)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(DeleteUserButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(AddUserButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(11))
		);
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>

	private void DeleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void AddUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
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
	// End of variables declaration
}