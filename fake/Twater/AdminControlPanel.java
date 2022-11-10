package fake.Twater;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Patterns.UserView;
import Patterns.VisitM;
import Patterns.Visited;


public class AdminControlPanel extends javax.swing.JFrame implements TreeSelectionListener
{

   private static AdminControlPanel instance = new AdminControlPanel();
   private Group root;
   private User current;
   private Group currentGroup;

    private AdminControlPanel() {
        root = new Group("Root");
        initComponents();
        current = null;
    }

    public DefaultMutableTreeNode getCurrentGroup()
    {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if(node == null) {
            node = (DefaultMutableTreeNode) tree.getModel().getRoot();
        }
        if(node.getUserObject().getClass().equals(User.class)) {
            node = (DefaultMutableTreeNode) node.getParent();
        }
        return node;
    }
    
    @Override
    public void valueChanged(TreeSelectionEvent select) 
    {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        
        if(node == null) {
            return;
        } 
        
        Object info = node.getUserObject();
        
        if(node.getUserObject().getClass().equals(User.class))
        {
            current = (User) info;
            currentGroup = Group.findGroup(current.getGroup());
        }
        else
        {
            current = null;
            currentGroup = (Group) info;
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent event) {
        if(current != null)
        {
            JFrame userWindow = new JFrame();
                    userWindow.setSize(new Dimension(400,400));
                    userWindow.setTitle(current.getUserID());
                    userWindow.setLayout(new BorderLayout());
                    userWindow.add(new UserView(current), BorderLayout.CENTER);
                    userWindow.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "User is not valid. Please choose again.");
        }
    }

    private void addUserActionPerformed(java.awt.event.ActionEvent event) {
        String id = this.userIDField.getText();
        if(currentGroup == null)
        {
            currentGroup = Group.findGroup("Root");
        }
        if(id.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter a valid input.");
        }
        else if(!User.exists(id))
        {
            User temp = new User(id, currentGroup.getUserID());
            if(current == null)
            {
                current = temp;
            }
            currentGroup.add(temp);
            DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
            model.insertNodeInto(new DefaultMutableTreeNode(temp), this.getCurrentGroup(), this.getCurrentGroup().getChildCount());
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "User with ID " + id + " already exists!");
        }
    }

    private void addGroupActionPerformed(java.awt.event.ActionEvent event) {
        String id = this.groupIDField.getText();
        if(currentGroup == null)
        {
            currentGroup = Group.findGroup("Root");
        }
        if(id.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter something");
        }
        else if(!Group.exists(id))
        {
            Group temp = new Group(id);
            currentGroup.add(temp);
            DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
            model.insertNodeInto(new DefaultMutableTreeNode(temp), this.getCurrentGroup(), this.getCurrentGroup().getChildCount());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Group " + id + " already exists!");
        }
    }

    private void groupTotalActionPerformed(java.awt.event.ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Total number of groups: " + Group.groups.size());
    }

    private void userTotalActionPerformed(java.awt.event.ActionEvent event) {
        Visited uservisit = new Visited();
        if(current != null)
        {
            current.accept(uservisit);
        }
    }

    private void messageTotalActionPerformed(java.awt.event.ActionEvent event) {
        VisitM messagevisit = new VisitM();
        if(current != null)
        {
            current.accept(messagevisit);
        }
    }

    private void positiveAmountActionPerformed(java.awt.event.ActionEvent event) {
       PositiveVisitor positivevisit = new PositiveVisitor();
        if(current != null)
        {
            current.accept(positivevisit);
        }
    }

    public static AdminControlPanel getInstance()
    {
        return instance;
    }

    private javax.swing.JButton addGroup;
    private javax.swing.JButton addUser;
    private javax.swing.JTextField groupIDField;
    private javax.swing.JButton groupTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton messageTotal;
    private javax.swing.JButton positiveAmount;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTree tree;
    private javax.swing.JPanel treeView;
    private javax.swing.JTextField userIDField;
    private javax.swing.JButton userTotal;



    @SuppressWarnings("unchecked")
    private void initComponents() {

        treeView = new javax.swing.JPanel();
        sp = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        userIDField = new javax.swing.JTextField();
        addUser = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        groupIDField = new javax.swing.JTextField();
        addGroup = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        userTotal = new javax.swing.JButton();
        groupTotal = new javax.swing.JButton();
        messageTotal = new javax.swing.JButton();
        positiveAmount = new javax.swing.JButton();

        
        jButton1.setText("Open User View");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                jButton1ActionPerformed(event);
            }
        });

        userTotal.setText("Show All Number of Users");
        userTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                userTotalActionPerformed(event);
            }
        });

        groupTotal.setText("Show All Number of Groups");
        groupTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                groupTotalActionPerformed(event);
            }
        });

        messageTotal.setText("Show All Number of Twats");
        messageTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                messageTotalActionPerformed(event);
            }
        });

        positiveAmount.setText("Show Positive %");
        positiveAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                positiveAmountActionPerformed(event);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeNode1 = new DefaultMutableTreeNode(root);
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        tree.addTreeSelectionListener(this);
        sp.setViewportView(tree);

        javax.swing.GroupLayout treeViewLayout = new javax.swing.GroupLayout(treeView);
        treeView.setLayout(treeViewLayout);
        treeViewLayout.setHorizontalGroup(
            treeViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(treeViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        treeViewLayout.setVerticalGroup(
            treeViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(treeViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
        );

        addUser.setText("Add New User");
        addUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                addUserActionPerformed(event);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUser))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        addGroup.setText("Add New Group");
        addGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                addGroupActionPerformed(event);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addGroup))
                .addContainerGap(14, Short.MAX_VALUE))
        );


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treeView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groupTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(messageTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(positiveAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(groupTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(messageTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positiveAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(treeView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }
    

}