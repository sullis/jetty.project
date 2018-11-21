//
//  ========================================================================
//  Copyright (c) 1995-2018 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.websocket.jsr356.tests.client;

import org.eclipse.jetty.websocket.core.DummyCoreSession;
import org.eclipse.jetty.websocket.core.FrameHandler;
import org.eclipse.jetty.websocket.jsr356.JavaxWebSocketContainer;
import org.eclipse.jetty.websocket.jsr356.JavaxWebSocketFrameHandler;
import org.eclipse.jetty.websocket.jsr356.JavaxWebSocketSession;
import org.eclipse.jetty.websocket.jsr356.UpgradeRequest;
import org.eclipse.jetty.websocket.jsr356.UpgradeRequestAdapter;
import org.eclipse.jetty.websocket.jsr356.UpgradeResponse;
import org.eclipse.jetty.websocket.jsr356.UpgradeResponseAdapter;
import org.eclipse.jetty.websocket.jsr356.client.JavaxWebSocketClientContainer;
import org.eclipse.jetty.websocket.jsr356.tests.DummyEndpoint;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.websocket.EndpointConfig;

public abstract class AbstractClientSessionTest
{
    protected static JavaxWebSocketSession session;
    protected static JavaxWebSocketContainer container;

    @BeforeAll
    public static void initSession() throws Exception
    {
        container = new JavaxWebSocketClientContainer();
        container.start();
        Object websocketPojo = new DummyEndpoint();
        UpgradeRequest upgradeRequest = new UpgradeRequestAdapter();
        UpgradeResponse upgradeResponse = new UpgradeResponseAdapter();
        JavaxWebSocketFrameHandler frameHandler =
            container.newFrameHandler(websocketPojo, upgradeRequest, upgradeResponse, null);
        FrameHandler.CoreSession channel = new DummyCoreSession();
        String id = "dummy";
        EndpointConfig endpointConfig = null;
        session = new JavaxWebSocketSession(container,
            channel,
            frameHandler,
            null,
            id,
            endpointConfig);
        container.addManaged(session);
    }

    @AfterAll
    public static void stopContainer() throws Exception
    {
        container.stop();
    }
}