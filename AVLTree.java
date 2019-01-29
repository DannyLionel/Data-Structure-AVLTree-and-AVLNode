public class AVLTree
{
	private static AVLNode root;

	public AVLTree()
	{
		root = null;
	}


	public static AVLNode getRoot() {
		return root;
	}

	public void remove(int number)
	{
		root = remove(number, root);
	}

	public AVLNode remove(int Number, AVLNode node)
	{ 

		if( node == null) 
		{
			System.out.println(Number+" Not found in AVL Tree\n");
			return null;
		}
		else 
		{
			if(node.number < Number) 
			{
				node.right = remove( Number, node.right);
			}
			else if( node.number > Number) 
			{
				node.left  = remove( Number, node.left);
			}

			else if( node.left  == null)
			{ 
				node = node.right;
			}
			else if( node.right == null) 
			{
				node = node.left;
			}

			else if( height( node.left ) > height( node.right ))
			{ 
				node = rotatewithright(node);  
				node.right = remove( Number, node.right ); 
			}
			else 
			{ 
				node = rotatewithleft( node );  
				node.left  = remove( Number, node.left ); 
			}

			if( node != null ) 
			{
				node.height = height( node.left ) + height( node.right );
			}
		}
		
		return node;
	}

	public void insert(int number, String name, String lastName)
	{
		root = insert(number,name, lastName, root);
	}

	
	private AVLNode insert(int number, String name,String lastName,  AVLNode current)
	{
		if( current == null ) // the node is null
		{
			current = new AVLNode( number,name, lastName, null,null);
		}
		else if(number < current.number) // it is less than current
		{
			current.left = insert(number, name, lastName, current.left);
			if(height(current.left) - height(current.right) == 2) // there is an imbalance
			{
				if(number < current.left.number) // see what kind of imbalance it is
				{
					current = rotatewithleft(current);
				}
				else
				{
					current = secondrotatewithleft(current);
				}
			}
		}
		else if(number > current.number) // it is greater than current
		{
			current.right = insert(number,name, lastName, current.right);
			if(height(current.right) - height(current.left) == 2) // there is an imbalance
			{
				if(number > current.right.number) // see what kind of imbalance it is
				{
					current = rotatewithright(current);
				}
				else
				{
					current = secondrotatewithright(current);
				}
			}
		}
		current.height = Math.max(height(current.left), height(current.right)) + 1; 
		return current;
	}  

	public AVLNode isInsideUser (int number){
	
		return isInside(number, root);

	}
	public static AVLNode isInside (int number, AVLNode current){
		
		while (current != null){
			if (number == current.number ){
				return current;
			}

			else if (number < current.number ){ //traverse left side
				current = current.left;	
			}
			else if (number > current.number ){ //traverse right side
				current = current.right;

			}
		}
		return null;


	}
	
	private static int height(AVLNode current)
	{
		return current == null ? -1 : current.height;
	}

	private AVLNode rotatewithleft( AVLNode child2 )
	{
		AVLNode child1 = child2.left;
		child2.left = child1.right;
		child1.right = child2;
		child2.height = Math.max( height( child2.left ), height( child2.right ) ) + 1;
		child1.height = Math.max( height( child1.left ), child2.height ) + 1;
		return child1;
	}

	private AVLNode rotatewithright(AVLNode child1 )
	{
		AVLNode child2 = child1.right;
		child1.right = child2.left;
		child2.left = child1;
		child1.height = Math.max( height( child1.left ), height( child1.right ) ) + 1;
		child2.height = Math.max( height( child2.right ), child1.height ) + 1;
		return child2;
	}

	private AVLNode secondrotatewithleft( AVLNode child3 )
	{
		child3.left = rotatewithright( child3.left );
		return rotatewithleft( child3 );
	}

	private AVLNode secondrotatewithright( AVLNode child1 )
	{
		child1.right = rotatewithleft( child1.right );
		return rotatewithright( child1 );
	}



}
