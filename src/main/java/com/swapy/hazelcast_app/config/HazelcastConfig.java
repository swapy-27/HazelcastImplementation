package com.swapy.hazelcast_app.config;

import com.hazelcast.config.*;
import com.swapy.hazelcast_app.Entity.Student;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class HazelcastConfig {
    private static final String CLUSTER_NAME = "students-cluster";
    private static final String MAP_NAME = "students";
    private static final int MAX_SIZE = 200;
    private static final int TTL_SECONDS = 3600;
    private static final int FACTORY_ID = 1;

    @Bean(name = "hazelcastConfigBean")
    public Config hazelcastConfig() {
        Config config = new Config();

        // Set the cluster name
        config.setClusterName(CLUSTER_NAME);

        // Configure network settings (multicast for simple setup)
        NetworkConfig networkConfig = config.getNetworkConfig();
        JoinConfig joinConfig = networkConfig.getJoin();
        MulticastConfig multicastConfig = joinConfig.getMulticastConfig();
        multicastConfig.setEnabled(true);

        // Configure a map (cache) with eviction policy
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName(MAP_NAME);

        EvictionConfig evictionConfig = new EvictionConfig()
                .setSize(MAX_SIZE)
                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                .setEvictionPolicy(EvictionPolicy.LRU);

        mapConfig.setEvictionConfig(evictionConfig);
        mapConfig.setTimeToLiveSeconds(TTL_SECONDS); // 1 hour TTL

        config.addMapConfig(mapConfig);

        // Hazelcast IdentifiedDataSerializable
        config.getSerializationConfig().addDataSerializableFactory(
                FACTORY_ID, classId -> (classId == 1) ? new Student() : null
        );

        return config;
    }
}
