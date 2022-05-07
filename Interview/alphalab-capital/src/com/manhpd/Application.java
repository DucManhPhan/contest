package com.manhpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiPredicate;

public class Application {

    private static final String DELIMITER_ORDER_DATA = " ";

    private static final String BUY_SIDE_ORDER = "B";

    private static final String SELL_SIDE_ORDER = "S";

    private static final String END_ORDER = "END";

    private static int counter = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<String[]> lines = readLines(bufferedReader);
        List<Order> orders = extractOrders(lines);

        process(orders);
    }

    private static void process(List<Order> orders) {
        // define priority queue for buy order
        Comparator<Order> buyOrderComparator = (order1, order2) -> {
//            if (order1.orderData.price < order2.orderData.price) {
//                return 1;
//            } else if (order1.orderData.price == order2.orderData.price) {
//                return 0;
//            } else {
//                return -1;
//            }

            if (order1.orderData.price != order2.orderData.price) {
                return order2.orderData.price - order1.orderData.price;
            } else {
                return order1.count - order2.count;
            }
        };
        PriorityQueue<Order> buyOrders = new PriorityQueue<>(buyOrderComparator);

//        int count = 0;
//        for (Order order : orders) {
//            ++count;
//            buyOrders.offer(order);
//
//            if (count == 3) {
//                break;
//            }
//        }

//        System.out.println(buyOrders.poll().orderData);
//        System.out.println(buyOrders.poll().orderData);
//        System.out.println(buyOrders.poll().orderData);

        // define priority queue for sell order
        Comparator<Order> sellOrderComparator = (order1, order2) -> {
//            if (order1.orderData.price > order2.orderData.price) {
//                return 1;
//            }
//            else if (order1.orderData.price == order2.orderData.price) {
//                return 0;
//            }
//            else {
//                return -1;
//            }

            if (order1.orderData.price != order2.orderData.price) {
                return order1.orderData.price - order2.orderData.price;
            } else {
                return order1.count - order2.count;
            }
        };
        PriorityQueue<Order> sellOrders = new PriorityQueue<>(sellOrderComparator);

//        int count = 0;
//        for (Order order : orders) {
//            ++count;
//            sellOrders.offer(order);
//
//            if (count == 4) {
//                break;
//            }
//        }
//
//        System.out.println(sellOrders.poll().orderData);
//        System.out.println(sellOrders.poll().orderData);
//        System.out.println(sellOrders.poll().orderData);
//        System.out.println(sellOrders.poll().orderData);

        for (Order order : orders) {
            if (order.cmd == OrderCommand.SUB) {
                processSubmitOrder(order, buyOrders, sellOrders);
            } else if (order.cmd == OrderCommand.CXL) {
                processCancelOrder(order, buyOrders, sellOrders);
            }
        }

        printOrderBook(BUY_SIDE_ORDER, buyOrders);
        printOrderBook(SELL_SIDE_ORDER, sellOrders);
    }

    private static void printOrderBook(String orderSide, PriorityQueue<Order> orders) {
        StringBuilder sb = new StringBuilder();
        sb.append(orderSide + ": ");

        while (!orders.isEmpty()) {
            sb.append(orders.poll().orderData.toString());
            sb.append(DELIMITER_ORDER_DATA);
        }

        System.out.println(sb.toString().trim());
    }

    private static void processSubmitOrder(Order currentOrder, PriorityQueue<Order> buyOrders,
                                           PriorityQueue<Order> sellOrders) {
        String currentSideOrder = currentOrder.orderData.side;

        if (BUY_SIDE_ORDER.equals(currentSideOrder)) {
            if (sellOrders.isEmpty()) {
                if (currentOrder.type == OrderType.MO) {
                    System.out.println(0);
                    return;
                }

                buyOrders.offer(currentOrder);

                System.out.println(0);
                return;
            }

            matchOrders(currentOrder, sellOrders, buyOrders, (order, prioritizedOrder) ->
                                                        order.orderData.price >= prioritizedOrder.orderData.price);
        } else {
            if (buyOrders.isEmpty()) {
                if (currentOrder.type == OrderType.MO) {
                    System.out.println(0);
                    return;
                }

                sellOrders.offer(currentOrder);

                System.out.println(0);
                return;
            }

            matchOrders(currentOrder, buyOrders, sellOrders, (order, prioritizedOrder) ->
                                                        order.orderData.price <= prioritizedOrder.orderData.price);
        }
    }

    private static void matchOrders(Order currentOrder, PriorityQueue<Order> oppositeOrders,
                                    PriorityQueue<Order> priorityOrders, BiPredicate<Order, Order> hasSatisfiedPriceLO) {
        int tradedMoney = 0;

        while (!oppositeOrders.isEmpty()) {
            Order prioritizedOrder = oppositeOrders.peek();

            if (currentOrder.type == OrderType.MO || hasSatisfiedPriceLO.test(currentOrder, prioritizedOrder)) {
                int minQuantity = Math.min(currentOrder.orderData.quantity, prioritizedOrder.orderData.quantity);
                currentOrder.orderData.quantity -= minQuantity;
                prioritizedOrder.orderData.quantity -= minQuantity;

                tradedMoney += (minQuantity * prioritizedOrder.orderData.price);

                if (prioritizedOrder.orderData.quantity == 0) {
                    oppositeOrders.poll();

                    if (oppositeOrders.isEmpty()) {
                        if (currentOrder.type == OrderType.LO) {
                            priorityOrders.offer(currentOrder);
                        }

                        break;
                    }
                }

                if (currentOrder.orderData.quantity == 0) {
                    break;
                }
            }

            if (currentOrder.type != OrderType.MO && !hasSatisfiedPriceLO.test(currentOrder, prioritizedOrder)) {
                priorityOrders.offer(currentOrder);
                break;
            }
        }

        System.out.println(tradedMoney);
    }

    private static void processCancelOrder(Order currentOrder, PriorityQueue<Order> buyOrders,
                                           PriorityQueue<Order> sellOrders) {
        String orderId = currentOrder.orderData.orderId;
        Order buyOrder = search(orderId, buyOrders);
        if (buyOrder != null) {
            buyOrders.remove(buyOrder);
        }

        Order sellOrder = search(orderId, sellOrders);
        if (sellOrder != null) {
            sellOrders.remove(sellOrder);
        }
    }

    private static Order search(String orderId, PriorityQueue<Order> priorityOrders) {
        for (Order order : priorityOrders) {
            if (orderId.equals(order.orderData.orderId)) {
                return order;
            }
        }

        return null;
    }

    private static List<Order> extractOrders(List<String[]> dataLines) {
        Objects.requireNonNull(dataLines);
        List<Order> orders = new ArrayList<>();

        for (String[] line : dataLines) {
            if (END_ORDER.equals(line[0])) {
                break;
            }

            Order currentOrder = toOrder(line);
            orders.add(currentOrder);
        }

        return orders;
    }

    private static Order toOrder(String[] orderFactors) {
        if (orderFactors.length < 2) {
            return null;
        }

        String cmd = orderFactors[0];
        OrderCommand orderCommand = OrderCommand.valueOf(cmd);

        switch (orderCommand) {
            case SUB:
                return Order.makeSubmitOrder(orderFactors);

            case CXL:
                return Order.makeCancelOrder(orderFactors);

            default:
                return null;
        }
    }

    private static List<String[]> readLines(BufferedReader reader) throws IOException {
        Objects.requireNonNull(reader);

        List<String[]> lines = new ArrayList<>();
        String currentLine = "";

        List<String> tmpData = new ArrayList<String>() {{
            // test case 1
//            add("SUB LO B Ffuj 200 13");
//            add("SUB LO B Yy7P 150 11");
//            add("SUB LO B YuFU 100 13");
//            add("SUB LO S IpD8 150 14");
//            add("SUB LO S y93N 190 15");
//            add("SUB LO B Y5wb 230 14");
//            add("SUB MO B IZLO 250");
//            add("CXL Ffuj");
//            add("CXL 49Ze");
//            add("END");

            // test case 2
            add("SUB LO B c9Xt 200 10");
            add("SUB MO B ESSq 300");
            add("CXL i9Ze");
            add("SUB LO S Zfjg 300 13");
            add("SUB LO S p7kU 250 13");
            add("SUB LO S rrjX 700 13");
            add("SUB LO S W8DN 400 13");
            add("CXL p7kU");
            add("SUB MO B Q9DZ 1270");
            add("END");

            // test case 3
        }};

        for (String line : tmpData) {
            String[] factors = line.split(DELIMITER_ORDER_DATA);
            lines.add(factors);
        }

//        while ((currentLine = reader.readLine()) != null) {
//            String[] factors = currentLine.split(DELIMITER_ORDER_DATA);
//            lines.add(factors);
//        }

        return lines;
    }

    static class Order {
        public OrderCommand cmd;

        public OrderType type;

        public OrderData orderData;

        public int count;

        public static Order makeSubmitOrder(String[] data) {
            Order submitOrder = new Order();
            submitOrder.cmd = OrderCommand.SUB;
            submitOrder.count = ++counter;

            String typeOrder = data[1];
            submitOrder.type = OrderType.valueOf(typeOrder);

            OrderData orderData = new OrderData();
            orderData.side = data[2];
            orderData.orderId = data[3];
            orderData.quantity = Integer.valueOf(data[4]);

            if (submitOrder.type == OrderType.LO) {
                orderData.price = Integer.valueOf(data[5]);
            } else if (submitOrder.type == OrderType.MO) {
                orderData.price = -1;
            }

            submitOrder.orderData = orderData;
            return submitOrder;
        }

        public static Order makeCancelOrder(String[] data) {
            Order cancelOrder = new Order();
            cancelOrder.cmd = OrderCommand.CXL;
            cancelOrder.count = ++counter;

            OrderData orderData = new OrderData();
            orderData.orderId = data[1];

            cancelOrder.orderData = orderData;
            return cancelOrder;
        }
    }

    static class OrderData {
        // case-sensitive
        public String orderId;

        public String side;

        public int quantity;

        public int price;

        @Override
        public String toString() {
            return new StringBuilder()
                    .append(this.quantity)
                    .append("@")
                    .append(this.price)
                    .append("#")
                    .append(this.orderId)
                    .toString();
        }
    }

    enum OrderType {
        LO, // Limit Order
        MO  // Market Order
    }

    enum OrderCommand {
        SUB, // Submit Order
        CXL  // Cancel Order
    }

}
