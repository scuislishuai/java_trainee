package com.lis.trainee.zook;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * ConfigUpdater
 *
 * @author Lis
 * @version 1.0
 * @date 2021-12-8 15:50
 */
public class ConfigUpdater {

  public static final String PATH = "/config";
  private ActiveKeyValueStore store;
  private Random random = new Random();

  public ConfigUpdater(String hosts) throws IOException, InterruptedException {
    store = new ActiveKeyValueStore();
    store.connect(hosts);
  }

  public void run() throws InterruptedException, KeeperException {
    while (true) {
      String value = random.nextInt(100) + "";
      store.write(PATH, value);
      System.out.printf("Set %s to %s\n", PATH, value);
      TimeUnit.SECONDS.sleep(random.nextInt(10));
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
    ConfigUpdater configUpdater = new ConfigUpdater("192.168.137.128");
    configUpdater.run();
  }
}
