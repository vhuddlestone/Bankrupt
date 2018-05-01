package frames;

import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.bankrupt.tools.BankAccountManager;
import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.bankaccount.Operation;
import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.tools.CustomerOperation;
import com.bankrupt.tools.Values;
import com.bankrupt.user.User;
import javax.swing.JTable;

/**
 *
 * @author valentin
 */
public class CustomerAccountsFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form CustomerAccountsFrame
	 */
	private SQLInteraction sqlInteraction;
	private int selectedAccountIndex = -1;

	private javax.swing.JTable accountsTable;
	private javax.swing.JLabel comptesLabel;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JLabel operationsLabel;
	private javax.swing.JTable operationsTable;

	private BankAccountManager bankAccountManager;
	private CustomerOperation customerOperations;
	Vector<BankAccount> vectAccounts;

	public CustomerAccountsFrame(User user, SQLInteraction sqlInteraction) {
        initComponents();
        this.sqlInteraction=sqlInteraction;
        bankAccountManager=new BankAccountManager(sqlInteraction);
        customerOperations= new CustomerOperation();
        this.vectAccounts = bankAccountManager.getUserAccounts(user);
        DefaultTableModel tableModel=parseAccountsToJTableModel(vectAccounts);
        accountsTable.setModel(tableModel);
    }

	private void initComponents() {

        comptesLabel = new javax.swing.JLabel();
        operationsLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        accountsTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        operationsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        comptesLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        comptesLabel.setText("Vos comptes :");

        operationsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        operationsLabel.setText("Opérations :");

        accountsTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        accountsTable.addMouseListener(new MouseListener(){
		    @Override
		    public void mouseClicked(MouseEvent e){
		        int selectedAccount = accountsTable.getSelectedRow();
		        if(selectedAccount !=-1) {
			        updateOperationsTable(selectedAccount);
		        }
		    }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        jScrollPane3.setViewportView(accountsTable);

        operationsTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jScrollPane4.setViewportView(operationsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(comptesLabel)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operationsLabel)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(operationsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comptesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }
        
        // </editor-fold>  

//    /**
//     * This method is called from within the constructor to initialize the form.
//     * WARNING: Do NOT modify this code. The content of this method is always
//     * regenerated by the Form Editor.
//     */
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
//    private void initComponents() {
//
//        accountsTable = new javax.swing.JTable();
//        accountsTable = new JTable();
//        operationsTable = new javax.swing.JTable();
//        operationsTable = new JTable();
//        comptesLabel = new javax.swing.JLabel();
//        operationsLabel = new javax.swing.JLabel();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//
//        accountsTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
//        accountsTable.setFont(new java.awt.Font("Tahoma", 0, 18));
//        accountsTable.addMouseListener(new MouseListener(){
//		    @Override
//		    public void mouseClicked(MouseEvent e){
//		        int selectedAccount = accountsTable.getSelectedRow();
//		        if(selectedAccount !=-1) {
//			        updateOperationsTable(selectedAccount);
//		        }
//		    }
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//
//
//        operationsTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
//        operationsTable.setFont(new java.awt.Font("Tahoma", 0, 18));
//
//        comptesLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
//        comptesLabel.setText("Vos comptes :");
//
//        operationsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
//        operationsLabel.setText("Op\u00E9rations :");
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(26, 26, 26)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(accountsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(comptesLabel))
//                .addGap(18, 18, 18)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(operationsLabel)
//                    .addComponent(operationsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(157, 157, 157)
//                .addComponent(comptesLabel)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                .addComponent(accountsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addComponent(operationsLabel)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(operationsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(21, 21, 21))
//        );
//
//        pack();
//    }// </editor-fold>      
    
    private DefaultTableModel parseOperationsToJTableModel(Vector<Operation> vectOperation) {
    	DefaultTableModel modelTable= new DefaultTableModel();
    	
    	//TODO TOFINISH
    	int index=0;
    	
		String header[] = { "Montant", "Compte", "Date"};
		modelTable.setColumnIdentifiers(header);
		
		for (Operation operation : vectOperation) {
			Object[] model = new Object[8];
			model[0]=operation.getAmount();

			if(selectedAccountIndex==operation.getReceiver()) {
				model[1]=operation.getSender();
			}else {
				model[1]=operation.getReceiver();
			}
			model[2]=operation.getDate();

			modelTable.addRow(model);
			index++;
		}
		return modelTable;
    }
    
	private void updateOperationsTable(int selectedAccount) {
		Vector<Operation> vectOperations=customerOperations.getOperationsOfAccount(vectAccounts.get(selectedAccount).getAccountNumber(), sqlInteraction);
		DefaultTableModel tableModel=parseOperationsToJTableModel(vectOperations);
		operationsTable.setModel(tableModel);
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
}