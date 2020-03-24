package com.test.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @auth admin
 * @date 2020/3/24 9:45
 * @Description
 */
public class MyThread implements Runnable {

    private ProductService productService = new ProductService();

    private CountDownLatch countDownLatch;

    public MyThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();

            String name = "iphone11";
            //查询库存
            Product product = productService.getByName(name);
            if (product.getStock() > 0) {
                int a = productService.updateStock(name);
                if (a > 0) {
                    System.out.println(Thread.currentThread().getName() + "下单成功");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
