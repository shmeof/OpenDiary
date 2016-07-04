package com.sjf.open.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.sjf.open.api.MetricsAggregations;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaosi on 16-6-29.
 */

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    private static String INDEX = "qunar-index";
    private static String TYPE = "employee";
    private static String TYPE2 = "employee";
    private static Client client;

    /**
     * 初始化
     *
     * @return
     */
    private static Client Init() {
        // 设置
        Settings settings = Settings.settingsBuilder().put("cluster.name", "qunar-cluster").build();
        Client client = null;
        try {
            client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {
            logger.error("buildClient---Host异常 {}", e);
        }
        return client;
    }

    public static void main(String[] args) {

        client = Init();

//        Aggregations.aggs(client,INDEX,TYPE);
        MetricsAggregations.minAggregation(client,INDEX,TYPE);

        client.close();
    }
}
