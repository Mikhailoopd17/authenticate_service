package com.example.authenticate_service.config;

import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.jetty.JettyWebServer;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JettyConfig {
    @Value("${jetty.requests.logging.file}")
    private String logFile;

    @Value("${jetty.requests.logging.enabled}")
    private Boolean logEnabled;

    @Value("${jetty.requests.graceful.shutdown.time}")
    private Integer jettyRequestsGracefulShutdownTime;

    @Value("${jetty.threads.max:500}")
    private Integer jettyThreadsMax;

    @Value("${jetty.threads.min:50}")
    private Integer jettyThreadsMin;


    @Bean
    public JettyServletWebServerFactory embeddedServletContainerFactory() {
        JettyServletWebServerFactory webServer = new JettyServletWebServerFactory() {
            @Override
            protected JettyWebServer getJettyWebServer(Server server) {

                if (logEnabled) {
                    NCSARequestLog requestLog = new NCSARequestLog(logFile);
                    requestLog.setAppend(true);
                    requestLog.setRetainDays(1);
                    requestLog.setExtended(true);
                    requestLog.setLogTimeZone(getTimeZone());
                    requestLog.setLogDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    RequestLogHandler requestLogHandler = new RequestLogHandler();
                    requestLogHandler.setRequestLog(requestLog);
                    requestLog.setAppend(true);

                    requestLogHandler.setHandler(server.getHandler());

                    HandlerList topLevelHandlers = new HandlerList();
                    topLevelHandlers.addHandler(requestLogHandler);
                    topLevelHandlers.addHandler(server.getHandler());

                    server.setHandler(topLevelHandlers);
                }
                StatisticsHandler handler = new StatisticsHandler();
                handler.setHandler(server.getHandler());
                server.setHandler(handler);
                server.setStopTimeout(jettyRequestsGracefulShutdownTime);
                server.setStopAtShutdown(true);

                return super.getJettyWebServer(server);
            }
        };
        webServer.setThreadPool(new QueuedThreadPool(jettyThreadsMax));
        return webServer;
    }

    private String getTimeZone() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("Z");
        return "GMT" + dateFormat.format(new Date());
    }
}
