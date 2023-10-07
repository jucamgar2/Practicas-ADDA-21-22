package util;

import us.lsi.tiposrecursivos.BinaryTree;

public record TreeInteger(BinaryTree<Integer> b,Integer i) {
	public static TreeInteger of(BinaryTree<Integer> x,Integer y) {
		return new TreeInteger(x,y);
	}
}
