package com.manhpd;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoinChangeII {

    private int count = 0;

    public static void main(String[] args) {
//        int amount = 500;
//        int[] coins = {3,5,7,8,9,10,11};

//        int amount = 5;
//        int[] coins = {1, 2, 5};

//        int amount = 3;
//        int[] coins = {2};

        int amount = 0;
        int[] coins = {7};

//        int amount = 10;
//        int[] coins = {10};

//        System.out.println(new CoinChangeII().change(amount, coins));

        CoinChangeII coinChangeII = new CoinChangeII();
        long startMs = System.currentTimeMillis();
        coinChangeII.useStack(amount, coins);

        long timeMs = System.currentTimeMillis() - startMs;
        System.out.println(coinChangeII.count + " with " + timeMs + "ms");

//        System.out.println(new CoinChangeII().changeBacktracking(amount, coins));
    }

    public int changeBacktracking(int amount, int[] coins) {
        long startMs = System.currentTimeMillis();
        backtracking(amount, coins, 0);

        long timeMs = System.currentTimeMillis() - startMs;
        System.out.println("Time: " + timeMs);

        return this.count;
    }

    public void dp(int amount, int[] coins) {

    }

    public void backtracking(int amount, int[] coins, int idx) {
//        System.out.println(amount + "-" + idx);
        if (amount < 0) {
            return;
        }

        if (amount == 0) {
            ++count;
            return;
        }

        for (int i = idx; i < coins.length; ++i) {
            backtracking(amount - coins[i], coins, i);
        }
    }

    /**
     * Use stack to optimize backtracking
     *
     * @param amount
     * @param coins
     */
    public void useStack(int amount, int[] coins) {
        Stack<StkNode> stk = new Stack<>();
        stk.push(new StkNode(amount, 0));

        StkNode tmp = null;
        int i = 0;

        while (!stk.isEmpty()) {
            int currentIdx = i;

            while (currentIdx < coins.length) {
                tmp = stk.peek();
                int currentCoins = tmp.amount - coins[currentIdx];
                if (currentCoins < 0) {
//                    System.out.println(currentCoins + "-" + currentIdx);
                   ++currentIdx;
                } else if (currentCoins == 0) {
//                    System.out.println(currentCoins + "-" + currentIdx);
                    ++this.count;
                    ++currentIdx;
                } else {
//                    System.out.println(currentCoins + "-" + currentIdx);
                    stk.push(new StkNode(currentCoins, currentIdx));
                }
            }

            i = stk.peek().idx + 1;
            stk.pop();
        }
    }

    private static class StkNode {
        public int amount;
        public int idx;

        public StkNode(int amount, int idx) {
            this.amount = amount;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return this.amount + "-" + this.idx;
        }
    }

    /**
     * Use Tree to save the common nodes
     */
    private static class Node implements Comparable<Node> {
        public static final Node ZERO = new Node(0, 0);

        private final int amount;
        private final int index;

        public Node(int amount, int index) {
            this.amount = amount;
            this.index = index;
        }

        public int getAmount() {
            return amount;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return amount == node.amount && index == node.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(amount, index);
        }

        @Override
        public String toString() {
            return String.format("\"%s-%s\"", amount, index);
        }

        @Override
        public int compareTo(Node o) {
            int cAmount = Integer.compare(this.amount, o.amount);
            return cAmount == 0 ? Integer.compare(this.index, o.index) : cAmount;
        }
    }

    public int change(int amount, int[] coins) {
        long startMs = System.currentTimeMillis();

        List<Integer> list = Arrays.stream(coins).boxed().sorted().collect(Collectors.toList());
        Map<Node, Set<Node>> mem = new HashMap<>();
        mem.put(Node.ZERO, Collections.singleton(Node.ZERO));
        Node root = create(mem, amount, list.size());
        memoise(mem, list, root);

        Map<Node, Integer> count = new HashMap<>();
        count.put(Node.ZERO, 1);
        mem.keySet().stream().sorted()
                .forEach(k -> count.put(k, count.containsKey(k)
                        ? count.get(k)
                        : mem.get(k).stream().sorted().map(count::get).reduce(0, Integer::sum)));
        long timeMs = System.currentTimeMillis() - startMs;
        System.out.println("Time use node: " + timeMs);

        return count.get(root);
    }

    private Node create(Map<Node, Set<Node>> mem, int amount, int idx) {
        Node node = new Node(amount, idx);
        if (!mem.containsKey(node)) {
            mem.put(node, new HashSet<>());
        }
        return node;
    }

    private void memoise(Map<Node, Set<Node>> mem, List<Integer> list, Node root) {
        IntStream.range(0, root.getIndex())
                .forEach(i -> {
                    Set<Node> children = mem.get(root);
                    if (root.getAmount() == 0) {
                        children.add(Node.ZERO);
                        return;
                    }
                    int amount = root.getAmount() - list.get(i);
                    if (amount == 0) {
                        children.add(Node.ZERO);
                    } else if (amount > 0) {
                        Node node = create(mem, amount, i + 1);
                        if (!children.contains(node)) {
                            children.add(node);
                            memoise(mem, list, node);
                        }
                    }
                });
    }

}
