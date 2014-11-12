package com.qunar.test;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by he.chen on 14-10-23.
 */
//@OTAService("kaiyuan")
@Service("normPrice")
public class HomeInnsService implements OtaAdapterService {



    public String getPrice() {
        return "homeInns price==========================";
    }
}
