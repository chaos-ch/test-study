package com.ch.test;

import org.springframework.stereotype.Service;

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
