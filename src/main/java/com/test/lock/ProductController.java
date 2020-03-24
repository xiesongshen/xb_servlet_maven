package com.test.lock;

import com.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @auth admin
 * @date 2020/3/23 11:07
 * @Description
 */
@WebServlet("/product/*")
public class ProductController extends BaseServlet {

    private static Integer count = 1;

    private ProductService productService = new ProductService();

    //模拟高并发1（多线程）
    protected void updateStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10000; i++) {
            MyThread myThread = new MyThread(countDownLatch);
            Thread thread = new Thread(myThread);
            thread.start();
        }
        countDownLatch.countDown();
    }

    //模拟高并发2,匿名内部类（多线程）
    protected void updateStock2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String name = request.getParameter("name");
        String name = "iphone11";
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    synchronized (this) {
                        //查询库存
                        Product product = productService.getByName(name);
                        if (product.getStock() > 0) {
                            int a = productService.updateStock(name);
                            if (a > 0) {
                                System.out.println(Thread.currentThread().getName() + "下单成功");
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
        countDownLatch.countDown();
    }

    //模拟高并发3(jmeter压力测试工具)
    protected void updateStock3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "iphone11";
        //查询库存
        Product product = productService.getByName(name);
        if (product.getStock() > 0) {
            int a = productService.updateStock(name);
            if (a > 0) {
                System.out.println(Thread.currentThread().getName() + "下单成功");
            }
        }
    }

    //乐观锁(版本号，时间戳)
    protected void updateStock4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "iphone11";
        //查询库存,version
        Product product = productService.getByName(name);
        if (product.getStock() > 0) {
            int a = productService.updateStock4(name, product.getVersion());
            if (a > 0) {
                System.out.println(Thread.currentThread().getName() + "下单成功");
            }
        }

    }

    //悲观锁
    protected void updateStock5(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = 1;
        //根据查询条件是否是唯一字段来确定是锁行还是锁表
        //锁行  where id=? （id）
        //锁表  除了where id=? 都是锁表 ,name=?

        //for update 作用是把id=1的商品加上行锁
        Product product = productService.getByName5(id);
        //提交（commit）以后会释放锁（行锁）
        int a = productService.updateStock5(id);
        if (a > 0) {
            System.out.println(Thread.currentThread().getName() + "下单成功");
            System.out.println("count:" + (++count));
        }

    }
}
