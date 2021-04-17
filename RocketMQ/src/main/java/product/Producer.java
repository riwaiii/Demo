package product;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

public class Producer {
    public static void main(String[] args) {
        //group名
        DefaultMQProducer producer = new DefaultMQProducer("p1");
        //设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
        producer.setNamesrvAddr("192.168.221.177:9876");
        try {
            //启动
            producer.start();
            for (int i = 0; i < 10; i++){
                Message msg = new Message("topic1",//topic
                        "tagA",//tag
                        ("produce msg" + i).getBytes(StandardCharsets.UTF_8));
                //调用producer的send()方法发送消息
                //这里调用的是同步的方式，所以会有返回结果
                SendResult result = producer.send(msg);
                System.out.println(result);
            }
            //发送完消息之后，调用shutdown()方法关闭producer
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
    }
}
