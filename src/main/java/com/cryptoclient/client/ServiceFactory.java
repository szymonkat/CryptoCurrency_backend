package com.cryptoclient.client;

import com.cryptoclient.client.coinlayer.CoinLayerClient;
import com.cryptoclient.client.coinlayer.CoinLayerConfig;
import com.cryptoclient.client.coinlayer.CoinLayerService;
import com.cryptoclient.client.nomics.NomicsClient;
import com.cryptoclient.client.nomics.NomicsConfig;
import com.cryptoclient.client.nomics.NomicsService;
import com.cryptoclient.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {

    public static final String COIN_LAYER = "coinlayer";
    public static final String NOMICS = "nomics";
    @Autowired
    private CoinLayerConfig coinLayerConfig;
    @Autowired
    private CoinLayerClient coinLayerClient;
    @Autowired
    private NomicsConfig nomicsConfig;
    @Autowired
    private NomicsClient nomicsClient;

    public ApiService createService(final String serviceName) throws NotFoundException {
        switch (serviceName) {
            case COIN_LAYER:
                return new CoinLayerService(coinLayerClient, coinLayerConfig);
            case NOMICS:
                return new NomicsService(nomicsClient, nomicsConfig);
            default:
                throw new NotFoundException("Please type 'coinlayer' or 'nomics'," +
                        " otherwise Your request cannot be " +
                        "processed");
        }
    }
}
