package com.qunar.hotel.sa.product.price.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by he.chen on 14-10-27.
 */
public abstract class AbstractService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static final String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n";
}
