/*
 *	AVLNode class 
 */
public class AVLNode
{
	public int height;
	public int number;
	public String firstname;
	public String lastname;
	public AVLNode right;
	public AVLNode left;
	

	public AVLNode(int newNumber, String newName, String newLastName)
	{
		this(newNumber, newName, newLastName, null, null);
	}

	public AVLNode(int newNumber, String newName, String newLastName, AVLNode newleft, AVLNode xright)
	{
		number = newNumber;
		left = newleft;
		firstname = newName;
		lastname = newLastName;
		right = xright;
	}


}
