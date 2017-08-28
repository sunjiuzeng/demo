package com.demo.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ElasticSearchClient {

    private ElasticSearchClient() {
        init();
    }

    private static class ElasticSearchClientHolder {
        private static final ElasticSearchClient INSTANCE = new ElasticSearchClient();
    }

    public static final ElasticSearchClient getInstance() {
        return ElasticSearchClientHolder.INSTANCE;
    }


    private Client client;

    private void init() {
        String esHost = PropUtil.getValue("es.host");
        int esPort = PropUtil.getIntValue("es.port");
        String pingTimeout = PropUtil.getValue("es.client.ping_timeout");
        String clusterName = PropUtil.getValue("es.cluster.name");

        // 集群连接超时设置
        Settings settings = ImmutableSettings.settingsBuilder().put("client.transport.ping_timeout", pingTimeout)
                .put("cluster.name", clusterName).build();

        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(esHost, esPort));
    }

    public void destroy() {
        if (client != null) {
            client.close();
        }
    }

    public Client getClient() {
        return client;
    }

}
