package dataStructure.skiplist2;

import java.util.Random;

public class SkipListTest {

	/**
	 * 跳跃表的节点,包括key-value和上下左右4个指针
	 * 参考：http://www.acmerblog.com/skip-list-impl-java-5773.html
	 */
	static class SkipListNode<T> {
		public int key;
		public T value;
		public SkipListNode<T> up, down, left, right; // 上下左右 四个指针

		public static final int HEAD_KEY = Integer.MIN_VALUE; // 负无穷
		public static final int TAIL_KEY = Integer.MAX_VALUE; // 正无穷

		public int pos; // 主要为了打印链表用

		public SkipListNode(int k, T v) {
			// TODO Auto-generated constructor stub
			key = k;
			value = v;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null) {
				return false;
			}
			if (!(o instanceof SkipListNode<?>)) {
				return false;
			}
			SkipListNode<T> ent;
			try {
				ent = (SkipListNode<T>) o; // 检测类型
			} catch (ClassCastException ex) {
				return false;
			}
			return (ent.getKey() == key) && (ent.getValue() == value);
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "key-value:" + key + "-" + value;
		}
	}

	/**
	 * 不固定层级的跳跃表 参考：http://www.acmerblog.com/skip-list-impl-java-5773.html
	 */
	static class SkipList<T> {
		private SkipListNode<T> head, tail;
		private int nodes;// 节点总数
		private int listLevel;// 层数
		private Random random;// 用于投掷硬币
		private static final double PROBABILITY = 0.5;// 向上提升一个的概率

		public SkipList() {
			random = new Random();
			clear();
		}

		/**
		 * 清空跳跃表
		 */
		public void clear() {
			head = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
			tail = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
			horizontalLink(head, tail);
			listLevel = 0;
			nodes = 0;
		}

		public boolean isEmpty() {
			return nodes == 0;
		}

		public int size() {
			return nodes;
		}

		/**
		 * 在最下面一层，找到要插入的位置前面的那个key
		 */
		private SkipListNode<T> findNode(int key) {
			SkipListNode<T> p = head;
			while (true) {
				while (p.right.key != SkipListNode.TAIL_KEY && p.right.key <= key) {
					p = p.right;
				}
				if (p.down != null) {
					p = p.down;
				} else {
					break;
				}

			}
			return p;
		}

		/**
		 * 查找是否存储key，存在则返回该节点，否则返回null
		 */
		public SkipListNode<T> search(int key) {
			SkipListNode<T> p = findNode(key);
			if (key == p.getKey()) {
				return p;
			} else {
				return null;
			}
		}

		/**
		 * 向跳跃表中添加key-value
		 * 
		 */
		public void put(int k, T v) {
			SkipListNode<T> p = findNode(k);
			// 如果key值相同，替换原来的vaule即可结束
			if (k == p.getKey()) {
				p.value = v;
				return;
			}
			SkipListNode<T> q = new SkipListNode<T>(k, v);
			backLink(p, q);
			int currentLevel = 0;// 当前所在的层级是0
			// 抛硬币
			while (random.nextDouble() < PROBABILITY) {
				// 如果超出了高度，需要重新建一个顶层
				if (currentLevel >= listLevel) {
					listLevel++;
					SkipListNode<T> p1 = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
					SkipListNode<T> p2 = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
					horizontalLink(p1, p2);
					vertiacallLink(p1, head);
					vertiacallLink(p2, tail);
					head = p1;
					tail = p2;
				}
				// 将p移动到上一层
				while (p.up == null) {
					p = p.left;
				}
				p = p.up;

				SkipListNode<T> e = new SkipListNode<T>(k, null);// 只保存key就ok
				backLink(p, e);// 将e插入到p的后面
				vertiacallLink(e, q);// 将e和q上下连接
				q = e;
				currentLevel++;
			}
			nodes++;// 层数递增
		}

		public void remove(int key) {
			SkipListNode<T> p = findNode(key);
			if (p.getKey() != key) {
				return;
			}
			// 删除元素后重新关联，同时使被删除的对象游离，便于垃圾回收
			horizontalLink(p.left, p.right);
			p.right = null;
			p.left = null;
			// 自底向上，使所有键等于key的SkipListEntry对象左右两个方向的引用置空
			while (p.up != null) {
				p = p.up;
				horizontalLink(p.left, p.right);
				p.right = null;
				p.left = null;
			}

			// 自顶向下，使所有键等于key的SkipListEntry对象上下两个方向的引用置空
			while (p.down != null) {
				SkipListNode<T> temp = p.down;
				p.down = null;
				temp.up = null;
				p = temp;
			}

			/*
			 * 删除元素后，如果顶层的链表只有head和tail两个元素，则删除顶层。
			 * 删除顶层以后最新的顶层如果依然只有head和tail两个元素，则也要被删除，以此类推。
			 */
			while (head.right.key == tail.key && listLevel > 0) {
				SkipListNode<T> p1, p2;
				p1 = head.down;
				p2 = tail.down;

				head.right = null;
				head.down = null;

				tail.left = null;
				tail.down = null;

				p1.up = null;
				p2.up = null;
				head = p1;
				tail = p2;
				listLevel = listLevel - 1;
			}
			// 成功移除一个元素，大小减1
			nodes = nodes - 1;
			// System.out.println("-----删除[" + key + "]后的跳跃表是:-----");

		}

		// node1后面插入node2
		private void backLink(SkipListNode<T> node1, SkipListNode<T> node2) {
			node2.left = node1;
			node2.right = node1.right;
			node1.right.left = node2;
			node1.right = node2;
		}

		/**
		 * 水平双向连接
		 */
		private void horizontalLink(SkipListNode<T> node1, SkipListNode<T> node2) {
			node1.right = node2;
			node2.left = node1;
		}

		/**
		 * 垂直双向连接
		 */
		private void vertiacallLink(SkipListNode<T> node1, SkipListNode<T> node2) {
			node1.down = node2;
			node2.up = node1;
		}

		/**
		 * 打印出原始数据
		 */
		@Override
		public String toString() {
			if (isEmpty()) {
				return "跳跃表为空！";
			}
			StringBuilder builder = new StringBuilder();
			SkipListNode<T> p = head;
			while (p.down != null) {
				p = p.down;
			}
			while (p.left != null) {
				p = p.left;
			}
			if (p.right != null) {
				p = p.right;
			}
			while (p.right != null) {
				builder.append(p);
				builder.append("\n");
				p = p.right;
			}
			return builder.toString();
		}

		/**
		 * 打印出跳表的图示结构(水平方向)
		 */
		public void printHorizontal() {
			String s = "";
			int i;
			SkipListNode<T> p;
			p = head;
			while (p.down != null) {
				p = p.down;
			}
			i = 0;
			while (p != null) {
				p.pos = i++;
				p = p.right;
			}
			p = head;
			while (p != null) {
				s = getOneRow(p);
				System.out.println(s);
				p = p.down;
			}
		}

		private String getOneRow(SkipListNode<T> p) {
			String s;
			int a, b, i;
			a = 0;
			s = "" + p.key;
			p = p.right;
			while (p != null) {
				SkipListNode<T> q;
				q = p;
				while (q.down != null){
					q = q.down;
				}
				b = q.pos;
				s = s + " <-";
				for (i = a + 1; i < b; i++){
					s = s + "--------";
				}
				s = s + "> " + p.key;
				a = b;
				p = p.right;
			}

			return s;
		}

		/**
		 * 打印出跳表的图示结构(垂直方向)
		 */
		public void printVertical() {
			String s = "";
			SkipListNode<T> p;
			p = head;
			while (p.down != null){
				p = p.down;
			}
			while (p != null) {
				s = getOneColumn(p);
				System.out.println(s);
				p = p.right;
			}
		}

		private String getOneColumn(SkipListNode<T> p) {
			String s = "";
			while (p != null) {
				s = s + " " + p.key;
				p = p.up;
			}
			return (s);
		}

	}

	public static void main(String[] args) {
		SkipList<String> list = new SkipList<String>();
		System.out.println(list);
		list.put(2, "yan");
		list.put(1, "co");
		list.put(3, "feng");
		list.put(1, "cao");// 测试同一个key值
		list.put(4, "曹");
		list.put(6, "丰");
		list.put(5, "艳");
		System.out.println(list);
		System.out.println("size：" + list.size());
		System.out.println("height:" + list.listLevel);
        list.printHorizontal();
		System.out.println("删除元素3,2后：");
		list.remove(3);
		list.remove(2);
		System.out.println(list);
		System.out.println("size：" + list.size());
		System.out.println("height:" + list.listLevel);
		list.printHorizontal();
	}

}