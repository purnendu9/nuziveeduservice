package com.nuziveedu.service.router;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.nuziveedu.service.resource.GetFormsList;
import com.nuziveedu.service.resource.NuziveeduFormResource;

public class WebSwitch extends Application
{
    @Override
    public Restlet createInboundRoot()
    {
        Router router = new Router(getContext());
        router.attach("/getformsforuser/{handle}", GetFormsList.class);
        router.attach("/form/{handle}", NuziveeduFormResource.class);
        return router;
    }
}	

