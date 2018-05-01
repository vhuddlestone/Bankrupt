package frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.tools.Values;
import com.bankrupt.user.User;
import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.org.apache.bcel.internal.generic.Select;

public class InternalOperationFrame {

	//Swing components
	private JFrame frame;
	private javax.swing.JTable accountsTable;
	private javax.swing.JTextField amountField;
	private javax.swing.JComboBox<String> destAccountCombo;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JComboBox<String> sourceAccountCombo;
	private javax.swing.JButton virerButton;
	
	// usefull variables
	private User currentUser;
	private SQLInteraction sqlInteraction;
	private Vector<BankAccount> accountsVect= null;;

	/**
	 * Create the application.
	 */
	public InternalOperationFrame(User user, SQLInteraction sqlInteraction) {
		this.sqlInteraction=sqlInteraction;
		this.currentUser=user;
		this.accountsVect = user.accountManagement.getUserAccounts(user, sqlInteraction);
		initialize();
	}
	
	private DefaultTableModel parseAccountsToJTableModel(Vector<BankAccount> vectAccounts) {
    	DefaultTableModel modelList = new DefaultTableModel();
    	
		String header[] = { "Type", "Numero", "Solde" };
		
		modelList.setColumnIdentifiers(header);
		int index=0;
		for (BankAccount account : vectAccounts) {
			Object[] model = new Object[8];
			if(account.getAccountType()==Values.currentAccountType) {
				model[0]=(Values.currentAccount);
			}else {
				model[0]=account.getSavingType();
			}
			model[1]=(String.valueOf(account.getAccountNumber()));
			model[2]= account.getBalance();

			modelList.addRow(model);
			index++;
		}
		return modelList;
	}    

	private void virerButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	private void updateDestinationCombobox() {
		System.out.println(sourceAccountCombo.getSelectedIndex());
		if(sourceAccountCombo.getSelectedIndex() != -1) {
			if(accountsVect!=null) {
				destAccountCombo.removeAllItems();
				int n=0;
				for(BankAccount account : accountsVect) {
					if(n!=sourceAccountCombo.getSelectedIndex()) {
						destAccountCombo.addItem(String.valueOf(account.getAccountNumber()));	
					}
					n++;
				}
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		jScrollPane1 = new javax.swing.JScrollPane();
		accountsTable = new javax.swing.JTable();
		sourceAccountCombo = new javax.swing.JComboBox<>();
		destAccountCombo = new javax.swing.JComboBox<>();
		amountField = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		virerButton = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        accountsTable.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
	    DefaultTableModel tableModel=parseAccountsToJTableModel(accountsVect);
	    accountsTable.setModel(tableModel);
	    
        jScrollPane1.setViewportView(accountsTable);

        if(accountsVect!=null) {
        	for(BankAccount account : accountsVect) {
        		sourceAccountCombo.addItem(String.valueOf(account.getAccountNumber()));
        	}
        	updateDestinationCombobox();
        }
        sourceAccountCombo.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                updateDestinationCombobox();
            }
        });
        sourceAccountCombo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        destAccountCombo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        amountField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Montant :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Source :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Destination :");

        virerButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        virerButton.setText("Virer");
        virerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                virerButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Transferer de l'argent sur ses compte :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Comptes personnels :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(virerButton))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sourceAccountCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(destAccountCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sourceAccountCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(destAccountCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(virerButton)
                        .addGap(0, 167, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        frame.setVisible(true);
	}
}
