import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.TextArea;
import java.awt.Label;
import java.io.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainTest extends JFrame implements ActionListener {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	/////////////////GLOBALS/////////////////////
	static TextArea directoryArea = new TextArea();
	static TextArea searchArea = new TextArea();
	static String list= "";
	static String searchlist= "";
	static String writetoFile = "";
	static AVLTree avl = new AVLTree();
	static AVLNode search = new AVLNode(0,null,null);
	static int areaCode = 0;
	static String number1 = "";
	static int number = 0;
	static String firstName = "";
	static String lastName = "";
	private JTextField insertFirstNameField;
	private JTextField insertLastNameField;
	private JTextField insertNumberField;
	private static JTextField removeNumberField;
	private JTextField removeFirstNameField;
	private JTextField removeLastNameField;
	private JTextField searchFirstNameField;
	private JTextField searchLastNameField;
	private JTextField searchNumberField;
	////////////////GLOBALS/////////////////////

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainTest frame = new MainTest();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


		list = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
		directoryArea.setText(list);
		inorder(avl.getRoot());


	}

	/**
	 * Create the frame.
	 */
	public MainTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		directoryArea.setEditable(false);


		directoryArea.setBounds(10, 24, 335, 628);
		contentPane.add(directoryArea);

		Label label = new Label("INSERT NEW PERSON:");
		label.setBounds(351, 0, 143, 28);
		contentPane.add(label);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(351, 37, 61, 14);
		contentPane.add(lblNewLabel);

		insertFirstNameField = new JTextField();
		insertFirstNameField.setBounds(472, 34, 86, 20);
		contentPane.add(insertFirstNameField);
		insertFirstNameField.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(351, 68, 61, 14);
		contentPane.add(lblLastName);

		insertLastNameField = new JTextField();
		insertLastNameField.setColumns(10);
		insertLastNameField.setBounds(472, 65, 86, 20);
		contentPane.add(insertLastNameField);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(351, 103, 111, 14);
		contentPane.add(lblPhoneNumber);

		insertNumberField = new JTextField();
		insertNumberField.setColumns(10);
		insertNumberField.setBounds(472, 96, 86, 20);
		contentPane.add(insertNumberField);

		JButton insertBtn = new JButton("Insert");
		insertBtn.setBounds(585, 64, 89, 23);
		insertBtn.setActionCommand("insert");
		insertBtn.addActionListener(this);
		contentPane.add(insertBtn);

		JLabel label_3 = new JLabel("Phone Number");
		label_3.setBounds(351, 180, 111, 14);
		contentPane.add(label_3);

		removeNumberField = new JTextField();
		removeNumberField.setColumns(10);
		removeNumberField.setBounds(472, 177, 86, 20);
		contentPane.add(removeNumberField);

		JButton btnRemove_byNumber = new JButton("Remove");
		btnRemove_byNumber.setBounds(585, 176, 89, 23);
		btnRemove_byNumber.setActionCommand("remove");
		btnRemove_byNumber.addActionListener(this);
		contentPane.add(btnRemove_byNumber);

		Label label_4 = new Label("REMOVE BY NUMBER:");
		label_4.setBounds(351, 143, 126, 28);
		contentPane.add(label_4);

		JButton btnRemove_byName = new JButton("Remove");
		btnRemove_byName.setBounds(585, 240, 89, 23);
		btnRemove_byName.setActionCommand("removename");
		btnRemove_byName.addActionListener(this);
		contentPane.add(btnRemove_byName);

		Label label_2 = new Label("REMOVE BY NAME:");
		label_2.setBounds(351, 208, 126, 28);
		contentPane.add(label_2);

		JLabel label_1 = new JLabel("First Name");
		label_1.setBounds(351, 244, 61, 14);
		contentPane.add(label_1);

		removeFirstNameField = new JTextField();
		removeFirstNameField.setColumns(10);
		removeFirstNameField.setBounds(472, 241, 86, 20);
		contentPane.add(removeFirstNameField);

		JLabel label_5 = new JLabel("Last Name");
		label_5.setBounds(351, 275, 61, 14);
		contentPane.add(label_5);

		removeLastNameField = new JTextField();
		removeLastNameField.setColumns(10);
		removeLastNameField.setBounds(472, 272, 86, 20);
		contentPane.add(removeLastNameField);


		searchArea.setEditable(false);
		searchArea.setBounds(351, 446, 323, 206);
		contentPane.add(searchArea);

		JLabel lblDirectoryList = new JLabel("DIRECTORY LIST");
		lblDirectoryList.setBounds(115, 4, 126, 14);
		contentPane.add(lblDirectoryList);

		JLabel lblSearchList = new JLabel("SEARCH LIST");
		lblSearchList.setBounds(472, 426, 86, 14);
		contentPane.add(lblSearchList);

		Label label_6 = new Label("SEARCH BY:");
		label_6.setBounds(351, 295, 126, 28);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("First Name");
		label_7.setBounds(351, 332, 61, 14);
		contentPane.add(label_7);

		searchFirstNameField = new JTextField();
		searchFirstNameField.setColumns(10);
		searchFirstNameField.setBounds(472, 329, 86, 20);
		contentPane.add(searchFirstNameField);

		JLabel label_8 = new JLabel("Last Name");
		label_8.setBounds(351, 363, 61, 14);
		contentPane.add(label_8);

		searchLastNameField = new JTextField();
		searchLastNameField.setColumns(10);
		searchLastNameField.setBounds(472, 360, 86, 20);
		contentPane.add(searchLastNameField);

		JLabel label_9 = new JLabel("Phone Number");
		label_9.setBounds(351, 398, 111, 14);
		contentPane.add(label_9);

		searchNumberField = new JTextField();
		searchNumberField.setColumns(10);
		searchNumberField.setBounds(472, 395, 86, 20);
		contentPane.add(searchNumberField);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(585, 359, 89, 23);
		btnSearch.setActionCommand("search");
		btnSearch.addActionListener(this);
		contentPane.add(btnSearch);

		JButton button416 = new JButton("416");
		button416.setBounds(72, 683, 89, 23);
		button416.setActionCommand("416");
		button416.addActionListener(this);
		contentPane.add(button416);

		JButton button905 = new JButton("905");
		button905.setBounds(177, 683, 89, 23);
		button905.setActionCommand("905");
		button905.addActionListener(this);
		contentPane.add(button905);

		JLabel lblAreaCode = new JLabel("AREA CODE");
		lblAreaCode.setBounds(142, 658, 105, 14);
		contentPane.add(lblAreaCode);

		JButton btnSave = new JButton("SAVE");
		btnSave.setBounds(469, 683, 89, 23);
		btnSave.setActionCommand("save");
		btnSave.addActionListener(this);
		contentPane.add(btnSave);

	}
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		if (action.equals("416")) {
			areaCode = 1;
			AVLTree avl = new AVLTree();

			FileReader fFile = null;
			BufferedReader bFile = null; {

				try
				{
					fFile = new FileReader("416n2.in");
					bFile = new BufferedReader(fFile);	
				}
				catch(Exception ex)
				{
					System.out.println("\n\tFile not found");
					System.exit(0);
				}

				String string = new String("");

				try {
					while((string = bFile.readLine()) != null)
					{
						number1 = "";
						firstName = "";
						lastName = "";
						number = 0;
						int blankspace = 0;
						for (int i = 0; i < string.length();i++){

							if (blankspace == 0){
								if (string.charAt(i) == ' '){
									blankspace++;
								}
								else {
									number1+= string.charAt(i);
									number = Integer.parseInt(number1);
								}

							}
							else if (blankspace == 1){
								if (string.charAt(i) == ' '){
									blankspace++;
								}
								else {
									firstName+= string.charAt(i);
								}
							}
							else if (blankspace == 2){
								if (string.charAt(i) == ' '){
									blankspace++;
								}
								else {
									lastName+= string.charAt(i);
								}
							}
						}
						avl.insert(number, firstName, lastName);
					}
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
			}
			list = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
			directoryArea.setText(list);
			writetoFile = "";
			inorder(avl.getRoot());

		}
		if (action.equals("905")) {
			areaCode = -1;
			AVLTree avl = new AVLTree();

			FileReader fFile = null;
			BufferedReader bFile = null; {

				try
				{
					fFile = new FileReader("905n2.in");
					bFile = new BufferedReader(fFile);	
				}
				catch(Exception ex)
				{
					System.out.println("\n\tFile not found");
					System.exit(0);
				}

				String string = new String("");

				try {
					while((string = bFile.readLine()) != null)
					{
						number1 = "";
						firstName = "";
						lastName = "";
						number = 0;
						int blankspace = 0;
						for (int i = 0; i < string.length();i++){

							if (blankspace == 0){
								if (string.charAt(i) == ' '){
									blankspace++;
								}
								else {
									number1+= string.charAt(i);
									number = Integer.parseInt(number1);
								}

							}
							else if (blankspace == 1){
								if (string.charAt(i) == ' '){
									blankspace++;
								}
								else {
									firstName+= string.charAt(i);
								}
							}
							else if (blankspace == 2){
								if (string.charAt(i) == ' '){
									blankspace++;
								}
								else {
									lastName+= string.charAt(i);
								}
							}
						}
						avl.insert(number, firstName, lastName);
					}
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
			}
			list = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
			directoryArea.setText(list);
			writetoFile = "";
			inorder(avl.getRoot());
		}
		if (action.equals("save") && areaCode == 1){

			File file = new File("/Users/Admin/workspace/Question3/416n2.in");
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(writetoFile);
				bw.close();
				System.out.println ("SUCCESS");
			}
			catch (IOException e) {
				System.out.println ("FAIL");
			}
		}
		else if (action.equals("save") && areaCode == -1){
			File file = new File("/Users/Admin/workspace/Question3/905n2.in");
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(writetoFile);
				bw.close();
				System.out.println ("SUCCESS");
			}
			catch (IOException e) {
				System.out.println ("FAIL");
			}

		}
		if (action.equals("insert")){
			if (insertNumberField.getText().length()!= 7){

			}
			else{
				try {
					avl.insert(Integer.parseInt(insertNumberField.getText()), insertFirstNameField.getText(), insertLastNameField.getText());
					insertNumberField.setText("");
					insertFirstNameField.setText("");
					insertLastNameField.setText("");
					list = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
					writetoFile = "";
					inorder(avl.getRoot());
				}
				catch (Exception e){

				}
			}
		}
		if (action.equals("remove")){
			try {
				avl.remove(Integer.parseInt(removeNumberField.getText()));
				avl.remove(Integer.parseInt(removeNumberField.getText()));
				removeNumberField.setText("");
				list = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
				writetoFile = "";
				inorder(avl.getRoot());
			}
			catch (Exception e){

			}

		}
		if (action.equals("search")){
			searchlist = "";
			searchArea.setText(searchlist);
			if (searchNumberField.getText().isEmpty() == false && searchFirstNameField.getText().isEmpty() == false && searchLastNameField.getText().isEmpty() == false){

			}
			else if (searchFirstNameField.getText().isEmpty() == false && searchLastNameField.getText().isEmpty() == false){
				searchlist = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
				inordernamesearch (avl.getRoot(),searchFirstNameField.getText(),searchLastNameField.getText(), 0);
				searchNumberField.setText("");
				searchFirstNameField.setText("");
				searchLastNameField.setText("");
			}
			else if (searchNumberField.getText().isEmpty() == false){


				try {
					search = avl.isInsideUser(Integer.parseInt(searchNumberField.getText()));
					searchNumberField.setText("");
					searchFirstNameField.setText("");
					searchLastNameField.setText("");
					searchlist = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
					searchlist += Integer.toString(search.number) + "\t" + search.firstname + "\t\t" + search.lastname + "\n";
					searchArea.setText(searchlist);
				}
				catch (Exception e){

				}
			}
			else if (searchLastNameField.getText().isEmpty() == false){
				searchlist = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
				inordernamesearch (avl.getRoot(),null,searchLastNameField.getText(), 0);
				searchNumberField.setText("");
				searchFirstNameField.setText("");
				searchLastNameField.setText("");
			}
			else if (searchFirstNameField.getText().isEmpty() == false){
				searchlist = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
				inordernamesearch (avl.getRoot(),searchFirstNameField.getText(),null, 0);
				searchNumberField.setText("");
				searchFirstNameField.setText("");
				searchLastNameField.setText("");
			}



		}
		if (action.equals("removename")){
			if (removeFirstNameField.getText().isEmpty() == false && removeLastNameField.getText().isEmpty() == false){
				inordernamesearch (avl.getRoot(),removeFirstNameField.getText(),removeLastNameField.getText(), 1);
				inordernamesearch (avl.getRoot(),removeFirstNameField.getText(),removeLastNameField.getText(), 1);
				removeFirstNameField.setText("");
				removeLastNameField.setText("");
				inorder(AVLTree.getRoot());
			}
		}

	}
	public static void inorder(AVLNode current)
	{

		if (current != null)
		{
			inorder(current.left);
			list += Integer.toString(current.number) + "\t" + current.firstname + "\t\t" + current.lastname + "\n";
			writetoFile += String.format(Integer.toString(current.number) + " " + current.firstname + " " + current.lastname + "%n");
			directoryArea.setText(list);
			inorder(current.right);
		}

	} 
	public static void inordernamesearch(AVLNode current2,String firstName, String lastName,int todo)
	{
		if (todo == 0){
			if (firstName != null && lastName != null){
				if (current2 != null)
				{


					if (current2.firstname.equalsIgnoreCase(firstName) && current2.lastname.equalsIgnoreCase(lastName) ){
						searchlist += Integer.toString(current2.number) + "\t" + current2.firstname + "\t\t" + current2.lastname + "\n";
						searchArea.setText(searchlist);
					}

					inordernamesearch(current2.left,firstName,lastName, todo);
					inordernamesearch(current2.right,firstName,lastName, todo);
				}

			}
			else if (firstName != null){
				if (current2 != null)
				{


					if (current2.firstname.equalsIgnoreCase(firstName)){
						searchlist += Integer.toString(current2.number) + "\t" + current2.firstname + "\t\t" + current2.lastname + "\n";
						searchArea.setText(searchlist);
					}

					inordernamesearch(current2.left,firstName,lastName, todo);
					inordernamesearch(current2.right,firstName,lastName, todo);
				}

			}
			else if (lastName != null){
				if (current2 != null)
				{


					if (current2.lastname.equalsIgnoreCase(lastName)){
						searchlist += Integer.toString(current2.number) + "\t" + current2.firstname + "\t\t" + current2.lastname + "\n";
						searchArea.setText(searchlist);
					}

					inordernamesearch(current2.left,firstName,lastName, todo);
					inordernamesearch(current2.right,firstName,lastName, todo);
				}

			}


		}
		else if (todo == 1) {
			if (firstName != null && lastName != null){
				if (current2 != null)
				{


					if (current2.firstname.equalsIgnoreCase(firstName) && current2.lastname.equalsIgnoreCase(lastName) ){
						System.out.println("This is getting removed: " + current2.number);
						avl.remove(current2.number,avl.getRoot());
						list = ("Number\t\tFirst Name\tLast Name\n----------------------------------------------------------------\n");
						writetoFile = "";
						
					}

					inordernamesearch(current2.left,firstName,lastName, todo);
					inordernamesearch(current2.right,firstName,lastName, todo);
				}

			}
		}

	}  
}
