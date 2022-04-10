package linh.ui;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;

import linh.model.Client;
import linh.model.GroupProducts;
import linh.model.Product;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;


public class WH extends JFrame {
	private Container contentPane;
	private JTabbedPane tbp;
	private JTabbedPane tabAdmin;

	public GroupProducts iphone,samsung, nokia;
	public GroupProducts gprSelected=null;
	ArrayList<Product> listProduct=new ArrayList<Product>();
	ArrayList<Product> listSavePR=new ArrayList<Product>();
	ArrayList<Product> listCart=new ArrayList<Product>();
	ArrayList<GroupProducts> groupPr;

	private Product product;
	private Product productI;
	private Product productS;
	private Product productN;


	int year;
	int sttPr=1;
	ArrayList<Client> listClient=new ArrayList<Client>();

	//admin import product	
	private JTextField txtIDPr;
	private JTextField txtNamePr;
	private JTextField txtNumberOfPr;
	private JTextField txtPrice;
	private JTextField txtMFG;
	private JTextField txtEXP;

	private JComboBox jcbTypePr;
	private JTable jtableImport;
	private DefaultTableModel tableImport=null;

	private JButton btnImport;
	private JButton btnSavePr ;
	private JButton btnDeletePr;

	//admin check products	
	private JComboBox cbbCheck;
	private JButton btnCheck;
	private JButton btnDelete;
	private JButton btnAddCheck;
	private JTextField txtIDAdd;
	private JTextField txtQuantityAdd;
	private JTextField txtCheckType;
	private JTextField txtCheckName;
	private JTextField txtCheckID;
	private DefaultTableModel dftCheck;
	private JTable tableCheck;

	//admin check client
	private JButton btnReg;
	private DefaultTableModel dftClient;
	private JButton btnClientCheck;
	private JTree tree;
	private JTable tableTree;
	private DefaultMutableTreeNode root;
	DefaultMutableTreeNode nodeCl=null;

	private JTable tableClient;
	private JTextField txtCheckClientID;
	private JTextField txtClientPhone;
	private JTextField txtRegName;
	private JTextField txtRegPhone;
	private JTextField txtRegAddress;

	//client find products
	private JPanel pnFindSe;
	private JTextField txtFindSe;
	private JTextField txtPriceSe;
	private JTextField txtTypeSe;

	// info products, which client found	
	private JPanel pnInfo;
	private DefaultTableModel dftCheckBuy=null;
	private JTable tableCheckBuy;
	protected boolean flatSe=false;
	private JButton btnAddCart;

	//client selects products to add to the cart
	private JPanel pnCart;
	private DefaultTableModel tableCart=null;
	private JTable jtableCart;
	private JButton btnOrder;
	private JButton btnDeleteCart;
	private JTextField txtSum;
	int sumBuy=0;

	// client enters information and makes a purchase
	private JPanel pnInfoClient;
	private JTextField txtNameCl;
	private JTextField txtMail;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JCheckBox reg;
	private JButton btnBuyPr;

	private JPanel pnInfoThanks;



	public void showWindow() {
		this.setSize(950,700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public WH(String title) {
		super(title);
		addData();
		addControls();
		addEvents();
	}

	private void addData() {
		// TODO Auto-generated method stub
		groupPr= new ArrayList<GroupProducts>();
		iphone= new GroupProducts("G1","Iphone");
		samsung= new GroupProducts("G2","Samsung");
		nokia= new GroupProducts("G3","Nokia");
		groupPr.add(iphone);
		groupPr.add(samsung);
		groupPr.add(nokia);

		//добавить 1 товар в группу iphone
		productI =new Product("I1","iphoneX","100", "25000","2019","2029");
		iphone.getProducts().add(productI);
		productI.setGroupPr(iphone);
		listSavePR.add(productI);

		//добавить 1 товар в группу samsung
		productS =new Product("S1","SamsungJ7","43", "10000","2018","2029");
		samsung.getProducts().add(productS);
		productS.setGroupPr(samsung);
		listSavePR.add(productS);

		//добавить 1 товар в группу nokia
		productN =new Product("N1","Nokia1012","100", "5000","2009","2019");
		nokia.getProducts().add(productN);
		productN.setGroupPr(nokia);
		listSavePR.add(productN);

	}

	private void addControls() {
		panelTitle();
		panelTabHome();
		panelTabAdmin();
		panelTabClient();
	}
	public void panelTitle() {
		contentPane= getContentPane();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnTitle = new JPanel();
		pnTitle.setForeground(new Color(0, 0, 0));
		pnTitle.setBackground(new Color(25, 25, 112));
		pnTitle.setBounds(0, 0,950, 60);
		contentPane.add(pnTitle);
		pnTitle.setLayout(new BorderLayout());
		JLabel jlabTitle = new JLabel("Управление складской логистикой");
		jlabTitle.setBackground(new Color(176, 224, 230));
		jlabTitle.setForeground(new Color(230, 230, 250));
		jlabTitle.setFont(new Font("Sylfaen", Font.BOLD, 20));
		jlabTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnTitle.add(jlabTitle,BorderLayout.CENTER);

		tbp = new JTabbedPane(JTabbedPane.TOP);
		tbp.setBounds(10, 70, 910, 580);
		contentPane.add(tbp);
	}
	public void panelTabHome() {
		JPanel pnHome= new JPanel();
		tbp.add("Home", pnHome);
		pnHome.setLayout(null);

		JLabel jlabWelcome = new JLabel("ДОБРО ПОЖАЛОВАТЬ В НАШЕ ПРОГРАММНОЕ "
				+ "ОБЕСПЕЧЕНИЕ ДЛЯ УПРАВЛЕНИЯ СКЛАДСКОЙ ЛОГИСТИКОЙ");
		jlabWelcome.setBounds(0, 11, 900, 29);
		jlabWelcome.setForeground(new Color(0, 100, 0));
		jlabWelcome.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		jlabWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		pnHome.add(jlabWelcome);

		JLabel jlabImage = new JLabel("");
		jlabImage.setBounds(10, 61, 860, 418);
		jlabImage.setForeground(new Color(255, 140, 0));
		jlabImage.setHorizontalAlignment(SwingConstants.CENTER);
		jlabImage.setIcon(new ImageIcon("image/isometric-warehouse.gif"));
		pnHome.add(jlabImage);
	}
	public void panelTabAdmin() {
		JPanel pnAdmin= new JPanel();
		tbp.add("ADMIN", pnAdmin);
		pnAdmin.setLayout(null);
		tabAdmin = new JTabbedPane(JTabbedPane.LEFT);
		tabAdmin.setBounds(10, 11, 865, 506);
		pnAdmin.add(tabAdmin);
		panelTabAdminImport();
		panelTabAdminCheckProduct();
		panelTabAdminCheckClient();
		//panelTabAdminInvoice();
	}
	private void panelTabAdminCheckClient() {		
		//split	
		JPanel pnCheckClient = new JPanel();
		tabAdmin.add("check the clients", pnCheckClient);
		pnCheckClient.setLayout(null);

		JLabel jlabListClient = new JLabel("List Of Clients ");
		jlabListClient.setForeground(new Color(107, 142, 35));
		jlabListClient.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
		jlabListClient.setHorizontalAlignment(SwingConstants.CENTER);
		jlabListClient.setBounds(59, 11, 672, 32);
		pnCheckClient.add(jlabListClient);

		JSplitPane splitClient = new JSplitPane();
		splitClient.setOneTouchExpandable(true);
		splitClient.setForeground(new Color(0, 128, 0));
		splitClient.setBounds(40, 50, 678, 173);
		pnCheckClient.add(splitClient);

		//split tree
		JScrollPane scrollPaneTree = new JScrollPane();
		scrollPaneTree.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneTree.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitClient.setLeftComponent(scrollPaneTree);

		root= new DefaultMutableTreeNode("Clients");
		tree = new JTree(root);
		tree.expandRow(0);
		scrollPaneTree.setViewportView(tree);

		//split client 
		JScrollPane scrollPaneClient = new JScrollPane();
		scrollPaneClient.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneClient.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitClient.setRightComponent(scrollPaneClient);
		tableClient = new JTable();

		dftClient= new DefaultTableModel();
		dftClient.addColumn("ID");
		dftClient.addColumn("Name");
		dftClient.addColumn("Phone");
		dftClient.addColumn("Address");
		dftClient.addColumn("quantity of orders");
		tableClient = new JTable(dftClient);
		scrollPaneClient.setViewportView(tableClient);		

		//find client
		JPanel pnFindClient = new JPanel();
		pnFindClient.setBorder(new TitledBorder(null, "Find Client", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 205)));
		pnFindClient.setBounds(40, 273, 274, 161);
		pnCheckClient.add(pnFindClient);
		pnFindClient.setLayout(null);

		JLabel jlabIdClient = new JLabel("ID Client");
		jlabIdClient.setBounds(20, 44, 73, 20);
		pnFindClient.add(jlabIdClient);
		txtCheckClientID = new JTextField(10);
		txtCheckClientID.setBounds(103, 42, 141, 25);
		pnFindClient.add(txtCheckClientID);

		JLabel jlabPhoneClient = new JLabel("Phone Client");
		jlabPhoneClient.setBounds(20, 78, 73, 25);
		pnFindClient.add(jlabPhoneClient);
		txtClientPhone = new JTextField(10);
		txtClientPhone.setBounds(103, 78, 141, 25);
		pnFindClient.add(txtClientPhone);

		btnClientCheck = new JButton("Check");
		btnClientCheck.setBounds(106, 114, 89, 23);
		pnFindClient.add(btnClientCheck);

		//reg. client 

		JPanel pnRegClient = new JPanel();
		pnRegClient.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		pnRegClient.setBounds(350, 261, 340, 200);
		pnCheckClient.add(pnRegClient);
		pnRegClient.setLayout(null);

		JLabel jlabReg = new JLabel("Registration ");
		jlabReg.setHorizontalAlignment(SwingConstants.CENTER);
		jlabReg.setBounds(30, 11, 296, 21);
		pnRegClient.add(jlabReg);

		JLabel JlabRegName = new JLabel("Full Name");
		JlabRegName.setBounds(64, 46, 55, 14);
		pnRegClient.add(JlabRegName);
		txtRegName = new JTextField(10);
		txtRegName.setBounds(143, 46, 164, 20);
		pnRegClient.add(txtRegName);

		JLabel jlabRegPhone = new JLabel("Phone");
		jlabRegPhone.setBounds(64, 77, 55, 14);
		pnRegClient.add(jlabRegPhone);
		txtRegPhone = new JTextField(10);
		txtRegPhone.setBounds(143, 77, 164, 20);
		pnRegClient.add(txtRegPhone);

		JLabel jlabRegAddress = new JLabel("Address");
		jlabRegAddress.setBounds(64, 108, 55, 14);
		pnRegClient.add(jlabRegAddress);
		txtRegAddress = new JTextField(10);
		txtRegAddress.setBounds(143, 108, 164, 20);
		pnRegClient.add(txtRegAddress);

		btnReg = new JButton("Sign Up");
		btnReg.setBounds(150, 140, 89, 23);
		pnRegClient.add(btnReg);

	}


	private void panelTabAdminCheckProduct() {
		JPanel pnCheck = new JPanel();
		pnCheck.setLayout(null);
		tabAdmin.add(pnCheck,"Check the product");

		//check box
		JLabel lblNewLabel = new JLabel("Check by:");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(10, 43, 61, 14);
		pnCheck.add(lblNewLabel);

		cbbCheck = new JComboBox();
		cbbCheck.setModel(new DefaultComboBoxModel(new String[] {"ALL","EXP", "Quantity"}));
		cbbCheck.setBounds(81, 43, 132, 22);
		pnCheck.add(cbbCheck);

		//find	
		JPanel panelFind = new JPanel();
		panelFind.setBorder(new TitledBorder(null, "Find:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panelFind.setForeground(new Color(255, 255, 0));
		panelFind.setBounds(20, 70, 200, 120);
		pnCheck.add(panelFind);
		panelFind.setLayout(null);

		JLabel jlabCheckN = new JLabel("Product:");
		jlabCheckN.setBounds(20, 28, 50, 20);
		panelFind.add(jlabCheckN);
		txtCheckName = new JTextField(10);
		txtCheckName.setBounds(87, 25, 100, 20);
		panelFind.add(txtCheckName);

		JLabel jlabCheckType = new JLabel("Type product:");
		jlabCheckType.setBounds(10, 59, 80, 14);
		panelFind.add(jlabCheckType);
		txtCheckType = new JTextField(10);
		txtCheckType.setBounds(87, 56, 100, 20);
		panelFind.add(txtCheckType);

		JLabel jlabCheckID = new JLabel("ID:");
		jlabCheckID.setBounds(35, 85, 46, 14);
		panelFind.add(jlabCheckID);
		txtCheckID = new JTextField(10);
		txtCheckID.setBounds(87, 85, 100, 20);
		panelFind.add(txtCheckID);

		//button 
		btnCheck = new JButton("Check");
		btnCheck.setBounds(75, 200, 89, 20);
		pnCheck.add(btnCheck);

		btnDelete= new JButton("Delete");
		btnDelete.setBounds(450, 270, 89, 20);
		pnCheck.add(btnDelete);
		pnCheck.add(panelFind);

		//table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(240, 25, 480, 230);
		pnCheck.add(scrollPane);


		dftCheck=new DefaultTableModel(new Object[][] {},
				new String[] {
						"ID ", "Type", "Name ", "Quantity", "Price(Rub)", "MFG", "EXP", "Status"
		}
				);
		tableCheck = new JTable(dftCheck);
		scrollPane.setViewportView(tableCheck);


		//add
		JPanel pnAddPR = new JPanel();
		pnAddPR.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Enter more product quantity", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 204)));
		pnAddPR.setBounds(290, 300, 380, 160);
		pnCheck.add(pnAddPR);
		pnAddPR.setLayout(null);

		JLabel jlabID = new JLabel("ID Product:");
		jlabID.setBounds(27, 35, 70, 14);
		pnAddPR.add(jlabID);
		txtIDAdd = new JTextField(10);
		txtIDAdd.setBounds(116, 35, 212, 20);
		pnAddPR.add(txtIDAdd);



		JLabel jlabQuantity = new JLabel("Quantity:");
		jlabQuantity.setBounds(38, 75, 70, 14);
		pnAddPR.add(jlabQuantity);
		txtQuantityAdd = new JTextField(10);
		txtQuantityAdd.setBounds(116, 75, 212, 20);
		pnAddPR.add(txtQuantityAdd);

		btnAddCheck = new JButton("Add");
		btnAddCheck.setBounds(167, 110, 89, 23);
		pnAddPR.add(btnAddCheck);

	}

	public void panelTabAdminImport() {
		JPanel pnAdd = new JPanel();
		tabAdmin.add("import products" ,pnAdd);
		pnAdd.setLayout(null);

		JLabel jlabTypePr = new JLabel("Type Products:");
		jlabTypePr.setBounds(50, 30, 112, 20);
		pnAdd.add(jlabTypePr);

		jcbTypePr = new JComboBox();
		jcbTypePr.setBounds(43, 50, 102, 30);
		pnAdd.add(jcbTypePr);

		JLabel jlabIDPr = new JLabel("ID Product:");
		jlabIDPr.setBounds(299, 33, 70, 14);
		pnAdd.add(jlabIDPr);

		txtIDPr = new JTextField(10);
		txtIDPr.setBounds(379, 27, 198, 20);
		pnAdd.add(txtIDPr);


		JLabel jlabNamePr = new JLabel("Name Product:");
		jlabNamePr.setBounds(289, 58, 90, 14);
		pnAdd.add(jlabNamePr);

		txtNamePr = new JTextField(10);
		txtNamePr.setBounds(379, 55, 198, 20);
		pnAdd.add(txtNamePr);

		JLabel jlabNumberOfPr = new JLabel("Quantity:");
		jlabNumberOfPr.setBounds(300, 86, 150, 14);
		pnAdd.add(jlabNumberOfPr);

		txtNumberOfPr = new JTextField(10);
		txtNumberOfPr.setBounds(379, 86, 198, 20);
		pnAdd.add(txtNumberOfPr);

		JLabel jlabPrice = new JLabel("Price(RUB):");
		jlabPrice.setBounds(299, 120, 70, 14);
		pnAdd.add(jlabPrice);

		txtPrice = new JTextField(10);
		txtPrice.setBounds(379, 117, 198, 20);
		pnAdd.add(txtPrice);

		JLabel jlabMFG = new JLabel("MFG:");
		jlabMFG.setBounds(324, 151, 32, 14);
		pnAdd.add(jlabMFG);

		txtMFG= new JTextField(10);
		txtMFG.setBounds(379, 148, 198, 20);
		pnAdd.add(txtMFG);


		JLabel jlabEXP = new JLabel("EXP:");
		jlabEXP.setBounds(324, 185, 32, 14);
		pnAdd.add(jlabEXP);

		txtEXP = new JTextField(10);
		txtEXP.setBounds(379,182 , 198, 20);
		pnAdd.add(txtEXP);


		tableImport= new DefaultTableModel();
		tableImport.addColumn("ID");
		tableImport.addColumn("Name Product");
		tableImport.addColumn("Type Product");
		tableImport.addColumn("Quantity");
		tableImport.addColumn("Price(Rub)");
		tableImport.addColumn("MFG");
		tableImport.addColumn("EXP");
		jtableImport= new JTable(tableImport);

		JScrollPane jscTabelAdd = new JScrollPane();
		jscTabelAdd.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jscTabelAdd.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jscTabelAdd.setBounds(34, 242, 650, 204);
		pnAdd.add(jscTabelAdd);
		jscTabelAdd.setViewportView(jtableImport);

		btnImport = new JButton("Add Product");
		btnImport.setBounds(590, 98, 130, 23);
		pnAdd.add(btnImport);

		btnSavePr = new JButton("Save");
		btnSavePr.setBounds(200, 456, 89, 23);
		pnAdd.add(btnSavePr);

		btnDeletePr = new JButton("Delete");
		btnDeletePr.setBounds(400, 456, 89, 23);
		pnAdd.add(btnDeletePr);	
	}

	//покупатель
	public void panelTabClient() {
		JPanel pnClient = new JPanel();
		tbp.addTab("CLIENT", null, pnClient, null);
		pnClient.setLayout(null);


		JLabel jlabWelcomeClient = new JLabel("Welcome to our warehouse");
		jlabWelcomeClient.setBackground(new Color(255, 255, 0));
		jlabWelcomeClient.setForeground(new Color(75, 0, 130));
		jlabWelcomeClient.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		jlabWelcomeClient.setHorizontalAlignment(SwingConstants.CENTER);
		jlabWelcomeClient.setBounds(132, 0, 610, 23);
		pnClient.add(jlabWelcomeClient);



		//find product
		pnFindSe = new JPanel();
		pnFindSe.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Find", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnFindSe.setBounds(280, 50, 300, 150);

		pnClient.add(pnFindSe);
		pnFindSe.setLayout(null);

		JLabel jlabProductFind = new JLabel("Product:");
		jlabProductFind.setBounds(33, 35, 75, 14);
		pnFindSe.add(jlabProductFind);
		txtFindSe = new JTextField(10);
		txtFindSe.setBounds(118, 35, 140, 17);
		pnFindSe.add(txtFindSe);

		JLabel jlabTypeBuy = new JLabel("Type:");
		jlabTypeBuy.setBounds(50, 65, 98, 14);
		pnFindSe.add(jlabTypeBuy);
		txtTypeSe = new JTextField(10);
		txtTypeSe.setBounds(118, 65, 140, 17);
		pnFindSe.add(txtTypeSe);

		JLabel jlabPriceBuy = new JLabel("Price:");
		jlabPriceBuy.setBounds(45, 95, 98, 14);
		pnFindSe.add(jlabPriceBuy);
		txtPriceSe = new JTextField(10);
		txtPriceSe.setBounds(118, 95, 140, 17);
		pnFindSe.add(txtPriceSe);

		//information product
		pnInfo = new JPanel();
		pnInfo.setBorder(new TitledBorder(null, "Product information", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnInfo.setBounds(330, 30, 560, 225);
		pnClient.add(pnInfo);
		pnInfo.setLayout(null);
		pnInfo.setVisible(false);

		//table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(30, 40, 500, 140);
		pnInfo.add(scrollPane);


		dftCheckBuy=new DefaultTableModel(new Object[][] {},
				new String[] {
						"ID ", "Type", "Name ", "Quantity in stock", "Price(Rub)", "MFG", "EXP", "Status"
		}
				);
		tableCheckBuy = new JTable(dftCheckBuy);
		scrollPane.setViewportView(tableCheckBuy);

		btnAddCart = new JButton("Add to cart");
		btnAddCart.setBounds(200, 190, 100, 23);
		pnInfo.add(btnAddCart);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 261, 885, 12);
		pnClient.add(separator);

		//Cart
		pnCart = new JPanel();
		pnCart.setForeground(Color.RED);
		pnCart.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cart", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pnCart.setBounds(200, 284, 500, 257);
		pnClient.add(pnCart);
		pnCart.setLayout(null);

		JScrollPane scpCart = new JScrollPane();
		scpCart.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scpCart.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scpCart.setBounds(25, 27, 450, 150);
		pnCart.add(scpCart);

		tableCart= new DefaultTableModel();
		tableCart.addColumn("STT");
		tableCart.addColumn("Product");
		tableCart.addColumn("ID");
		tableCart.addColumn("Quantily");
		tableCart.addColumn("Price(Rub)");
		tableCart.addColumn("Total");

		jtableCart= new JTable(tableCart);
		scpCart.setViewportView(jtableCart);

		JLabel labSum=new JLabel("total price:");
		labSum.setBounds(300, 180, 100, 20);
		txtSum= new JTextField(10);
		txtSum.setBounds(380,180,80,20);
		pnCart.add(labSum);
		pnCart.add(txtSum);

		btnOrder = new JButton("Add to bag");
		btnOrder.setBounds(291, 213, 100, 23);
		pnCart.add(btnOrder);

		btnDeleteCart = new JButton("Delete");
		btnDeleteCart.setBounds(90, 213, 89, 23);
		pnCart.add(btnDeleteCart);


		// thank
		pnInfoThanks = new JPanel();
		pnInfoThanks.setBounds(10, 284, 1100, 245);
		pnClient.add(pnInfoThanks);
		pnInfoThanks.setLayout(null);
		pnInfoThanks.setVisible(false);

		JLabel jlabThanks = new JLabel();
		jlabThanks.setBounds(40, 10, 1000, 200);
		ImageIcon myIcon= new ImageIcon(new ImageIcon("image/thank.jpg").getImage().getScaledInstance(800, 300, Image.SCALE_DEFAULT));
		jlabThanks.setIcon(myIcon);
		pnInfoThanks.add(jlabThanks);


		// information client
		pnInfoClient = new JPanel();
		//pnInfoClient.setBackground(new Color(173, 255, 47));
		pnInfoClient.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Client", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnInfoClient.setBounds(570, 290, 300, 245);
		pnClient.add(pnInfoClient);
		pnInfoClient.setLayout(null);
		pnInfoClient.setVisible(false);


		JLabel jlabNameCl = new JLabel("Your name:");
		jlabNameCl.setBounds(17, 24, 69, 14);
		pnInfoClient.add(jlabNameCl);
		txtNameCl = new JTextField(10);
		txtNameCl.setBounds(96, 21, 150, 20);
		pnInfoClient.add(txtNameCl);


		JLabel jlabPhone = new JLabel("Phone:");
		jlabPhone.setBounds(27, 55, 46, 14);
		pnInfoClient.add(jlabPhone);
		txtPhone = new JTextField(10);
		txtPhone.setBounds(96, 52, 150, 20);
		pnInfoClient.add(txtPhone);

		JLabel jlabAddress = new JLabel("Address:");
		jlabAddress.setBounds(17, 89, 55, 14);
		pnInfoClient.add(jlabAddress);
		txtAddress = new JTextField(10);
		txtAddress.setBounds(96, 86, 150, 20);
		pnInfoClient.add(txtAddress);

		JLabel jlabMail = new JLabel("Email:");
		jlabMail.setBounds(27, 121, 46, 14);
		pnInfoClient.add(jlabMail);
		txtMail = new JTextField(10);
		txtMail.setBounds(96, 119, 150, 18);
		pnInfoClient.add(txtMail);

		reg = new JCheckBox("you are registered");
		reg.setBounds(90, 149, 130, 23);
		pnInfoClient.add(reg);

		btnBuyPr = new JButton("Check out");
		btnBuyPr.setBounds(110, 197, 100, 23);
		pnInfoClient.add(btnBuyPr);
		btnBuyPr.setEnabled(false);

	}

	private void addEvents() {

		for (GroupProducts i:groupPr)
		{
			jcbTypePr.addItem(i);
		}
		//import
		jcbTypePr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jcbTypePr.getSelectedIndex()==-1)
					return;
				gprSelected= (GroupProducts)jcbTypePr.getSelectedItem();
			}
		});

		btnImport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Product prImport= new Product(txtIDPr.getText(),txtNamePr.getText(),
						txtNumberOfPr.getText(),txtPrice.getText(),txtMFG.getText(),txtEXP.getText());
				//gprSelected.getProducts().add(prImport);
				prImport.setGroupPr(gprSelected);
				Vector<String> vec= new Vector<String>();
				vec.add(prImport.getId());
				vec.add(prImport.getNameProduct());
				vec.add(prImport.getGroupPr()+"");
				vec.add(prImport.getNumber()+"");
				vec.add(prImport.getPrice()+"");
				vec.add(prImport.getMfg());
				vec.add(prImport.getExp());

				boolean i=true;
				for (Product pr: listSavePR)
					if (prImport.getId().equalsIgnoreCase(pr.getId()))
					{
						JOptionPane.showMessageDialog(null, "Этот id уже существует, пожалуйста, создайте новый");
						i=false;
						break;
					}
				if(i==true)
				{
					tableImport.addRow(vec);
					txtIDPr.setText("");
					txtNamePr.setText("");
					txtNumberOfPr.setText("");
					txtPrice.setText("");
					txtMFG.setText("");
					txtEXP.setText("");
					listProduct.add(prImport);	
				}
			}
		});
		btnSavePr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listSavePR.addAll(listProduct); 
				JOptionPane.showMessageDialog(null, "saved");
				listProduct.clear();
				tableImport.setRowCount(0);
			}
		});
		btnDeletePr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//sửa lỗi nếu không chọn thì không thực hi
				if(jtableImport.getSelectedRow()==-1)
					return;
				else
				{
					int row= jtableImport.getSelectedRow();
					int ret = JOptionPane.showConfirmDialog(null, "do you want to delete the product?",
							"delete", JOptionPane.YES_NO_OPTION);
					if (ret==JOptionPane.YES_OPTION)
					{
						tableImport.removeRow(row);
						listProduct.remove(row);
					}
					else
						return;
				}	
			}});

		//check
		cbbCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Calendar instance = Calendar.getInstance();
				if(cbbCheck.getSelectedIndex()==-1)
					return;

				//check by All			
				if (cbbCheck.getSelectedItem().equals("ALL"))
				{
					dftCheck.setRowCount(0);
					for (int i=0; i<listSavePR.size();i++)
						addTableValue(dftCheck,"Good",i);
				}
				//check by exp			

				if (cbbCheck.getSelectedItem().equals("EXP"))
				{
					dftCheck.setRowCount(0);
					int s=0;
					year = instance.get(Calendar.YEAR);
					for (int i=0; i<listSavePR.size();i++)
						if (Integer.parseInt(listSavePR.get(i).getExp())<=year) 
						{
							addTableValue(dftCheck,"Out of date!",i);
							s+=1;
						}
					if (s==0)
						JOptionPane.showMessageDialog(null,"all product have status good!");	
				}	


				//check by quantity			
				if (cbbCheck.getSelectedItem().equals("Quantity"))
				{
					dftCheck.setRowCount(0);
					int s=0;
					for (int i=0; i<listSavePR.size();i++)
						if (Integer.parseInt(listSavePR.get(i).getNumber())<50)
						{
							addTableValue(dftCheck,"Quantity less than 50 must be added",i);
							s+=1;
						}
					if (s==0)
						JOptionPane.showMessageDialog(null,"all product have status good!");	
				}

				tableCheck.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
				{
					@Override
					public Component getTableCellRendererComponent(JTable table, Object value, 
							boolean isSelected, boolean hasFocus, int row, int column)
					{
						final Component c = super.getTableCellRendererComponent(table, value, 
								isSelected, hasFocus, row, column);
						String str=(String) tableCheck.getValueAt(row, column);
						if (column==6 && Integer.parseInt(str)<=year)
						{
							tableCheck.setValueAt("Out of date!",row, 7);
							c.setForeground(Color.red);
						}
						else if (column==3 && Integer.parseInt(str)<50)

						{
							tableCheck.setValueAt("<50",row, 7);
							c.setForeground(Color.red);
						}
						else
							c.setForeground(Color.black);
						return c;
					}
				});
			}
		});

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tableCheck.getSelectedRow()==-1)
					return;
				else
				{
					int row= tableCheck.getSelectedRow();
					int ret = JOptionPane.showConfirmDialog(null, "do you want to delete the product?",
							"delete", JOptionPane.YES_NO_OPTION);
					if (ret==JOptionPane.YES_OPTION)
					{
						dftCheck.removeRow(row);
						listSavePR.remove(row);
					}
					else
						return;
				}	
			}});

		btnCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dftCheck.setRowCount(0);
				int s=0;
				for (int i=0; i<listSavePR.size();i++)
				{
					if (txtCheckID.getText().equalsIgnoreCase(listSavePR.get(i).getId()))
					{
						addTableValue(dftCheck,"product available",i);
						s+=1;
					}
					else if (txtCheckName.getText().equalsIgnoreCase(listSavePR.get(i).getNameProduct()))
					{
						addTableValue(dftCheck,"product available",i);
						s+=1;
					}
					else if (txtCheckType.getText().equalsIgnoreCase(listSavePR.get(i).getGroupPr().getNameGroup()))
					{
						addTableValue(dftCheck,"product available",i);
						s+=1;
					}
				}
				if (s==0)
					JOptionPane.showMessageDialog(null, "product not available");
			}
		});

		btnAddCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Boolean flat=false;
				for( Product pr: listSavePR)
				{
					if (pr.getId().equalsIgnoreCase(txtIDAdd.getText()))
					{
						pr.setNumber((Integer.parseInt(pr.getNumber())+Integer.parseInt(txtQuantityAdd.getText()))+"");
						flat=true;
					}
				}
				if (flat==false)
				{
					JOptionPane.showMessageDialog(null, "not found the product");
				}
				else 
					JOptionPane.showMessageDialog(null, "successful product addition");

			}
		});

		//reg client
		btnReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub


				try {
					Integer.parseInt(txtRegPhone.getText());
					//random Id
					String strID;
					Random generator = new Random();
					int id= Math.abs(generator.nextInt());
					strID=String.valueOf(id);
					Client client= new Client(strID,txtRegName.getText(),txtRegPhone.getText(),
							txtRegAddress.getText());
					boolean i=true;
					for (Client cl: listClient)
						if (client.getPhone().equalsIgnoreCase(cl.getPhone()))
						{
							JOptionPane.showMessageDialog(null, "Этот phone уже существует, пожалуйста, создайте новый");
							i=false;
							break;
						}

					if(i==true)
					{
						listClient.add(client);
						JOptionPane.showMessageDialog(null, "registered successfully");

						//add list clients in the jtree
						nodeCl = new DefaultMutableTreeNode(client);
						DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
						root = (DefaultMutableTreeNode)model.getRoot();
						root.add(nodeCl);
						model.reload(root);//important

						txtRegAddress.setText("");
						txtRegName.setText("");
						txtRegPhone.setText("");	
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "phone is not numbers");
				}


			}});

		tree.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				DefaultMutableTreeNode nodeSelected = 
						(DefaultMutableTreeNode) 
						tree.getLastSelectedPathComponent();
				if (nodeSelected.getLevel()==0 && nodeSelected!=null)
				{
					dftClient.setRowCount(0);
					for (Client client: listClient) {
						showListClient(client);
					}						
				}
				if (nodeSelected.getLevel()==1 && nodeSelected!=null)
				{
					Client clSelected = (Client)nodeSelected.getUserObject(); 
					dftClient.setRowCount(0);
					showListClient(clSelected);
				}		
			}
		});

		btnClientCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dftClient.setRowCount(0);
				boolean flat= false;
				for (Client cl: listClient)
				{
					if (cl.getIdClient().equalsIgnoreCase(txtCheckClientID.getText()))
					{
						showListClient(cl);
						flat= true;
					}
					if (txtClientPhone.getText().equalsIgnoreCase(cl.getPhone()))
					{
						showListClient(cl);
						flat= true;
					}
				}
				if (flat==false)
					JOptionPane.showMessageDialog(null, "not found the client");

			}
		});

		txtTypeSe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dftCheckBuy.setRowCount(0);
				for (int i=0; i<listSavePR.size();i++)
					if (txtTypeSe.getText().equalsIgnoreCase(listSavePR.get(i).getGroupPr().getNameGroup()))
					{
						addTableValue(dftCheckBuy,"available",i);
						flatSe=true;
					}
				if (flatSe) {
					pnFindSe.setBounds(23, 45, 280, 170);
					pnInfo.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "product not available!");
			}			
		});

		txtFindSe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dftCheckBuy.setRowCount(0);
				for (int i=0; i<listSavePR.size();i++)
					if (txtFindSe.getText().equalsIgnoreCase(listSavePR.get(i).getNameProduct()))
					{
						addTableValue(dftCheckBuy,"available",i);
						flatSe=true;
					}
				if (flatSe) {
					pnFindSe.setBounds(23, 45, 280, 170);
					pnInfo.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "product not available!");
			}
		});

		txtPriceSe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dftCheckBuy.setRowCount(0);
				for (int i=0; i<listSavePR.size();i++)
					if (Integer.parseInt(txtPriceSe.getText())>=Integer.parseInt(listSavePR.get(i).getPrice()))
					{
						addTableValue(dftCheckBuy,"available",i);
						flatSe=true;
					}
				if (flatSe) {
					pnFindSe.setBounds(23, 45, 280, 170);
					pnInfo.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "product not available!");
			}

		});


		btnAddCart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnInfoThanks.setVisible(false);
				pnCart.setVisible(true);

				if (tableCheckBuy.getSelectedRow()==-1)
					return;
				else
				{
					int row= tableCheckBuy.getSelectedRow();
					String quantityBuy= JOptionPane.showInputDialog("Quantity: ");
					if (Integer.parseInt(quantityBuy)>Integer.parseInt(dftCheckBuy.getValueAt(row,3)+""))
					{
						int ret= JOptionPane.showConfirmDialog(null,"Only"+ dftCheckBuy.getValueAt(row,3)+
								" products left in stock!\n Do you want to continue buying?","buy?",JOptionPane.YES_NO_OPTION);
						if (ret==JOptionPane.YES_OPTION)
						{
							quantityBuy= JOptionPane.showInputDialog("Quantity: ");
						}
						else
							return;
					}
					Vector<String > vec = new Vector<String>();
					Product pr = new Product(dftCheckBuy.getValueAt(row,0)+"",dftCheckBuy.getValueAt(row,2)+"",
							quantityBuy,dftCheckBuy.getValueAt(row,4)+"");
					listCart.add(pr);
					vec.add((sttPr++)+"");
					vec.add(pr.getNameProduct());
					vec.add(pr.getId());
					vec.add(pr.getNumber());
					vec.add(pr.getPrice());
					vec.add(Integer.parseInt(pr.getNumber())*Integer.parseInt(pr.getPrice())+"");
					tableCart.addRow(vec);


					sumBuy+= Integer.parseInt(pr.getNumber())*Integer.parseInt(pr.getPrice());
					txtSum.setText(sumBuy+"");

				}
			}
		});

		btnDeleteCart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jtableCart.getSelectedRow()==-1)
					return;
				else
				{
					int row= jtableCart.getSelectedRow();
					int ret = JOptionPane.showConfirmDialog(null, "do you want to delete the product?",
							"delete", JOptionPane.YES_NO_OPTION);
					if (ret==JOptionPane.YES_OPTION)
					{
						sumBuy-= Integer.parseInt(tableCart.getValueAt(row, 5)+"");
						tableCart.removeRow(row);
						listCart.remove(row);
						txtSum.setText(sumBuy+"");

					}
					else
						return;
				}	
			}
		});
		btnOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!btnOrder.isSelected())
				{
					pnCart.setBounds(21, 284, 500, 257);
					pnInfoClient.setVisible(true);
					pnInfoThanks.setVisible(false);
				}

			}
		});

		reg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (reg.isSelected())
					btnBuyPr.setEnabled(true);
				else
					btnBuyPr.setEnabled(false);

			}
		});

		btnBuyPr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean flat = false;
				for (Client cl:listClient) {
					if (cl.getPhone().equalsIgnoreCase(txtPhone.getText()))
					{
						flat= true;
						cl.getPr().addAll(listCart);
						tableCart.setRowCount(0);
						for (Product pr : listSavePR)
						{
							for (Product prbuy: listCart)
								if (prbuy.getId().equalsIgnoreCase(pr.getId()))
									pr.setNumber((Integer.parseInt(pr.getNumber())-Integer.parseInt(prbuy.getNumber()))+"");
						}
						saveBill(cl);
						listCart.clear();
						pnInfoThanks.setVisible(true);
						pnInfoClient.setVisible(false);
						pnCart.setVisible(false);
						break;
					}
				}
				if (flat==false) {
					JOptionPane.showMessageDialog(null, "not found our client! please sign up");
				}

			}

		});
	}

	protected void saveBill(Client cl) {
		// TODO Auto-generated method stub
		JFileChooser chooser= new JFileChooser();
		if (chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION);
		{
			try {
				int sum=0;
				FileOutputStream fos= new FileOutputStream(chooser.getSelectedFile());
				OutputStreamWriter osw= new OutputStreamWriter(fos,"UTF-8");
				osw.write("			 RECIEPT:\n ");
				osw.write(" name: "+cl.getNameClient()+"\n");
				osw.write(" phone: "+cl.getPhone()+"\n");
				osw.write(" id: "+cl.getIdClient()+"\n");
				osw.write(" address: "+cl.getAddress()+"\n");
				osw.write(" ==================================================="+"\n");
				osw.write("Product--------Quantity----Price(Rub)-----sum(rub)\n");
				for (Product pr:listCart)
				{
					int total= Integer.parseInt(pr.getNumber())*Integer.parseInt(pr.getPrice());
					sum+=total;
					osw.write(pr.getNameProduct()+"--------"+pr.getPrice()+"--------"+pr.getNumber()+"-------------"+(total)+"\n");
				}
				osw.write("                                                                            TOTAL: "+sum);
				osw.close();
				fos.close();
				JOptionPane.showMessageDialog(null, "saved");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "can't saved");
			}
		}

	}

	protected void showListClient(Client client) {
		// TODO Auto-generated method stub
		Vector<String> vec= new Vector<String>();
		vec.add(client.getIdClient());
		vec.add(client.getNameClient());
		vec.add(client.getPhone());
		vec.add(client.getAddress());
		vec.add(client.getPr().size()+"");
		dftClient.addRow(vec);

	}

	protected void addTableValue(DefaultTableModel dtfTable, String strOK,int i) {
		// TODO Auto-generated method stub

		Vector<String> vec= new Vector<String>();
		vec.add(listSavePR.get(i).getId());
		vec.add(listSavePR.get(i).getGroupPr()+"");
		vec.add(listSavePR.get(i).getNameProduct());
		vec.add(listSavePR.get(i).getNumber()+"");
		vec.add(listSavePR.get(i).getPrice()+"");
		vec.add(listSavePR.get(i).getMfg());
		vec.add(listSavePR.get(i).getExp());
		vec.add(strOK);
		dtfTable.addRow(vec);
	}


}
