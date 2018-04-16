package frames;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import com.bankroute.user.User;
import java.awt.Font;
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
		Vector<User> vectUser = user.getCustomersInCharge();

		String header[] = { "id", "firstname", "lastname", "password", "mail", "address", "role" ,""};
		Object[] model = new Object[8];

		DefaultTableModel modelTable = new DefaultTableModel();
		modelTable.setColumnIdentifiers(header);

		JButton editButton=new JButton();
		editButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		editButton.setText("edit");
		editButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//todo
			}
		});
		int i = 0;
		for (User customer : vectUser) {
			model[0] = customer.getId();
			model[1] = customer.getFirstName();
			model[2] = customer.getLastName();
			model[3] = customer.getPassword();
			model[4] = customer.getMail();
			model[5] = customer.getAddress();
			model[6] = customer.getRole();
			model[7]="";

			modelTable.addRow(model);
			i++;
		}
	
		UserTable.setModel(modelTable);
		editButton.paint(getGraphics());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        UserTable.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "First name", "Last name", "Address", "Mail", "role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        UserTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
        UserTable.setMinimumSize(new java.awt.Dimension(90, 15));
        UserTable.setRowHeight(20);
        UserTable.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(UserTable);
        UserTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(20)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(AddUserButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(EditButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(DeleteUserButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 899, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(DeleteUserButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(EditButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddUserButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
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
}
