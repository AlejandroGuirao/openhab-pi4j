package com.pi4j.services.impl;

/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: OSGi Service
 * FILENAME      :  Activator.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  http://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2015 Pi4J
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pi4j.services.GpioService;
import com.pi4j.services.SystemInformationService;
import com.pi4j.services.NetworkInformationService;

import com.pi4j.wiringpi.GpioUtil;

public class Pi4JActivator implements BundleActivator {
    private static final Logger logger = LoggerFactory.getLogger(Pi4JActivator.class);

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        logger.info("Pi4J start");
        if(!System.getProperty("user.name").equals("root")) {
            logger.info("activating non privileged access to gpio.");
            GpioUtil.enableNonPrivilegedAccess();
        }
        bundleContext.registerService(GpioService.class.getName(), new GpioServiceImpl(), null);
        bundleContext.registerService(SystemInformationService.class.getName(), new SystemInformationServiceImpl(),null);
        bundleContext.registerService(NetworkInformationService.class.getName(), new NetworkInformationServiceImpl(),null);
        /*
         * try {
         * GpioUtil.enableNonPrivilegedAccess();
         * bundleContext.registerService(GpioService.class.getName(), new GpioServiceImpl(), null);
         * bundleContext.registerService(SystemInformationService.class.getName(), new SystemInformationServiceImpl(),
         * null);
         * bundleContext.registerService(NetworkInformationService.class.getName(), new NetworkInformationServiceImpl(),
         * null);
         * } catch(Throwable ex) {
         * logger.error(ex.toString());
         * logger.error(ex.getMessage());
         * for(StackTraceElement e : ex.getStackTrace()) {
         * if(e.isNativeMethod()) {
         * logger.info("native:" + e.getClassName() + ":" + e.getMethodName());
         * } else {
         * logger.info(e.getClassName() + ":" + e.getMethodName() + ":" + e.getLineNumber());
         * }
         * }
         * }
         */
        logger.info("Pi4J started");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        logger.info("Pi4J stop");
    }
}
